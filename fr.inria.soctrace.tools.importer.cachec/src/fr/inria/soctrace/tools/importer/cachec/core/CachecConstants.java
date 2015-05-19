/*******************************************************************************
 * Copyright (c) 2012-2014 Generoso Pagano.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 *     David Beniamine - Adaptation from PjDump to HeapInfo
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.cachec.core;


/**
 * Constants for cachec parser
 * 
 * @author "David Beniamine <David.Beniamine@imag.fr>"
 */
public class CachecConstants {

	/**
	 * Field separator
	 */
	public static final String SEPARATOR = " ";

	/**
	 * Page size
	 */
	public static final int DB_PAGE_SIZE = 20000;




	//Structure file: name,start,size
	public static final int Field_Addr = 0;
	public static final int Field_EventType = 1;
	public static final int Field_Time = 2;


	// Root name
	public static final String ROOT_NAME = "Memory Root";

	/**
	 * Time shift exponent (nanoseconds)
	 */
	public static final int TIME_SHIFT = 9;

	public static final String TRACE_NAME = "cachec";

	public static final String TRACE_file = ".trace";
	public static final String EventType_file = ".events";

}
