package fr.inria.soctrace.tools.importer.kptracecsvcat.core;

import java.io.FileNotFoundException;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
/**
 * KPTraceCSV Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptracecsvcatManager{
	
	private SystemDBObject sysDB;
	private TraceDBObject traceDB;
	private String filename;	
	private Integer numberOfEvents;
	
	public KptracecsvcatManager(SystemDBObject sysDB, TraceDBObject traceDB, String filename){
		this.sysDB = sysDB;
		this.traceDB = traceDB;
		this.filename = filename;
		this.numberOfEvents = 0;
	}
	
	public void importTrace() throws SoCTraceException{
		
		// write file in traceDB
		KptracecsvcatFileManager fm = new KptracecsvcatFileManager(traceDB,filename,"description");
		fm.saveFile();
		
		
		// parse trace and save events in trace DB
		KptracecsvcatParserManager pm = new KptracecsvcatParserManager(traceDB,filename);
		try {
			numberOfEvents = pm.parseTrace();
		} catch (FileNotFoundException e) {
			throw new SoCTraceException();
		}
		
		
		// Write trace params in systemDB
		KptracecsvcatTraceManager tm = new KptracecsvcatTraceManager(sysDB);
		tm.writeTrace(numberOfEvents,traceDB.getDBName());
		
	}
	
}
