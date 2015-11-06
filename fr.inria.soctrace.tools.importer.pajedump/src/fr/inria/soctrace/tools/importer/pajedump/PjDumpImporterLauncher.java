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
package fr.inria.soctrace.tools.importer.pajedump;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.inria.soctrace.framesoc.core.tools.importers.TraceChecker;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.headless.launcher.HeadlessPluginLauncher;
import fr.inria.soctrace.tools.importer.pajedump.PajeDumpImporter;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;

/**
 * This class is used to launched the pj_dump importer from a command line
 * 
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class PjDumpImporterLauncher extends HeadlessPluginLauncher {
	
	@Override
	public void launch(String[] args) {
		PajeDumpImporter pjdImporter = new PajeDumpImporter();
		PajeDumpInput pjdInput = new PajeDumpInput();
		TraceChecker traceChecker = new TraceChecker();
		
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
			
			// Set up the inputs
			pjdInput.setFiles(line.getArgList());
			
			pjdInput.setDoublePrecision(line.hasOption("d"));
			pjdInput.setFlattenImbrication(line.hasOption("f"));

			if (line.hasOption("t")) {
				pjdInput.setPrecision(Integer.valueOf(line.getOptionValue("t")));
			} else {
				pjdInput.setPrecision(PajeDumpInput.DEFAULT_PRECISION_VALUE);
			}
			
			if (pjdInput.getFiles().isEmpty()) {
				System.out
						.println("Error: At least one input file must be provided.");
				printUsage();
				return;
			}

			pjdImporter.launch(pjdInput);

			PluginImporterJob job = pjdImporter.getJob();

			// Wait for the import to finish before exiting
			job.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			System.out
					.println("Error while parsing the arguments of the command line: "
							+ args.toString());
			e.printStackTrace();
			printUsage();
		}

		// Perform a check of the newly imported traces
		traceChecker.checkTraces(null);
	}

	@Override
	public void printUsage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("pjdump_importer [OPTION] FILE \n"
				+ "PJDump Importer imports a pjdump FILE into the database.",
				options);
	}

	@Override
	public void defineOptions() {
		options.addOption("d", "double-precision", false, "Use double precision (long is used as default)");
		options.addOption("t", "timestamp-shift", true, "Specify the shift in the timestamp decimal precision");
		options.addOption("f", "flatten-imbrication", false, "Flatten imbricated overlapping states in separate states");
		options.addOption("h", "help", false, "Print this help");
	}
}
