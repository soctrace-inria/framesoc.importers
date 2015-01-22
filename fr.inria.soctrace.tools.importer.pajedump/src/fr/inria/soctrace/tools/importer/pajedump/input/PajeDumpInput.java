/**
 * 
 */
package fr.inria.soctrace.tools.importer.pajedump.input;

import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpInput implements IFramesocToolInput {

	protected List<String> files;
	protected boolean doublePrecision;
	
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

	public boolean isDoublePrecision() {
		return doublePrecision;
	}

	public void setDoublePrecision(boolean doublePrecision) {
		this.doublePrecision = doublePrecision;
	}

	@Override
	public String toString() {
		return "PajeDumpInput [files=" + files + ", doublePrecision="
				+ doublePrecision + "]";
	}	
	
}
