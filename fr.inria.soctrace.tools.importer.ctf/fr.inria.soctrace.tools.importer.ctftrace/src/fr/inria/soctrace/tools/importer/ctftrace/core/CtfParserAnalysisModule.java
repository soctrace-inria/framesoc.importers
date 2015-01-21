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

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import fr.inria.linuxtools.statesystem.core.statevalue.ITmfStateValue;
import fr.inria.linuxtools.statesystem.core.statevalue.TmfStateValue;
import fr.inria.linuxtools.tmf.core.exceptions.TmfTraceException;
import fr.inria.linuxtools.tmf.core.statesystem.ITmfStateProvider;
import fr.inria.linuxtools.tmf.core.statesystem.TmfStateSystemAnalysisModule;

public class CtfParserAnalysisModule extends TmfStateSystemAnalysisModule {

	/**
	 * The file name of the History Tree
	 */
	public static final String HISTORY_TREE_FILE_NAME = "stateHistory.ht"; //$NON-NLS-1$
	static final String CONFIG_DOMAIN_TYPE_KERNEL = "KERNEL"; //$NON-NLS-1$
	static final String CONFIG_ELEMENT_EVENT = "event"; //$NON-NLS-1$
	static final String CONFIG_ELEMENT_DOMAIN = "domain"; //$NON-NLS-1$

	/** The ID of this analysis module */
	public static final String ID = "fr.inria.soctrace.tools.importer.ctftrace.core.analysis"; //$NON-NLS-1$

	private CtfParser theCtfParser;
	private CtfTraceSub theTrace;
	private File htFile;

	@Override
	protected ITmfStateProvider createStateProvider() {
		return new CtfParserStateProvider(theTrace, theCtfParser);
	}

	public CtfParserAnalysisModule(CtfTraceSub aTrace, CtfParser aParser,
			String aDirectory, File aFile) {
		theTrace = aTrace;
		theCtfParser = aParser;
		htFile = aFile;
		setId(ID);
	}

	@Override
	protected String getSsFileName() {
		return HISTORY_TREE_FILE_NAME;
	}

	public void performAnalysis(IProgressMonitor monitor) {
		executeAnalysis(monitor);
	}

	@Override
	protected boolean executeAnalysis(final IProgressMonitor monitor) {
		IProgressMonitor mon = (monitor == null ? new NullProgressMonitor()
				: monitor);
		final ITmfStateProvider provider = createStateProvider();
		String id = getId();

		try {
			createFullHistory(id, provider, htFile);
		} catch (TmfTraceException e) {
			return false;
		}
		return !mon.isCanceled();
	}
	
	// LttngStrings
    static final String EXIT_SYSCALL = "exit_syscall";
    static final String IRQ_HANDLER_ENTRY = "irq_handler_entry";
    static final String IRQ_HANDLER_EXIT = "irq_handler_exit";
    static final String SOFTIRQ_ENTRY = "softirq_entry";
    static final String SOFTIRQ_EXIT = "softirq_exit";
    static final String SOFTIRQ_RAISE = "softirq_raise";
    static final String SCHED_SWITCH = "sched_switch";
    static final String SCHED_WAKEUP = "sched_wakeup";
    static final String SCHED_WAKEUP_NEW = "sched_wakeup_new";
    static final String SCHED_PROCESS_FORK = "sched_process_fork";
    static final String SCHED_PROCESS_EXIT = "sched_process_exit";
    static final String SCHED_PROCESS_FREE = "sched_process_free";
    static final String STATEDUMP_PROCESS_STATE = "lttng_statedump_process_state";

    /* System call names */
    static final String SYSCALL_PREFIX = "sys_";
    static final String COMPAT_SYSCALL_PREFIX = "compat_sys_";
    static final String SYS_CLONE = "sys_clone";

    /* Field names */
    static final String IRQ = "irq";
    static final String COMM = "comm";
    static final String NAME = "name";
    static final String PID = "pid";
    static final String TID = "tid";
    static final String PPID = "ppid";
    static final String STATUS = "status";
    static final String VEC = "vec";
    static final String PREV_COMM = "prev_comm";
    static final String PREV_TID = "prev_tid";
    static final String PREV_STATE = "prev_state";
    static final String NEXT_COMM = "next_comm";
    static final String NEXT_TID = "next_tid";
    static final String PARENT_TID = "parent_tid";
    static final String CHILD_COMM = "child_comm";
    static final String CHILD_TID = "child_tid";
    
    // State value
    /* CPU Status */
    static final int CPU_STATUS_IDLE = 0;
    static final int CPU_STATUS_RUN_USERMODE = 1;
    static final int CPU_STATUS_RUN_SYSCALL = 2;
    static final int CPU_STATUS_IRQ = 3;
    static final int CPU_STATUS_SOFTIRQ = 4;

    static final ITmfStateValue CPU_STATUS_IDLE_VALUE = TmfStateValue.newValueInt(CPU_STATUS_IDLE);
    static final ITmfStateValue CPU_STATUS_RUN_USERMODE_VALUE = TmfStateValue.newValueInt(CPU_STATUS_RUN_USERMODE);
    static final ITmfStateValue CPU_STATUS_RUN_SYSCALL_VALUE = TmfStateValue.newValueInt(CPU_STATUS_RUN_SYSCALL);
    static final ITmfStateValue CPU_STATUS_IRQ_VALUE = TmfStateValue.newValueInt(CPU_STATUS_IRQ);
    static final ITmfStateValue CPU_STATUS_SOFTIRQ_VALUE = TmfStateValue.newValueInt(CPU_STATUS_SOFTIRQ);

    /* Process status */
    static final int PROCESS_STATUS_UNKNOWN = 0;
    static final int PROCESS_STATUS_WAIT_BLOCKED = 1;
    static final int PROCESS_STATUS_RUN_USERMODE = 2;
    static final int PROCESS_STATUS_RUN_SYSCALL = 3;
    static final int PROCESS_STATUS_INTERRUPTED = 4;
    static final int PROCESS_STATUS_WAIT_FOR_CPU = 5;

    static final ITmfStateValue PROCESS_STATUS_UNKNOWN_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_UNKNOWN);
    static final ITmfStateValue PROCESS_STATUS_WAIT_BLOCKED_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_WAIT_BLOCKED);
    static final ITmfStateValue PROCESS_STATUS_RUN_USERMODE_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_RUN_USERMODE);
    static final ITmfStateValue PROCESS_STATUS_RUN_SYSCALL_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_RUN_SYSCALL);
    static final ITmfStateValue PROCESS_STATUS_INTERRUPTED_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_INTERRUPTED);
    static final ITmfStateValue PROCESS_STATUS_WAIT_FOR_CPU_VALUE = TmfStateValue.newValueInt(PROCESS_STATUS_WAIT_FOR_CPU);

    /* SoftIRQ-specific stuff. -1: null/disabled, >= 0: running on that CPU */
    static final int SOFT_IRQ_RAISED = -2;

    static final ITmfStateValue SOFT_IRQ_RAISED_VALUE = TmfStateValue.newValueInt(SOFT_IRQ_RAISED);
}
