package fr.inria.soctrace.tools.importer.ctftrace.core;

import java.util.HashMap;
import fr.inria.linuxtools.tmf.ctf.core.CtfTmfEvent;
import fr.inria.linuxtools.tmf.ctf.core.CtfTmfTrace;
import fr.inria.linuxtools.tmf.core.event.ITmfEvent;
import fr.inria.linuxtools.tmf.core.event.ITmfEventField;
import fr.inria.linuxtools.statesystem.core.exceptions.AttributeNotFoundException;
import fr.inria.linuxtools.statesystem.core.exceptions.StateValueTypeException;
import fr.inria.linuxtools.statesystem.core.exceptions.TimeRangeException;
import fr.inria.linuxtools.tmf.core.statesystem.AbstractTmfStateProvider;
import fr.inria.linuxtools.tmf.core.statesystem.ITmfStateProvider;
import fr.inria.linuxtools.statesystem.core.statevalue.ITmfStateValue;
import fr.inria.linuxtools.statesystem.core.statevalue.TmfStateValue;

public class CtfParserStateProvider extends AbstractTmfStateProvider {

	// Event names HashMap
	private final HashMap<String, Integer> knownEventNames;
	private CtfParser ctfParser;

	public CtfParserStateProvider(CtfTmfTrace trace, CtfParser aCtfParser) {
		super(trace, CtfTmfEvent.class, "LTTng Kernel"); //$NON-NLS-1$
		knownEventNames = fillEventNames();
		ctfParser = aCtfParser;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	protected void eventHandle(ITmfEvent ev) {
		/*
		 * AbstractStateChangeInput should have already checked for the correct
		 * class type
		 */
		CtfTmfEvent event = (CtfTmfEvent) ev;

		int quark;
		ITmfStateValue value;
		CtfRecord aRecord = new CtfRecord();
		
		final ITmfEventField content = event.getContent();
		final String eventName = event.getEventName();
		final long timeStamp = event.getTimestamp().getValue();
		final long adjustedTimeStamp = event.getTimestamp().getValue() - ctfParser.getMinTimestamp();
		
		aRecord.cpu = event.getCPU();
		aRecord.type = eventName;
		aRecord.setTimestamp(adjustedTimeStamp);	
		aRecord.getParameters(content, event.getType());
		
		try {
			/* Shortcut for the "current CPU" attribute node */
			final Integer currentCPUNode = ss.getQuarkRelativeAndAdd(
					getNodeCPUs(), String.valueOf(event.getCPU()));
			
			/*
			 * Shortcut for the "current thread" attribute node. It requires
			 * querying the current CPU's current thread.
			 */
			quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
					CtfParserConstants.CURRENT_THREAD);
			value = ss.queryOngoingState(quark);
			
			// Get the thread ID
			int thread = value.unboxInt();
			final Integer currentThreadNode = ss.getQuarkRelativeAndAdd(
					getNodeThreads(), String.valueOf(thread));
	    		
			aRecord.pid = thread;

			// If the producer does not exist yet
			if (!ctfParser.producerExist(thread, true)) {
				// Create it
				int execNameQuark = -1;
				try {

					execNameQuark = ss.getQuarkRelative(currentThreadNode,
							CtfParserConstants.EXEC_NAME);

					int ppidQuark = ss.getQuarkRelative(currentThreadNode,
							CtfParserConstants.PPID);

					// Get its name
					String execName = ss.queryOngoingState(execNameQuark)
							.unboxStr();

					if (ppidQuark != -1) {
						// Get its parent id
						int ppid = ss.queryOngoingState(ppidQuark).unboxInt();
						ctfParser.addProducer(thread, ppid, execName);
					}

				} catch (AttributeNotFoundException e) {
					e.printStackTrace();
				} catch (StateValueTypeException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Feed event to the history system if it's known to cause a state
			 * transition.
			 */
			switch (getEventIndex(eventName)) {

			case 1: // "exit_syscall":
			/* Fields: int64 ret */
			{
				/* Clear the current system call on the process */
				quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
						CtfParserConstants.SYSTEM_CALL);
				value = TmfStateValue.nullValue();
				ss.modifyAttribute(timeStamp, value, quark);

				/* Put the process' status back to user mode */
				quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.PROCESS_STATUS_RUN_USERMODE_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.PROCESS_STATUS_RUN_USERMODE;
				ctfParser.setProcessState(aRecord, thread);

				/* Put the CPU's status back to user mode */
				quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.CPU_STATUS_RUN_USERMODE_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.CPU_STATUS_RUN_USERMODE;
				ctfParser.setCPUState(aRecord);
			}
				break;

			case 2: // "irq_handler_entry":
			/* Fields: int32 irq, string name */
			{

				Integer irqId = ((Long) content.getField(CtfParserAnalysisModule.IRQ)
						.getValue()).intValue();

				/*
				 * Mark this IRQ as active in the resource tree. The state value
				 * = the CPU on which this IRQ is sitting
				 */
				quark = ss.getQuarkRelativeAndAdd(getNodeIRQs(),
						irqId.toString());
				value = TmfStateValue.newValueInt(event.getCPU());
				ss.modifyAttribute(timeStamp, value, quark);

				/* Change the status of the running process to interrupted */
				quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.PROCESS_STATUS_INTERRUPTED_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				//Update Process State
				aRecord.type = CtfParserConstants.PROCESS_STATUS_INTERRUPTED;
				ctfParser.setProcessState(aRecord, thread);
				
				/* Change the status of the CPU to interrupted */
				quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.CPU_STATUS_IRQ_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.CPU_STATUS_IRQ;
				ctfParser.setCPUState(aRecord);
				
				//Update CPU State
				//aRecord.type = "CPU_STATUS_IRQ";
				//ctfParser.setProcessState(aRecord, aRecord.cpu);
			}
				break;

			case 3: // "irq_handler_exit":
			/* Fields: int32 irq, int32 ret */
			{
				Integer irqId = ((Long) content.getField(CtfParserAnalysisModule.IRQ)
						.getValue()).intValue();

				/* Put this IRQ back to inactive in the resource tree */
				quark = ss.getQuarkRelativeAndAdd(getNodeIRQs(),
						irqId.toString());
				value = TmfStateValue.nullValue();
				ss.modifyAttribute(timeStamp, value, quark);

				/* Set the previous process back to running */
				setProcessToRunning(timeStamp, currentThreadNode, aRecord);
				ctfParser.setProcessState(aRecord, thread);
				
				/* Set the CPU status back to running or "idle" */
				cpuExitInterrupt(timeStamp, currentCPUNode, currentThreadNode, aRecord);
				ctfParser.setCPUState(aRecord);
			}
				break;

			case 4: // "softirq_entry":
			/* Fields: int32 vec */
			{
				Integer softIrqId = ((Long) content.getField(CtfParserAnalysisModule.VEC)
						.getValue()).intValue();

				/*
				 * Mark this SoftIRQ as active in the resource tree. The state
				 * value = the CPU on which this SoftIRQ is processed
				 */
				quark = ss.getQuarkRelativeAndAdd(getNodeSoftIRQs(),
						softIrqId.toString());
				value = TmfStateValue.newValueInt(event.getCPU());
				ss.modifyAttribute(timeStamp, value, quark);

				/* Change the status of the running process to interrupted */
				quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.PROCESS_STATUS_INTERRUPTED_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.PROCESS_STATUS_INTERRUPTED;
				ctfParser.setProcessState(aRecord, thread);

				/* Change the status of the CPU to interrupted */
				quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.CPU_STATUS_SOFTIRQ_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.CPU_STATUS_SOFTIRQ;
				ctfParser.setCPUState(aRecord);
			}
				break;

			case 5: // "softirq_exit":
			/* Fields: int32 vec */
			{
				Integer softIrqId = ((Long) content.getField(CtfParserAnalysisModule.VEC)
						.getValue()).intValue();

				/* Put this SoftIRQ back to inactive (= -1) in the resource tree */
				quark = ss.getQuarkRelativeAndAdd(getNodeSoftIRQs(),
						softIrqId.toString());
				value = TmfStateValue.nullValue();
				ss.modifyAttribute(timeStamp, value, quark);

				/* Set the previous process back to running */
				setProcessToRunning(timeStamp, currentThreadNode, aRecord);
				ctfParser.setProcessState(aRecord, thread);
				
				/* Set the CPU status back to "busy" or "idle" */
				cpuExitInterrupt(timeStamp, currentCPUNode, currentThreadNode, aRecord);
				ctfParser.setCPUState(aRecord);
			}
				break;

			case 6: // "softirq_raise":
			/* Fields: int32 vec */
			{
				Integer softIrqId = ((Long) content.getField(CtfParserAnalysisModule.VEC)
						.getValue()).intValue();

				/*
				 * Mark this SoftIRQ as *raised* in the resource tree. State
				 * value = -2
				 */
				quark = ss.getQuarkRelativeAndAdd(getNodeSoftIRQs(),
						softIrqId.toString());
				value = CtfParserAnalysisModule.SOFT_IRQ_RAISED_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
			}
				break;

			case 7: // "sched_switch":
			/*
			 * Fields: string prev_comm, int32 prev_tid, int32 prev_prio, int64
			 * prev_state, string next_comm, int32 next_tid, int32 next_prio
			 */
			{
				Integer prevTid = ((Long) content.getField(
						CtfParserAnalysisModule.PREV_TID).getValue()).intValue();
				Long prevState = (Long) content.getField(
						CtfParserAnalysisModule.PREV_STATE).getValue();
				String nextProcessName = (String) content.getField(
						CtfParserAnalysisModule.NEXT_COMM).getValue();
				Integer nextTid = ((Long) content.getField(
						CtfParserAnalysisModule.NEXT_TID).getValue()).intValue();
				
				Integer formerThreadNode = ss.getQuarkRelativeAndAdd(
						getNodeThreads(), prevTid.toString());
				Integer newCurrentThreadNode = ss.getQuarkRelativeAndAdd(
						getNodeThreads(), nextTid.toString());

				/* Set the status of the process that got scheduled out. */
				quark = ss.getQuarkRelativeAndAdd(formerThreadNode,
						CtfParserConstants.STATUS);
				if (prevState != 0) {
					value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_BLOCKED_VALUE;
					aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_BLOCKED;
				} else {
					value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_FOR_CPU_VALUE;
					aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU;
				}
				ss.modifyAttribute(timeStamp, value, quark);
				
				ctfParser.setProcessState(aRecord, prevTid);

				/* Set the status of the new scheduled process */
				setProcessToRunning(timeStamp, newCurrentThreadNode, aRecord);	   
				ctfParser.setProcessState(aRecord, nextTid);
				
				/* Set the exec name of the new process */
				quark = ss.getQuarkRelativeAndAdd(newCurrentThreadNode,
						CtfParserConstants.EXEC_NAME);
				value = TmfStateValue.newValueString(nextProcessName);
				ss.modifyAttribute(timeStamp, value, quark);

				/*
				 * Make sure the PPID and system_call sub-CtfParserConstants
				 * exist
				 */
				ss.getQuarkRelativeAndAdd(newCurrentThreadNode,
						CtfParserConstants.SYSTEM_CALL);
				ss.getQuarkRelativeAndAdd(newCurrentThreadNode,
						CtfParserConstants.PPID);

				/* Set the current scheduled process on the relevant CPU */
				quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
						CtfParserConstants.CURRENT_THREAD);
				value = TmfStateValue.newValueInt(nextTid);
				ss.modifyAttribute(timeStamp, value, quark);

				/* Set the status of the CPU itself */
				if (nextTid > 0) {
					/* Check if the entering process is in kernel or user mode */
					quark = ss.getQuarkRelativeAndAdd(newCurrentThreadNode,
							CtfParserConstants.SYSTEM_CALL);
					if (ss.queryOngoingState(quark).isNull()) {
						value = CtfParserAnalysisModule.CPU_STATUS_RUN_USERMODE_VALUE;
						aRecord.type = CtfParserConstants.CPU_STATUS_RUN_USERMODE;
					} else {
						value = CtfParserAnalysisModule.CPU_STATUS_RUN_SYSCALL_VALUE;
						aRecord.type = CtfParserConstants.CPU_STATUS_RUN_SYSCALL;
					}
				} else {
					value = CtfParserAnalysisModule.CPU_STATUS_IDLE_VALUE;
					aRecord.type = CtfParserConstants.CPU_STATUS_IDLE;
				}
				quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
						CtfParserConstants.STATUS);
				ss.modifyAttribute(timeStamp, value, quark);

				ctfParser.setCPUState(aRecord);
			}
				break;

			case 8: // "sched_process_fork":
			/*
			 * Fields: string parent_comm, int32 parent_tid, string child_comm,
			 * int32 child_tid
			 */
			{
				// String parentProcessName = (String)
				// event.getFieldValue("parent_comm");
				String childProcessName = (String) content.getField(
						CtfParserAnalysisModule.CHILD_COMM).getValue();
				// assert ( parentProcessName.equals(childProcessName) );

				Integer parentTid = ((Long) content.getField(
						CtfParserAnalysisModule.PARENT_TID).getValue()).intValue();
				Integer childTid = ((Long) content.getField(
						CtfParserAnalysisModule.CHILD_TID).getValue()).intValue();

				Integer parentTidNode = ss.getQuarkRelativeAndAdd(
						getNodeThreads(), parentTid.toString());
				Integer childTidNode = ss.getQuarkRelativeAndAdd(
						getNodeThreads(), childTid.toString());

				/* Assign the PPID to the new process */
				quark = ss.getQuarkRelativeAndAdd(childTidNode,
						CtfParserConstants.PPID);
				value = TmfStateValue.newValueInt(parentTid);
				ss.modifyAttribute(timeStamp, value, quark);

				/* Set the new process' exec_name */
				quark = ss.getQuarkRelativeAndAdd(childTidNode,
						CtfParserConstants.EXEC_NAME);
				value = TmfStateValue.newValueString(childProcessName);
				ss.modifyAttribute(timeStamp, value, quark);

				/* Set the new process' status */
				quark = ss.getQuarkRelativeAndAdd(childTidNode,
						CtfParserConstants.STATUS);
				value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_FOR_CPU_VALUE;
				ss.modifyAttribute(timeStamp, value, quark);
				
				aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU;
				ctfParser.setProcessState(aRecord, childTid);

				/* Set the process' syscall name, to be the same as the parent's */
				quark = ss.getQuarkRelativeAndAdd(parentTidNode,
						CtfParserConstants.SYSTEM_CALL);
				value = ss.queryOngoingState(quark);
				if (value.isNull()) {
					/*
					 * Maybe we were missing info about the parent? At least we
					 * will set the child right. Let's suppose "sys_clone".
					 */
					value = TmfStateValue
							.newValueString(CtfParserAnalysisModule.SYS_CLONE);
				}
				quark = ss.getQuarkRelativeAndAdd(childTidNode,
						CtfParserConstants.SYSTEM_CALL);
				ss.modifyAttribute(timeStamp, value, quark);
			}
				break;

			case 9: // "sched_process_exit":
				/* Fields: string comm, int32 tid, int32 prio */
				break;

			case 10: // "sched_process_free":
			/* Fields: string comm, int32 tid, int32 prio */
			/*
			 * A sched_process_free will always happen after the sched_switch
			 * that will remove the process from the cpu for the last time. So
			 * this is when we should delete everything wrt to the process.
			 */
			{
				Integer tid = ((Long) content.getField(CtfParserAnalysisModule.TID)
						.getValue()).intValue();
				/*
				 * Remove the process and all its sub-CtfParserConstants from
				 * the current state
				 */
				quark = ss.getQuarkRelativeAndAdd(getNodeThreads(),
						tid.toString());
				ss.removeAttribute(timeStamp, quark);
			}
				break;

			case 11: // "lttng_statedump_process_state":
			/*
			 * Fields: int32 type, int32 mode, int32 pid, int32 submode, int32
			 * vpid, int32 ppid, int32 tid, string name, int32 status, int32
			 * vtid
			 */
			{
				Integer tid = ((Long) content.getField(CtfParserAnalysisModule.TID)
						.getValue()).intValue();
				int ppid = ((Long) content.getField(CtfParserAnalysisModule.PPID)
						.getValue()).intValue();
				int status = ((Long) content.getField(CtfParserAnalysisModule.STATUS)
						.getValue()).intValue();
				String name = (String) content.getField(CtfParserAnalysisModule.NAME)
						.getValue();
				
				//CTFParser addition
				//If not already added, add producer
				if (!ctfParser.producerExist(tid, true)) {
					//System.out.println("tid " + tid + ", ppid " + ppid	+ ", execname " + name);
					ctfParser.addProducer(tid, ppid, name);
				}

				/*
				 * "mode" could be interesting too, but it doesn't seem to be
				 * populated with anything relevant for now.
				 */

				int curThreadNode = ss.getQuarkRelativeAndAdd(getNodeThreads(),
						tid.toString());

				/* Set the process' name */
				quark = ss.getQuarkRelativeAndAdd(curThreadNode,
						CtfParserConstants.EXEC_NAME);
				if (ss.queryOngoingState(quark).isNull()) {
					/* If the value didn't exist previously, set it */
					value = TmfStateValue.newValueString(name);
					ss.modifyAttribute(timeStamp, value, quark);
				}

				/* Set the process' PPID */
				quark = ss.getQuarkRelativeAndAdd(curThreadNode,
						CtfParserConstants.PPID);
				if (ss.queryOngoingState(quark).isNull()) {
					value = TmfStateValue.newValueInt(ppid);
					ss.modifyAttribute(timeStamp, value, quark);
				}

				/* Set the process' status */
				quark = ss.getQuarkRelativeAndAdd(curThreadNode,
						CtfParserConstants.STATUS);
				if (ss.queryOngoingState(quark).isNull()) {
					/*
					 * "2" here means "WAIT_FOR_CPU", and "5" "WAIT_BLOCKED" in
					 * the LTTng kernel.
					 */
					if (status == 2) {
						value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_FOR_CPU_VALUE;
						aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU;
					} else if (status == 5) {
						value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_BLOCKED_VALUE;
						aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_BLOCKED;
					} else {
						value = CtfParserAnalysisModule.PROCESS_STATUS_UNKNOWN_VALUE;
						aRecord.type = CtfParserConstants.PROCESS_STATUS_UNKNOWN;
					}
					ss.modifyAttribute(timeStamp, value, quark);
					
					ctfParser.setProcessState(aRecord, tid);
				}
			}
				break;

			case 12: // "sched_wakeup":
			case 13: // "sched_wakeup_new":
			/*
			 * Fields (same fields for both types): string comm, int32 pid,
			 * int32 prio, int32 success, int32 target_cpu
			 */
			{
				final int tid = ((Long) content.getField(CtfParserAnalysisModule.TID)
						.getValue()).intValue();
				final int threadNode = ss.getQuarkRelativeAndAdd(
						getNodeThreads(), String.valueOf(tid));

				/*
				 * The process indicated in the event's payload is now ready to
				 * run. Assign it to the "wait for cpu" state, but only if it
				 * was not already running.
				 */
				quark = ss.getQuarkRelativeAndAdd(threadNode,
						CtfParserConstants.STATUS);
				int status = ss.queryOngoingState(quark).unboxInt();

				if (status != CtfParserAnalysisModule.PROCESS_STATUS_RUN_SYSCALL
						&& status != CtfParserAnalysisModule.PROCESS_STATUS_RUN_USERMODE) {
					value = CtfParserAnalysisModule.PROCESS_STATUS_WAIT_FOR_CPU_VALUE;
					ss.modifyAttribute(timeStamp, value, quark);
					
					aRecord.type = CtfParserConstants.PROCESS_STATUS_WAIT_FOR_CPU;
					ctfParser.setProcessState(aRecord, tid);
				}
			}
				break;

			default:
			/* Other event types not covered by the main switch */
			{
				if (eventName.startsWith(CtfParserAnalysisModule.SYSCALL_PREFIX)
						|| eventName
								.startsWith(CtfParserAnalysisModule.COMPAT_SYSCALL_PREFIX)) {
					/*
					 * This is a replacement for the old sys_enter event. Now
					 * syscall names are listed into the event type
					 */

					/* Assign the new system call to the process */
					quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
							CtfParserConstants.SYSTEM_CALL);
					value = TmfStateValue.newValueString(eventName);
					ss.modifyAttribute(timeStamp, value, quark);

					/* Put the process in system call mode */
					quark = ss.getQuarkRelativeAndAdd(currentThreadNode,
							CtfParserConstants.STATUS);
					value = CtfParserAnalysisModule.PROCESS_STATUS_RUN_SYSCALL_VALUE;
					ss.modifyAttribute(timeStamp, value, quark);
					aRecord.type = CtfParserConstants.PROCESS_STATUS_RUN_SYSCALL;
					ctfParser.setProcessState(aRecord, thread);

					/* Put the CPU in system call (kernel) mode */
					quark = ss.getQuarkRelativeAndAdd(currentCPUNode,
							CtfParserConstants.STATUS);
					value = CtfParserAnalysisModule.CPU_STATUS_RUN_SYSCALL_VALUE;
					ss.modifyAttribute(timeStamp, value, quark);
					
					aRecord.type = CtfParserConstants.CPU_STATUS_RUN_SYSCALL;
					ctfParser.setCPUState(aRecord);
				}
			}
				break;
			} // End of big switch
			
