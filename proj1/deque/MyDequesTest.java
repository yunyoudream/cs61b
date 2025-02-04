package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyDequesTest {
    @Test
    public void randomizedTest() {
        int N = 128000;

        LinkedListDeque<Integer> lq = new LinkedListDeque<>();
        ArrayDeque<Integer> aq = new ArrayDeque<>();

        for (int i = 0; i < N ; i += 1) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                lq.addFirst(randVal);
                aq.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                lq.addLast(randVal);
                aq.addLast(randVal);
            } else if (operationNumber == 2) {
                assertEquals(lq.size(), aq.size());
            } else if (operationNumber == 3) {
                assertEquals(lq.isEmpty(), aq.isEmpty());
            } else if (operationNumber == 4) {
                if(lq.isEmpty()) { continue; }
                int lqVal = lq.removeFirst();
                int aqVal = aq.removeFirst();
                assertEquals(lqVal, aqVal);
            } else if (operationNumber == 5) {
                if(lq.isEmpty()) { continue; }
                int lqVal = lq.removeLast();
                int aqVal = aq.removeLast();
                assertEquals(lqVal, aqVal);
            } else if (operationNumber == 6) {
                if(lq.isEmpty()) { continue; }
                int index = lq.size() - 1;
                assertEquals(lq.get(index), aq.get(index));
            }
        }
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> aq = new ArrayDeque<>();
        aq.removeFirst();
        aq.removeLast();
        System.out.println(aq.size());
    }
}
