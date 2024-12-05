import java.util.Comparator;
import java.util.List;

public class PriorityScheduling extends Scheduler {

    public PriorityScheduling(List<Process> processList, int quantumTime, int contextSwitch) {
        super(processList, quantumTime, contextSwitch);
    }

    @Override
    public void exec() {
        System.out.println(" *********** << Non-preemptive  Priority Scheduling using context switching = 2 >> ************** ");
                
        int timeNow = 0;
        double average_w = 0;
        double average_t = 0;

        processList.sort(Comparator.comparingInt(Process::getArrivalTime)
                .thenComparingInt(Process::getPriority));

        for (int i = 0; i < processList.size(); i++) {
            Process process = processList.get(i);
            //make current time to = arrive time this already sorted  not count
            if (timeNow< process.getArrivalTime()) {
                timeNow = process.getArrivalTime();
            }
            //context
            timeNow = timeNow + 2;
            process.setWaitingTime( timeNow-process.getArrivalTime());
            process.setTurnAroundTime(process.getBurstTime() + process.getWaitingTime());

            timeNow += process.getBurstTime();
            average_w += process.getWaitingTime();
            average_t += process.getWaitingTime();
            System.out.println(process);
        }

        average_w = average_w / processList.size();
        average_t = average_t / processList.size();
        System.out.println("verage wating time is : " + average_w);
        System.out.println("verage turnAround Time time is : " + average_t);
    }
}