			//If the event type was modified during the exploration
			aRecord.type = eventName;
			ctfParser.newEvent(aRecord, true);

		} catch (AttributeNotFoundException ae) {
			/*
			 * This would indicate a problem with the logic of the manager here,
			 * so it shouldn't happen.
			 */
			ae.printStackTrace();

		} catch (TimeRangeException tre) {
			/*
			 * This would happen if the events in the trace aren't ordered
			 * chronologically, which should never be the case ...
			 */
			System.err
					.println("TimeRangeExcpetion caught in the state system's event manager."); //$NON-NLS-1$
			System.err
					.println("Are the events in the trace correctly ordered?"); //$NON-NLS-1$
			tre.printStackTrace();

		} catch (StateValueTypeException sve) {
			/*
			 * This would happen if we were trying to push/pop attributes not of
			 * type integer. Which, once again, should never happen.
			 */
			sve.printStackTrace();
		}
	}

	private static HashMap<String, Integer> fillEventNames() {
		/*
		 * TODO Replace with straight strings in the switch/case once we move to
		 * Java 7
		 */
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put(CtfParserAnalysisModule.EXIT_SYSCALL, 1);
		map.put(CtfParserAnalysisModule.IRQ_HANDLER_ENTRY, 2);
		map.put(CtfParserAnalysisModule.IRQ_HANDLER_EXIT, 3);
		map.put(CtfParserAnalysisModule.SOFTIRQ_ENTRY, 4);
		map.put(CtfParserAnalysisModule.SOFTIRQ_EXIT, 5);
		map.put(CtfParserAnalysisModule.SOFTIRQ_RAISE, 6);
		map.put(CtfParserAnalysisModule.SCHED_SWITCH, 7);
		map.put(CtfParserAnalysisModule.SCHED_PROCESS_FORK, 8);
		map.put(CtfParserAnalysisModule.SCHED_PROCESS_EXIT, 9);
		map.put(CtfParserAnalysisModule.SCHED_PROCESS_FREE, 10);
		map.put(CtfParserAnalysisModule.STATEDUMP_PROCESS_STATE, 11);
		map.put(CtfParserAnalysisModule.SCHED_WAKEUP, 12);
		map.put(CtfParserAnalysisModule.SCHED_WAKEUP_NEW, 13);

		return map;
	}

	@Override
	public ITmfStateProvider getNewInstance() {
		return new CtfParserStateProvider((CtfTmfTrace) this.getTrace(), ctfParser);
	}

	
    private int getNodeCPUs() {
        return ss.getQuarkAbsoluteAndAdd(CtfParserConstants.CPUS);
    }

    private int getNodeThreads() {
    	return ss.getQuarkAbsoluteAndAdd(CtfParserConstants.THREADS);
    }

    private int getNodeIRQs() {
        return ss.getQuarkAbsoluteAndAdd(CtfParserConstants.RESOURCES, CtfParserConstants.IRQS);
    }

    private int getNodeSoftIRQs() {
        return ss.getQuarkAbsoluteAndAdd(CtfParserConstants.RESOURCES, CtfParserConstants.SOFT_IRQS);
    }
    
    private int getEventIndex(String eventName) {
        Integer ret = knownEventNames.get(eventName);
        return (ret != null) ? ret : -1;
    }
    
    // ------------------------------------------------------------------------
    // Advanced state-setting methods
    // ------------------------------------------------------------------------

    /**
     * When we want to set a process back to a "running" state, first check
     * its current System_call attribute. If there is a system call active, we
     * put the process back in the syscall state. If not, we put it back in
     * user mode state.
     */
    private void setProcessToRunning(long ts, int currentThreadNode, CtfRecord aRecord)
            throws AttributeNotFoundException, TimeRangeException,
            StateValueTypeException {
        int quark;
        ITmfStateValue value;

        quark = ss.getQuarkRelativeAndAdd(currentThreadNode, CtfParserConstants.SYSTEM_CALL);
        if (ss.queryOngoingState(quark).isNull()) {
            /* We were in user mode before the interruption */
            value = CtfParserAnalysisModule.PROCESS_STATUS_RUN_USERMODE_VALUE;
         	aRecord.type = CtfParserConstants.PROCESS_STATUS_RUN_USERMODE;
        } else {
            /* We were previously in kernel mode */
            value = CtfParserAnalysisModule.PROCESS_STATUS_RUN_SYSCALL_VALUE;
         	aRecord.type = CtfParserConstants.PROCESS_STATUS_RUN_SYSCALL;
        }

        quark = ss.getQuarkRelativeAndAdd(currentThreadNode, CtfParserConstants.STATUS);
        ss.modifyAttribute(ts, value, quark);
    }

    /**
     * Similar logic as above, but to set the CPU's status when it's coming out
     * of an interruption.
     */
    private void cpuExitInterrupt(long ts, int currentCpuNode, int currentThreadNode, CtfRecord aRecord)
            throws StateValueTypeException, AttributeNotFoundException,
            TimeRangeException {
        int quark;
        ITmfStateValue value;

        quark = ss.getQuarkRelativeAndAdd(currentCpuNode, CtfParserConstants.CURRENT_THREAD);
        if (ss.queryOngoingState(quark).unboxInt() > 0) {
            /* There was a process on the CPU */
            quark = ss.getQuarkRelative(currentThreadNode, CtfParserConstants.SYSTEM_CALL);
            if (ss.queryOngoingState(quark).isNull()) {
                /* That process was in user mode */
                value = CtfParserAnalysisModule.CPU_STATUS_RUN_USERMODE_VALUE;
				aRecord.type = CtfParserConstants.CPU_STATUS_RUN_USERMODE;
            } else {
                /* That process was in a system call */
                value = CtfParserAnalysisModule.CPU_STATUS_RUN_SYSCALL_VALUE;
                aRecord.type = CtfParserConstants.CPU_STATUS_RUN_SYSCALL;
            }
        } else {
            /* There was no real process scheduled, CPU was idle */
            value = CtfParserAnalysisModule.CPU_STATUS_IDLE_VALUE;
            aRecord.type = CtfParserConstants.CPU_STATUS_IDLE;
        }
        quark = ss.getQuarkRelativeAndAdd(currentCpuNode, CtfParserConstants.STATUS);
        ss.modifyAttribute(ts, value, quark);
    }
}
