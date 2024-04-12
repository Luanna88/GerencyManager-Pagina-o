package so.scheduler;

import java.util.ArrayList;
import java.util.Random;
import so.Process;

public class Lottery extends Scheduler {
	private ArrayList<Process> processes;
    private ArrayList<Integer> tickets;

    public Lottery() {
        processes = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    @Override
    public void execute (Process p) {
        processes.add(p);

        Random rand = new Random();
        int numTickets = rand.nextInt(100) + 1; 
        tickets.add(numTickets);

        getCm().runProcess(p);
    }
    
    private Process selectProcessByTickets() {
        if (processes.isEmpty())
            return null;

        int totalTickets = 0;
        for (Integer ticket : tickets) {
            totalTickets += ticket;
        }

        Random rand = new Random();
        int winningTicket = rand.nextInt(totalTickets) + 1;

        int cumulativeTickets = 0;
        for (int i = 0; i < processes.size(); i++) {
            cumulativeTickets += tickets.get(i);
            if (winningTicket <= cumulativeTickets) {
                return processes.get(i);
            }
        }

        return null;
    }

    @Override
    public void scheduleNextProcess() {
        Process nextProcess = selectProcessByTickets();
        if (nextProcess != null) {
            getCm().runProcess(nextProcess);
        }
    }
   
    @Override
    public void finish(Process p) {
        int index = processes.indexOf(p);
        if (index != -1) {
            processes.remove(index);
            tickets.remove(index);
        }

        getCm().finishProcess(p);
    }
    
    
    @Override
	public boolean isEmpty() {
		return processes.isEmpty();
	}
}
