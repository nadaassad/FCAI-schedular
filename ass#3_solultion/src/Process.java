import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Process {

    private String name;
    private int ArrivalTime;
    private int burstTime;
    private int waitingTime; 
    private int turnAroundTime;
    private int quantum;
    private int remainingTime;
    private int piriority;
    private int agingPriority;

    public Process (String name, int ArrivalTime, int burstTime, int priority, int quantum) {
        this.name = name;
        this.ArrivalTime = ArrivalTime;
        this.burstTime = burstTime;
        this.piriority = priority;
        this.remainingTime = burstTime;
        this.quantum = quantum;
        turnAroundTime = 0;
        waitingTime = 0;
        agingPriority = 0;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
    public void setAgingPriority(int agingPriority) {
        this.agingPriority = agingPriority;
    }

    public int getArrivalTime() { 
        return ArrivalTime; 
    }
    public int getBurstTime() {
        return burstTime;
    }
    public int getWaitingTime() {
        return waitingTime;
    }
    public int getTurnAroundTime() {
        return turnAroundTime;
    }
    public int getRemainingTime() {
        return remainingTime;
    }
    public int getPriority() {
        return piriority; 
    }
    public int getAgingPriority() {
        return agingPriority;
    }


    @Override
    public String toString() {
        return "Process: " + name + ", " + "Arrival  Time: " + ArrivalTime + ", " + "Burst Time: " + burstTime + ", " + "Waiting Time: " + waitingTime + ", " + "TurnAround Time: " + turnAroundTime + ", " + "Priority: "+ piriority + "\n";
    }

    public static double calculateV1(List<Process>processes){
        /*double lastArrivalTime = processes.stream().mapToInt(p->p.ArrivalTime).max().orElse(1);
        lastArrivalTime = lastArrivalTime/10;
        System.out.println(lastArrivalTime);
        return lastArrivalTime;*/
        List<Process> copy =new ArrayList<>(processes);
        copy.sort(Comparator.comparingInt(p->p.ArrivalTime));
        double lastArrivalTime = copy.get(copy.size()-1).ArrivalTime;
        System.out.println(lastArrivalTime/10);
        return lastArrivalTime/10;
    }

    public static double calculateV2(List<Process>processes){
        /*double maxBurstTime = processes.stream().mapToInt(p->p.burstTime).max().orElse(1);
        maxBurstTime = maxBurstTime/10;
        System.out.println(maxBurstTime);
        return maxBurstTime;*/
        List<Process> copy =new ArrayList<>(processes);
        copy.sort(Comparator.comparingInt(p->p.burstTime));
        double maxBurstTime = copy.get(copy.size()-1).burstTime;
        System.out.println(maxBurstTime/10);
        return maxBurstTime/10;
    }

    public static void calculateFCAILFactor(List<Process>processes ){
        double v1 = calculateV1(processes);
        double v2 = calculateV2(processes) ;
        for (Process process : processes) {
            double factor = (10 - process.piriority) + (process.ArrivalTime / v1) + (process.remainingTime / v2);
        }

    }
}
