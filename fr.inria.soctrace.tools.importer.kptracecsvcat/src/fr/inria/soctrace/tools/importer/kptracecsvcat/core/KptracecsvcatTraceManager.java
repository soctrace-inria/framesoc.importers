package fr.inria.soctrace.tools.importer.kptracecsvcat.core;

import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.TraceType;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.utils.SQLConstants.FramesocTable;

public class KptracecsvcatTraceManager{

	private SystemDBObject sysDB;
	private TraceType tt;
	private boolean traceTypeExisting;
	
	private final String typename = "KPtrace CSV cat";
	
	public KptracecsvcatTraceManager(SystemDBObject sysDB){
		this.sysDB = sysDB;
		try {
			this.traceTypeExisting = (sysDB.isTraceTypePresent(this.typename));
		} catch (SoCTraceException e) {
			e.printStackTrace();
		}
	}
	
	public void writeTrace(Integer numberOfEvents, String traceName) throws SoCTraceException{
		
		if(!traceTypeExisting){
			this.tt = new TraceType(sysDB.getNewId(FramesocTable.TRACE_TYPE.toString(), "ID"));
			this.tt.setName(this.typename);
			sysDB.save(tt);
		}else{
			this.tt = sysDB.getTraceType(typename);
		}
		
		Trace t = new Trace(sysDB.getNewId(FramesocTable.TRACE.toString(), "ID"));
		t.setType(tt);
		t.setNumberOfEvents(numberOfEvents);
		t.setDbName(traceName);
		//TODO set exposant
		t.setTimeUnit(-6);// exposant correspondant a la puissance de mico secondes
		sysDB.save(t);
	}
	
}
