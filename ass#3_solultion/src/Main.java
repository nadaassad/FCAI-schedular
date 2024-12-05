import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Process> processes = new ArrayList<>();

//        processes.add(new Process("P1", 0, 5, 3));
//        processes.add(new Process("P2", 0, 4, 2));
//        processes.add(new Process("P3", 4, 8, 2));
//        processes.add(new Process("P4", 6, 6, 4));
//        processes.add(new Process("P5", 2, 3, 1));
//        processes.add(new Process("P6", 1, 9, 2));
//        processes.add(new Process("P7", 1, 7, 3));
//        processes.add(new Process("P8", 5, 2, 5));
//        processes.add(new Process("P9", 7, 10, 4));
//        processes.add(new Process("P10", 9, 1, 5));

        processes.add(new Process("P1", 0, 17, 4, 4));
        processes.add(new Process("P2", 3, 6, 9, 3));
        processes.add(new Process("P3", 4, 10, 3, 5));
        processes.add(new Process("P4", 29, 4, 8, 2));


        Process.calculateV1(processes);
        Process.calculateV2(processes);


//        Scheduler SRTF = new SRTF(processes, 2, 2, 6);
//        SRTF.exec();
    }
}
