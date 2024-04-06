package so.scheduler;

import java.util.Comparator;
import so.Process;

public class FCFS extends SchedulerQueue {

	public FCFS() {
		super(new Comparator<Process>() {
			@Override
			public int compare(Process sp1, Process sp2) {
				return -1;
			}
			
		});
	}

}
