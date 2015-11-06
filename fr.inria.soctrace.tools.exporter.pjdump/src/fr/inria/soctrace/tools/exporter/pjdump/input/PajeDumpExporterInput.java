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
package fr.inria.soctrace.tools.exporter.pjdump.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.lib.model.Trace;

/**
 * Class handling the inputs for the pajedump exporter
 * 
 * Currently some of the parameters are just heere to be used when using the
 * exporter from command line. These parameter are: the start and end dates, the
 * output file and the filters of the event types and the event producers
 * 
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class PajeDumpExporterInput implements IFramesocToolInput {

	public static final int UNSPECIFIED_TIME = -1;
	
	/**
	 * The trace we want to export
	 */
	private List<Trace> traces = new ArrayList<Trace>();

	/**
	 * Export directory
	 */
	private String directory ="";
	
	/**
	 * Output file of the paje dump export
	 */
	private String outPutFile = "";
	
	/**
	 * Timestamp specifying the exported time interval of the trace
	 */
	private long startingTime = UNSPECIFIED_TIME;
	private long endingTime = UNSPECIFIED_TIME;
	
	/**
	 * List of exported event producers and event type
	 * All are exported if empty	
	 */
	private List<String> selectedEventProducers = new ArrayList<String>();
	private List<String> selectedEventTypes = new ArrayList<String>();
	
	/**
	 * Check parameters
	 * 
	 * @return true if all is OK
	 */
	public boolean check() {
		if (traces == null || traces.isEmpty())
			return false;
		File dir = new File(directory);
		if (directory == null || !dir.isDirectory() || !dir.canWrite())
			return false;
		return true;
	}

	/**
	 * Debug print
	 */
	public void print() {
		System.out.println("Trace:");
		for (Trace trace : traces) {
			trace.print();
		}
		System.out.println("Directory");
		System.out.println(directory);
	}

	@Override
	public String getCommand() {
		return "";
	}

	public List<Trace> getTraces() {
		return traces;
	}

	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getOutPutFile() {
		return outPutFile;
	}

	public void setOutPutFile(String outPutFile) {
		this.outPutFile = outPutFile;
	}

	public long getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	public long getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(long endingTime) {
		this.endingTime = endingTime;
	}

	public List<String> getSelectedEventProducers() {
		return selectedEventProducers;
	}

	public void setSelectedEventProducers(List<String> selectedEventProducers) {
		this.selectedEventProducers = selectedEventProducers;
	}

	public List<String> getSelectedEventTypes() {
		return selectedEventTypes;
	}

	public void setSelectedEventTypes(List<String> selectedEventTypes) {
		this.selectedEventTypes = selectedEventTypes;
	}

}
