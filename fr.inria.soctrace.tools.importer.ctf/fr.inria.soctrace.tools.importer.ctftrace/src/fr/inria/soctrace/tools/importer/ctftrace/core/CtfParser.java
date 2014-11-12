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
	private TraceDBObject traceDB;
	private String[] tracePath;
	private Trace trace;
	private int numberOfEvents = 0;
	private int numberOfCPUs = 0;
	private Map<String, EventType> types = new HashMap<String, EventType>();
	private Map<Integer, EventProducer> producersMap = new HashMap<Integer, EventProducer>();
	private long minTimestamp;
	private long maxTimestamp;

	private String traceDBName;
	private TraceType traceType;

	private HashMap<Integer, State> processState = new HashMap<Integer, State>();
	private HashMap<Integer, State> CPUState = new HashMap<Integer, State>();
	private List<String> stateEvent = new ArrayList<String>();

	/*
	 * Set correspondence between the CTF parser and the soctrace
	 * representations of the type
	 */
	private HashMap<String, String> typeCorrespondence = new HashMap<String, String>();

	private int page;
	private int eventsPerPage;
	private List<Event> events;

	// ID Managers
	private IdManager eventIdManager;
	private IdManager eventParamIdManager;
	private IdManager eventIdTypeManager;
	private IdManager eventParamTypeIdManager;
	private IdManager eventProducerIdManager;

	public CtfParser(SystemDBObject aSysDB, TraceDBObject aTraceDB,
			CtfParserArgs args) throws SoCTraceException {
		sysDB = aSysDB;
		traceDB = aTraceDB;
		tracePath = args.traceFiles;
		traceDBName = args.traceDbName;

		typeCorrespondence.put("IntegerDeclaration", "INTEGER");
		typeCorrespondence.put("StringDeclaration", "STRING");
		typeCorrespondence.put("FloatDeclaration", "FLOAT");

		stateEvent.add("PROCESS_STATUS_UNKNOWN");
		stateEvent.add("PROCESS_STATUS_WAIT_BLOCKED");
		stateEvent.add("PROCESS_STATUS_RUN_USERMODE");
		stateEvent.add("PROCESS_STATUS_RUN_SYSCALL");
		stateEvent.add("PROCESS_STATUS_INTERRUPTED");
		stateEvent.add("PROCESS_STATUS_WAIT_FOR_CPU");
		stateEvent.add("CPU_STATUS_IDLE");
		stateEvent.add("CPU_STATUS_RUN_USERMODE");
		stateEvent.add("CPU_STATUS_RUN_SYSCALL");
		stateEvent.add("CPU_STATUS_IRQ");
		stateEvent.add("CPU_STATUS_SOFTIRQ");
	}

	public void parseTrace(IProgressMonitor monitor) {
		try {
			page = 0;
			numberOfEvents = 0;
			numberOfCPUs = 0;
			eventsPerPage = 0;
			events = new LinkedList<Event>();
			maxTimestamp = 0L;

			// Init ID managers
			eventProducerIdManager = new IdManager();
			eventIdManager = new IdManager();
			eventParamIdManager = new IdManager();
			eventIdTypeManager = new IdManager();
			eventParamTypeIdManager = new IdManager();

			createSpecialproducers();

			CtfTraceSub aT = new CtfTraceSub(this);
			aT.setDirectory(tracePath[0]);
			aT.initTrace(null, tracePath[0], TmfEvent.class);
			monitor.subTask("Building states and events");
			aT.buildSystem(monitor);
			if (monitor.isCanceled()) {
				aT.close();
				traceDB.dropDatabase();
				sysDB.rollback();
				return;
			}
			
			monitor.subTask("Building links");
			aT.buildLink(monitor);
			
			if (monitor.isCanceled()) {
				aT.close();
				traceDB.dropDatabase();
				sysDB.rollback();
				return;
			}

			// Save the remaining events even though the page is not full
			saveEvents(events);

			// Debug
			for (EventProducer aEP : producersMap.values()) {
				if (aEP.getName().equals("_StubEventProducer"))
					logger.debug("Unitialized pid " + aEP.getLocalId());
			}

			logger.info("Number of events " + numberOfEvents
					+ ", number of producers " + producersMap.keySet().size()
					+ ", number of EventTypes " + types.keySet().size());
			logger.info("Saved " + numberOfEvents + " events in " + page
					+ " pages.");

			saveProducers();
			saveTypes();

			buildTrace();

			aT.close();

		} catch (TmfTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SoCTraceException e) {
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
	private void saveEvents(List<Event> events) throws SoCTraceException {
		for (Event e : events) {
			traceDB.save(e);
			for (EventParam ep : e.getEventParams()) {
				traceDB.save(ep);
			}
		}
		traceDB.commit(); // committing each page is faster
	}

	/**
	 * Save the event producers
	 * 
	 * @throws SoCTraceException
	 */
	private void saveProducers() throws SoCTraceException {
		for (EventProducer e : producersMap.values()) {
			traceDB.save(e);
		}

		traceDB.commit();
	}

	/**
	 * Save the event types
	 * 
	 * @throws SoCTraceException
	 */
	private void saveTypes() throws SoCTraceException {
		for (EventType et : types.values()) {
			traceDB.save(et);
			for (EventParamType ept : et.getEventParamTypes()) {
				traceDB.save(ept);
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
	private Event setEvent(int id, int page, CtfRecord record)
			throws SoCTraceException {

		Event e = new Event(id);
		e.setCpu(record.cpu);
		e.setPage(page);
		e.setTimestamp(record.getTimestamp());
		
		if(e.getTimestamp() > maxTimestamp)
			maxTimestamp = e.getTimestamp();

		EventProducer aProducer = producersMap.get(record.pid);
		// Check if we have already encountered the producer
		if (aProducer == null) {
			// System.out.println("Error finding producer with pid: " +
			// record.pid );

			// If not then create a stub
			createProducerStub(record.pid);
		}
		e.setEventProducer(producersMap.get(record.pid));

		return e;
	}

	/**
	 * Return the requested type or create it otherwise
	 * 
	 * @param aRecord
	 *            CtfRecord holding the type info
	 * @return the requested EventType
	 */
	private EventType getType(CtfRecord aRecord) {
		if (!types.containsKey(aRecord.type)) {
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

			types.put(aRecord.type, et);
		}

		return types.get(aRecord.type);
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
	public void newEvent(CtfRecord aRecord) {
		Event e;
		try {
			e = setEvent(eventIdManager.getNextId(), page, aRecord);

			EventType et = getType(aRecord);
			et.setName(aRecord.type);
			e.setType(et);

			setParameters(e, aRecord);
			saveEvent(e);

		} catch (SoCTraceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Check if a given event producer already exists
	 * 
	 * @param aPid
	 *            pid of the producer
	 * @return true if the producer already exist and is not a stub
	 */
	public boolean producerExist(int aPid) {
		if (producersMap.containsKey(aPid)) {
			if (producersMap.get(aPid).getName() != "_StubEventProducer") {
				return true;
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
		//if (producersMap.get(ppid) == null || ppid < 0)
		//	return;
		if(producersMap.get(ppid) == null || ppid < 0)
			ppid = EventProducer.NO_PARENT_ID;

		EventProducer p;
		if (!producersMap.containsKey(pid)) {
			p = new EventProducer(eventProducerIdManager.getNextId());
			String stringPID = String.valueOf(pid);
			p.setLocalId(stringPID);
		} else {
			p = producersMap.get(pid);
		}
		p.setName(name);
		p.setType(name);
		p.setParentId(ppid);
		if (ppid >= 0 && producersMap.get(ppid) != null) {
			p.setParentId(producersMap.get(ppid).getId());
		} else {
			logger.error("Could not find ppid for " + name + " (ppid: " + ppid
					+ ", pid: " + pid + ")");
		}

		// System.out.println("new producer: " + p.toString());
		producersMap.put(pid, p);
	}

	/**
	 * If we do not have all the info on a producer yet in the trace then create
	 * a producer stub with just the pid
	 * 
	 * @param aPid
	 *            Pid of the event producer
	 */
	public void createProducerStub(int aPid) {
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());
		String stringPID = String.valueOf(aPid);
		p.setLocalId(stringPID);

		// Set a fake name to flag stub producer
		p.setName("_StubEventProducer");
		producersMap.put(aPid, p);
	}

	/**
	 * Create a type from a given IEventDeclaration representing a dataType
	 * 
	 * @param anEventDeclaration
	 *            The class representing a type
	 */
	public void setType(IEventDeclaration anEventDeclaration) {
		if (!types.containsKey(anEventDeclaration.getName())) {
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
			types.put(anEventDeclaration.getName(), et);

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
	public void setProcessState(CtfRecord aRecord, int aProducer) {
		State previousState = processState.get(aProducer);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());
			saveEvent(previousState);
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(page);
		aState.setTimestamp(aRecord.getTimestamp());
		
		if(aState.getTimestamp() > maxTimestamp)
			maxTimestamp = aState.getTimestamp();

		// Check if we have already encountered the producer
		if (!producersMap.containsKey(aProducer)) {
			// If not then create a stub
			createProducerStub(aProducer);
		}
		aState.setEventProducer(producersMap.get(aProducer));

		try {
			aState.setType(getType(aRecord));
		} catch (SoCTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set the created State as the current state for the process
		processState.put(aProducer, aState);
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
	private void saveEvent(Event anEvent) {
		events.add(anEvent);
		eventsPerPage++;
		numberOfEvents++;

		if (eventsPerPage > MAX_EVENT_PER_PAGE) {
			// save events
			try {
				saveEvents(events);
			} catch (SoCTraceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			events.clear();

			eventsPerPage = 0;
			page++;
		}
	}

	/**
	 * Build and save the trace database representation
	 */
	private void buildTrace() {
		try {
			trace = new Trace(sysDB.getNewId(FramesocTable.TRACE.toString(),
					"ID"));

			trace.setDescription("Ctf trace imported on " + getCurrentDate());
			trace.setDbName(traceDBName);
			buildTraceType();
			trace.setType(traceType);
			trace.setProcessed(false);
			trace.setNumberOfEvents(numberOfEvents);
			trace.setNumberOfCpus(numberOfCPUs);
			trace.setTimeUnit(TimeUnit.NANOSECONDS.getInt());
			// All timestamps are modified to fit with a starting date of 0
			trace.setMinTimestamp(0L);
			trace.setMaxTimestamp(getMaxTimestamp());

			sysDB.save(trace);
		} catch (SoCTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create event producers that do not appear in the trace as such These
	 * producers are the init process and the swapper
	 */
	private void createSpecialproducers() {
		// Create fake producer init with PID 1
		EventProducer p = new EventProducer(eventProducerIdManager.getNextId());
		String stringPID = String.valueOf(1);
		p.setLocalId(stringPID);
		p.setName("init");
		p.setType("init");
		producersMap.put(1, p);

		// Create fake producer swapper with PID 0
		EventProducer p2 = new EventProducer(eventProducerIdManager.getNextId());
		stringPID = String.valueOf(0);
		p2.setLocalId(stringPID);
		p2.setName("swapper");
		p2.setType("swapper");
		producersMap.put(0, p2);

		// Create fake event type for link
		EventType et = new EventType(eventIdTypeManager.getNextId(),
				EventCategory.LINK);
		et.setName("link");
		types.put("link", et);
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
			long duration, int cpu) {
		Link aLink = new Link(eventIdManager.getNextId());
		aLink.setCpu(cpu);
		aLink.setPage(page);
		aLink.setTimestamp(timeStamp);

		// Check if we have already encountered the producer
		if (!producersMap.containsKey(prevThread)) {
			// If not then create a stub
			createProducerStub(prevThread);
		}
		aLink.setEventProducer(producersMap.get(prevThread));

		// Check if we have already encountered the receiver
		if (!producersMap.containsKey(thread)) {
			// If not then create a stub
			createProducerStub(thread);
		}
		aLink.setEndProducer(producersMap.get(thread));
		aLink.setEndTimestamp(timeStamp + duration);

		try {
			aLink.setType(types.get("link"));
		} catch (SoCTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		saveEvent(aLink);
	}

	/**
	 * Update the state of a process
	 * 
	 * @param aRecord
	 *            CtfRecord containing the event info
	 * @param aProducer
	 *            Event producer for which we changed the state
	 */
	public void setCPUState(CtfRecord aRecord) {
		State previousState = CPUState.get(aRecord.cpu);

		// Check if this is the first state for this producer
		if (previousState != null) {
			// Set the timestamp for state ending
			previousState.setLongPar(aRecord.getTimestamp());

			saveEvent(previousState);
		} else {
			// Create an event producer for this CPU
			createCPUproducer(aRecord.cpu);
			numberOfCPUs++;
		}

		State aState = new State(eventIdManager.getNextId());
		aState.setCpu(aRecord.cpu);
		aState.setPage(page);
		aState.setTimestamp(aRecord.getTimestamp());
		aState.setEventProducer(producersMap.get(-2 - aRecord.cpu));

		try {
			aState.setType(getType(aRecord));
		} catch (SoCTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		String aName = "CPU " + aCpu;
		p.setName(aName);
		p.setType(aName);

		producersMap.put(-2 - aCpu, p);
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
}
