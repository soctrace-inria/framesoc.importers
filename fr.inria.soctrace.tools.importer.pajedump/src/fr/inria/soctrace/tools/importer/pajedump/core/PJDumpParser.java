/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.pajedump.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
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
import fr.inria.soctrace.lib.model.Link;
import fr.inria.soctrace.lib.model.PunctualEvent;
import fr.inria.soctrace.lib.model.State;
import fr.inria.soctrace.lib.model.Variable;
import fr.inria.soctrace.lib.model.utils.ModelConstants.EventCategory;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.IdManager;
import fr.inria.soctrace.lib.utils.TagList;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;

/**
 * PJDump Parser core class.
 * 
 * Warning: the current implementation of this parser works under the hypothesis that a producer may
 * be in a single state at a given time.
 * 
 * Warning: for the flat hierarchy option, the taken hypotheses are the following:
 *  - For a given imbrication level, there can only be one current active state (no overlapping)
 *  - A less imbricated state always ends after the more imbricated states
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PJDumpParser {

	private static final Logger logger = LoggerFactory.getLogger(PJDumpParser.class);

	protected SystemDBObject sysDB;
	protected TraceDBObject traceDB;
	protected String traceFile;
	protected int numberOfEvents = 0;
	protected long minTimestamp;
	protected long maxTimestamp;
	protected int timeUnit;
	protected int precision;
	
	/**
	 * Flat State imbrication 
	 */
	protected boolean flattenImbrication = false;
	// Store the unfinished state for a given producer and a given imbrication level 
	protected Map<String, Map<Integer, State>> pendingStates = new HashMap<String, Map<Integer, State>>();
	// Store the normal end date of a given state
	protected Map<Integer, Long> normalStateEnd = new HashMap<Integer, Long>();
	// Store the imbrication level of the last state for a given producer
	protected Map<String, Integer> currentImbricationLevel = new HashMap<String, Integer>();
	// Store the last state added for a given producer
	protected Map<String, State> lastAddedState = new HashMap<String, State>();
	
	private Map<String, PJDumpLineParser> parserMap = new HashMap<String, PJDumpLineParser>();

	private Map<String, EventProducer> producersMap = new HashMap<String, EventProducer>();
	private Map<String, EventType> types = new HashMap<String, EventType>();
	private int page = 0;
	private IdManager eIdManager = new IdManager();
	private IdManager etIdManager = new IdManager();
	private IdManager epIdManager = new IdManager();
	private List<Event> elist = new LinkedList<Event>();
	private Map<String, List<Link>> endPendingLinks = new HashMap<String, List<Link>>();
	private Map<String, List<Link>> startPendingLinks = new HashMap<String, List<Link>>();
	private long byteRead = 0; // byte read corresponding to events not saved yet
	private boolean doublePrecision = true;

	public PJDumpParser(SystemDBObject sysDB, TraceDBObject traceDB, String traceFile,
			PajeDumpInput input) {

		this.traceFile = traceFile;
		this.sysDB = sysDB;
		this.traceDB = traceDB;
		this.timeUnit = input.getTimeUnit();
		this.doublePrecision = input.isDoublePrecision();
		this.precision = input.getPrecision();
		this.flattenImbrication = input.isFlattenImbrication();

		parserMap.put(PJDumpConstants.CONTAINER, new ContainerParser());
		parserMap.put(PJDumpConstants.EVENT, new EventParser());
		parserMap.put(PJDumpConstants.LINK, new LinkParser());
		parserMap.put(PJDumpConstants.STATE, new StateParser());
		parserMap.put(PJDumpConstants.VARIABLE, new VariableParser());
	}

	public PJDumpParser(SystemDBObject sysDB, TraceDBObject traceDB, String traceFile,
			boolean doublePrecision, int timeUnit) {
		this.traceFile = traceFile;
		this.sysDB = sysDB;
		this.traceDB = traceDB;
		this.timeUnit = timeUnit;
		this.doublePrecision = doublePrecision;

		parserMap.put(PJDumpConstants.CONTAINER, new ContainerParser());
		parserMap.put(PJDumpConstants.EVENT, new EventParser());
		parserMap.put(PJDumpConstants.LINK, new LinkParser());
		parserMap.put(PJDumpConstants.STATE, new StateParser());
		parserMap.put(PJDumpConstants.VARIABLE, new VariableParser());
	}

	/**
	 * 
	 * @param monitor
	 *            progress monitor
	 * @param numberOfTraces
	 * @param currentTrace
	 * @throws SoCTraceException
	 */
	public void parseTrace(IProgressMonitor monitor, int currentTrace, int numberOfTraces)
			throws SoCTraceException {

		logger.debug("Trace file: {}", traceFile);

		try {
			monitor.beginTask("Import trace (" + currentTrace + "/" + numberOfTraces + ")",
					PJDumpConstants.WORK);
			monitor.subTask("Trace file: " + traceFile);
			// Trace Events, EventTypes and Producers
			boolean part = parseRawTrace(monitor);
			saveProducers();
			saveTypes();
			saveTraceMetadata(part);
		} finally {
			monitor.done();
		}

	}

	private long getFileSize(String filename) {
		File file = new File(filename);
		return file.length();
	}

	private boolean parseRawTrace(IProgressMonitor monitor) throws SoCTraceException {

		try {
			boolean partialImport = false;
			numberOfEvents = 0;
			minTimestamp = Long.MAX_VALUE;
			maxTimestamp = Long.MIN_VALUE;
			page = 0;
			elist.clear();

			// we add +1 to file size to avoid dividing by 0
			double scale = ((double) PJDumpConstants.WORK) / (getFileSize(traceFile) + 1);
			// add +1 to the byte read too to compensate
			byteRead = 1;

			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(
					new FileInputStream(traceFile))));
			String[] line;
			while ((line = getLine(br)) != null) {

				if (line.length < PJDumpConstants.MIN_LINE_NUMBER_OF_PARAMETERS) {
					malformedLineException(line);
				}

				logger.debug(Arrays.toString(line));
				PJDumpLineParser parser = parserMap.get(line[PJDumpConstants.ENTITY]);
				if (parser == null) {
					malformedLineException(line);
				}

				parser.parseLine(line);

				if (elist.size() == PJDumpConstants.PAGE_SIZE)
					page++;

				if (elist.size() >= PJDumpConstants.PAGE_SIZE && endPendingLinks.isEmpty()
						&& startPendingLinks.isEmpty()) {
					saveEvents(elist);
					monitor.worked(getWorked(scale));
					byteRead = 0;
					numberOfEvents += elist.size();
					elist.clear();
					if (monitor.isCanceled()) {
						if (getLine(br) != null) {
							// there were other lines
							partialImport = true;
						}
						break;
					}
				}
			}

			if(flattenImbrication){
				checkUnfinishedStates();
			}
			
			if (elist.size() > 0) {
				saveEvents(elist);
				monitor.worked(getWorked(scale));
				byteRead = 0;
				numberOfEvents += elist.size();
				elist.clear();
			}

			logger.debug("Saved {} events on {} pages", numberOfEvents, (page + 1));

			return partialImport;

		} catch (Exception e) {
			throw new SoCTraceException(e);
		}

	}

	private void malformedLineException(String[] line) throws SoCTraceException {
		throw new SoCTraceException("Malformed line: " + Arrays.toString(line));
	}

	private int getWorked(double scale) {
		return (int) (scale * byteRead);
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

			byteRead += strLine.length() + 1;

			strLine = strLine.trim();
			if (strLine.equals(""))
				continue;
			if (strLine.startsWith("#"))
				continue;

			args = strLine.split(PJDumpConstants.SEPARATOR);
		}
		return args;
	}

	private void saveProducers() throws SoCTraceException {
		Collection<EventProducer> eps = producersMap.values();
		for (EventProducer ep : eps) {
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

	protected void saveTraceMetadata(boolean partialImport) throws SoCTraceException {
		String alias = FilenameUtils.getBaseName(traceFile);
		TagList tlist = new TagList();
		if (partialImport) {
			tlist.addValue("part");
		}
		if (flattenImbrication) {
			tlist.addValue("flat");
		}
		if (tlist.size() > 0) {
			alias = alias + " " + tlist.getValueString();
		}
		PJDumpTraceMetadata metadata = new PJDumpTraceMetadata(sysDB, traceDB.getDBName(),
				alias, numberOfEvents, minTimestamp, maxTimestamp, timeUnit);
		metadata.createMetadata();
		metadata.saveMetadata();
	}

	private void updateMinMax(long timestamp) {
		if (timestamp < minTimestamp)
			minTimestamp = timestamp;
		if (timestamp > maxTimestamp)
			maxTimestamp = timestamp;
	}

	private EventType getType(String name, int category) {
		if (!types.containsKey(name)) {
			EventType et = new EventType(etIdManager.getNextId(), category);
			et.setName(name);
			types.put(name, et);
		}
		return types.get(name);
	}

	private int getEventProducerId(String name) {
		if (!producersMap.containsKey(name)) {
			return -1;
		}
		return producersMap.get(name).getId();
	}

	private long getTimestamp(String ts) {
		if (doublePrecision) {
			Double timestamp = Double.parseDouble(ts);
			timestamp = Math.pow(10, precision) * timestamp;
			return timestamp.longValue();
		} else {
			return Long.parseLong(ts);
		}
	}

	// entity parsers

	private class EventParser implements PJDumpLineParser {
		public void parseLine(String[] fields) throws SoCTraceException {
			checkLine(fields, PJDumpConstants.E_ARGUMENTS);
			PunctualEvent e = new PunctualEvent(eIdManager.getNextId());
			e.setEventProducer(producersMap.get(fields[PJDumpConstants.E_CONTAINER]));
			e.setPage(page);
			e.setTimestamp(getTimestamp(fields[PJDumpConstants.E_TIME]));
			e.setType(getType(fields[PJDumpConstants.E_VALUE], EventCategory.PUNCTUAL_EVENT));
			elist.add(e);
			updateMinMax(e.getTimestamp());
		}
	}

	private void checkLine(String[] fields, int number) throws SoCTraceException {
		if (fields.length < number) {
			throw new SoCTraceException("Line has the wrong number of arguments: "
					+ Arrays.toString(fields));
		}
	}

	private class LinkParser implements PJDumpLineParser {
		public void parseLine(String[] fields) throws SoCTraceException {
			checkLine(fields, PJDumpConstants.L_ARGUMENTS);
			Link l = new Link(eIdManager.getNextId());
			l.setPage(page);
			l.setTimestamp(getTimestamp(fields[PJDumpConstants.L_START_TIME]));
			l.setType(getType(fields[PJDumpConstants.L_VALUE], EventCategory.LINK));
			l.setEndTimestamp(getTimestamp(fields[PJDumpConstants.L_END_TIME]));
			elist.add(l);

			// start producer
			if (producersMap.containsKey(fields[PJDumpConstants.L_START_CONTAINER])) {
				l.setEventProducer(producersMap.get(fields[PJDumpConstants.L_START_CONTAINER]));
			} else {
				if (!startPendingLinks.containsKey(fields[PJDumpConstants.L_START_CONTAINER])) {
					startPendingLinks.put(fields[PJDumpConstants.L_START_CONTAINER],
							new LinkedList<Link>());
				}
				startPendingLinks.get(fields[PJDumpConstants.L_START_CONTAINER]).add(l);
			}

			// end producer
			if (producersMap.containsKey(fields[PJDumpConstants.L_END_CONTAINER])) {
				l.setEndProducer(producersMap.get(fields[PJDumpConstants.L_END_CONTAINER]));
			} else {
				if (!endPendingLinks.containsKey(fields[PJDumpConstants.L_END_CONTAINER])) {
					endPendingLinks.put(fields[PJDumpConstants.L_END_CONTAINER],
							new LinkedList<Link>());
				}
				endPendingLinks.get(fields[PJDumpConstants.L_END_CONTAINER]).add(l);
			}
			updateMinMax(l.getTimestamp());
			updateMinMax(l.getEndTimestamp());
		}
	}

	private class StateParser implements PJDumpLineParser {
		
		public void parseLine(String[] fields) throws SoCTraceException {
			checkLine(fields, PJDumpConstants.S_ARGUMENTS);
			State s = new State(eIdManager.getNextId());
			s.setEventProducer(producersMap.get(fields[PJDumpConstants.S_CONTAINER]));
			s.setPage(page);
			s.setTimestamp(getTimestamp(fields[PJDumpConstants.S_START_TIME]));
			s.setType(getType(fields[PJDumpConstants.S_VALUE], EventCategory.STATE));
			s.setImbricationLevel(Double.valueOf(
					fields[PJDumpConstants.S_IMBRICATION]).intValue());
			s.setEndTimestamp(getTimestamp(fields[PJDumpConstants.S_END_TIME]));
			if (!flattenImbrication) {
				elist.add(s);
			} else {
				handleFlattenState(s, fields);
			}
			updateMinMax(s.getTimestamp());
			updateMinMax(s.getEndTimestamp());
		}


		private void handleFlattenState(State s, String[] fields) throws SoCTraceException {
			String epName = fields[PJDumpConstants.S_CONTAINER];
			
			// Current state imbrication level
			int stateImbricationLevel = s.getImbricationLevel();
			
			if (!pendingStates.containsKey(epName)) {
				pendingStates.put(epName,
						new HashMap<Integer, State>());
			}
			
			if(!currentImbricationLevel.containsKey(epName))
				currentImbricationLevel.put(epName, 0);
			
			// Imbrication level of the last state processed
			int prodImbricationLevel = currentImbricationLevel.get(epName);

			
			if (prodImbricationLevel < stateImbricationLevel) {
				// Close the previous state at the old imbrication level
				State oldState = pendingStates.get(epName).get(
						prodImbricationLevel);
				oldState.setEndTimestamp(s.getTimestamp());
				elist.add(oldState);
				
				lastAddedState.put(epName, oldState);

				// Start a new one at the new imbrication level
				pendingStates.get(epName).put(stateImbricationLevel, s);
				normalStateEnd.put(s.getId(), s.getEndTimestamp());
			} else if (prodImbricationLevel > stateImbricationLevel) {
				// Save the state at the previous imbrication level 
				State oldState = pendingStates.get(epName).get(
						prodImbricationLevel);
				
				if (oldState.getEndTimestamp() > s.getTimestamp()) {
					logger.error("Warning: This trace contains a state that ends after a state of higher imbrication launch. The generated trace may not be accurate. "
							+ oldState.toString() + ", " + s.toString());
				}

				elist.add(oldState);
				pendingStates.get(epName).remove(prodImbricationLevel);

				// Fill with the upper imbricated state
				prodImbricationLevel--;
				long previousEndDate = oldState.getEndTimestamp();
				while (previousEndDate < s.getTimestamp()) {
					previousEndDate = fillIntermediate(previousEndDate,
							prodImbricationLevel, oldState.getEventProducer(),
							s.getTimestamp());
					prodImbricationLevel--;
				}

				pendingStates.get(epName).put(stateImbricationLevel, s);
				normalStateEnd.put(s.getId(), s.getEndTimestamp());			
			} else {
				State oldState = pendingStates.get(epName).get(
						stateImbricationLevel);
				
				// If first state for the event producer
				if (oldState == null && stateImbricationLevel == 0) {
					pendingStates.get(epName).put(stateImbricationLevel, s);
					normalStateEnd.put(s.getId(), s.getEndTimestamp());
				} else {
					if (oldState.getEndTimestamp() < s.getTimestamp()
							&& stateImbricationLevel > 0) {
						// Add a state of the upper level to fill the time gap
						fillWithUpperLevel(oldState, s);
					} else if (oldState.getEndTimestamp() > s.getTimestamp()) {
						logger.error("Warning: This trace contains overlaping states within the same imbrication. The generated trace may not be accurate. "
								+ oldState.toString() + ", " + s.toString());
					}

					elist.add(oldState);
					lastAddedState.put(epName, oldState);
					pendingStates.get(epName).put(stateImbricationLevel, s);
					normalStateEnd.put(s.getId(), s.getEndTimestamp());
				}
			}
			// Update teh current imbrication level
			currentImbricationLevel.put(epName, stateImbricationLevel);
		}


		/**
		 * Add a state from a previously splitted state
		 * 
		 * @param previousEndDate
		 *            the end date of teh previous state
		 * @param imbricationLevel
		 *            current imbrication level
		 * @param eventProducer
		 *            name of the currently processed event producer
		 * @param maxEndTimeStamp
		 *            the maximum end time stamp (== starting date of the next
		 *            state
		 * @return the end date of the created state
		 * @throws SoCTraceException
		 */
		private long fillIntermediate(long previousEndDate,
				int imbricationLevel, EventProducer eventProducer, long maxEndTimeStamp)
				throws SoCTraceException {
			// Get current unfinished state
			State pendingState = pendingStates.get(eventProducer.getName())
					.get(imbricationLevel);

			// Create a new state
			State intermediateState = new State(eIdManager.getNextId());
			intermediateState.setEventProducer(pendingState.getEventProducer());
			intermediateState.setPage(page);
			intermediateState.setTimestamp(previousEndDate);
			intermediateState.setType(pendingState.getType());
			intermediateState.setImbricationLevel(pendingState
					.getImbricationLevel());
			intermediateState.setEndTimestamp(Math.min(normalStateEnd.get(pendingState.getId()), maxEndTimeStamp));

			elist.add(intermediateState);
			lastAddedState.put(eventProducer.getName(), intermediateState);

			return intermediateState.getEndTimestamp();
		}

		/**
		 * Create a state to fill the time gap between two states that are at
		 * the same imbrication level
		 * 
		 * @param oldState
		 *            previous state
		 * @param s
		 *            next state
		 * @throws SoCTraceException
		 */
		private void fillWithUpperLevel(State oldState, State s)
				throws SoCTraceException {
			// Get current unfinished state
			State pendingState = pendingStates.get(
					oldState.getEventProducer().getName()).get(
					oldState.getImbricationLevel() - 1);

			// Create a new state
			State intermediateState = new State(eIdManager.getNextId());
			intermediateState.setEventProducer(pendingState.getEventProducer());
			intermediateState.setPage(page);
			intermediateState.setTimestamp(oldState.getEndTimestamp());
			intermediateState.setType(pendingState.getType());
			intermediateState.setImbricationLevel(pendingState
					.getImbricationLevel());
			intermediateState.setEndTimestamp(s.getTimestamp());

			elist.add(intermediateState);
		}
	}

	private class VariableParser implements PJDumpLineParser {
		public void parseLine(String[] fields) throws SoCTraceException {
			checkLine(fields, PJDumpConstants.V_ARGUMENTS);
			Variable v = new Variable(eIdManager.getNextId());
			v.setEventProducer(producersMap.get(fields[PJDumpConstants.V_CONTAINER]));
			v.setPage(page);
			v.setTimestamp(getTimestamp(fields[PJDumpConstants.V_START_TIME]));
			v.setType(getType(fields[PJDumpConstants.V_TYPE], EventCategory.VARIABLE));
			v.setValue(Double.valueOf(fields[PJDumpConstants.V_VALUE]));
			v.setEndTimestamp(0);
			elist.add(v);
			updateMinMax(v.getTimestamp());
		}
	}

	private class ContainerParser implements PJDumpLineParser {
		public void parseLine(String[] fields) throws SoCTraceException {
			checkLine(fields, PJDumpConstants.C_ARGUMENTS);
			if (producersMap.containsKey(fields[PJDumpConstants.C_NAME]))
				return;
			EventProducer ep = new EventProducer(epIdManager.getNextId());
			ep.setName(fields[PJDumpConstants.C_NAME]);
			ep.setParentId(getEventProducerId(fields[PJDumpConstants.C_PARENT_CONTAINER]));
			ep.setType(fields[PJDumpConstants.C_TYPE]);
			ep.setLocalId(String.valueOf(ep.getId()));
			producersMap.put(ep.getName(), ep);
			if (endPendingLinks.containsKey(ep.getName())) {
				for (Link l : endPendingLinks.get(ep.getName())) {
					l.setEndProducer(ep);
				}
				endPendingLinks.remove(ep.getName());
			}
			if (startPendingLinks.containsKey(ep.getName())) {
				for (Link l : startPendingLinks.get(ep.getName())) {
					l.setEventProducer(ep);
				}
				startPendingLinks.remove(ep.getName());
			}
		}
	}

	/**
	 * Close all the unfinished states
	 * 
	 * @throws SoCTraceException
	 */
	private void checkUnfinishedStates() throws SoCTraceException {
		for (String epName : pendingStates.keySet()) {
			int lastImbricationLevel = currentImbricationLevel.get(epName);
			long previousEndTime = lastAddedState.get(epName).getEndTimestamp();

			for (int i = lastImbricationLevel; i >= 0; i--) {
				State pendingState = pendingStates.get(epName).get(i);

				// Create a new state
				State intermediateState = new State(eIdManager.getNextId());
				intermediateState.setEventProducer(pendingState
						.getEventProducer());
				intermediateState.setPage(page);
				intermediateState.setTimestamp(previousEndTime);
				intermediateState.setType(pendingState.getType());
				intermediateState.setImbricationLevel(pendingState
						.getImbricationLevel());
				intermediateState.setEndTimestamp(normalStateEnd
						.get(pendingState.getId()));

				elist.add(intermediateState);

				previousEndTime = intermediateState.getEndTimestamp();
			}
		}
		
	}

}
