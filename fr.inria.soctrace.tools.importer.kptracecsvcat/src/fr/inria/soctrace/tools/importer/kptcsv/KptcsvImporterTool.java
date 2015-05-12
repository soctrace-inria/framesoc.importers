/*******************************************************************************
 * Copyright (c) 2015 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Alexis Martin - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.kptcsv;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FileInput;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.importer.kptcsv.core.KptcsvManager;
import fr.inria.soctrace.tools.importer.kptcsv.core.KptcsvTraceSizeMetadata;

/**
 * Kpt csv Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptcsvImporterTool extends FramesocTool {

	private final static String DB_BASE_NAME = "KPTCSV_";

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long
	 * operation and we don't want to freeze the UI.
	 */
	private class KptracecsvcatImporterPluginJobBody implements
			IPluginToolJobBody {

		private String filename;

		public KptracecsvcatImporterPluginJobBody(FileInput file) {
			this.filename = file.getFiles().get(0);
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException {

			System.out.println(filename);

			DeltaManager delta = new DeltaManager();

			String sysDbName = Configuration.getInstance().get(
					SoCTraceProperty.soctrace_db_name);
			String traceDbName = FramesocManager.getInstance().getTraceDBName(
					DB_BASE_NAME);

			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;

			try {

				// open system DB
				sysDB = new SystemDBObject(sysDbName, DBMode.DB_OPEN);
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

				// parsing
				KptcsvManager manager = new KptcsvManager(sysDB, traceDB,
						filename);
				delta.start();
				manager.importTrace();
				delta.end("Import trace");

				// close the trace DB and the system DB (commit)
				traceDB.close();
				sysDB.close();
				new KptcsvTraceSizeMetadata(new File(filename), traceDbName);

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
			}

		}

	}

	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Kptracecsvcat Importer",
				new KptracecsvcatImporterPluginJobBody((FileInput) input));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {

		if (input instanceof FileInput) {
			FileInput args = (FileInput) input;
			if (args.getFiles().size() == 0) {
				return new ParameterCheckStatus(false, "Specify a trace file.");
			}
			File f = new File(args.getFiles().get(0));
			if (!f.isFile()) {
				return new ParameterCheckStatus(false, "The file "
						+ f.getName()
						+ " does not exist or it is not a valid file.");
			}
			return new ParameterCheckStatus(true, "");
		}

		return new ParameterCheckStatus(false, "Wrong input type.");
	}

}
