package so;

public class Execute {
	
	public static void main (String[]args) {
		
		Process p1 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS,50);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1);
		
		System.out.println("\n****************************************************************************************************************\n");
		
		Process p2 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS,25);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2);
		
		System.out.println("\n****************************************************************************************************************\n");

		
		Process p3 = (Process)SystemOperation.systemCall(SystemCallType.CREATE_PROCESS,28);
		SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3);
		
	}

}