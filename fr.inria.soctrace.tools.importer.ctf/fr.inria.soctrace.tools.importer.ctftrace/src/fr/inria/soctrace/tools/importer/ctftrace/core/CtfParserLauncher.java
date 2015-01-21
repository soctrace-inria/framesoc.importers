/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Youenn Corre - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.ctftrace.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.DeltaManager;

/**
 * Class used to launch the Ctf parser.
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 * 
 */
public class CtfParserLauncher {

	/**
	 * Wrapper application interface
	 * 
	 * @param args
	 *            CtfTrace Arguments
	 */
	public void launch(CtfParserArgs args, IProgressMonitor monitor) throws SoCTraceException {

		SystemDBObject sysDB = null;
		TraceDBObject traceDBSoft = null;
		TraceDBObject traceDBHard = null;
		try {
			DeltaManager delta = new DeltaManager();

			// open system DB
			sysDB = new SystemDBObject(args.sysDbName, DBMode.DB_OPEN);
			// create new trace DB
			delta.start();
			traceDBSoft = new TraceDBObject(args.traceDbNameSW, DBMode.DB_CREATE);
			traceDBHard = new TraceDBObject(args.traceDbNameHW, DBMode.DB_CREATE);
			delta.end("Trace DB creation");

			// parsing
			CtfParser parser = new CtfParser(sysDB, traceDBSoft, traceDBHard, args);
			delta.start();
			parser.parseTrace(monitor);
			delta.end("Parse trace");

			if (monitor.isCanceled()) {
				sysDB.close();
				return;
			}
		} catch (Exception e) {
			List<TraceDBObject> tdbs = new ArrayList<>();
			tdbs.add(traceDBHard);
			tdbs.add(traceDBSoft);
			PluginImporterJob.catchImporterException(e, sysDB, tdbs);
		} finally {
			// close the trace DB and the system DB (commit)
			DBObject.finalClose(traceDBSoft);
			DBObject.finalClose(traceDBHard);
			DBObject.finalClose(sysDB);
		}
	}

}
