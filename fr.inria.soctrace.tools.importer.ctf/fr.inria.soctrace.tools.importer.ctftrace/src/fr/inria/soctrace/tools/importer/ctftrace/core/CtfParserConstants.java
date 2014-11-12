package fr.inria.soctrace.tools.importer.ctftrace.core;

public class CtfParserConstants {
	
	public static final String TRACE_TYPE = "CTFTrace.0.0";
	
	/** Attributes of the trace*/
	/* First-level attributes */
    static final String CPUS = "CPUs";
    static final String THREADS = "Threads";
    static final String RESOURCES = "Resources";

    /* Sub-attributes of the CPU nodes */
    static final String CURRENT_THREAD = "Current_thread";
    static final String STATUS = "Status";

    /* Sub-attributes of the Thread nodes */
    static final String PPID = "PPID";
    //static final String STATUS = "Status"
    static final String EXEC_NAME = "Exec_name";
    static final String SYSTEM_CALL = "System_call";

    /* Attributes under "Resources" */
    static final String IRQS = "IRQs";
    static final String SOFT_IRQS = "Soft_IRQs";

    /* Misc stuff */
    static final String UNKNOWN = "Unknown";
    
    // State Process Type 
    static final String PROCESS_STATUS_UNKNOWN = "PROCESS_STATUS_UNKNOWN";
    static final String PROCESS_STATUS_WAIT_BLOCKED = "PROCESS_STATUS_WAIT_BLOCKED";
    static final String PROCESS_STATUS_RUN_USERMODE = "PROCESS_STATUS_RUN_USERMODE";
    static final String PROCESS_STATUS_RUN_SYSCALL = "PROCESS_STATUS_RUN_SYSCALL";
    static final String PROCESS_STATUS_INTERRUPTED = "PROCESS_STATUS_INTERRUPTED";
    static final String PROCESS_STATUS_WAIT_FOR_CPU = "PROCESS_STATUS_WAIT_FOR_CPU";
   
    static final String CPU_STATUS_IDLE = "CPU_STATUS_IDLE";
    static final String CPU_STATUS_RUN_USERMODE = "CPU_STATUS_RUN_USERMODE";
    static final String CPU_STATUS_RUN_SYSCALL = "CPU_STATUS_RUN_SYSCALL";
    static final String CPU_STATUS_IRQ = "CPU_STATUS_IRQ";
    static final String CPU_STATUS_SOFTIRQ = "CPU_STATUS_SOFTIRQ";
   
}
