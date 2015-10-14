package fr.inria.soctrace.tools.exporter.pjdump.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.lib.model.Trace;

public class PajeDumpExporterInput implements IFramesocToolInput {

	/**
	 * The trace we want to export
	 */
	private List<Trace> traces = new ArrayList<Trace>();

	/**
	 * Export directory
	 */
	private String directory;

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

}
