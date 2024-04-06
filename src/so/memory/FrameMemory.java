package so.memory;

public class FrameMemory {
	
	private int pageNumber;
	private int offset;
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public FrameMemory(int pageNumber, int offset) {
		this.pageNumber = pageNumber;
		this.offset = offset;
	}
	
	public FrameMemory(int pageNumber) {
		this(pageNumber, 0);
	}
}
