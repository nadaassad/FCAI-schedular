import java.util.List;
import java.util.ArrayList;


public class Scheduler {

    protected List<Process> processList = new ArrayList<>();;
    protected List<Process> readyQueue = new ArrayList<>();
    protected int quantumTime;
    protected int contextSwitch;

    public Scheduler(List<Process> processList, int quantumTime, int contextSwitch) {
        this.processList = processList;
        this.quantumTime = quantumTime;
        this.contextSwitch = contextSwitch;
    }

    public void exec() {

    }
}
