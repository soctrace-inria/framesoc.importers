/**
 * 
 */
package fr.inria.soctrace.tools.importer.pajedump.input;

import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.lib.model.utils.ModelConstants.TimeUnit;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpInput implements IFramesocToolInput {

	protected List<String> files;
	protected boolean doublePrecision = true;
	protected int timeUnit = TimeUnit.UNKNOWN.getInt();
	protected int precision = 0;
	protected boolean flattenImbrication = false;
	
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

	public int getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(int timeUnit) {
		this.timeUnit = timeUnit;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public boolean isFlattenImbrication() {
		return flattenImbrication;
	}

	public void setFlattenImbrication(boolean flattenImbrication) {
		this.flattenImbrication = flattenImbrication;
	}

	@Override
	public String toString() {
		return "PajeDumpInput [files=" + files + ", doublePrecision="
				+ doublePrecision + ", Time unit="
				+ TimeUnit.getLabel(timeUnit) + ", flattenImbrication="
				+ flattenImbrication + "]";
	}
	
}
