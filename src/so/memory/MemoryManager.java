package so.memory;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import so.SubProcess;
import so.Process;

public class MemoryManager {
    private int pageSize;
    private int sizeMemory;
    private SubProcess[][] physicalMemory;
    private Hashtable<String, FrameMemory> logicalMemory;
    
    public static int NUM_OF_INSTRUCTIONS_PER_SUB_PROCESS = 7;
    
    public MemoryManager(int sizeMemory, int pageSize) {
        this.pageSize = pageSize;
        this.sizeMemory = sizeMemory;
        
        int numOfPages= (int)Math.ceil((double) this.sizeMemory / this.pageSize);
        this.physicalMemory = new SubProcess[sizeMemory][pageSize];
        this.logicalMemory = new Hashtable<>();
    }
    
    public void write(Process p) {
        this.writeUsingPaging(p);
    }
        
    public void writeUsingPaging(Process p) {
        List<FrameMemory> frames = this.getFrames(p);
        if (frames != null) {
            for (int i = 0; i < frames.size(); i++) {
                for (int offset = 0; offset < this.pageSize; offset++) {
                    FrameMemory frame = frames.get(i);
                    
                    // Atualiza a memória física:
                    SubProcess sp = new SubProcess(p.getId(), NUM_OF_INSTRUCTIONS_PER_SUB_PROCESS);
                    this.physicalMemory[frame.getPageNumber()][offset] = sp;
                    
                    // Atualiza a memória lógica:
                    frame.setOffset(offset);
                    this.logicalMemory.put(sp.getId(), frame);
                }
            }
        } else {
            // Troca de páginas
        }
        SubProcess.count = 0;
        this.printMemoryStatus();
    }
    
    public List<SubProcess> read(Process p) {
        List<String> ids = p.getProcesses();
        List<SubProcess> sps = new LinkedList<>();
        for (String id : ids) {
            FrameMemory frame = this.logicalMemory.get(id);
            if (frame != null) {
                SubProcess sp = this.physicalMemory[frame.getPageNumber()][frame.getOffset()];
                if (sp != null) {
                    sps.add(sp);
                }
            }
        }
        return sps;
    }
    
    private List<FrameMemory> getFrames(Process p) {
        List<FrameMemory> frames = new LinkedList<>();
        int  numOfPages = (int)Math.ceil((double) this.sizeMemory / this.pageSize);
        for (int frame = 0; frame < this.physicalMemory.length; frame++) {
            if (this.physicalMemory[frame][0] == null) {
                frames.add(new FrameMemory(frame));
                if (frames.size() == numOfPages) {
                    return frames;
                }
            }
        }
        return null;
    }

    
    private void printMemoryStatus() {
        for (int i = 0; i < this.physicalMemory.length; i++) {
            for (int j = 0; j < this.pageSize; j++) {
                SubProcess sp = this.physicalMemory[i][j];
                String spId = null;
                if (sp != null) {
                    spId = sp.getId();
                }
                if (j == this.physicalMemory[i].length - 1) {
                    System.out.println(spId);
                } else {
                    System.out.print(spId + " | ");
                }
            }
        }
    }

    public void finish() {
        for (int i = 0; i < this.physicalMemory.length; i++) {
            for (int j = 0; j < this.pageSize; j++) {
                this.physicalMemory[i][j] = null;
            }
        }
        this.logicalMemory.clear();
        
        SubProcess.count = 0;
    }
}
