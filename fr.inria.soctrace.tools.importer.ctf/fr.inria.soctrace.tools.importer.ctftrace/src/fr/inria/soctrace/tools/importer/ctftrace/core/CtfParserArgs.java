/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Youenn Corre - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.ctftrace.core;

/**
 * Arguments for the parser.
 *
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class CtfParserArgs {
		
	/** The name of the System Database */
	public String sysDbName = null;
	
	/** The name of the Trace Database */
	public String traceDbNameSW = null;
	public String traceDbNameHW = null;
	
	/** Trace files vector. The first file must be the main one (CPU0) */
	public String[] traceFiles = null;
	
	/** Trace description (optional) */
	public String traceDescription = null;

}

