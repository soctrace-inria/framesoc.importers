package fr.inria.soctrace.tools.importer.ctftrace.core;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.linuxtools.tmf.ctf.core.CtfTmfTrace;
import fr.inria.linuxtools.statesystem.core.exceptions.AttributeNotFoundException;
import fr.inria.linuxtools.statesystem.core.exceptions.StateSystemDisposedException;
import fr.inria.linuxtools.statesystem.core.exceptions.StateValueTypeException;
import fr.inria.linuxtools.statesystem.core.exceptions.TimeRangeException;
import fr.inria.linuxtools.tmf.core.exceptions.TmfAnalysisException;
import fr.inria.linuxtools.statesystem.core.interval.ITmfStateInterval;
import fr.inria.linuxtools.statesystem.core.ITmfStateSystem;

public class CtfTraceSub extends CtfTmfTrace {

	private String directory;
	private CtfParser aParser;
	protected CtfParserAnalysisModule analysisModule;
	protected File htFile;

	public CtfTraceSub(CtfParser aCtfParser) {
		aParser = aCtfParser;
	}

	/**
	 * Run the customized state system builder. We explore all the events and
	 * build the state events at the same time
	 */
	public void buildSystem(IProgressMonitor monitor) {
		try {
			// Build the state system specific to LTTng kernel traces 
			htFile = new File(directory + CtfParserAnalysisModule.HISTORY_TREE_FILE_NAME);

			// Create the analysis module
			analysisModule = new CtfParserAnalysisModule(this, aParser,
					directory, htFile);

			// Set minimum time stamps in order to perform the subtraction on
			// every time stamp( shift to zero)
			aParser.setMinTimestamp(getStartTime().getValue());

			// Init analysis module
			analysisModule.setTrace(this);

			// Get the events and build states
			analysisModule.performAnalysis(monitor);
			if (monitor.isCanceled()) {
				analysisModule.close();
				htFile.delete();
			}
		} catch (TmfAnalysisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the link events
	 */
	public void buildLink(IProgressMonitor monitor) {
		ITmfStateSystem ssq = analysisModule.getStateSystem();
		try {
			long start = ssq.getStartTime();
			long end = ssq.getCurrentEndTime();

			List<Integer> currentThreadQuarks = ssq.getQuarks(
					CtfParserConstants.CPUS,
					"*", CtfParserConstants.CURRENT_THREAD); //$NON-NLS-1$

			for (int currentThreadQuark : currentThreadQuarks) {

				// Adjust the query range to include the previous and following
				// intervals
				long qstart = Math.max(
						ssq.querySingleState(start, currentThreadQuark)
								.getStartTime() - 1, ssq.getStartTime());
				long qend = Math.min(
						ssq.querySingleState(end, currentThreadQuark)
								.getEndTime() + 1, ssq.getCurrentEndTime());
				
				// Get the CPU number
				String fullPath = ssq.getFullAttributePath(currentThreadQuark);
				String[] tokens = fullPath.split("/");
				int cpu = Integer.parseInt(tokens[1]);

				List<ITmfStateInterval> currentThreadIntervals = ssq
						.queryHistoryRange(currentThreadQuark, qstart, qend);
				int prevThread = 0;
				long prevEnd = 0;
				long lastEnd = 0;
				for (ITmfStateInterval currentThreadInterval : currentThreadIntervals) {
					long time = currentThreadInterval.getStartTime();
					if (time != lastEnd) {
						// don't create links where there are gaps in intervals
						// due to the resolution
						prevThread = 0;
						prevEnd = 0;
					}
					int thread = currentThreadInterval.getStateValue()
							.unboxInt();

					if (thread > 0 && prevThread > 0) {
						aParser.createLink(prevThread, thread, prevEnd - aParser.getMinTimestamp(), time
								- prevEnd, cpu);
					}

					lastEnd = currentThreadInterval.getEndTime() + 1;

					if (thread != 0) {
						prevThread = thread;
						prevEnd = lastEnd;
					}
					
					if (monitor.isCanceled()) {
						analysisModule.close();
						htFile.delete();
						return;
					}
				}
			}
		} catch (TimeRangeException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (StateValueTypeException e) {
			e.printStackTrace();
		} catch (StateSystemDisposedException e) {
			e.printStackTrace();
		}
		
		analysisModule.close();
		
		// The state system creates a file containing the built state of the
		// analyzed trace. We delete it since we don't need it
		htFile.delete();
		return;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
