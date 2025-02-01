package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int step = 1000;
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opcounts = new AList<>();
        SLList<Integer> test = new SLList<>();
        int M = 1000;

        int i = 1;
        while (i <= 128000) {
            test.addLast(i);

            if (i == step) {
                ns.addLast(step);
                opcounts.addLast(M);
                Stopwatch sw = new Stopwatch();
                for (int j = 0; j < M;j ++) {
                    test.getLast();
                }
                times.addLast(sw.elapsedTime());
                step *= 2;
            }
            i ++;
        }
        printTimingTable(ns, times, opcounts);
    }

}
