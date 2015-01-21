/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.otf2.input;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class Otf2Input implements IFramesocToolInput {

	private String traceFile;
	private boolean importVariable = false;
	private boolean parseHierarchy = false;
	
	@Override
	public String getCommand() {
		return "";
	}

	public String getTraceFile() {
		return traceFile;
	}

	public void setTraceFile(String traceFile) {
		this.traceFile = traceFile;
	}

	public boolean isImportVariable() {
		return importVariable;
	}

	public void setImportVariable(boolean importVariable) {
		this.importVariable = importVariable;
	}

	public boolean isParseHierarchy() {
		return parseHierarchy;
	}

	public void setParseHierarchy(boolean parseHierarchy) {
		this.parseHierarchy = parseHierarchy;
	}

	@Override
	public String toString() {
		return "Otf2Input [traceFile=" + traceFile + ", importVariable=" + importVariable
				+ ", parseHierarchy=" + parseHierarchy + "]";
	}
	
}
