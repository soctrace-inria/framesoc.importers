/**
 * 
 */
package fr.inria.soctrace.tools.importer.paje.input;

import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeInput extends PajeDumpInput {

	/**
	 * Command line arguments for pj_dump
	 */
	protected String arguments;

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "PajeInput [arguments=" + arguments + ", files=" + files
				+ ", doublePrecision=" + doublePrecision + "]";
	}

}
