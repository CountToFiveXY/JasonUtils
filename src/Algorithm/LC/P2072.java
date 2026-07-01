package Algorithm.LC;

/**
 * P2072 — The Winner University
 *
 * Problem:
 * There are n students sitting in a circle.
 *
 * Each student starts with some number of candies.
 *
 * In each round:
 * - A student who still has candies gives ONE candy to the next student clockwise.
 * - Students with 0 candies do nothing.
 *
 * Return the index of the student who receives the last candy.
 *
 * ---------------------------------------------------
 * Example 1:
 *
 * Input:
 * candies = [5,1,2]
 * k = 4
 *
 * Output:
 * 0
 *
 * ---------------------------------------------------
 * Circle Diagram:
 *
 *      0 ----> 1
 *      ^       |
 *      |       v
 *      2 <-----
 *
 * ---------------------------------------------------
 * Constraints:
 *
 * 1 <= candies.length <= 1e5
 * 1 <= candies[i] <= 1e9
 * 1 <= k <= sum(candies)
 *
 * ---------------------------------------------------
 */

import java.util.*;

public class P2072 {

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("========== Normal Tests ==========");
        test(sol,
                new int[]{5, 1, 2},
                4,
                -1);

        test(sol,
                new int[]{2, 3, 4},
                5,
                -1);

        System.out.println("\n========== Tricky Tests ==========");
        test(sol,
                new int[]{1, 1, 1, 1},
                4,
                -1);

        test(sol,
                new int[]{100, 1, 1},
                50,
                -1);

        test(sol,
                new int[]{1, 100, 1},
                2,
                -1);

        System.out.println("\n========== Edge Tests ==========");
        test(sol,
                new int[]{1},
                1,
                -1);

        test(sol,
                new int[]{1, 1},
                1,
                -1);

        test(sol,
                new int[]{1, 1},
                2,
                -1);

        System.out.println("\n========== Stress Test ==========");
        stressTest(sol);
    }

    private static void test(Solution sol, int[] candies, int k, int expected) {

        long start = System.currentTimeMillis();

        int result = sol.solve(candies, k);

        long end = System.currentTimeMillis();

        boolean tle = (end - start > 2000);

        if (tle) {
            System.out.println(
                    "Input: candies=" + Arrays.toString(candies)
                            + ", k=" + k
                            + " => TLE ❌"
            );
            return;
        }

        System.out.println(
                "Input: candies=" + Arrays.toString(candies)
                        + ", k=" + k
                        + " | Expected: " + expected
                        + " | Result: " + result
                        + " => "
                        + (result == expected ? "Match ✅" : "Not Match ❌")
        );
    }

    private static void stressTest(Solution sol) {

        int n = 100_000;
        int[] candies = new int[n];

        Arrays.fill(candies, 1_000_000);

        int k = 1_000_000;

        long start = System.currentTimeMillis();

        int result = sol.solve(candies, k);

        long end = System.currentTimeMillis();

        if (end - start > 2000) {
            System.out.println("Stress Test => TLE ❌");
            return;
        }

        System.out.println(
                "Stress Test => Completed in "
                        + (end - start)
                        + "ms | Result = "
                        + result
        );
    }

    static class Solution {

        public int solve(int[] candies, int k) {

            // TODO: implement

            return -1;
        }
    }
}

/*
==================== HINT ====================

Observation:
- Students form a circle
- Each active student passes exactly one candy per turn
- Think carefully about:
    1. Total passes
    2. Circular traversal
    3. When a student becomes inactive

Possible approaches:
- Queue simulation
- Circular index simulation
- Greedy / math optimization

Try to avoid O(total candies) simulation if constraints are large.

================================================
*/