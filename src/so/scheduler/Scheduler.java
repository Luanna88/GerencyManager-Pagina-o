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
        for (int i = 0; i < cm.NUM_OF_CORES; i++) {
            if (cm.getCores()[i].getCurrentSubProcess() == null) {
                cm.registerProcess(i, p);
                return;
            }
        }
    }

    public void finish(Process p) {
        for (int i = 0; i < cm.NUM_OF_CORES; i++) {
            SubProcess currentSubProcess = cm.getCores()[i].getCurrentSubProcess();
            if (currentSubProcess != null && currentSubProcess.equals(p)) {
                cm.getCores()[i].setCurrentSubProcess(null);
                return;
            }
        }
    }

    public CpuManager getCm() {
        return cm;
    }
}
