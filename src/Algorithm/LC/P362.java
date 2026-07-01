package Algorithm.LC;

import java.util.*;

/**
 * LeetCode 362 - Design Hit Counter
 *
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., 300 seconds).
 *
 * Implement the HitCounter class:
 *  - HitCounter() Initializes the object.
 *  - void hit(int timestamp) Records a hit at timestamp (in seconds).
 *  - int getHits(int timestamp) Returns the number of hits in the past 300 seconds.
 *
 * Constraints:
 *  - 1 <= timestamp <= 2 * 10^9
 *  - Calls are made in chronological order
 *
 * -------------------------------------------------------
 * Diagram (Sliding Window of 300 seconds):
 *
 * timeline →
 * [---valid window---]
 * t-299           t
 *
 * Example:
 * hits at: 1, 2, 3, 300, 301
 *
 * getHits(301):
 * valid window = [2 ... 301]
 * count = hits at 2,3,300,301 = 4
 *
 * -------------------------------------------------------
 */

public class P362 {

    static class HitCounter {

        public HitCounter() {

        }

        public void hit(int timestamp) {

        }

        public int getHits(int timestamp) {
            return -1;
        }
    }

    // ===================== Test Harness =====================
    public static void main(String[] args) {
        System.out.println("=== LeetCode 362 Hit Counter Tests ===");

        testNormal();
        testTricky();
        testEdge();
        testStress();
    }

    // -------- Normal Test --------
    private static void testNormal() {
        HitCounter counter = new HitCounter();

        counter.hit(1);
        counter.hit(2);
        counter.hit(3);

        int res = counter.getHits(4); // expect 3

        printResult("Normal", res == 3, res, 3);
    }

    // -------- Tricky Test --------
    private static void testTricky() {
        HitCounter counter = new HitCounter();

        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        counter.hit(300);

        int res1 = counter.getHits(300); // expect 4

        counter.hit(301);
        int res2 = counter.getHits(301); // expect 4 (1 expired)

        boolean ok = (res1 == 4 && res2 == 4);

        printResult("Tricky", ok, res1 + "," + res2, "4,4");
    }

    // -------- Edge Test --------
    private static void testEdge() {
        HitCounter counter = new HitCounter();

        int res1 = counter.getHits(1); // no hits → 0

        counter.hit(100);
        int res2 = counter.getHits(100); // 1

        boolean ok = (res1 == 0 && res2 == 1);

        printResult("Edge", ok, res1 + "," + res2, "0,1");
    }

    // -------- Stress Test (TLE detection) --------
    private static void testStress() {
        HitCounter counter = new HitCounter();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {
            counter.hit(i);
            counter.getHits(i);
        }

        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println("Stress: TLE (" + duration + " ms)");
        } else {
            System.out.println("Stress: Match (" + duration + " ms)");
        }
    }

    private static void printResult(String name, boolean match, Object actual, Object expected) {
        if (match) {
            System.out.println(name + ": Match");
        } else {
            System.out.println(name + ": Not Match | actual=" + actual + " expected=" + expected);
        }
    }
}

/**
 * ===================== HINT =====================
 *
 * Think about:
 *
 * 1. Sliding window of last 300 seconds
 * 2. You don't need to store ALL timestamps forever
 * 3. Data structures to consider:
 *    - Queue (remove outdated hits)
 *    - Fixed array of size 300 (circular buffer)
 *
 * Key idea:
 *   Only keep hits within [timestamp - 299, timestamp]
 *
 * Follow-up:
 *   How to optimize space if hits are extremely frequent?
 *
 */