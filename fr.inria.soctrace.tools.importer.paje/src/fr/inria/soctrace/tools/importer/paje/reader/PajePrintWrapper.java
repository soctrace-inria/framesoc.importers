package fr.inria.soctrace.tools.importer.paje.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import fr.inria.soctrace.tools.importer.paje.Activator;

/**
 * Wrapper for otf2-print program.
 * 
 * It looks for the otf2-print executable path in the configuration file
 * ./<eclipse.dir>/configuration/<plugin.name>/otf2-print.path.
 * 
 * If this file is not found, one is created with a default value, pointing to
 * the precompiled executable (./<plugin.name>/exe/otf2-print).
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajePrintWrapper extends ExternalProgramWrapper {

	private final static Logger logger = LoggerFactory.getLogger(PajePrintWrapper.class);

	
	private static final String CMD = "pj_dump";

	/**
	 * Constructor
	 * 
	 * @param arguments
	 *            program arguments
	 */
	public PajePrintWrapper(List<String> arguments) {
		super(CMD, arguments);
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
