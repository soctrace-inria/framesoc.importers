package fr.inria.soctrace.tools.exporter.pjdump;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.exporter.pjdump.input.PajeDumpExporterInput;

public class PajeDumpExporter extends FramesocTool {

	private final static Logger logger = LoggerFactory
			.getLogger(PajeDumpExporter.class);

	private PluginImporterJob job;

	public PajeDumpExporter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long
	 * operation and we don't want to freeze the UI.
	 */
	public class PajeDumpExporterPluginJobBody implements IPluginToolJobBody {

		private PajeDumpExporterInput input;

		public PajeDumpExporterPluginJobBody(IFramesocToolInput input) {
			this.input = (PajeDumpExporterInput) input;
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException {
			DeltaManager delta = new DeltaManager();
			delta.start();
			monitor.setCanceled(false);
			List<Trace> traces = input.getTraces();
			DeltaManager traceDelta = new DeltaManager();
			for (Trace trace : traces) {

				try {

					logger.debug("Exporting " + trace);
					traceDelta.start();

					PajeDumpExportWriter exportWriter = new PajeDumpExportWriter(
							input.getDirectory());
					// Trace tr = input.getTraces().get(0);
					exportWriter.readTrace(trace, trace.getMinTimestamp(),
							trace.getMaxTimestamp());

					exportWriter.getWriterThread().join();

					if (monitor.isCanceled()) {
						logger.debug("Export cancelled through monitor ("
								+ trace + ")");
						break;
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (monitor.isCanceled())
						break;
					traceDelta.end("Import trace");
				}
			}
			delta.end("All traces imported");
		}
	}

	@Override
	public void launch(IFramesocToolInput input) {
		job = new PluginImporterJob("PJDump Exporter",
				new PajeDumpExporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();

	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {
		PajeDumpExporterInput pjdinput = (PajeDumpExporterInput) input;

		if (pjdinput.getTraces().isEmpty())
			return new ParameterCheckStatus(false,
					"You must select at least one trace.");

		// Check directory exist and is writable
		File f = new File(pjdinput.getDirectory());
		if (!f.isDirectory() || !f.canWrite()) {
			return new ParameterCheckStatus(false,
					"You must provide an existing and writtable directory.");
		}

		return new ParameterCheckStatus(true, "");
	}

}
