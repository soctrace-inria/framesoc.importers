/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Damien Dosimont
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.paraver.core;

import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.model.utils.ModelConstants.TimeUnit;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.tools.importer.pajedump.core.PJDumpTraceMetadata;

/**
 * Paraver trace metadata
 * 
 * @author "Damien Dosimont <damien.dosimont@imag.fr>"
 */
public class ParaverTraceMetadata extends PJDumpTraceMetadata {
	
	@Override
	public String getTraceTypeName() {
		return ParaverConstants.TRACE_TYPE;
	}
		
	public ParaverTraceMetadata(SystemDBObject sysDB, String dbName, String alias, int events, long min, long max) throws SoCTraceException {
		super(sysDB, dbName, alias, events, min, max, 0);
	}
	
	@Override
	public void setTraceFields(Trace trace) {
		trace.setAlias(alias);
		trace.setDbName(dbName);
		trace.setDescription("paraver trace imported " + getCurrentDate());
		trace.setNumberOfCpus(1);
		trace.setNumberOfEvents(events);
		trace.setOutputDevice("paraver");
		trace.setProcessed(false);
		trace.setMinTimestamp(min);
		trace.setMaxTimestamp(max);
		trace.setTimeUnit(TimeUnit.NANOSECONDS.getInt());
		
		trace.setTracedApplication("unknown");
		trace.setBoard("unknown");
		trace.setOperatingSystem("unknown");
	}


}