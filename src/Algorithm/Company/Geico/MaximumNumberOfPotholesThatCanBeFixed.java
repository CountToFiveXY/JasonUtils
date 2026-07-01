package Algorithm.Company.Geico;

import java.util.PriorityQueue;

/**
 * LeetCode 3119 — Maximum Number of Potholes That Can Be Fixed
 *
 * Problem Summary:
 * ----------------
 * You are given a string `road` consisting of characters:
 *   - 'x' : a pothole
 *   - '.' : a normal road segment
 *
 * You are also given an integer `budget` representing the number of units
 * available to fix potholes.
 *
 * Rules:
 * - In one repair operation, you can repair n consecutive potholes for a price of n + 1.
 *
 * Return the maximum number of potholes that can be fixed such that the sum of the prices of all of the fixes doesn't go over the given budget.
 *
 *
 * Goal:
 * -----
 * Return the **maximum number of potholes** that can be fixed with the given budget.
 *
 * Diagram Example:
 *
 *  Input: road = "..", budget = 5
 *
 * Output: 0
 *
 * Explanation:
 *
 * There are no potholes to be fixed.
 *
 * Input: road = "x.x.xxx...x", budget = 14
 * Output: 6
 * Explanation: Multiple segments can be fixed within budget.
 * Notes:
 * - Greedy / sliding window style problem
 * - This is NOT a DP problem
 * - Focus on continuous 'x' segments
 */

public class MaximumNumberOfPotholesThatCanBeFixed {

    // =========================
    // METHOD STUB (NO SOLUTION)
    // =========================
    public static int maximumPotholes(String road, int budget) {
        // we count the consective segements
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a , b) -> b - a
        );
        int len = 0;
        for (int i = 0 ; i < road.length(); i++) {
            if (road.charAt(i) == 'x') {
                len++;
            } else {
                if (len > 0) {
                    pq.offer(len);
                }
                len = 0;
            }
        }

        if (len > 0) {
            pq.offer(len);
        }

        int res = 0;
        while(!pq.isEmpty()) {
            int curr = pq.poll();
            int cost = curr + 1;

            if (cost <= budget) {
                budget -= cost;
                res += curr;
            } else {
                if (budget > 1) {
                    res += budget - 1;
                }
            }
        }
        return res;
    }

    // =========================
    // TEST HARNESS
    // =========================
    public static void main(String[] args) {

        // ---------- Happy cases ----------
        runTest("Happy-1",
                "..xx.xxxx.x",
                5,
                4);

        runTest("Happy-2",
                "xxxx",
                3,
                2);

        // ---------- Edge cases ----------
        runTest("Edge-empty-road",
                "",
                10,
                0);

        runTest("Edge-no-potholes",
                "....",
                3,
                0);

        runTest("Edge-zero-budget",
                "xx.xx",
                0,
                0);

        // ---------- Bad / tricky ----------
        runTest("Bad-budget-too-small",
                "x.x.x",
                1,
                1);

        runTest("Bad-single-segment",
                "xxxxx",
                2,
                2);

        // ---------- Stress ----------
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('x');
        runTest("Stress-long-segment",
                sb.toString(),
                500,
                499);
    }

    // =========================
    // TEST RUNNER (NO THROW)
    // =========================
    private static void runTest(String name, String road, int budget, int expected) {
        int actual = maximumPotholes(road, budget);
        if (actual == expected) {
            System.out.printf("✅ %-25s Match (expected=%d, actual=%d)%n",
                    name, expected, actual);
        } else {
            System.out.printf("❌ %-25s Not Match (expected=%d, actual=%d)%n",
                    name, expected, actual);
        }
    }
}
