package Algorithm.LC.P346;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * LeetCode 346 – Moving Average from Data Stream
 * ============================================================
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given a stream of integers and a window size `k`,
 * calculate the moving average of all integers in the
 * sliding window.
 *
 * API:
 * ----
 * MovingAverage(int size)
 * double next(int val)
 *
 * Rules:
 * ------
 * - Window size is fixed
 * - If the number of elements is less than size,
 *   return the average of all seen elements
 *
 * Example:
 * --------
 * size = 3
 * next(1) -> 1.0
 * next(10)-> (1 + 10) / 2
 * next(3) -> (1 + 10 + 3) / 3
 * next(5) -> (10 + 3 + 5) / 3
 *
 * ============================================================
 *
 * ASCII DIAGRAM – SLIDING WINDOW
 *
 * Stream →   1     10      3      5
 * Window     [1]
 * Window     [1,10]
 * Window     [1,10,3]
 * Window           [10,3,5]
 *
 * ============================================================
 */
public class MovingAverage346 {

    // =============================
    // Configuration
    // =============================
    private final int size;

    private double sum;
    Queue<Integer> q;



    // =============================
    // Constructor
    // =============================
    public MovingAverage346(int size) {
        this.size = size;
        sum = 0;
        q = new LinkedList<>();
        // TODO: initialize data structures
    }

    // =============================
    // Core API (DO NOT IMPLEMENT)
    // =============================
    public double next(int val) {
        // TODO: interview implementation goes here
        q.offer(val);
        sum += val;
        if (q.size() > size) {
            sum -= q.poll();
        }

        return (double) (sum / q.size());
    }

    // =============================
    // Test Harness
    // =============================
    public static void main(String[] args) {

        MovingAverage346 movingAverage = new MovingAverage346(3);

        System.out.println("===== LC 346 Moving Average Tests =====");

        test(movingAverage.next(1),  1.0,  "next(1)");
        test(movingAverage.next(10), 5.5,  "next(10)");
        test(movingAverage.next(3),  4.6667, "next(3)");
        test(movingAverage.next(5),  6.0,  "next(5)");

        System.out.println("===== Tests Completed =====");
    }

    private static void test(double actual, double expected, String label) {
        if (Math.abs(actual - expected) > 0.0001) {
            System.err.println("❌ FAIL: " + label +
                    " | expected=" + expected +
                    " actual=" + actual);
        } else {
            System.out.println("✅ PASS: " + label);
        }
    }
}
