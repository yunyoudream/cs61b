package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> anr = new AListNoResizing<>();
        BuggyAList<Integer> ba = new BuggyAList<>();

        for (int i = 4; i <= 6; i++) {
            anr.addLast(i);
            ba.addLast(i);
        }

        assertEquals(anr.removeLast(), ba.removeLast());
        assertEquals(anr.removeLast(), ba.removeLast());
        assertEquals(anr.removeLast(), ba.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bsize = BL.size();
                assertEquals(size, bsize);
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                } else {
                    int lLast = L.getLast();
                    int blLast = BL.getLast();
                    assertEquals(lLast, blLast);
                    System.out.println("getLast(" + lLast + ")");
                }
            } else {
                if (L.size() == 0) {
                    continue;
                }
                else {
                    int lLast = L.removeLast();
                    int blLast = BL.removeLast();
                    assertEquals(lLast, blLast);
                    System.out.println("removeLast(" + lLast + ")");
                }
            }
        }
    }


}
