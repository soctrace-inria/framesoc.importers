/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Damien Dosimont, Generoso Pagano
 ******************************************************************************/

package fr.inria.soctrace.tools.importer.paje.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.tools.management.ExternalProgramWrapper;

/**
 * Paje Dump wrapper.
 * 
 * It looks for the pj_dump executable path in the configuration file
 * ./<eclipse.dir>/configuration/<plugin.name>/pj_dump.path.
 * 
 * If this file is not found, one is created with a default value, pointing to
 * the precompiled executable (./<plugin.name>/exe/pj_dump).
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory
			.getLogger(PajeDumpWrapper.class);

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public PajeDumpWrapper(List<String> arguments) {
		super(new PajeDumpConfigManager().readPath(), arguments);
	}

	/**
	 * Execute the external program.
	 * 
	 * @param monitor
	 *            progress monitor
	 * @param processor
	 *            line processor
	 * @return the execution status
	 */
	public int executeSync(final IProgressMonitor monitor, File output) {
		logger.debug("Executing: {}", fCommand);

		ProcessBuilder pb = new ProcessBuilder(fCommand);
		pb.redirectErrorStream(true);
		pb.redirectOutput(output);

		int exitValue = 1;
		try {
			boolean exited = false;
			final Process p = pb.start();

			while (!exited) {
				try {
					if (monitor.isCanceled()) {
						p.destroy();
						return 0;
					}
					exitValue = p.exitValue();
					exited = true;
				} catch (IllegalThreadStateException e) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			return exitValue;
		} catch (IOException e) {
			e.printStackTrace();
			return IStatus.ERROR;
		}
	}

}
