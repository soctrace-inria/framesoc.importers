/**
 * 
 */
package fr.inria.soctrace.tools.importer.paje.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeInput extends PajeDumpInput {

	public static class PJDumpOpt {
		public String shortOpt;
		public String longOpt;
		public String comment;
		public boolean hasParam;
		public String arg;

		public PJDumpOpt(String s, String l, String c, boolean p) {
			this(s, l, c, p, "");
		}

		public PJDumpOpt(String s, String l, String c, boolean p, String a) {
			shortOpt = s;
			longOpt = l;
			comment = c;
			hasParam = p;
			arg = a;
		}

		@Override
		public String toString() {
			return shortOpt + ", " + longOpt + (hasParam ? ("=" + arg) : "") + " - " + comment;
		}
	}

	private static List<PJDumpOpt> os = new ArrayList<>();
	public static Map<String, PJDumpOpt> shorts = new HashMap<>();
	public static Map<String, PJDumpOpt> longs = new HashMap<>();

	static {
		os.add(new PJDumpOpt("-a", "--stop-at", "Stop the trace simulation at TIME", true, "TIME"));
		os.add(new PJDumpOpt("-e", "-end", "Dump ends at timestamp END (instead of EOF)", true,
				"END"));
		os.add(new PJDumpOpt("-f", "--flex", "Use flex-based file reader", false));
		os.add(new PJDumpOpt("-n", "--no-strict", "Support old field names in event definitions",
				false));
		os.add(new PJDumpOpt("-s", "--start", "Dump starts at timestamp START (instead of 0)",
				true, "START"));
		os.add(new PJDumpOpt("-u", "--user-defined", "Dump user-defined fields", false));
		os.add(new PJDumpOpt("-z", "--ignore-incomplete-links",
				"Ignore incomplete links (not recommended)", false));
		for (PJDumpOpt opt : os) {
			shorts.put(opt.shortOpt, opt);
			longs.put(opt.longOpt, opt);
		}
	}

	/**
	 * Command line arguments for pj_dump
	 */
	protected String arguments;

	public String getArgumentLine() {
		return arguments;
	}

	public void setArgumentLine(String arguments) {
		this.arguments = arguments;
	}

	public String[] getArguments() {
		return this.arguments.split("\\s+");
	}

	@Override
	public String toString() {
		return "PajeInput [arguments=" + arguments + ", files=" + files + ", doublePrecision="
				+ doublePrecision + "]";
	}

	public static String getDoc() {
		StringBuilder sb = new StringBuilder();
		for (PJDumpOpt opt : os) {
			sb.append(opt.toString() + "\n");
		}
		return sb.toString();
	}

	public static boolean correctOption(String s) {

		if (s.matches("(.+)=.*")) {
			String t[] = s.split("=");
			if (shorts.containsKey(t[0])) {
				if (!paramOk(shorts, t)) {
					return false;
				}
			} else if (longs.containsKey(t[0])) {
				if (!paramOk(longs, t)) {
					return false;
				}
			} else {
				return false;
			}
			return true;
		}

		return (shorts.containsKey(s) && !shorts.get(s).hasParam)
				|| (longs.containsKey(s) && !longs.get(s).hasParam);

	}

	private static boolean paramOk(Map<String, PJDumpOpt> map, String t[]) {
		if (!map.get(t[0]).hasParam) {
			return false;
		} else if (t.length < 2) {
			return false;
		}
		return true;
	}
}
