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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.headless.launcher.HeadlessPluginLauncher;
import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.tools.exporter.pjdump.PajeDumpExporter;
import fr.inria.soctrace.tools.exporter.pjdump.input.PajeDumpExporterInput;

/**
 * This class is used to launched the pj_dump exporter from a command line
 * 
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class PjDumpExporterLauncher extends HeadlessPluginLauncher {

	List<Integer> traceId = new ArrayList<Integer>();
	
	@Override
	public void launch(String[] args) {
		PajeDumpExporter pjdExporter = new PajeDumpExporter();
		PajeDumpExporterInput pjdeInput = new PajeDumpExporterInput();

		List<String> argList = new ArrayList<String>(Arrays.asList(args));

		options = new Options();
		defineOptions();
		
		CommandLineParser parser = new DefaultParser();
		CommandLine line;
		try {
			line = parser.parse(options, args);

			// Check if asking for help
			if (line.hasOption("h")) {
				printUsage();
				return;
			}

			// Set up the input
			if (line.hasOption("e"))
				pjdeInput.setEndingTime(Long.valueOf(line.getOptionValue("e")));

			if (line.hasOption("s"))
				pjdeInput.setStartingTime(Long.valueOf(line.getOptionValue("s")));

			if (line.hasOption("d"))
				pjdeInput.setDirectory(line.getOptionValue("d"));
			
			if (line.hasOption("o"))
				pjdeInput.setOutPutFile(line.getOptionValue("o"));
			
			for (String aTraceID : line.getArgList()) {
				int id = Integer.valueOf(aTraceID);
				traceId.add(id);
			}
			
			pjdeInput.setTraces(getTracesFromId());

			if (pjdeInput.getTraces().isEmpty()) {
				System.out.println("Error: you must provide at least one trace to export.");
				printUsage();
				return;
			}
			
			if (pjdeInput.getDirectory().isEmpty()) {
				System.out.println("Error: you must provide an output directory.");
				printUsage();
				return;
			}

			pjdExporter.launch(pjdeInput);
			PluginImporterJob job = pjdExporter.getJob();

			// Wait for the import to finish before exiting
			job.join();
		} catch (ParseException e) {
			e.printStackTrace();
			System.out
					.println("Error while parsing the arguments of the command line: "
							+ argList);
			printUsage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the traces from their IDs
	 * 
	 * @return a collection of traces
	 */
	private List<Trace> getTracesFromId() {
		List<Trace> exportedTraces = new ArrayList<Trace>();
		loadTraces();

		for (Trace trace : traces) {
			if (traceId.contains(trace.getId())) {
				exportedTraces.add(trace);
			}
		}

		return exportedTraces;
	}

	@Override
	public void printUsage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter
				.printHelp(
						"pjdump_exporter [OPTION] TRACE_ID -d OUTPUT_DIRECTORY\n"
								+ "PJDump Exporter allows to export a trace designated by its TRACE_ID into pjdump format in the OUTPUT_DIRECTORY.",
						options);
	}

	@Override
	public void defineOptions() {
		options.addOption("s", "starting-date", true,
				"Starting time of the trace");
		options.addOption("e", "ending-date", true, "Ending time of the trace");
		options.addOption("o", "output-file", true, "Output file");
		options.addOption("d", "output-directory", true, "Output directory");
		options.addOption("h", "help", false, "Print this help");
	}
}
