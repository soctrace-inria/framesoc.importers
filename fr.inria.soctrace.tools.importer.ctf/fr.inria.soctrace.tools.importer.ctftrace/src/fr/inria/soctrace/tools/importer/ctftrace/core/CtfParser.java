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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

import org.eclipse.core.runtime.IProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.linuxtools.tmf.core.event.TmfEvent;
import fr.inria.linuxtools.tmf.core.exceptions.TmfTraceException;
import fr.inria.linuxtools.ctf.core.event.IEventDeclaration;
import fr.inria.soctrace.framesoc.ui.colors.FramesocColorManager;
import fr.inria.soctrace.lib.model.Event;
import fr.inria.soctrace.lib.model.EventParam;
import fr.inria.soctrace.lib.model.EventParamType;
import fr.inria.soctrace.lib.model.EventProducer;
import fr.inria.soctrace.lib.model.EventType;
import fr.inria.soctrace.lib.model.Link;
import fr.inria.soctrace.lib.model.State;
import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.TraceType;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.model.utils.ModelConstants.EventCategory;
import fr.inria.soctrace.lib.model.utils.ModelConstants.TimeUnit;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.storage.utils.SQLConstants.FramesocTable;
import fr.inria.soctrace.lib.utils.IdManager;

/**
 * @author youenn
 * 
 */

public class CtfParser {

	private static final Logger logger = LoggerFactory.getLogger(CtfParser.class);

	private final int MAX_EVENT_PER_PAGE = 25000;
	private SystemDBObject sysDB;
	private TraceDBObject traceDBSoft;
	private TraceDBObject traceDBHard;
	private String[] tracePath;
	private int numberOfEventsSW = 0;
	private int numberOfEventsHW = 0;
	private int numberOfCPUs = 0;
	private Map<String, EventType> typesSW = new HashMap<String, EventType>();
	private Map<Integer, EventProducer> producersMapSW = new HashMap<Integer, EventProducer>();
	private Map<String, EventType> typesHW = new HashMap<String, EventType>();
	private Map<Integer, EventProducer> producersMapHW = new HashMap<Integer, EventProducer>();
	private long minTimestamp;
	private long maxTimestamp;
	private SoCTraceException socTraceException = null;

	private String traceDBNameSW;
	private String traceDBNameHW;
	private TraceType traceType;

	private HashMap<Integer, State> processState = new HashMap<Integer, State>();
	private HashMap<Integer, State> CPUState = new HashMap<Integer, State>();
	private HashMap<Integer, State> irqState = new HashMap<Integer, State>();
	private HashMap<Integer, State> softIrqState = new HashMap<Integer, State>();
	private HashMap<Integer, EventProducer> irqList = new HashMap<Integer, EventProducer>();
	private HashMap<Integer, EventProducer> softIrqList = new HashMap<Integer, EventProducer>();
	private List<String> stateEvent = new ArrayList<String>();

	/*
	 * Set correspondence between the CTF parser and the soctrace
	 * representations of the type
	 */
	private HashMap<String, String> typeCorrespondence = new HashMap<String, String>();

	private int pageSW;
	private int pageHW;
	private int eventsPerPageSW;
	private int eventsPerPageHW;
	private List<Event> eventsHW;
	private List<Event> eventsSW;

	// ID Managers
	private IdManager eventIdManager;
	private IdManager eventParamIdManager;
	private IdManager eventIdTypeManager;
	private IdManager eventParamTypeIdManager;
	private IdManager eventProducerIdManager;

