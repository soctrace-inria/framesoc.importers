package fr.inria.soctrace.tools.importer.ctftrace.core;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
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
	public void launch(CtfParserArgs args, IProgressMonitor monitor)
			throws SoCTraceException {

		DeltaManager delta = new DeltaManager();

		// open system DB
		SystemDBObject sysDB = new SystemDBObject(args.sysDbName,
				DBMode.DB_OPEN);
		// create new trace DB
		delta.start();
		TraceDBObject traceDBSoft = new TraceDBObject(args.traceDbNameSW,
				DBMode.DB_CREATE);
		TraceDBObject traceDBHard = new TraceDBObject(args.traceDbNameHW,
				DBMode.DB_CREATE);
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

		// close the trace DB and the system DB (commit)
		traceDBSoft.close();
		traceDBHard.close();
		sysDB.close();
	}

}
