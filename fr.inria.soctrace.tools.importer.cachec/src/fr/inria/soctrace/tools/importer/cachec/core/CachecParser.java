/*******************************************************************************
 * Copyright (c) 2012-2015 Generoso Pagano.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 *     David Beniamine - Adaptation from PjDump to HeapInfo
 *     Youenn Corre - Adaptation from HeapInfo to cachec
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.cachec.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.lib.model.Event;
import fr.inria.soctrace.lib.model.EventParam;
import fr.inria.soctrace.lib.model.EventParamType;
import fr.inria.soctrace.lib.model.EventProducer;
import fr.inria.soctrace.lib.model.EventType;
import fr.inria.soctrace.lib.model.Variable;
import fr.inria.soctrace.lib.model.utils.ModelConstants.EventCategory;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.IdManager;

/**
 * cachec Parser core class.
 * 
 * Warning: the current implementation of this parser works under the hypothesis
 * that a producer may be in a single state at a given time.
 * 
 * @author "David Beniamine <David.Beniamine@imag.fr>"
 */
public class CachecParser {

	private class MemZone{
		private Long StartAddr=0L;
		private Long EndAddr=0L;
		private EventProducer ep;
		public MemZone(Long startAddr, Long endAddr, EventProducer ep){
			this.ep=ep;
			this.StartAddr = startAddr;
			this.EndAddr = endAddr;
		}

		public boolean InZone(Long Addr)
		{
			return Addr >= this.StartAddr && Addr < this.EndAddr;
		}
		public EventProducer getProducer() {
			return ep;
		}
	};

	private static final Logger logger = LoggerFactory
			.getLogger(CachecParser.class);

	private Long PAGE_SIZE=4096L;
	private Long CACHEL_SIZE=64L;
	private int PAGE_BITS = 12;
	private int CACHEL_BITS = 6;

	private SystemDBObject sysDB;
	private TraceDBObject traceDB =  null;
	private List<String> traceFiles;
	private Map<String, EventType> types = new HashMap<String, EventType>();

	private Long currEnd=0L;
	private int numberOfEvents = 0;
	private int page = 0;
	private IdManager eIdManager = new IdManager();
	private IdManager etIdManager = new IdManager();
	private IdManager epIdManager = new IdManager();
	private int nbProducer = 0;

	private Long toLineAddr(Long Addr)
	{
		return (Addr >> CACHEL_BITS) << CACHEL_BITS;
	}

	private Long toPageAddr(Long Addr)
	{
		return (Addr >> PAGE_BITS) << PAGE_BITS;
	}

	private void saveTraceMetadata()
			throws SoCTraceException {
		String alias = FilenameUtils.getBaseName(traceDB.getDBName());
		CachecTraceMetadata metadata = new CachecTraceMetadata(sysDB, traceDB.getDBName(), alias, numberOfEvents);
		metadata.createMetadata();
		metadata.saveMetadata();
		sysDB.commit();
	}

	private EventProducer root = null;

	private Map<Long, EventProducer> LastLevelProducers = new HashMap<Long,EventProducer>();
	private EventProducer PageProducer=null;
	private Long CurPage=0L;
	private List<EventProducer> tmpProducer = new ArrayList<EventProducer>();


	private List<MemZone> ZoneList=new ArrayList<MemZone>();

	private List<Event> eventList = new LinkedList<Event>();


	public CachecParser(SystemDBObject sysDB, TraceDBObject traceDB,
			List<String> traceFileName) {

		this.traceFiles = traceFileName;
		this.sysDB = sysDB;
		this.traceDB = traceDB;

		numberOfEvents = 0;
		page = 0;
	}

