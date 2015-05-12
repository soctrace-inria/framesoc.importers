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
package fr.inria.soctrace.tools.importer.kptcsv.core;

import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.TraceType;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.utils.SQLConstants.FramesocTable;
/**
 * KPTraceCSV Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptcsvTraceManager{

	private SystemDBObject sysDB;
	private TraceType tt;
	private boolean traceTypeExisting;
	
	private final String typename = "KPtrace CSV cat";
	
	public KptcsvTraceManager(SystemDBObject sysDB){
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
