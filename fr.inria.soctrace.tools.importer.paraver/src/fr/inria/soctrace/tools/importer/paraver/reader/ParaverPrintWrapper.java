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
package fr.inria.soctrace.tools.importer.paraver.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.tools.management.ExternalProgramWrapper;

/**
 * Wrapper for the perl script converting paraver traces into pjdump.
 * 
 * @author "Damien Dosimont <damien.dosimont@imag.fr>"
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class ParaverPrintWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory.getLogger(ParaverPrintWrapper.class);

	/**
	 * Perl interpreter
	 */
	private static final String INTERPRETER = "perl";

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public ParaverPrintWrapper(List<String> arguments) {
		super(INTERPRETER, generateArgs(arguments));
	}

	private static List<String> generateArgs(List<String> arguments) {
		ArrayList<String> trueArguments = new ArrayList<String>();
		trueArguments.add(new ParaverPrintConfigManager().readPath());
		trueArguments.addAll(arguments);
		return trueArguments;
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
	public IStatus executeSync(final IProgressMonitor monitor) {
		logger.debug("Executing: {}", fCommand);
		ProcessBuilder pb = new ProcessBuilder(fCommand);
		int exitValue = 1;
		try {
			final Process p = pb.start();

			boolean exited = false;
			while (!exited) {
				try {
					if (monitor.isCanceled()) {
						p.destroy();
						return Status.CANCEL_STATUS;
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
			if (exitValue == 0) {
				return Status.OK_STATUS;
			} else {
				return Status.CANCEL_STATUS;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