	/**
	 * 
	 * @param monitor
	 *            progress monitor
	 * @throws SoCTraceException
	 */
	public void parseTrace(IProgressMonitor monitor) throws SoCTraceException {
		monitor.beginTask("Parsing Trace", traceFiles.size());

		// Files containing event producers info
		List<String> producerFiles = new LinkedList<String>();
		// Files containing trace info
		List<String> eventFiles = new LinkedList<String>();
		List<String> eventTypeFiles = new LinkedList<String>();

		for (String aTraceFile : traceFiles) {
			// If trace info file type
			if(aTraceFile.endsWith(CachecConstants.TRACE_file))
				eventFiles.add(aTraceFile);
			else if (aTraceFile.endsWith(CachecConstants.EventType_file))
				eventTypeFiles.add(aTraceFile);
			else
				producerFiles.add(aTraceFile);
		}

		for (String aTraceFile : eventTypeFiles) {
			monitor.subTask(aTraceFile + ": Building Event Types...");
			parseEventTypes(monitor, aTraceFile);
			monitor.worked(1);
		}

		for (String aTraceFile : producerFiles) {
			monitor.subTask(aTraceFile + ": Building Event Producers...");
			parseEventProd(monitor, aTraceFile);
			monitor.worked(1);

			System.err.println(nbProducer + " event producers were saved");
			logger.debug(nbProducer + " event producers were saved");
		}

		if (monitor.isCanceled()) {
			traceDB.dropDatabase();

			sysDB.rollback();
			return;
		}

		for (String aTraceFile : eventFiles) {
			monitor.subTask(aTraceFile + ": Building Events...");
			logger.debug("Trace file: {}", aTraceFile);

			// Trace Events, EventTypes and Producers
			parseRawTrace(monitor, aTraceFile);
			monitor.worked(1);

			if (monitor.isCanceled()) {
				traceDB.dropDatabase();

				sysDB.rollback();
				return;
			}
		}

		monitor.subTask("Finalizing...");
		// Save remaining producers
		if (!tmpProducer.isEmpty()) {
			saveProducers(tmpProducer);
			tmpProducer.clear();
		}
		saveTypes();
		saveTraceMetadata();
	}

	private void cachecRawParser(String[] line) throws SoCTraceException
	{

		/*
		 * line is like:
		 *  {'Addr', 'FirstAcc', 'T0', ..., 'TN'}
		 */
		//Create ep for Addr
		EventProducer ep;
		EventType et=null;

		Long addr=toLineAddr(Long.valueOf(line[CachecConstants.Field_Addr]));
		//cacheline << 6
		// Get the cache line
		ep = LastLevelProducers.get(addr);
		// No event producer, should we construct one ?
		if (ep == null) 
		{
			// Is addr
			for(MemZone mz: ZoneList)
			{
				if(mz.InZone(addr))
				{
					//parent ep
					if(CurPage!=toPageAddr(addr))
					{
						Long pAddr=toPageAddr(addr);
						//Need to construct the page producer
						PageProducer = new EventProducer(epIdManager.getNextId());
						PageProducer.setName(pAddr.toString());
						PageProducer.setParentId(mz.getProducer().getId());
						PageProducer.setLocalId(String.valueOf(PageProducer.getId()));
						nbProducer++;
						tmpProducer.add(PageProducer);
						//No need to keep last level prods
						LastLevelProducers.clear();
					}


					//Lets construct the ep

					ep = new EventProducer(epIdManager.getNextId());
					ep.setName(addr.toString());
					ep.setParentId(PageProducer.getId());
					ep.setLocalId(String.valueOf(ep.getId()));
					tmpProducer.add(ep);
					nbProducer++;
					LastLevelProducers.put(addr, ep);

					// Check if we should save the producer
					if (nbProducer % CachecConstants.DB_PAGE_SIZE == 0) {
						saveProducers(tmpProducer);
						tmpProducer.clear();
						//TODO: if producers are sorted, we might be able to clean also
						// other poducers lists at one point
					}
					break;
				}
			}
		}

		// We haven't constructed any ep => let's get out
		if(ep==null)
			return;

		// Get the type
		et=types.get(line[CachecConstants.Field_EventType]);
		//System.out.println("Adding event of type "+line[CachecConstants.Field_EventType]+ " at addr "+Long.valueOf(line[CachecConstants.Field_Addr]) + "shifted " + addr);
		
		Event e = new Event(eIdManager.getNextId());
		e.setEventProducer(ep);
		e.setType(et);
		e.setPage(page);
		Long time=Long.parseLong(line[CachecConstants.Field_Time]);
		currEnd=currEnd>time?currEnd:time;
		e.setTimestamp(time);
		eventList.add(e);
	}


