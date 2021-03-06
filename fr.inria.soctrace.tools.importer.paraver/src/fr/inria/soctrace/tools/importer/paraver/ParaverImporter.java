/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Damien Dosimont - initial API and implementation
 *      Generoso Pagano - refactoring
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.paraver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.ArgumentsManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpConstants;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpParser;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpTraceSizeMetadata;
import fr.inria.soctrace.tools.importer.paraver.core.ParaverConstants;
import fr.inria.soctrace.tools.importer.paraver.core.ParaverTraceMetadata;
import fr.inria.soctrace.tools.importer.paraver.input.ParaverInput;
import fr.inria.soctrace.tools.importer.paraver.reader.ParaverPrintWrapper;

/**
 * Paraver importer tool.
 * 
 * @author "Damien Dosimont <damien.dosimont@imag.fr>"
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class ParaverImporter extends FramesocTool {

	private final static Logger logger = LoggerFactory.getLogger(ParaverImporter.class);

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long operation and we don't
	 * want to freeze the UI.
	 */
	public class ParaverImporterPluginJobBody implements IPluginToolJobBody {

		private ParaverInput input;
	
		class ParaverParser extends PJDumpParser {
			String alias;

			public ParaverParser(SystemDBObject sysDB, TraceDBObject traceDB, String traceFile,
					String alias) {
				super(sysDB, traceDB, traceFile, false, 0);
				this.alias = alias;
			}

			@Override
			protected void saveTraceMetadata(boolean partialImport) throws SoCTraceException {

				ParaverTraceMetadata metadata = new ParaverTraceMetadata(sysDB,
						traceDB.getDBName(), alias, numberOfEvents, minTimestamp, maxTimestamp);
				metadata.createMetadata();
				metadata.saveMetadata();
			}
		}

		public ParaverImporterPluginJobBody(IFramesocToolInput input) {
			this.input = (ParaverInput) input;
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException {
			DeltaManager delta = new DeltaManager();
			delta.start();

			Assert.isTrue(input.getFiles().size() == 1);
			String traceFile = input.getFiles().iterator().next();
			if (monitor.isCanceled())
				return;

			logger.debug("Importing trace {}", traceFile);
			String traceDbName = getNewTraceDBName(traceFile);

			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;

			try {
				// open system DB
				sysDB = SystemDBObject.openNewInstance();
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

				// parsing
				ArrayList<String> arguments = new ArrayList<String>();
				String inputComand = traceFile;
				String output = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString()
						+ File.separator + "tmp" + PJDumpConstants.TRACE_EXT;
				arguments.add(inputComand);
				if(input.isUseEventForState())
					arguments.add("-e");
				
				arguments.add("-o");
				arguments.add(output);
				ParaverPrintWrapper printer = new ParaverPrintWrapper(arguments);

				IStatus status = printer.executeSync(monitor);
				if (status.equals(Status.CANCEL_STATUS) || monitor.isCanceled()) {
					throw new SoCTraceException();
				}
				File outputFile = new File(output);

				ParaverParser parser = new ParaverParser(sysDB, traceDB, output,
						FilenameUtils.getBaseName(inputComand));
				parser.parseTrace(monitor, 1, 1);
				outputFile.delete();

			} catch (SoCTraceException e) {
				PluginImporterJob.catchImporterException(e, sysDB, traceDB);
			} finally {
				// close the trace DB and the system DB (commit)
				DBObject.finalClose(traceDB);
				DBObject.finalClose(sysDB);
				if (monitor.isCanceled())
					return;
				
				new PJDumpTraceSizeMetadata(new File(traceFile), traceDbName);
				delta.end("Import trace");
			}
		}
	}

	private String getNewTraceDBName(String traceFile) {
		String basename = FilenameUtils.getBaseName(traceFile);
		String extension = FilenameUtils.getExtension(traceFile);
		if (extension.equals(ParaverConstants.TRACE_EXT)) {
			basename = basename.replace(ParaverConstants.TRACE_EXT, "");
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
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Paraver Importer",
				new ParaverImporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {

		ParaverInput arg = (ParaverInput) input;

		if (arg.getFiles().size() != 1) {
			return new ParameterCheckStatus(false, "Specify a single " + ParaverConstants.TRACE_EXT
					+ " file.");
		}

		String fileName = arg.getFiles().iterator().next();

		File f = new File(fileName);
		if (!f.isFile()) {
			logger.debug("Not a file: {}", f.getName());
			return new ParameterCheckStatus(false, f.getName() + " is not a file.");
		}
		String basename = FilenameUtils.getBaseName(f.getAbsolutePath());
		if (!f.getName().equals(basename + ParaverConstants.TRACE_EXT)) {
			logger.debug("Wrong extension: {}", f.getName());
			return new ParameterCheckStatus(false, "The file does not end with "
					+ ParaverConstants.TRACE_EXT + ".");
		}

		File dir = f.getParentFile();
		File[] files = dir.listFiles();
		Set<String> fileSet = new HashSet<>();
		for (File tf : files) {
			fileSet.add(tf.getName());
		}
		if (!fileSet.contains(basename + ParaverConstants.PCF_EXT)) {
			logger.debug("{} not found", basename + ParaverConstants.PCF_EXT);
			return new ParameterCheckStatus(false, basename + ParaverConstants.PCF_EXT
					+ " not found.");
		}

		// check for .row too, if necessary
		return new ParameterCheckStatus(true, "");
	}
}
