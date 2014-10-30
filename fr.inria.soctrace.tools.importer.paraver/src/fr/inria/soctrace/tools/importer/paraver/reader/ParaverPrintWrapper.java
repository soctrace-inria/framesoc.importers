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

package fr.inria.soctrace.tools.importer.paraver.reader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import fr.inria.soctrace.tools.importer.paraver.Activator;


public class ParaverPrintWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory.getLogger(ParaverPrintWrapper.class);

	
	private static final String CMD = "perl";
	/**
	 * Default perl script executable location
	 */
	private static final String SCRIPT_PATH = "perl" + File.separator + "prv2pjdump.pl";

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public ParaverPrintWrapper(List<String> arguments) {
		super(CMD, generateArgs(arguments));
	}

	private static List<String> generateArgs(List<String> arguments) {
		ArrayList<String> arguments2 =new ArrayList<String>();
		arguments2.add(readPath());
		arguments2.addAll(arguments);
		return arguments2;
	}

	/**
	 * Read the executable path from the configuration file
	 * 
	 * @return the executable path
	 */
	private static String readPath() {
			// executable path
			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			Path path = new Path(SCRIPT_PATH);
			URL fileURL = FileLocator.find(bundle, path, null);
			String executablePath = null;
			try {
				executablePath = FileLocator.resolve(fileURL).getPath().toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return executablePath;

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
					if (monitor.isCanceled()){
						p.destroy();
						return Status.CANCEL_STATUS;
					}
					exitValue=p.exitValue();
					exited = true;
				} catch (IllegalThreadStateException e) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			if (exitValue==0){
				return Status.OK_STATUS;
			}else{
				return Status.CANCEL_STATUS;
			}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return null;
			}
	}

}
