package fr.inria.soctrace.tools.importer.pajedump.core;

import java.util.HashMap;
import java.util.Map;

import fr.inria.soctrace.framesoc.ui.colors.FramesocColor;

/**
 * Class containing default colors for PJDump traces.
 * 
 * XXX This class is just an example of how default colors can be specified by a
 * parser. A better solution would be to add the possibility to the user to
 * specify a color file, in addition to these defaults.
 * 
 * TODO complete color lists
 * 
 * TODO color file passed by the user
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PJDumpColors {

	public static final Map<String, FramesocColor> ET_COLORS = new HashMap<>();
	public static final Map<String, FramesocColor> EP_COLORS = new HashMap<>();;

	// TODO: the list of colors is incomplete

	static {

		// Event Types
		ET_COLORS.put("MPI_Send", new FramesocColor(255, 0, 0));
		ET_COLORS.put("MPI_Receive", new FramesocColor(0, 255, 0));

		// Event Producers
		EP_COLORS.put("rank1 (PROCESS:2)", new FramesocColor(255, 0, 0));
		EP_COLORS.put("rank2 (PROCESS:3)", new FramesocColor(0, 255, 0));
		EP_COLORS.put("rank3 (PROCESS:4)", new FramesocColor(0, 0, 255));

	}
}
