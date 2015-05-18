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

import fr.inria.soctrace.lib.model.utils.SoCTraceException;

/**
 * Interface for cachec line parsers
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 * @author "David Beniamine <David.Beniamine@imag.fr>"
 */
public interface CachecLineParser {
	
	/**
	 * Parse the line
	 * @param fields array containing line tokens
	 * @throws SoCTraceException 
	 */
	void parseLine(String[] fields) throws SoCTraceException;
	
}
