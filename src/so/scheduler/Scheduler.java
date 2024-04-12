package so.scheduler;

import so.cpu.CpuManager;
import so.Process;

public abstract class Scheduler {
    private CpuManager cm;

    public Scheduler() {
        cm = new CpuManager();
    }

    public CpuManager getCm() {
        return cm;
    }
    public abstract void execute (Process p);
    public abstract void finish (Process p);
	public abstract  boolean isEmpty();

	public abstract void scheduleNextProcess();
}
