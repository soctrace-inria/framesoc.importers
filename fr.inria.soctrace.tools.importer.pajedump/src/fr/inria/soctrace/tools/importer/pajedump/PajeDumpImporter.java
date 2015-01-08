/*******************************************************************************
 * Copyright (c) 2012-2014 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.pajedump;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IProgressMonitor;
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
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpConstants;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpParser;

/**
 * Paje dump importer tool.
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpImporter extends FramesocTool {

	private final static Logger logger = LoggerFactory.getLogger(PajeDumpImporter.class);

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long operation and we don't
	 * want to freeze the UI.
	 */
	public class PJDumpImporterPluginJobBody implements IPluginToolJobBody {

		private String args[]; // TODO use the input with the new mechanism

		public PJDumpImporterPluginJobBody(IFramesocToolInput input) {
			List<String> files= ((TraceFileInput) input).getTraceFiles();
			this.args = files.toArray(new String[files.size()]);
		}

		@Override
		public void run(IProgressMonitor monitor) {
			DeltaManager delta = new DeltaManager();
			delta.start();

			ArgumentsManager argsm = new ArgumentsManager();
			argsm.parseArgs(args);
			argsm.printArgs();

			boolean doublePrecision = true;
			if (!argsm.getFlags().isEmpty() && argsm.getFlags().contains("l")) {
				doublePrecision = false;
				System.out.println("Long option selected");
			}

			int numberOfTraces = argsm.getTokens().size();
			int currentTrace = 1;
			Set<String> usedNames = new HashSet<>();
			DeltaManager traceDelta = new DeltaManager();
			for (String traceFile : argsm.getTokens()) {

				logger.debug("Importing " + traceFile);

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

					// parsing
					PJDumpParser parser = new PJDumpParser(sysDB, traceDB, traceFile,
							doublePrecision);
					parser.parseTrace(monitor, currentTrace, numberOfTraces);

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
		if (extension.equals(PJDumpConstants.TRACE_EXT)) {
			basename = basename.replace(PJDumpConstants.TRACE_EXT, "");
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

	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Paje Dump Importer",
				new PJDumpImporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {

		List<String> files= ((TraceFileInput) input).getTraceFiles();
		ArgumentsManager argsm = new ArgumentsManager();
		try {
			// do this in a try block, since the method is called also for
			// invalid input (it is called each time input changes)
			argsm.parseArgs(files.toArray(new String[files.size()]));
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
