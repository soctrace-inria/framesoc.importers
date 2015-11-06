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

/**
 * Class for the export of the trace in the framesoc database into the pjdump
 * format
 * 
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class PajeDumpExporter extends FramesocTool {

	private final static Logger logger = LoggerFactory
			.getLogger(PajeDumpExporter.class);

	private PluginImporterJob job;
	
	public PluginImporterJob getJob() {
		return job;
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

				logger.debug("Exporting " + trace);
				traceDelta.start();

				long startTimestamp = trace.getMinTimestamp();
				long endTimestamp = trace.getMaxTimestamp();
				if (input.getStartingTime() >= 0) {
					startTimestamp = Math.max(startTimestamp,
							input.getStartingTime());
				}
				if (input.getEndingTime() >= 0) {
					endTimestamp = Math
							.min(endTimestamp, input.getEndingTime());
				}
				
				PajeDumpExportWriter exportWriter = new PajeDumpExportWriter(
						input.getDirectory());
				exportWriter.readTrace(trace, startTimestamp,
						endTimestamp, monitor);

				if (monitor.isCanceled()) {
					logger.debug("Export cancelled through monitor (" + trace
							+ ")");
					break;
				}
				if (monitor.isCanceled())
					break;
				traceDelta.end("Import trace");
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

		// Check that at least one trace is selected
		if (pjdinput.getTraces().isEmpty())
			return new ParameterCheckStatus(false,
					"You must select at least one trace.");

		// Check that the directory exists and is writable
		File f = new File(pjdinput.getDirectory());
		if (!f.isDirectory() || !f.canWrite()) {
			return new ParameterCheckStatus(false,
					"You must provide an existing and writable directory.");
		}

		return new ParameterCheckStatus(true, "");
	}

}
