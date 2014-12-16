package fr.inria.soctrace.tools.importer.pajedump.core;

import java.util.HashMap;
import java.util.Map;

import fr.inria.soctrace.framesoc.ui.colors.FramesocColor;

/**
 * Class containing default colors for PJDump traces.
 * 
 * XXX This class is just an example of how default colors can be specified by a parser. A better
 * solution would be to add the possibility to the user to specify a color file, in addition to
 * these defaults.
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
		ET_COLORS.put("MPI_COMM", new FramesocColor(30, 30, 30));
		ET_COLORS.put("MPI_Wait", new FramesocColor(222, 7, 0));
		ET_COLORS.put("MPI_Comm_rank", new FramesocColor(127, 127, 127));
		ET_COLORS.put("MPI_Init", new FramesocColor(255, 211, 0));
		ET_COLORS.put("MPI_Irecv", new FramesocColor(30, 144, 255));
		ET_COLORS.put("MPI_Bcast", new FramesocColor(165, 42, 42));
		ET_COLORS.put("MPI_Recv", new FramesocColor(0, 0, 255));
		ET_COLORS.put("MPI_Barrier", new FramesocColor(128, 0, 128));
		ET_COLORS.put("MPI_Comm_size", new FramesocColor(191, 191, 191));
		ET_COLORS.put("MPI_Send", new FramesocColor(0, 128, 0));
		ET_COLORS.put("MPI_Allreduce", new FramesocColor(139, 105, 20));
		ET_COLORS.put("MPI_Finalize", new FramesocColor(255, 192, 203));
		ET_COLORS.put("MPI_Reduce", new FramesocColor(255, 0, 223));
		

		// Event Producers
		EP_COLORS.put("rank1 (PROCESS:2)", new FramesocColor(255, 0, 0));
		EP_COLORS.put("rank2 (PROCESS:3)", new FramesocColor(0, 255, 0));
		EP_COLORS.put("rank3 (PROCESS:4)", new FramesocColor(0, 0, 255));

	}
}
