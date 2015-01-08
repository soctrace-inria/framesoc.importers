/*******************************************************************************
 * Copyright (c) 2012-2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Damien Dosimont - initial API and implementation
 *      Generoso Pagano - refactoring
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.paje;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.ResourcesPlugin;
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
import fr.inria.soctrace.framesoc.core.tools.model.TraceFileInput;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.importer.paje.core.PajeConstants;
import fr.inria.soctrace.tools.importer.paje.core.PajeTraceMetadata;
import fr.inria.soctrace.tools.importer.paje.reader.PajeDumpWrapper;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpConstants;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpParser;

/**
 * Paje importer tool
 * 
 * @author "Damien Dosimont <damien.dosimont@imag.fr>"
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeImporter extends FramesocTool {

	private final static Logger logger = LoggerFactory.getLogger(PajeImporter.class);

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long operation and we don't
	 * want to freeze the UI.
	 */
	public class PajeImporterPluginJobBody implements IPluginToolJobBody {

		private String args[];

		class PajeParser extends PJDumpParser {
			private String alias;

			public PajeParser(SystemDBObject sysDB, TraceDBObject traceDB, String traceFile,
					String alias, boolean doublePrecision) {
				super(sysDB, traceDB, traceFile, doublePrecision);
				this.alias = alias;
			}

			@Override
			protected void saveTraceMetadata(boolean partialImport) throws SoCTraceException {
				PajeTraceMetadata metadata = new PajeTraceMetadata(sysDB, traceDB.getDBName(),
						alias, numberOfEvents, minTimestamp, maxTimestamp);
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

			ArgumentsManager argsm = new ArgumentsManager();
			argsm.parseArgs(args);
			argsm.printArgs();

			// prepare arguments for pj_dump tool
			ArrayList<String> arguments = new ArrayList<String>();
			for (String flag : argsm.getFlags()) {
				arguments.add("-" + flag);
			}
			boolean doublePrecision = true;
			if (arguments.contains("-l")) {
				System.out.println("Long option selected");
				doublePrecision = false;
				// remove -l because is not managed by pj_dump:
				// we manage it internally in the parser
				arguments.remove("-l");
			}

			// import traces
			int numberOfTraces = argsm.getTokens().size();
			int currentTrace = 1;
			Set<String> usedNames = new HashSet<>();
			DeltaManager traceDelta = new DeltaManager();
			for (String traceFile : argsm.getTokens()) {

				logger.debug("Importing trace: {}", traceFile);

				if (monitor.isCanceled())
					break;

				traceDelta.start();

				String traceDbName = getNewTraceDBName(usedNames, traceFile);

				SystemDBObject sysDB = null;
				TraceDBObject traceDB = null;

				try {
					// open system DB
					sysDB = SystemDBObject.openNewIstance();
					// create new trace DB
					traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

					// dumping
					monitor.beginTask("Dumping trace " + traceFile, IProgressMonitor.UNKNOWN);
					arguments.add(traceFile);
					String output = ResourcesPlugin.getWorkspace().getRoot().getLocation()
							.toString()
							+ File.separator + "tmp";
					String trueOutput = output + PJDumpConstants.TRACE_EXT;
					File outputFile = new File(trueOutput);
					PajeDumpWrapper printer = new PajeDumpWrapper(arguments);
					IStatus status = printer.executeSync(monitor, outputFile);
					System.out.println(status);
					if (status.equals(Status.CANCEL_STATUS) || monitor.isCanceled()) {
						throw new SoCTraceException();
					}
					// parsing dumped file
					PajeParser parser = new PajeParser(sysDB, traceDB, trueOutput,
							FilenameUtils.getBaseName(traceFile), doublePrecision);
					parser.parseTrace(monitor, currentTrace, numberOfTraces);
					// remove tmp file
					outputFile.delete();

				} catch (SoCTraceException ex) {
					System.err.println(ex.getMessage());
					ex.printStackTrace();
					System.err.println("Import failure. Trying to rollback modifications in DB.");
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
					traceDelta.end("Import trace");
					currentTrace++;
				}
			}
			delta.end("All traces imported");

		}

	}

	private String getNewTraceDBName(Set<String> usedNames, String traceFile) {
		String basename = FilenameUtils.getBaseName(traceFile);
		String extension = FilenameUtils.getExtension(traceFile);
		if (extension.equals(PajeConstants.TRACE_EXT)) {
			basename = basename.replace(PajeConstants.TRACE_EXT, "");
		}
		final String traceDbName = FramesocManager.getInstance().getTraceDBName(basename);
		String realName = traceDbName;
		int n = 0;
		while (usedNames.contains(realName)) {
			System.out.println("tested " + realName);
			realName = traceDbName + "_" + n++;
		}
		usedNames.add(realName);
		return realName;
	}

	/**
	 * TODO new mechanism for input + manage params for paje
	 */
	
	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Pajé Importer",
				new PajeImporterPluginJobBody(TraceFileInput.toArray(input)));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {

		ArgumentsManager argsm = new ArgumentsManager();
		try {
			// do this in a try block, since the method is called also for
			// invalid input (it is called each time input changes)
			argsm.parseArgs(TraceFileInput.toArray(input));
		} catch (IllegalArgumentException e) {
			return new ParameterCheckStatus(false, "Illegal arguments.");
		}

		// check if at least one trace file is specified
		if (argsm.getTokens().size() < 1) {
			return new ParameterCheckStatus(false, "Specify at least one trace file.");
		}

		// check trace files
		for (String file : argsm.getTokens()) {
			File f = new File(file);
			if (!f.isFile()) {
				return new ParameterCheckStatus(false, f.getName() + " does not exist.");
			}
		}

		return new ParameterCheckStatus(true, "");
	}

}
