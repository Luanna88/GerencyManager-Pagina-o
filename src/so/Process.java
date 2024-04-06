package so;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Process {
	
	private String id; 
	private int sizeInMemory;
	private List<String> processes;
	private int timeToExecute;
	private PriorityProcessType priorityType;
	public static int count;
	
	public Process (int sizeMemory) {
		count ++;
		
		Random rand = new Random();
		List<Integer> timeList = Arrays.asList(100,200,300,400,500,600,700,800,900,1000,10000);
		this.timeToExecute = timeList.get(rand.nextInt(timeList.size()));
		
		PriorityProcessType[] priorityList = PriorityProcessType.values();
		this.priorityType = priorityList[rand.nextInt(priorityList.length)];
		
		this.id = "P" + count;
		this.sizeInMemory = sizeMemory;
		this.processes = this.getProcesses();
		
	}

	public List<String> getProcesses() {
		if (this.processes == null) {
			this.processes = new LinkedList<>();
			for (int i = 0; i < this.sizeInMemory; i++) {
				String spId = this.getId() + i;
				this.processes.add(spId);
			}
		}
		return this.processes;
	}

	public String getId() {
		return id;
	}
	
	public int getSizeInMemory() {
		return sizeInMemory;
		
	}
	
	public void getSizeInMemory (int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}
	
	public int getTimeToExecute() {
		return timeToExecute;
	}
	
	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}

	public PriorityProcessType getPriorityType() {
		return priorityType;
	}

	public void setPriorityType(PriorityProcessType priorityType) {
		this.priorityType = priorityType;
	}
	
	
}