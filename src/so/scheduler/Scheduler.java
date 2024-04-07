package so.scheduler;

import so.cpu.CpuManager;
import so.Process;
import so.SubProcess;

public abstract class Scheduler {
    private CpuManager cm;

    public Scheduler() {
        cm = new CpuManager();
    }

    public void execute(Process p) {

    }

    public void finish(Process p) {
        
    }

    public CpuManager getCm() {
        return cm;
    }
}
