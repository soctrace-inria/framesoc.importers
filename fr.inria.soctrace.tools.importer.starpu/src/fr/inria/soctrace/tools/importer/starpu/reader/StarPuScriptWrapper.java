package fr.inria.soctrace.tools.importer.starpu.reader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StarPuScriptWrapper  extends StarPuWrapper {

	public StarPuScriptWrapper(List<String> arguments, String outputFilePath) {
		super(arguments);

		// Arguments for the script
		// $inputFile $outputFile $PathToPj_Dump
		List<String> scriptArguments = new LinkedList<String>();
		// -l -a
		List<String> scriptOptions = new LinkedList<String>();
		String pjDumpPath = new StarPuConfigManager().readPath();
		String scriptPath = new StarPuScriptConfigManager().readPath();

		// Trace file path should be in last position
		String filePath = arguments.get(arguments.size() - 1);

		scriptArguments.add(filePath);
		scriptArguments.add(outputFilePath);
		scriptArguments.add(pjDumpPath);
		
		// Export links as well
		scriptOptions.add("-l");
		// Keep whole trace not only State
		scriptOptions.add("-a");
		// For using simple trace files as input (not .org)   
		scriptOptions.add("-s");
		
		fCommand = new ArrayList<>(arguments.size() + 1);
		fCommand.add(scriptPath);
		// Options must be before arguments
		fCommand.addAll(scriptOptions);
		fCommand.addAll(scriptArguments);
	}

}
