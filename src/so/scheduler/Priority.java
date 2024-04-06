package so.scheduler;

import java.util.Comparator;
import so.Process;

public class Priority extends SchedulerQueue {

	public Priority() {
		super(new Comparator<Process>() {

			@Override
			public int compare(Process p1, Process p2) {
				return p1.getPriorityType().getLevel() > p2.getPriorityType().getLevel()
						? 1 : -1;
			}
			
		});
	}
	
}
