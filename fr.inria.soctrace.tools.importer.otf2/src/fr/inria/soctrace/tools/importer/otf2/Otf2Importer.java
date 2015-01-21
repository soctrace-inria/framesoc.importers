/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 *     Youenn Corre - OTF2 adaptation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.otf2;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.soctrace.framesoc.core.FramesocManager;
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
import fr.inria.soctrace.tools.importer.otf2.core.Otf2Constants;
import fr.inria.soctrace.tools.importer.otf2.core.Otf2Parser;
import fr.inria.soctrace.tools.importer.otf2.input.Otf2Input;

/**
 * Otf2 importer tool.
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class Otf2Importer extends FramesocTool {

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long operation and we don't
	 * want to freeze the UI.
	 */
	public class Otf2ImporterPluginJobBody implements IPluginToolJobBody {

		private Otf2Input input;

		public Otf2ImporterPluginJobBody(IFramesocToolInput input) {
			this.input = (Otf2Input) input;
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException {
			DeltaManager delta = new DeltaManager();
			delta.start();

			if (monitor.isCanceled())
				return;

			String traceDbName = getNewTraceDBName(input.getTraceFile());

			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;

			try {
				// open system DB
				sysDB = SystemDBObject.openNewIstance();
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

				// parsing
				Otf2Parser parser = new Otf2Parser(sysDB, traceDB, input);
				parser.parseTrace(monitor);

			} catch (SoCTraceException e) {
				PluginImporterJob.catchImporterException(e, sysDB, traceDB);
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
		if (extension.equals(Otf2Constants.TRACE_EXT)) {
			basename = basename.replace(Otf2Constants.TRACE_EXT, "");
		}
		return FramesocManager.getInstance().getTraceDBName(basename);
	}

	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Otf2 Importer",
				new Otf2ImporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {

		Otf2Input args = (Otf2Input) input;

		// check if a file is set
		String file = args.getTraceFile();
		if (file.trim().equals("")) {
			return new ParameterCheckStatus(false, "Specify a trace file.");
		}

		// check if the file exists
		File f = new File(file);
		if (!f.exists() || !f.isFile()) {
			return new ParameterCheckStatus(false, f.getName() + " does not exist.");
		}

		return new ParameterCheckStatus(true, "");
	}

}
