package so;

import java.util.List;
import java.util.Objects;
import so.memory.MemoryManager;
import so.scheduler.FCFS;
import so.scheduler.Scheduler;

public class SystemOperation {
	public  static MemoryManager mm;
	 public static Scheduler scheduler ;
	 
	public static Process systemCall(SystemCallType type, int sizeInMemory){
		if (type.equals(SystemCallType.CREATE_PROCESS)) {
			if (Objects.isNull(mm)) {
				mm = new MemoryManager(256, 4);
			}
			if (Objects.isNull(scheduler)) {
				scheduler = new FCFS();
			}
		}
		return new Process (sizeInMemory);
	}
	
	public static List<SubProcess> systemCall(SystemCallType type, Process p) {
		if (type.equals(SystemCallType.WRITE_PROCESS)) {
			mm.write(p);
			scheduler.execute(p);
		}
		else if (type.equals(SystemCallType.CLOSE_PROCESS)) {
			mm.finish();
			scheduler.finish(p);
		}
		else if (type.equals(SystemCallType.READ_PROCESS)) {
			mm.read(p);
		}
		return null;
	}
}
