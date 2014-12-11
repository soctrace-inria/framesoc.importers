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

