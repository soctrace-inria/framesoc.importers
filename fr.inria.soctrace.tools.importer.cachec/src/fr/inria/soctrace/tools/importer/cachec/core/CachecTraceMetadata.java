/*******************************************************************************
 * Copyright (c) 2012-2014 Generoso Pagano.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.cachec.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import fr.inria.soctrace.framesoc.core.tools.importers.AbstractTraceMetadataManager;
import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.model.utils.ModelConstants.TimeUnit;
import fr.inria.soctrace.lib.storage.SystemDBObject;

/**
 * Class to manage Trace metadata.
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class CachecTraceMetadata extends AbstractTraceMetadataManager {
	
	private String dbName;
	private String alias;
	private int events;
	
	@Override
	public String getTraceTypeName() {
		return CachecConstants.TRACE_NAME;
	}
		
	public CachecTraceMetadata(SystemDBObject sysDB, String dbName, String alias, int events) throws SoCTraceException {
		super(sysDB);
		this.dbName = dbName;
		this.alias = alias;
		this.events = events;
	}
	
	@Override
	public void setTraceFields(Trace trace) {
		trace.setAlias(alias);
		trace.setDbName(dbName);
		trace.setDescription("cachec trace imported " + getCurrentDate());
		trace.setNumberOfCpus(1);
		trace.setNumberOfEvents(events);
		trace.setOutputDevice("cachec");
		trace.setProcessed(false);
		trace.setTimeUnit(TimeUnit.NANOSECONDS.getInt());
		
		trace.setTracedApplication("unknown");
		trace.setBoard("unknown");
		trace.setOperatingSystem("unknown");
	}

	/**
	 * Get the current date.
	 * @return a string with the current date 
	 */
	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
		sdf.applyPattern("dd MMM yyyy HH:mm:ss z");
		return sdf.format(new Date()).toString();
	}

}
