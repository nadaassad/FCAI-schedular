import java.util.*;

public class SRTF extends Scheduler {
    int time;
    int agingThresold;

    // priorityQueue to store processes with the highest aging priority and shortest remaining time
    PriorityQueue<Process> readyQueue;

    public SRTF(List<Process> processList, int quantumTime, int contextSwitch, int agingThresold) {
        super(processList, quantumTime, contextSwitch);
        time = 0;
        this.agingThresold = agingThresold;
        
        // comparator prioritizes aging priority first, then remaining time
        // prioritize by aging priority (higher first)
        // then by remaining time (shorter first)
        readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getAgingPriority).reversed().thenComparingInt(Process::getRemainingTime));      
    }

    public void exec() {
        processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        int nProcesses = processList.size();
        int index = 0;
        while (nProcesses > 0) {
            // add current arriving processes to the ready queue
            while (index < processList.size() && processList.get(index).getArrivalTime() == time) {
                readyQueue.add(processList.get(index));
                index++;
            }
            Process currentProcess = null;
            if (!readyQueue.isEmpty()) {
                
            
                // get the process with the highest aging priority and the shortest remaining time
                currentProcess = readyQueue.poll();
                Process previousProcess = currentProcess; 
                // execute a unit of time for the selected process
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                time++;

                // update waiting time for the remaining processes in the ready queue
                for (Process p : readyQueue) {
                    p.setWaitingTime(p.getWaitingTime() + 1); // don't except the current as it's removed when calling poll()
                }
                // after each quantum time, check if any process needs aging
                if (time % quantumTime == 0) {
                    for (Process p : readyQueue) {
                        if (p.getWaitingTime() >= agingThresold) {
                            p.setAgingPriority(p.getAgingPriority() + 1);  // increase priority if waiting exceeds threshold
                        }
                    }
                }
                if (currentProcess.getRemainingTime() == 0) {
                    int TAT = time - currentProcess.getArrivalTime();
                    currentProcess.setTurnAroundTime(TAT);
                    System.out.println(currentProcess);
                    nProcesses--;
                }
                // reinsert the current process into the queue if it hasn't finished
                else {
                    readyQueue.add(currentProcess);
                }

                if (currentProcess != previousProcess) {
                    time += contextSwitch;
                    for (Process p : readyQueue) {
                        p.setWaitingTime(p.getWaitingTime() + 1); // don't except the current as it's removed when calling poll()
                    }
                }
            }
        }
    }
}
