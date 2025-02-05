package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void randomizedTest() {
        Comparator<Integer> comparator = new IntegerMaxComparator();
        ArrayDeque<Integer> aq = new ArrayDeque<>();
        MaxArrayDeque<Integer> maq = new MaxArrayDeque<>(comparator);

        int N = 128000;
        for (int i = 0; i < N ; i += 1) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                maq.addFirst(randVal);
                aq.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                maq.addLast(randVal);
                aq.addLast(randVal);
            } else if (operationNumber == 2) {
                assertEquals(maq.size(), aq.size());
            } else if (operationNumber == 3) {
                assertEquals(maq.isEmpty(), aq.isEmpty());
            } else if (operationNumber == 4) {
                if(maq.isEmpty()) { continue; }
                int maqVal = maq.removeFirst();
                int aqVal = aq.removeFirst();
                assertEquals(maqVal, aqVal);
            } else if (operationNumber == 5) {
                if(maq.isEmpty()) { continue; }
                int maqVal = maq.removeLast();
                int aqVal = aq.removeLast();
                assertEquals(maqVal, aqVal);
            } else if (operationNumber == 6) {
                if(maq.isEmpty()) { continue; }
                int index = maq.size() - 1;
                assertEquals(maq.get(index), aq.get(index));
            }
        }
    }
    @Test
    public void TestAdditionalMethod() {
        Comparator<Integer> maxComparator = new IntegerMaxComparator();
        MaxArrayDeque<Integer> maq = new MaxArrayDeque<>(maxComparator);

        maq.addFirst(1);
        maq.addLast(2);
        maq.addFirst(3);
        Integer max = 3;
        assertEquals(maq.max(), max);

    }
    private static class IntegerMaxComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            if(i1.compareTo(i2) > 0) {
                return 1;
            } else if (i1.compareTo(i2) == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
