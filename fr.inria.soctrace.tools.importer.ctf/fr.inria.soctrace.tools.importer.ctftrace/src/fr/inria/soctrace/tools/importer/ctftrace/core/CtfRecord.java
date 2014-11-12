package fr.inria.soctrace.tools.importer.ctftrace.core;

import java.util.HashMap;
import fr.inria.linuxtools.tmf.core.event.ITmfEventField;
import fr.inria.linuxtools.tmf.core.event.ITmfEventType;

public class CtfRecord {

	/** timestamp of the record */
	private long timestamp;

	/** CPU of the record */
	public int cpu;
	/** PID of the record */
	public int pid;
	/** type of the record */
	public String type;
	/** arguments of the record */
	public HashMap<String, String> attributesValue;
	/** Extra category parameters */
	public Long longPar;
	public Double doublePar;

	/**
	 * Default constructor
	 */
	public CtfRecord() {
		timestamp = 0;
		cpu = 0;
		pid = 0;
		type = "none";
		longPar = -1L;
		doublePar = -1.0;
		attributesValue = new HashMap<String, String>();
	}

	/**
	 * Set the parameters of an event from the TMF representation of an event
	 * 
	 * @param content
	 *            contains the values of the parameters
	 * @param anEventType
	 *            contains the name of the parameters
	 */
	public void getParameters(ITmfEventField content, ITmfEventType anEventType) {
		for (String fName : anEventType.getFieldNames()) {

			// Modification due to lttng 3.0 apparently the names no longer
			// begin with underscore so we suppress it
			if (fName.startsWith("_")) {
				fName = fName.substring(1);
			}
			attributesValue.put(fName, content.getField(fName).getFormattedValue());
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	// debug
	public String toString() {
		return "Record(timestamp: " + timestamp + ", " + "cpu: " + cpu + ", "
				+ "type: " + type + ", arguments: " + argumentsString() + ")";
	}

	public void print() {
		System.out.println(toString());
	}

	private String argumentsString() {
		StringBuffer sb = new StringBuffer();
		for (String s : attributesValue.keySet()) {
			sb.append(s + ": " + attributesValue.get(s) + " ");
		}
		return sb.toString();
	}
}
