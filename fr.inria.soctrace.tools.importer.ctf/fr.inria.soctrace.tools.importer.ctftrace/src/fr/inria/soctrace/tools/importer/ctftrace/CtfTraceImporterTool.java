package fr.inria.soctrace.tools.importer.ctftrace;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import fr.inria.linuxtools.tmf.ctf.core.CtfTmfTrace;
import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FileInput;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;
import fr.inria.soctrace.tools.importer.ctftrace.core.CtfParserArgs;
import fr.inria.soctrace.tools.importer.ctftrace.core.CtfParserLauncher;

public class CtfTraceImporterTool extends FramesocTool {

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long operation and we don't
	 * want to freeze the UI.
	 */
	private class CtfTraceImporterPluginJobBody implements IPluginToolJobBody {

		private FileInput input;

		public CtfTraceImporterPluginJobBody(IFramesocToolInput input) {
			this.input = (FileInput) input;
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException {

			try {
				List<String> files = input.getFiles();

				if (files.size() < 1) {
					System.err.println("Too few arguments");
					return;
				}

				String sysDbName = Configuration.getInstance().get(
						SoCTraceProperty.soctrace_db_name);
				String traceDbNameSW = FramesocManager.getInstance().getTraceDBName("CTFTRACE_SW");
				String traceDbNameHW = FramesocManager.getInstance().getTraceDBName("CTFTRACE_HW");

				// Arguments are given as files, but we only want directories
				Set<String> l = getUniqueDirectories(files);

				for (String s : files) {
					l.add(s);
					File t = new File(s);
					if (!t.exists()) {
						System.err.println("File " + s + " not found");
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
			} finally {
				monitor.done();
			}
		}
	}

	/**
	 * Extract unique directory paths from an array of file paths
	 * 
	 * @param theArgs
	 *            An array of file path as String
	 * @return A set of unique directories
	 */
	private Set<String> getUniqueDirectories(List<String> theArgs) {
		Set<String> directories = new HashSet<String>();
		for (String s : theArgs) {
			String directory = s.substring(0, s.lastIndexOf("/") + 1);
			// Make sure it is unique
			if (!directories.contains(directory)) {
				directories.add(directory);
			}
		}
		return directories;
	}

	/**
	 * Check that the argument is a valid trace path and if it is valid, enable the importation
	 */
	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {
		FileInput args = (FileInput) input;
		Set<String> traceDirectories = getUniqueDirectories(args.getFiles());
		ParameterCheckStatus status = new ParameterCheckStatus(false, "");
		for (String aDirectory : traceDirectories) {
			CtfTmfTrace aTrace = new CtfTmfTrace();
			IStatus validateStatus = aTrace.validate(null, aDirectory);
			status.valid = validateStatus.isOK();
			status.message = "";
			aTrace.close();

			if (!status.valid) {
				status.message = "Invalid trace or illegal arguments passed";
				return status;
			}
		}
		return status;
	}

	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("CtfTrace Importer",
				new CtfTraceImporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();
	}

}
