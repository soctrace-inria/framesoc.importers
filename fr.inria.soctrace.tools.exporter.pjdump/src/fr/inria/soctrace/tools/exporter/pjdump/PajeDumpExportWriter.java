package fr.inria.soctrace.tools.exporter.pjdump;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.ui.model.LoaderQueue;
import fr.inria.soctrace.framesoc.ui.model.TimeInterval;
import fr.inria.soctrace.lib.model.Event;
import fr.inria.soctrace.lib.model.EventParam;
import fr.inria.soctrace.lib.model.EventProducer;
import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.model.utils.ModelConstants.EventCategory;
import fr.inria.soctrace.lib.query.EventProducerQuery;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.DeltaManager;

public class PajeDumpExportWriter {
	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(PajeDumpExportWriter.class);

	private static final String CONTAINER = "Container";

	private static final String PJDUMP_SEPARATOR = ",";

	private Trace trace;
	private StringBuilder pjdumpExport = new StringBuilder();
	private int currentMaxNumberOfParameter = 0;
	private Map<String, Integer> parameterTypes = new HashMap<String, Integer>();
	private String filePath;
	private int monitorCheck = 20000;
	private int countEvents = 0;
	private String lineSeparator = "";
	private PrintWriter writer = null;

	private Job loaderJob;

	private WriterThread writerThread;

	public WriterThread getWriterThread() {
		return writerThread;
	}

	public PajeDumpExportWriter(String filePath) {
		this.filePath = filePath;
	}

	private void handleParameters(Event event) throws SoCTraceException {
		List<EventParam> tmpParameters = event.getEventParams();
		// Keep the parameter value
		Map<Integer, String> currentParameterValue = new HashMap<Integer, String>();

		// Parse parameter
		for (EventParam ep : tmpParameters) {

			// Remove white space
			String paramType = ep.getEventParamType().getName();

			// If we have not yet met this type of parameter
			if (!parameterTypes.containsKey(paramType)) {
				parameterTypes.put(paramType, currentMaxNumberOfParameter);
				currentMaxNumberOfParameter++;
			}

			// Remove white space and quote
			String paramValue = ep.getValue();

			currentParameterValue
					.put(parameterTypes.get(paramType), paramValue);
		}

		// Complete pjdump with existing parameter values or blank otherwise
		for (int i = 0; i < currentMaxNumberOfParameter; i++) {
			if (i != 0)
				pjdumpExport.append(PJDUMP_SEPARATOR);
			if (currentParameterValue.containsKey(i)) {
				pjdumpExport.append(currentParameterValue.get(i));
			}
		}
	}