	public CtfParser(SystemDBObject aSysDB, TraceDBObject aTraceDBSW,
			TraceDBObject aTraceDBHW, CtfParserArgs args)
			throws SoCTraceException {
		sysDB = aSysDB;
		traceDBSoft = aTraceDBSW;
		traceDBHard = aTraceDBHW;
		tracePath = args.traceFiles;
		traceDBNameSW = args.traceDbNameSW;
		traceDBNameHW = args.traceDbNameHW;
		
		typeCorrespondence.put("IntegerDeclaration", "INTEGER");
		typeCorrespondence.put("StringDeclaration", "STRING");
		typeCorrespondence.put("FloatDeclaration", "FLOAT");

		stateEvent.add(CtfParserConstants.PROCESS_STATUS_UNKNOWN);
		stateEvent.add(CtfParserConstants.PROCESS_STATUS_WAIT_BLOCKED);
		stateEvent.add(CtfParserConstants.PROCESS_STATUS_RUN_USERMODE);
		stateEvent.add(CtfParserConstants.PROCESS_STATUS_RUN_SYSCALL);
		stateEvent.add(CtfParserConstants.PROCESS_STATUS_INTERRUPTED);
		stateEvent.add(CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU);
		stateEvent.add(CtfParserConstants.CPU_STATUS_IDLE);
		stateEvent.add(CtfParserConstants.CPU_STATUS_RUN_USERMODE);
		stateEvent.add(CtfParserConstants.CPU_STATUS_RUN_SYSCALL);
		stateEvent.add(CtfParserConstants.CPU_STATUS_IRQ);
		stateEvent.add(CtfParserConstants.CPU_STATUS_SOFTIRQ);
		stateEvent.add(CtfParserConstants.SOFT_IRQ_STATUS_RAISED);
		stateEvent.add(CtfParserConstants.SOFT_IRQ_STATUS_ACTIVE);
		stateEvent.add(CtfParserConstants.SOFT_IRQ_STATUS_EXIT);
		stateEvent.add(CtfParserConstants.IRQ_STATUS_ACTIVE);
		stateEvent.add(CtfParserConstants.IRQ_STATUS_EXIT);
	}

