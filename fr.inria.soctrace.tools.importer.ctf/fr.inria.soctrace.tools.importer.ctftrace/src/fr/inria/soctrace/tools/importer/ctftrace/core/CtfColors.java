package fr.inria.soctrace.tools.importer.ctftrace.core;

import java.util.HashMap;
import java.util.Map;

import fr.inria.soctrace.framesoc.ui.colors.FramesocColor;

public class CtfColors {

	// Based on the color scheme of lttng (cf.
	// ControlFlowPresentationProvider.java & for
	// resources ResourcesPresentationProvider.java)
	public static final Map<String, FramesocColor> ET_COLORS = new HashMap<>();

	static {
		// Event Types
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_UNKNOWN,
				new FramesocColor(100, 100, 100));
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_WAIT_BLOCKED,
				new FramesocColor(200, 200, 0));
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_RUN_USERMODE,
				new FramesocColor(0, 200, 0));
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_RUN_SYSCALL,
				new FramesocColor(0, 0, 20));
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_INTERRUPTED,
				new FramesocColor(200, 0, 100));
		ET_COLORS.put(CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU,
				new FramesocColor(200, 100, 0));

		ET_COLORS.put(CtfParserConstants.CPU_STATUS_IDLE, new FramesocColor(
				200, 200, 200));
		ET_COLORS.put(CtfParserConstants.CPU_STATUS_RUN_USERMODE,
				new FramesocColor(0, 200, 0));
		ET_COLORS.put(CtfParserConstants.CPU_STATUS_RUN_SYSCALL,
				new FramesocColor(0, 0, 200));
		ET_COLORS.put(CtfParserConstants.CPU_STATUS_IRQ, new FramesocColor(200,
				0, 100));
		ET_COLORS.put(CtfParserConstants.CPU_STATUS_SOFTIRQ, new FramesocColor(
				200, 150, 100));

		ET_COLORS.put(CtfParserConstants.SOFT_IRQ_STATUS_ACTIVE,
				new FramesocColor(200, 150, 100));
		ET_COLORS.put(CtfParserConstants.SOFT_IRQ_STATUS_RAISED,
				new FramesocColor(200, 200, 0));
		ET_COLORS.put(CtfParserConstants.SOFT_IRQ_STATUS_EXIT,
				new FramesocColor(100, 100, 100));

		ET_COLORS.put(CtfParserConstants.IRQ_STATUS_ACTIVE, new FramesocColor(
				200, 0, 100));
		ET_COLORS.put(CtfParserConstants.IRQ_STATUS_EXIT, new FramesocColor(
				100, 100, 100));
		ET_COLORS.put(CtfParserConstants.LINK_TYPE, new FramesocColor(
				100, 100, 100));
	}
}
