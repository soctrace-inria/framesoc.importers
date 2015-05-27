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
	
	public PajeInput() {
		super();
	}

	public String getArgumentLine() {
		return arguments;
	}

	public void setArgumentLine(String arguments) {
		this.arguments = arguments;
	}

	public String[] getArguments() {
		// Return an empty array instead of an array with an empty string which
		// make pj_dump buggy
		if (this.arguments == null || this.arguments.isEmpty())
			return new String[0];

		return this.arguments.split("\\s+");
	}

	@Override
	public String toString() {
		return "PajeInput [arguments=" + arguments + ", files=" + files + ", doublePrecision="
				+ doublePrecision + "]";
	}

}