	public void parseTrace(IProgressMonitor monitor) throws SoCTraceException {
		try {
			pageSW = pageHW = 0;
			numberOfEventsSW = numberOfEventsHW = 0;
			numberOfCPUs = 0;
			eventsPerPageSW = eventsPerPageHW = 0;
			eventsHW = new LinkedList<Event>();
			eventsSW = new LinkedList<Event>();
			maxTimestamp = 0L;

			// Init ID managers
			eventProducerIdManager = new IdManager();
			eventIdManager = new IdManager();
			eventParamIdManager = new IdManager();
			eventIdTypeManager = new IdManager();
			eventParamTypeIdManager = new IdManager();

			createSpecialProducers();

			CtfTraceSub aT = new CtfTraceSub(this);
			aT.setDirectory(tracePath[0]);
			aT.initTrace(null, tracePath[0], TmfEvent.class);
			monitor.subTask("Building states and events");
			aT.buildSystem(monitor);
			if (monitor.isCanceled()) {
				aT.close();
				traceDBSoft.dropDatabase();
				traceDBHard.dropDatabase();
				sysDB.rollback();
				return;
			}
			checkUnfinishedStates();
			
			monitor.subTask("Building links");
			aT.buildLink(monitor);
			
			if (monitor.isCanceled()) {
				aT.close();
				traceDBSoft.dropDatabase();
				traceDBHard.dropDatabase();
				sysDB.rollback();
				return;
			}

			// Save the remaining events even though the page is not full
			saveEvents(eventsSW, traceDBSoft);
			saveEvents(eventsHW, traceDBHard);
			
			// Debug
			for (EventProducer aEP : producersMapSW.values()) {
				if (aEP.getName().equals("_StubEventProducer"))
					logger.debug("Unitialized pid " + aEP.getLocalId());
			}

			logger.info("Number of events " + numberOfEventsSW + "/" + numberOfEventsHW
					+ ", number of producers " + producersMapSW.keySet().size() + producersMapHW.keySet().size()
					+ ", number of EventTypes " + typesSW.keySet().size() + typesHW.keySet().size());
			logger.info("Saved " + numberOfEventsSW + "/" + numberOfEventsHW + " events in " + pageSW + pageHW
					+ " pages.");

			saveProducers();
			saveTypes();

			buildTraces();
			// Add the color scheme to Framesoc
			FramesocColorManager.getInstance().addEventTypeColors(CtfColors.ET_COLORS);
			
			aT.close();

		} catch (TmfTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Save the events of a page in the trace DB.
	 * 
	 * @param events
	 *            events list
	 * @throws SoCTraceException
	 */
	private void saveEvents(List<Event> events, TraceDBObject aTraceDB) throws SoCTraceException {
		for (Event e : events) {
			aTraceDB.save(e);
			for (EventParam ep : e.getEventParams()) {
				aTraceDB.save(ep);
			}
		}
		aTraceDB.commit(); // committing each page is faster
	}

	/**
	 * Save the event producers
	 * 
	 * @throws SoCTraceException
	 */
	private void saveProducers() throws SoCTraceException {
		// Soft
		for (EventProducer e : producersMapSW.values()) {
			traceDBSoft.save(e);
		}
		traceDBSoft.commit();
		
		// Hard
		for (EventProducer e : producersMapHW.values()) {
			traceDBHard.save(e);
		}
		traceDBHard.commit();
	}

	/**
	 * Save the event types
	 * 
	 * @throws SoCTraceException
	 */
	private void saveTypes() throws SoCTraceException {
		// Soft
		for (EventType et : typesSW.values()) {
			traceDBSoft.save(et);
			for (EventParamType ept : et.getEventParamTypes()) {
				traceDBSoft.save(ept);
			}
		}
		
		// Hard
		for (EventType et : typesHW.values()) {
			traceDBHard.save(et);
			for (EventParamType ept : et.getEventParamTypes()) {
				traceDBHard.save(ept);
			}
		}
	}

	/**
	 * Set the event with the info of the corresponding passed record. If the
	 * event PID correspond to a process which is not already in eventProducers,
	 * a new event producer stub is created.
	 * 
	 * @param id
	 *            event id
	 * @param page
	 *            event page
	 * @param record
	 *            event record
	 * @return the created event
	 * @throws SoCTraceException
	 */
	private Event setEvent(int id, boolean soft, CtfRecord record)
			throws SoCTraceException {

		Event e = new Event(id);
		e.setCpu(record.cpu);
		if (soft)
			e.setPage(pageSW);
		else
			e.setPage(pageHW);
		e.setTimestamp(record.getTimestamp());
		
		if(e.getTimestamp() > maxTimestamp)
			maxTimestamp = e.getTimestamp();

		EventProducer aProducer;
		
		if (soft)
			aProducer = producersMapSW.get(record.pid);
		else
			aProducer = producersMapHW.get(record.pid);
			
		// Check if we have already encountered the producer
		if (aProducer == null) {
			// System.out.println("Error finding producer with pid: " +
			// record.pid );

			// If not then create a stub
			createProducerStub(record.pid, soft);
		}
		if (soft)
			e.setEventProducer(producersMapSW.get(record.pid));
		else
			e.setEventProducer(producersMapHW.get(record.pid));

		return e;
	}

	/**
	 * Return the requested type or create it otherwise
	 * 
	 * @param aRecord
	 *            CtfRecord holding the type info
	 * @return the requested EventType
	 */
	private EventType getType(CtfRecord aRecord, boolean soft) {
		if (soft && !typesSW.containsKey(aRecord.type) || !soft && !typesHW.containsKey(aRecord.type)) {
			// We should never get there
			logger.debug("Adding type: " + aRecord.type);

			EventType et;
			if (isStateEvent(aRecord.type)) {
				et = new EventType(eventIdTypeManager.getNextId(),
						EventCategory.STATE);
			} else {
				et = new EventType(eventIdTypeManager.getNextId(),
						EventCategory.PUNCTUAL_EVENT);
			}
			et.setName(aRecord.type);

			// For every key
			for (String attributeName : aRecord.attributesValue.keySet()) {
				EventParamType eventParamType = new EventParamType(
						eventParamTypeIdManager.getNextId());
				eventParamType.setEventType(et);
				eventParamType.setName(attributeName);
				eventParamType.setType("STRING");
			}

			if (soft)
				typesSW.put(aRecord.type, et);
			else
				typesHW.put(aRecord.type, et);

		}
		if (soft)
			return typesSW.get(aRecord.type);
		else
			return typesHW.get(aRecord.type);
			
	}

	/**
	 * Set the event parameters
	 * 
	 * @param anEvent
	 *            The event for which the parameters are set
	 * @param aRecord
	 *            The CtfRecord holding the parameter values
	 * @throws SoCTraceException
	 */
	private void setParameters(Event anEvent, CtfRecord aRecord)
			throws SoCTraceException {

		Iterator<EventParamType> it = anEvent.getType().getEventParamTypes()
				.iterator();
		for (String anArgument : aRecord.attributesValue.keySet()) {
			EventParam ep = new EventParam(eventParamIdManager.getNextId());
			ep.setEvent(anEvent);
			ep.setValue(aRecord.attributesValue.get(anArgument));

			if (!it.hasNext())
				throw new SoCTraceException("Missing param type in structure: "
						+ aRecord.type);

			EventParamType ept = it.next();
			ep.setEventParamType(ept);
		}
	}

	/**
	 * Get the current date.
	 * 
	 * @return a string with the current date
	 */
	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
		sdf.applyPattern("dd MMM yyyy HH:mm:ss z");
		return sdf.format(new Date()).toString();
	}

	/**
	 * If the trace type is already saved in the DB, the TraceType object is
	 * loaded from the DB. Otherwise it is created.
	 * 
	 * @throws SoCTraceException
	 */
	private void buildTraceType() throws SoCTraceException {

		// If the trace type exist already
		if ((sysDB.isTraceTypePresent(CtfParserConstants.TRACE_TYPE))) {
			logger.debug("Tracetype exist");
			traceType = sysDB.getTraceType(CtfParserConstants.TRACE_TYPE);
		} else {
			logger.debug("Tracetype does not exist");
			traceType = new TraceType(sysDB.getNewId(
					FramesocTable.TRACE_TYPE.toString(), "ID"));
			traceType.setName(CtfParserConstants.TRACE_TYPE);
			sysDB.save(traceType);
		}
	}

	/**
	 * Create a new event
	 * 
	 * @param aRecord
	 *            A CtfRecord holding the event info
	 */
	public void newEvent(CtfRecord aRecord, boolean soft)
			throws SoCTraceException {
		Event e;

		e = setEvent(eventIdManager.getNextId(), soft, aRecord);

		EventType et = getType(aRecord, soft);
		et.setName(aRecord.type);
		e.setType(et);

		setParameters(e, aRecord);
		saveEvent(e, soft);
	}

	/**
	 * Check if a given event producer already exists
	 * 
	 * @param aPid
	 *            pid of the producer
	 * @return true if the producer already exist and is not a stub
	 */
	public boolean producerExist(int aPid, boolean soft) {
		if (soft) {
			if (producersMapSW.containsKey(aPid)) {
				if (producersMapSW.get(aPid).getName() != "_StubEventProducer") {
					return true;
				}
			}
		} else {
			if (producersMapHW.containsKey(aPid)) {
				if (producersMapHW.get(aPid).getName() != "_StubEventProducer") {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Create a producer
	 * 
	 * @param pid
	 *            Producer pid
	 * @param ppid
	 *            Producer parent pid
	 * @param name
	 *            producer name
	 */
	public void addProducer(int pid, int ppid, String name) {
		if(producersMapSW.get(ppid) == null || ppid < 0)
			ppid = EventProducer.NO_PARENT_ID;

		EventProducer p;
		if (!producersMapSW.containsKey(pid)) {
			p = new EventProducer(eventProducerIdManager.getNextId());
			String stringPID = String.valueOf(pid);
			p.setLocalId(stringPID);
		} else {
			p = producersMapSW.get(pid);
		}
		p.setName(name);
		p.setType(name);
		p.setParentId(ppid);
		if (ppid >= 0 && producersMapSW.get(ppid) != null) {
			p.setParentId(producersMapSW.get(ppid).getId());
		} else {
			logger.error("Could not find ppid for " + name + " (ppid: " + ppid
					+ ", pid: " + pid + ")");
		}

		producersMapSW.put(pid, p);
	}

	/**
	 * If we do not have all the info on a producer yet in the trace then create
	 * a producer stub with just the pid
	 * 
	 * @param aPid
	 *            Pid of the event producer
	 */
	public void createProducerStub(int aPid, boolean soft) {
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());
		String stringPID = String.valueOf(aPid);
		p.setLocalId(stringPID);

		// Set a fake name to flag stub producer
		p.setName("_StubEventProducer");
		
		if (soft)
			producersMapSW.put(aPid, p);
		else
			producersMapHW.put(aPid, p);
	}

	/**
	 * Create a type from a given IEventDeclaration representing a dataType
	 * 
	 * @param anEventDeclaration
	 *            The class representing a type
	 */
	public void setType(IEventDeclaration anEventDeclaration, boolean soft) {
		if (soft && !typesSW.containsKey(anEventDeclaration.getName()) || !soft
				&& !typesHW.containsKey(anEventDeclaration.getName())) {
		EventType et = new EventType(eventIdTypeManager.getNextId(),
					EventCategory.PUNCTUAL_EVENT);

			et.setName(anEventDeclaration.getName());

			// For every param
			for (String attributeName : anEventDeclaration.getFields()
					.getFields().keySet()) {
				EventParamType eventParamType = new EventParamType(
						eventParamTypeIdManager.getNextId());
				eventParamType.setEventType(et);
				eventParamType.setName(attributeName);
				eventParamType.setType(getParamType(anEventDeclaration
						.getFields().getFields().get(attributeName).getClass()
						.getSimpleName()));
			}
			if (soft)
				typesSW.put(anEventDeclaration.getName(), et);
			else
				typesHW.put(anEventDeclaration.getName(), et);

		}
	}

	/**
	 * Get the type of a parameter for a given class name representing a type
	 * (cf. typeCorrespondance)
	 * 
	 * @param aClassName
	 *            the name of the class for which we want the corresponding type
	 * @return the type of a parameter
	 */
	public String getParamType(String aClassName) {
		String type = "UNKNOWN";
		if (typeCorrespondence.containsKey(aClassName)) {
			type = typeCorrespondence.get(aClassName);
		} else {
			logger.debug("Encountered unsupported type: " + aClassName);
		}

		return type;
	}

	/**
	 * Update the state of a process
	 * 
	 * @param aRecord
	 *            CtfRecord containing the event info
	 * @param aProducer
	 *            Event producer for which we changed the state
	 */
	public void setProcessState(CtfRecord aRecord, int aProducer) throws SoCTraceException {
		State previousState = processState.get(aProducer);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());
			saveEvent(previousState, true);
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(pageSW);
		aState.setTimestamp(aRecord.getTimestamp());
		
		if(aState.getTimestamp() > maxTimestamp)
			maxTimestamp = aState.getTimestamp();

		// Check if we have already encountered the producer
		if (!producersMapSW.containsKey(aProducer)) {
			// If not then create a stub
			createProducerStub(aProducer, true);
		}
		aState.setEventProducer(producersMapSW.get(aProducer));

		aState.setType(getType(aRecord, true));

		// Set the created State as the current state for the process
		processState.put(aProducer, aState);
	}

	public void endProcess(CtfRecord aRecord, int aProducerID)
			throws SoCTraceException {
		State previousState = processState.get(aProducerID);
		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());
			saveEvent(previousState, true);
		}
		processState.put(aProducerID, null);
	}

	/**
	 * Check if an event belongs to the category STATE
	 * 
	 * @param anEventName
	 *            name of the event
	 * @return true if an event belongs to the category STATE
	 */
	public boolean isStateEvent(String anEventName) {
		return stateEvent.contains(anEventName);
	}

	/**
	 * Add the event to the list of event to be save and performs check to see
	 * if a commit is needed
	 * 
	 * @param anEvent
	 *            the Event to save
	 */
	private void saveEvent(Event anEvent, boolean soft)
			throws SoCTraceException {
	if (soft) {
			eventsSW.add(anEvent);
			eventsPerPageSW++;
			numberOfEventsSW++;

			if (eventsPerPageSW > MAX_EVENT_PER_PAGE) {
				// save events
				saveEvents(eventsSW, traceDBSoft);
				eventsSW.clear();

				eventsPerPageSW = 0;
				pageSW++;
			}
		} else {
			eventsHW.add(anEvent);
			eventsPerPageHW++;
			numberOfEventsHW++;

			if (eventsPerPageHW > MAX_EVENT_PER_PAGE) {
				// save events
				saveEvents(eventsHW, traceDBHard);

				eventsHW.clear();

				eventsPerPageHW = 0;
				pageHW++;
			}
		}
	}

	/**
	 * Build and save the trace database representation
	 */
	private void buildTraces() throws SoCTraceException {
			Trace traceSoft = new Trace(sysDB.getNewId(FramesocTable.TRACE.toString(),
					"ID"));

			traceSoft.setDescription("Ctf trace (SW) imported on " + getCurrentDate());
			traceSoft.setDbName(traceDBNameSW);
			buildTraceType();
			traceSoft.setType(traceType);
			traceSoft.setProcessed(false);
			traceSoft.setNumberOfEvents(numberOfEventsSW);
			traceSoft.setNumberOfCpus(numberOfCPUs);
			traceSoft.setTimeUnit(TimeUnit.NANOSECONDS.getInt());
			// All timestamps are modified to fit with a starting date of 0
			traceSoft.setMinTimestamp(0L);
			traceSoft.setMaxTimestamp(getMaxTimestamp());

			sysDB.save(traceSoft);
			sysDB.commit();
			
			Trace traceHard = new Trace(sysDB.getNewId(FramesocTable.TRACE.toString(),
					"ID"));

			traceHard.setDescription("Ctf trace (HW) imported on " + getCurrentDate());
			traceHard.setDbName(traceDBNameHW);
			buildTraceType();
			traceHard.setType(traceType);
			traceHard.setProcessed(false);
			traceHard.setNumberOfEvents(numberOfEventsHW);
			traceHard.setNumberOfCpus(numberOfCPUs);
			traceHard.setTimeUnit(TimeUnit.NANOSECONDS.getInt());
			// All timestamps are modified to fit with a starting date of 0
			traceHard.setMinTimestamp(0L);
			traceHard.setMaxTimestamp(getMaxTimestamp());
			
			sysDB.save(traceHard);
	}

	/**
	 * Create event producers that do not appear in the trace as such These
	 * producers are the init process and the swapper
	 */
	private void createSpecialProducers() {
		// Create fake producer init with PID 1
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());
		String stringPID = String.valueOf(1);
		p.setLocalId(stringPID);
		p.setName("init");
		p.setType("init");
		producersMapSW.put(1, p);

		// Create fake producer swapper with PID 0
		EventProducer p2 = new EventProducer(eventProducerIdManager.getNextId());
		stringPID = String.valueOf(0);
		p2.setLocalId(stringPID);
		p2.setName("swapper");
		p2.setType("swapper");
		producersMapSW.put(0, p2);

		// Create fake event type for link
		EventType et = new EventType(eventIdTypeManager.getNextId(),
				EventCategory.LINK);
		et.setName(CtfParserConstants.LINK_TYPE);
		typesSW.put(CtfParserConstants.LINK_TYPE, et);
		
		// Create fake root for the CPU
		EventProducer machine = new EventProducer(eventProducerIdManager.getNextId());
		stringPID = String.valueOf(0);
		machine.setLocalId(stringPID);
		machine.setName("Machine");
		machine.setType("Machine");
		producersMapHW.put(0, machine);
	}

