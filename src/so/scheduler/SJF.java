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
	
}
