package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Double> tiems = new AList<>();
        AList<Integer> opcounts = new AList<>();
        AList<Integer> test = new AList<>();
        int step = 1000;
        Stopwatch sw = new Stopwatch();

        int i = 1;
        while (i <= 128000) {
            test.addLast(i);
            if (i == step) {
                ns.addLast(step);
                opcounts.addLast(step);
                tiems.addLast(sw.elapsedTime());
                step *= 2;
            }
            i ++;
        }
        printTimingTable(ns, tiems, opcounts);
    }
}
