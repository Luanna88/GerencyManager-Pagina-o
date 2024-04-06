package so;

public class SubProcess {
	private String id;
	private int instructions;
	public static int count;
	
	public SubProcess(String processId, int instructionsNumber) {
		this.id = processId + count;
		this.instructions = instructionsNumber;
		count ++;	
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getInstructions() {
		return instructions;
	}
	public void setInstructions(int instructions) {
		this.instructions = instructions;
	}
}