	public void readTrace(Trace trace, long start, long end) {
		if (trace == null) {
			return;
		}

		this.trace = trace;

		// create the queue
		LoaderQueue<Event> queue = new LoaderQueue<>();

		// create the event loader
		EventLoader loader = new EventLoader();
		loader.setTrace(trace);
		loader.setQueue(queue);

		// compute the actual time interval to load (trim with trace min and
		// max)
		TimeInterval interval = new TimeInterval(start, end);
		interval.startTimestamp = Math.max(trace.getMinTimestamp(),
				interval.startTimestamp);
		interval.endTimestamp = Math.min(trace.getMaxTimestamp(),
				interval.endTimestamp);

		// launch the job loading the queue
		launchLoaderJob(loader, interval);

		writerThread = new WriterThread(queue);
		writerThread.start();
		try {
			writerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void launchLoaderJob(final EventLoader loader,
			final TimeInterval requestedInterval) {
		loaderJob = new Job("Loading Event Table...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				DeltaManager all = new DeltaManager();
				all.start();
				loader.loadWindow(requestedInterval.startTimestamp,
						requestedInterval.endTimestamp, monitor);
				logger.debug(all.endMessage("Loader Job: loaded everything"));
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}
		};
		loaderJob.setUser(false);
		loaderJob.schedule();
	}

	/**
	 * Wrapper Thread object for the filtering thread.
	 */
	class WriterThread extends Thread {

		private LoaderQueue<Event> queue;
		private volatile boolean stop = false;

		private boolean exportParameters = false;

		public WriterThread(LoaderQueue<Event> queue) {
			this.queue = queue;
			pjdumpExport = new StringBuilder();
		}

		@Override
		public void run() {
			lineSeparator = System.getProperty("line.separator");

			try {
				writer = new PrintWriter(filePath + "/" + trace.getAlias()
						+ "_" + trace.getId() + ".pjdump",
						System.getProperty("file.encoding"));

				writeEventProducers();

				while (!queue.done()) {
					List<Event> events = queue.pop();
					if (events.isEmpty())
						continue;
					// put the events in the cache
					for (Event e : events) {
						if (stop)
							break;

						writeEvent(e);
					}
				}
			} catch (InterruptedException e) {
				logger.debug("Interrupted while taking the queue head");
			} catch (FileNotFoundException e) {
				MessageDialog.openError(Display.getDefault().getActiveShell(),
						"Exception", "File " + filePath
								+ " could not be created (" + e.getMessage()
								+ ")");
			} catch (UnsupportedEncodingException e) {
				MessageDialog.openError(
						Display.getDefault().getActiveShell(),
						"Exception",
						"Unsupported encoding "
								+ System.getProperty("file.encoding") + " ("
								+ e.getMessage() + ")");
			} catch (SoCTraceException e) {
				MessageDialog.openError(Display.getDefault().getActiveShell(),
						"Exception", e.getMessage());
			} finally {
				if (writer != null) {
					// Write data into the file
					writer.write(pjdumpExport.toString());

					// Close the fd
					writer.flush();
					writer.close();
				}
			}
		}

		public void writeEvent(Event event) throws SoCTraceException {
			pjdumpExport.append(EventCategory.categoryToString(event
					.getCategory()) + PJDUMP_SEPARATOR);
			pjdumpExport.append(event.getEventProducer().getName()
					+ PJDUMP_SEPARATOR);
			pjdumpExport.append(event.getType().getName() + PJDUMP_SEPARATOR);
			pjdumpExport.append(event.getTimestamp() + PJDUMP_SEPARATOR);

			switch (event.getCategory()) {
			case EventCategory.PUNCTUAL_EVENT:
				break;

			case EventCategory.VARIABLE:
			case EventCategory.STATE:
				pjdumpExport.append(event.getLongPar() + PJDUMP_SEPARATOR);
				pjdumpExport.append((event.getLongPar() - event.getTimestamp())
						+ PJDUMP_SEPARATOR);
				pjdumpExport.append(event.getDoublePar() + PJDUMP_SEPARATOR);
				pjdumpExport.append(event.getType().getName()
						+ PJDUMP_SEPARATOR);
				break;

			case EventCategory.LINK:
				pjdumpExport.append(event.getLongPar() + PJDUMP_SEPARATOR);
				pjdumpExport.append((event.getLongPar() - event.getTimestamp())
						+ PJDUMP_SEPARATOR);
				pjdumpExport.append(event.getDoublePar() + PJDUMP_SEPARATOR);
				pjdumpExport.append(event.getEventProducer().getName()
						+ PJDUMP_SEPARATOR);
				pjdumpExport.append(event.getDoublePar() + PJDUMP_SEPARATOR);
				break;

			default:
			}

			if (exportParameters)
				handleParameters(event);
			else {
				// remove last PJDUMP_SEPARATOR
				pjdumpExport.delete(
						pjdumpExport.length() - PJDUMP_SEPARATOR.length(),
						pjdumpExport.length());
			}

			// New line
			pjdumpExport.append(lineSeparator);

			countEvents++;
			if (countEvents % monitorCheck == 0) {
				/*
				 * monitor.worked(1); if (monitor.isCanceled() || stop) {
				 * writer.flush(); writer.close(); return; }
				 */
			}
		}

		public void cancel() {
			stop = true;
		}

	}

	public void writeEventProducers() {
		TraceDBObject traceDB;
		HashMap<Integer, EventProducer> idProducerIndex = new HashMap<Integer, EventProducer>();

		try {
			// Open the trace database
			traceDB = TraceDBObject.openNewInstance(trace.getDbName());

			// Get the producers
			EventProducerQuery pq = new EventProducerQuery(traceDB);
			List<EventProducer> producers = pq.getList();

			for (EventProducer ep : producers) {
				idProducerIndex.put(ep.getId(), ep);
			}

			for (EventProducer ep : producers) {
				pjdumpExport.append(CONTAINER + PJDUMP_SEPARATOR);

				// If root producer
				if (ep.getParentId() == EventProducer.NO_PARENT_ID) {
					pjdumpExport.append("0" + PJDUMP_SEPARATOR);
				} else {
					pjdumpExport.append(idProducerIndex.get(ep.getParentId())
							.getName() + PJDUMP_SEPARATOR);
				}

				pjdumpExport.append(ep.getType() + PJDUMP_SEPARATOR);
				pjdumpExport.append(trace.getMinTimestamp() + PJDUMP_SEPARATOR);
				pjdumpExport.append(trace.getMaxTimestamp() + PJDUMP_SEPARATOR);
				pjdumpExport.append(trace.getMaxTimestamp()
						- trace.getMinTimestamp() + PJDUMP_SEPARATOR);
				pjdumpExport.append(ep.getName());
				// New line
				pjdumpExport.append(lineSeparator);
			}
		} catch (SoCTraceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