	private void parseRawTrace(IProgressMonitor monitor, String aTraceFile) throws SoCTraceException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new DataInputStream(new FileInputStream(aTraceFile))));
			String[] line;

			while ((line = getLine(br)) != null) {
				cachecRawParser(line);
				logger.debug(Arrays.toString(line));

				if (eventList.size() == CachecConstants.DB_PAGE_SIZE)
					page++;
				// XXX
				if (eventList.size() >= CachecConstants.DB_PAGE_SIZE) {
					saveEvents(eventList);
					numberOfEvents += eventList.size();
					eventList.clear();
				}

				if (monitor.isCanceled()) {
					return;
				}
			}

			if (eventList.size() > 0) {
				saveEvents(eventList);
				numberOfEvents += eventList.size();
				eventList.clear();
			}
			logger.debug("Saved {} events on {} pages", numberOfEvents,
					(page + 1));

		} catch (Exception e) {
			throw new SoCTraceException(e);
		}

	}

	/**
	 * Save the events of a page in the trace DB.
	 * 
	 * @param events
	 *            events list
	 * @throws SoCTraceException
	 */
	private void saveEvents(List<Event> events)
			throws SoCTraceException {
		for (Event e : events) {
			try {
				e.check();
			} catch (SoCTraceException ex) {
				logger.debug(ex.getMessage());
				throw new SoCTraceException(ex);
			}

			traceDB.save(e);
			for (EventParam ep : e.getEventParams()) {
				traceDB.save(ep);
			}
		}
		traceDB.commit(); // committing each page is faster
	}

	/**
	 * Get an event record from the given reader.
	 * 
	 * @param br
	 *            reader
	 * @return the record or null if the file is finished
	 * @throws IOException
	 */
	private String[] getLine(BufferedReader br) throws IOException {
		String strLine = null;
		String[] args = null;
		while (args == null) {
			if ((strLine = br.readLine()) == null)
				return null;

			strLine = strLine.trim();
			if (strLine.equals(""))
				continue;
			if (strLine.startsWith("#"))
				continue;

			args = strLine.split(CachecConstants.SEPARATOR);
		}
		return args;
	}

	private void saveProducers(List<EventProducer> theProducers) throws SoCTraceException {
		for (EventProducer ep : theProducers) {
			traceDB.save(ep);
		}

		traceDB.commit();
	}

	private void saveTypes() throws SoCTraceException {
		for (EventType et : types.values()) {
			traceDB.save(et);
			for (EventParamType ept : et.getEventParamTypes()) {
				traceDB.save(ept);
			}
		}
	}


	private void parseEventTypes(IProgressMonitor monitor, String aTraceFile) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(
					new FileInputStream(aTraceFile))));

			String[] line;
			while ((line = getLine(br)) != null) {
				// Create event types 
				EventType et = new EventType(etIdManager.getNextId(), EventCategory.PUNCTUAL_EVENT);
				et.setName(line[0]);
				types.put(et.getName(), et);
			};		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseEventProd(IProgressMonitor monitor, String aTraceFile) throws SoCTraceException {

		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(
					new FileInputStream(aTraceFile))));
			String[] line;
			nbProducer = 0;
			//Cache line sz
			line = getLine(br);
			CACHEL_SIZE=Long.valueOf(line[0]);
			CACHEL_BITS=(int)(Math.log10(CACHEL_SIZE)/Math.log10(2));
			//Page sz
			line = getLine(br);
			PAGE_SIZE=Long.valueOf(line[0]);
			PAGE_BITS=(int)(Math.log10(PAGE_SIZE)/Math.log10(2));

			List<EventProducer> tmpProducer = new ArrayList<EventProducer>();

			//root ep
			root = new EventProducer(epIdManager.getNextId());
			root.setName(CachecConstants.ROOT_NAME);
			root.setParentId(EventProducer.NO_PARENT_ID);
			root.setLocalId(String.valueOf(root.getId()));
			tmpProducer.add(root);
			nbProducer++;

			EventProducer ep;

			while ((line = getLine(br)) != null) {
				// Groups
				// Name
				// Start
				// Stop
				ep = new EventProducer(epIdManager.getNextId());
				ep.setName(line[0]);
				ep.setParentId(root.getId());
				ep.setLocalId(String.valueOf(ep.getId()));
				tmpProducer.add(ep);
				nbProducer++;

				line = getLine(br);
				Long Start = toPageAddr(Long.valueOf(line[0]));
				line = getLine(br);
				Long End = toPageAddr(Long.valueOf(line[0]));
				MemZone mz=new MemZone(Start,End,ep);
				ZoneList.add(mz);
			}

			// Save remaining producers
			if (!tmpProducer.isEmpty()) {
				saveProducers(tmpProducer);
				tmpProducer.clear();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
