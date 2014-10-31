/*******************************************************************************
 * Copyright (c) 2012-2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Damien Dosimont, Generoso Pagano
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.paje;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.ArgumentsManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.importer.paje.core.PajeConstants;
import fr.inria.soctrace.tools.importer.paje.core.PajeTraceMetadata;
import fr.inria.soctrace.tools.importer.paje.reader.PajePrintWrapper;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpConstants;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpParser;

public class PajeImporter extends FramesocTool {

	private final static Logger logger = LoggerFactory
			.getLogger(PajeImporter.class);

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long
	 * operation and we don't want to freeze the UI.
	 */
	public class PajeImporterPluginJobBody implements IPluginToolJobBody {

		private String args[];

		class PajeParser extends PJDumpParser {
			String alias;

			public PajeParser(SystemDBObject sysDB, TraceDBObject traceDB,
					String traceFile, String alias) {
				super(sysDB, traceDB, traceFile, true);
				this.alias = alias;
			}

			@Override
			protected void saveTraceMetadata(boolean partialImport)
					throws SoCTraceException {

				PajeTraceMetadata metadata = new PajeTraceMetadata(sysDB,
						traceDB.getDBName(), alias, numberOfEvents,
						minTimestamp, maxTimestamp);
				metadata.createMetadata();
				metadata.saveMetadata();
			}
		}

		public PajeImporterPluginJobBody(String[] args) {
			this.args = args;
		}

		@Override
		public void run(IProgressMonitor monitor) {
			DeltaManager delta = new DeltaManager();
			delta.start();

			logger.debug("Args: ");
			for (String s : args) {
				logger.debug(s);
			}

			ArgumentsManager argsm = new ArgumentsManager();
			argsm.parseArgs(args);
			argsm.printArgs();

			Assert.isTrue(argsm.getTokens().size() >= 1);
			String traceFile = argsm.getTokens().get(argsm.getTokens().size()-1);
			ArrayList<String> arguments = new ArrayList<String>();
			for (int i = 0; i < argsm.getTokens().size()-2; i++) {
				arguments.add("-"+argsm.getTokens().get(i));
			}

			if (monitor.isCanceled())
				return;

			String traceDbName = getNewTraceDBName(traceFile);

			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;

			try {
				// open system DB
				sysDB = SystemDBObject.openNewIstance();
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

				// parsing
				String output = ResourcesPlugin.getWorkspace().getRoot()
						.getLocation().toString()
						+ File.separator + "tmp";
				arguments.add(traceFile);
				PajePrintWrapper printer = new PajePrintWrapper(arguments);
				String trueOutput = output + PJDumpConstants.TRACE_EXT;
				File outputFile = new File(trueOutput);
				IStatus status = printer.executeSync(monitor, outputFile);
				if (status.equals(IStatus.CANCEL) || monitor.isCanceled()) {
					throw new SoCTraceException();
				}

				// while(!outputFile.exists())
				// {
				// outputFile = new File(output);
				// if (monitor.isCanceled()){
				// throw new SoCTraceException();
				// }
				// }
				PajeParser parser = new PajeParser(sysDB, traceDB, trueOutput,
						FilenameUtils.getBaseName(traceFile));
				parser.parseTrace(monitor, 1, 1);
				outputFile.delete();

			} catch (SoCTraceException ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
				System.err
						.println("Import failure. Trying to rollback modifications in DB.");
				if (sysDB != null)
					try {
						sysDB.rollback();
					} catch (SoCTraceException e) {
						e.printStackTrace();
					}
				if (traceDB != null)
					try {
						traceDB.dropDatabase();
					} catch (SoCTraceException e) {
						e.printStackTrace();
					}
			} finally {
				// close the trace DB and the system DB (commit)
				DBObject.finalClose(traceDB);
				DBObject.finalClose(sysDB);
				delta.end("Import trace");
			}

		}

	}

	private String getNewTraceDBName(String traceFile) {
		String basename = FilenameUtils.getBaseName(traceFile);
		String extension = FilenameUtils.getExtension(traceFile);
		if (extension.equals(PajeConstants.TRACE_EXT)) {
			basename = basename.replace(PajeConstants.TRACE_EXT, "");
		}
		return FramesocManager.getInstance().getTraceDBName(basename);
	}

	@SuppressWarnings("unused")
	private boolean checkArgs(ArgumentsManager argsm) {
		if (argsm.getTokens().size() != 1)
			return false;
		return true;
	}

	@Override
	public void launch(String[] args) {
		PluginImporterJob job = new PluginImporterJob("Pajé Importer",
				new PajeImporterPluginJobBody(args));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public boolean canLaunch(String[] args) {
		if (args.length < 1){
			return false;
		}
			File f = new File(args[args.length-1]);
			if (!f.isFile()){
				return false;
			}
		return true;
	}

}
