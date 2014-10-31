/*******************************************************************************
 * Copyright (c) 2012-2014 INRIA.
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
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.tools.management.ExternalProgramWrapper;
import fr.inria.soctrace.tools.importer.paje.Activator;

public class PajePrintWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory
			.getLogger(PajePrintWrapper.class);
	
	/**
	 * Configuration directory
	 */
	private final static String CONF_DIR = "configuration" + File.separator + Activator.PLUGIN_ID
			+ File.separator;

	/**
	 * Configuration file
	 */
	
	private static final String CMD_EXT = "pj_dump";
	private static final String CMD_INT = "exe" + File.separator + "pj_dump";

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public PajePrintWrapper(List<String> arguments) {
		super(readPath(), arguments);
	}

	/**
	 * Read the executable path from the configuration file
	 * 
	 * @return the executable path
	 */
	
	//Not used for the moment
	@SuppressWarnings("unused")
	private static String readPath() {
		try {
			// executable path
			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			Path path = new Path(CMD_INT);
			URL fileURL = FileLocator.find(bundle, path, null);
			String executablePath = FileLocator.resolve(fileURL).getPath().toString();
			return executablePath;
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;

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
	public IStatus executeSync(final IProgressMonitor monitor, File output) {
		logger.debug("Executing: {}", fCommand);
		ProcessBuilder pb = new ProcessBuilder(fCommand);
		pb.redirectOutput(output);
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
