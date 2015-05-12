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
public class KptcsvManager {

	private SystemDBObject sysDB;
	private TraceDBObject traceDB;
	private String filename;
	private Integer numberOfEvents;

	public KptcsvManager(SystemDBObject sysDB, TraceDBObject traceDB,
			String filename) {
		this.sysDB = sysDB;
		this.traceDB = traceDB;
		this.filename = filename;
		this.numberOfEvents = 0;
	}

	public void importTrace() throws SoCTraceException {

		// write file in traceDB
		KptcsvFileManager fm = new KptcsvFileManager(traceDB, filename,
				"description");
		fm.saveFile();

		// parse trace and save events in trace DB
		KptcsvParserManager pm = new KptcsvParserManager(traceDB, filename);
		try {
			numberOfEvents = pm.parseTrace();
		} catch (FileNotFoundException e) {
			throw new SoCTraceException();
		}

		// Write trace params in systemDB
		KptcsvTraceManager tm = new KptcsvTraceManager(sysDB);
		tm.writeTrace(numberOfEvents, traceDB.getDBName());

	}

}