	/**
	 * Create a link event corresponding to the given parameter
	 * 
	 * @param prevThread
	 *            pid of the thread at the origin of the event
	 * @param thread
	 *            pid of the thread at the end of the event
	 * @param timeStamp
	 *            starting date of the event
	 * @param duration
	 *            duration of the event
	 * @param cpu
	 *            cpu number on which the event occurs
	 */
	public void createLink(int prevThread, int thread, long timeStamp,
			long duration, int cpu) throws SoCTraceException {
		Link aLink = new Link(eventIdManager.getNextId());
		aLink.setCpu(cpu);
		aLink.setPage(pageSW);
		aLink.setTimestamp(timeStamp);

		// Check if we have already encountered the producer
		if (!producersMapSW.containsKey(prevThread)) {
			// If not then create a stub
			createProducerStub(prevThread, true);
		}
		aLink.setEventProducer(producersMapSW.get(prevThread));

		// Check if we have already encountered the receiver
		if (!producersMapSW.containsKey(thread)) {
			// If not then create a stub
			createProducerStub(thread, true);
		}
		aLink.setEndProducer(producersMapSW.get(thread));
		aLink.setEndTimestamp(timeStamp + duration);
		aLink.setType(typesSW.get(CtfParserConstants.LINK_TYPE));

		saveEvent(aLink, true);
	}

