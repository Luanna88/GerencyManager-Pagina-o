package so.scheduler;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import so.Process;
import so.SubProcess;
import so.SystemCallType;
import so.SystemOperation;
import so.cpu.Core;

public abstract class SchedulerQueue extends Scheduler {
    private PriorityQueue<Process> queue;
    private Hashtable<String, List<SubProcess>> subProcesses;

    public SchedulerQueue(Comparator<Process> comparator) {
        this.queue = new PriorityQueue<>(comparator);
        this.subProcesses = new Hashtable<>();
    }

    public PriorityQueue<Process> getQueue() {
        return queue;
    }

    public void execute(Process p) {
        List<SubProcess> sps = SystemOperation.systemCall(SystemCallType.READ_PROCESS, p);
        this.queue.add(p);
        this.subProcesses.put(p.getId(), sps);
        this.registerSubProcesses();
    }

    private void registerSubProcesses() {
        Process p = this.queue.peek();
        List<SubProcess> sps = this.subProcesses.get(p.getId());

        Core[] cores = this.getCm().getCores();
        for (Core core : cores) {
            if (core.getCurrentSubProcess() != null) {
                SubProcess sp = sps.get(0);
                this.getCm().registerProcess(core.getId(), sp);
            }
        }
    }

    public void finish(Process p) {
        boolean removed = queue.remove(p);

        if (removed) {
            List<SubProcess> sps = subProcesses.remove(p.getId());

            if (sps != null) {
                Core[] cores = getCm().getCores();
                for (Core core : cores) {
                    SubProcess currentSubProcess = core.getCurrentSubProcess();
                    if (currentSubProcess != null && sps.contains(currentSubProcess)) {
                        core.setCurrentSubProcess(null);
                    }
                }
            }
        }
    }
}

