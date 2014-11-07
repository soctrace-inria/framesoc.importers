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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

/**
 * Paje Dump wrapper.
 * 
 * It looks for the pj_dump executable path in the configuration file
 * ./<eclipse.dir>/configuration/<plugin.name>/pj_dump.path.
 * 
 * If this file is not found, one is created with a default value, pointing to the precompiled
 * executable (./<plugin.name>/exe/pj_dump).
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory.getLogger(PajeDumpWrapper.class);

	/**
	 * Configuration directory
	 */
	private final static String CONF_DIR = "configuration" + File.separator + Activator.PLUGIN_ID
			+ File.separator;

	/**
	 * Configuration file
	 */
	private final static String CONF_FILE = CONF_DIR + "pj_dump.path";

	/**
	 * Default oft2-print executable location
	 */
	private static final String DEFAULT_PATH = "exe" + File.separator + "pj_dump";

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public PajeDumpWrapper(List<String> arguments) {
		super(readPath(), arguments);
	}

	/**
	 * Read the executable path from the configuration file
	 * 
	 * @return the executable path
	 */
	private static String readPath() {

		String eclipseDir = Platform.getInstallLocation().getURL().getPath();

		// configuration directory
		File dir = new File(eclipseDir + CONF_DIR);
		if (!dir.exists())
			dir.mkdir();

		// configuration file
		String absolutePath = eclipseDir + CONF_FILE;
		File file = new File(absolutePath);

		try {
			// executable path
			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			Path path = new Path(DEFAULT_PATH);
			URL fileURL = FileLocator.find(bundle, path, null);
			String executablePath = FileLocator.resolve(fileURL).getPath().toString();

			if (!file.exists()) {
				logger.debug("Configuration file not found. Create it: {}", absolutePath);
				System.err.println("Configuration file '" + absolutePath
						+ "' not found. Create it with default value (" + executablePath + ")");
				file.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(executablePath);
				bw.close();
			} else {
				logger.debug("Configuration file found: {}", absolutePath);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.equals(""))
						continue;
					if (line.startsWith("#"))
						continue;
					break;
				}
				br.close();
				executablePath = line;
			}
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
			e.printStackTrace();
			return null;
		}
	}

}