	/**
	 * Update the state of a processor
	 * 
	 * @param aRecord
	 *            CtfRecord containing the event info
	 * @param aProducer
	 *            Event producer for which we changed the state
	 */
	public void setCPUState(CtfRecord aRecord) throws SoCTraceException {
		State previousState = CPUState.get(aRecord.cpu);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());

			saveEvent(previousState, false);
		} else {
			// Create an event producer for this CPU
			createCPUproducer(aRecord.cpu);
			numberOfCPUs++;
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(pageHW);
		aState.setTimestamp(aRecord.getTimestamp());
		aState.setEventProducer(producersMapHW.get(-2 - aRecord.cpu));
		aState.setType(getType(aRecord, false));

		// Set the created State as the current state for the process
		CPUState.put(aRecord.cpu, aState);
	}

	/**
	 * Create an event producer for a CPU This is used to build the state of the
	 * CPU We give a negative pid to the CPU since we are sure those won't be
	 * found in the trace event. The PID is computed with the formula: -2 - the
	 * CPU number ID (i.e. CPU 0 will have PID -2, CPU 1 the PID -3, etc.)
	 * 
	 * @param aCpu
	 *            the CPU ID
	 */
	public void createCPUproducer(int aCpu) {
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());

		// Give negative pid as we are sure those are not used as real PID
		String stringPID = String.valueOf(-2 - aCpu);
		p.setLocalId(stringPID);
		p.setParentId(producersMapHW.get(0).getId());

		String aName = "CPU " + aCpu;
		p.setName(aName);
		p.setType(aName);

		producersMapHW.put(-2 - aCpu, p);
	}

	public void createIRQProducer(int anIrq) {
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());

		// Give negative pid as we are sure those are not used as real PID
		p.setLocalId(String.valueOf(anIrq));
		p.setParentId(producersMapHW.get(0).getId());

		String aName = "IRQ " + anIrq;
		p.setName(aName);
		p.setType(aName);

		producersMapHW.put(p.getId(), p);
		irqList.put(anIrq, p);
	}
	
	public void createSoftIRQProducer(int anIrq) {
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());

		// Give negative pid as we are sure those are not used as real PID
		p.setLocalId(String.valueOf(anIrq));
		p.setParentId(producersMapHW.get(0).getId());

		String aName = "SOFT_IRQ " + anIrq;
		p.setName(aName);
		p.setType(aName);

		producersMapHW.put(p.getId(), p);
		softIrqList.put(anIrq, p);
	}
	
	/**
	 * Update the state of a soft irq
	 */
	public void setSoftIrqState(CtfRecord aRecord, int anIrqID) throws SoCTraceException {
		if (!softIrqState.containsKey(anIrqID))
			// Create an event producer for this IRQ
			createSoftIRQProducer(anIrqID);
		
		State previousState = softIrqState.get(anIrqID);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());
			saveEvent(previousState, false);
			
			// If the event is exit, then don't create a new state
			if (aRecord.type.equals(CtfParserConstants.SOFT_IRQ_STATUS_EXIT)) {
				softIrqState.put(anIrqID, null);
				return;
			}
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(pageHW);
		aState.setTimestamp(aRecord.getTimestamp());
		aState.setEventProducer(producersMapHW.get(softIrqList.get(anIrqID).getId()));
		aState.setType(getType(aRecord, false));

		// Set the created State as the current state for the soft irq
		softIrqState.put(anIrqID, aState);
	}
	
	/**
	 * Update the state of an irq
	 */
	public void setIrqState(CtfRecord aRecord, int anIrqID)
			throws SoCTraceException {
		if (!irqState.containsKey(anIrqID))
			// Create an event producer for this IRQ
			createIRQProducer(anIrqID);
		
		State previousState = irqState.get(anIrqID);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());
			saveEvent(previousState, false);

			// If the event is exit, then don't create a new state
			if (aRecord.type.equals(CtfParserConstants.IRQ_STATUS_EXIT)) {
				irqState.put(anIrqID, null);
				return;
			}
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(pageHW);
		aState.setTimestamp(aRecord.getTimestamp());
		aState.setEventProducer(producersMapHW.get(irqList.get(anIrqID).getId()));
		aState.setType(getType(aRecord, false));

		// Set the created State as the current state for the irq
		irqState.put(anIrqID, aState);
	}
	
	/**
	 * Check if a state was not close at the end of an importation, and if so
	 * set the end at the maximal timestamp
	 */
	void checkUnfinishedStates() throws SoCTraceException {

		// For processes
		for (int anEpID : processState.keySet()) {
			State previousState = processState.get(anEpID);

			// Check if this is the first state for this producer
			if (previousState != null) {
				// Set the timestamp for state ending
				previousState.setLongPar(maxTimestamp);
				saveEvent(previousState, true);
			}
		}

		// For CPU
		for (int aCPUID : CPUState.keySet()) {
			State previousState = CPUState.get(aCPUID);

			// If not null
			if (previousState != null) {
				// Set the timestamp for state ending
				previousState.setLongPar(maxTimestamp);

				saveEvent(previousState, false);
			}
		}
	}

	/**
	 * Handle exception that way in order to avoid changing API in tmf code
	 * 
	 * @param e
	 *            the soctraceException that was thrown
	 */
	public void handleException(SoCTraceException e) {
		// Keep only the first exception
		if (socTraceException != null)
			socTraceException = e;
	}

	public long getMinTimestamp() {
		return minTimestamp;
	}

	public void setMinTimestamp(long minTimestamp) {
		this.minTimestamp = minTimestamp;
	}

	public long getMaxTimestamp() {
		return maxTimestamp;
	}

	public void setMaxTimestamp(long maxTimestamp) {
		this.maxTimestamp = maxTimestamp;
	}

	public SoCTraceException getSocTraceException() {
		return socTraceException;
	}

	public void setSocTraceException(SoCTraceException socTraceException) {
		this.socTraceException = socTraceException;
	}
}
