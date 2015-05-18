package fr.inria.soctrace.tools.importer.cachec.input;

import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;

public class CachecInput implements IFramesocToolInput {
	
	protected List<String> files;


	@Override
	public String getCommand() {
		return "";
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

}
