package fr.inria.soctrace.tools.importer.ctftrace;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.linuxtools.tmf.ctf.core.CtfTmfTrace;
import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;
import fr.inria.soctrace.tools.importer.ctftrace.core.CtfParserArgs;
import fr.inria.soctrace.tools.importer.ctftrace.core.CtfParserLauncher;

public class CtfTraceImporterTool extends FramesocTool {

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long
	 * operation and we don't want to freeze the UI.
	 */
	private class CtfTraceImporterPluginJobBody implements IPluginToolJobBody {

		private String args[];

		public CtfTraceImporterPluginJobBody(String[] args) {
			this.args = args;
		}

		@Override
		public void run(IProgressMonitor monitor) {

			System.out.println("Args: ");
			for (String s : args) {
				System.out.println(s);
			}

			if (args.length < 1) {
				System.err.println("Too few arguments");
				return;
			}

			String sysDbName = Configuration.getInstance().get(
					SoCTraceProperty.soctrace_db_name);
			String traceDbNameSW = FramesocManager.getInstance().getTraceDBName(
					"CTFTRACE_SW");
			String traceDbNameHW = FramesocManager.getInstance().getTraceDBName(
					"CTFTRACE_HW");

			// Arguments are given as files, but we only want directories
			List<String> l = getUniqueDirectories(args);

			for (int i = 0; i < args.length; ++i) {
				l.add(args[i]);
				File t = new File(args[i]);
				if (!t.exists()) {
					System.err.println("File " + args[i] + " not found");
					return;
				}
			}

			int numTraceFiles = l.size();
			String[] traceFiles = new String[numTraceFiles];
			Iterator<String> it = l.iterator();
			for (int i = 0; i < numTraceFiles; ++i) {
				traceFiles[i] = it.next();
			}

			CtfParserArgs ctfArgs = new CtfParserArgs();
			ctfArgs.sysDbName = sysDbName;
			ctfArgs.traceDbNameSW = traceDbNameSW;
			ctfArgs.traceDbNameHW = traceDbNameHW;
			ctfArgs.traceFiles = traceFiles;
			monitor.beginTask("Parsing trace in " + traceFiles[0], IProgressMonitor.UNKNOWN);
			new CtfParserLauncher().launch(ctfArgs, monitor);
			monitor.done();
		}
	}

	/**
	 * Extract unique directory paths from an array of file paths
	 * 
	 * @param theArgs
	 *            An array of file path as String
	 * @return A list of unique directories
	 */
	private ArrayList<String> getUniqueDirectories(String[] theArgs) {
		ArrayList<String> directories = new ArrayList<String>();
		for (int i = 0; i < theArgs.length; ++i) {
			String directory = theArgs[i].substring(0,
					theArgs[i].lastIndexOf("/") + 1);

			// Make sure it is unique
			if (!directories.contains(directory)) {
				directories.add(directory);
			}
		}

		return directories;
	}

	/**
	 * Check that the argument is a valid trace path and if it is valid, enable
	 * the importation
	 */
	public boolean canLaunch(String[] args) {
		List<String> traceDirectories = getUniqueDirectories(args);
		return checkForValideTraces(traceDirectories);
	}

	/**
	 * 
	 * @return
	 */
	private boolean checkForValideTraces(List<String> uniqueTraceDirectories) {
		boolean isValid = false;
		for (String aDirectory : uniqueTraceDirectories) {
			CtfTmfTrace aTrace = new CtfTmfTrace();

			isValid = aTrace.validate(null, aDirectory).isOK();

			aTrace.close();

			if (!isValid)
				return false;
		}

		return isValid;
	}

	@Override
	public void launch(String[] args) {
		PluginImporterJob job = new PluginImporterJob("CtfTrace Importer",
				new CtfTraceImporterPluginJobBody(args));
		job.setUser(true);
		job.schedule();
	}

}
