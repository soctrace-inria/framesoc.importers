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
	public void launch(CtfParserArgs args, IProgressMonitor monitor) {

		DeltaManager delta = new DeltaManager();

		try {
			// open system DB
			SystemDBObject sysDB = new SystemDBObject(args.sysDbName,
					DBMode.DB_OPEN);
			// create new trace DB
			delta.start();
			TraceDBObject traceDB = new TraceDBObject(args.traceDbName,
					DBMode.DB_CREATE);
			delta.end("Trace DB creation");

			// parsing
			CtfParser parser = new CtfParser(sysDB, traceDB, args);
			delta.start();
			parser.parseTrace(monitor);
			delta.end("Parse trace");
			
			if (monitor.isCanceled()) {
				sysDB.close();
				return;
			}

			// close the trace DB and the system DB (commit)
			traceDB.close();
			sysDB.close();
		} catch (SoCTraceException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
