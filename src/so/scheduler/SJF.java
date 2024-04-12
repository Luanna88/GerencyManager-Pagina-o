package so.scheduler;

import java.util.Comparator;
import so.Process;

public class SJF extends SchedulerQueue{

	public SJF() {
		super(new Comparator<Process>() {

			@Override
			public int compare(Process sp1, Process sp2) {
				return sp1.getTimeToExecute() <= sp2.getTimeToExecute()
						?  1 : -1;
			}
		});
	}
	@Override
    public boolean isEmpty() {
        return getQueue().isEmpty();
    }

    @Override
    public void scheduleNextProcess() {
        if (!isEmpty()) {
            Process nextProcess = getQueue().poll(); 
        } else {
            System.out.println("A fila de processos está vazia."); 
        }
    }
}
