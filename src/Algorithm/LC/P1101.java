package Algorithm.LC;

import java.util.Arrays;

/**
 * LC 1101 — The Earliest Moment When Everyone Become Friends
 *
 * Problem:
 * There are n people labeled from 0 to n - 1.
 * Given an array logs where logs[i] = [timestamp, x, y]
 * indicates that person x and person y became friends at time timestamp.
 *
 * Friendship is transitive:
 * If A is friend with B, and B is friend with C,
 * then A is friend with C.
 *
 * Return the earliest time for which all people are connected.
 * If impossible, return -1.
 *
 * Example:
 * Input:
 * logs = [
 *   [20190101,0,1],
 *   [20190104,3,4],
 *   [20190107,2,3],
 *   [20190211,1,5],
 *   [20190224,2,4],
 *   [20190301,0,3],
 *   [20190312,1,2]
 * ]
 * n = 6
 *
 * Output: 20190301
 *
 *
 * Concept Diagram:
 *
 * Initially:
 * 0   1   2   3   4   5
 *
 * After unions:
 *
 * 0 — 1 — 5
 * |         \
 * 3 — 4 — 2
 *
 * All connected at 20190301
 *
 *
 * Constraints:
 * 2 <= n <= 10^5
 * 1 <= logs.length <= 10^5
 *
 * Expected:
 * Efficient solution should be near O(n log n)
 *
 */

public class P1101 {

    // =====================================================
    // 🚫 DO NOT IMPLEMENT (Skeleton Only)
    // =====================================================

    static class UnionFind {
        int count;
        int[] parents;

        UnionFind(int n) {
            count = n;
            parents = new int[n];
            for (int i =0 ; i< n; i++) {
                parents[i] = i;
            }
        }

        int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }

        void union(int a, int b) {
            int p1 = find(a), p2 = find(b);

            if (p1 != p2) {
                parents[p1] = parents[p2];
                count--;
            }
        }

    }
    public static int earliestAcq(int[][] logs, int n) {

        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        for (int[] log: logs) {
            uf.union(log[1], log[2]);

            if (uf.count == 1) {
                return log[0];
            }
        }
        // TODO: Implement solution
        return -1; // placeholder
    }

    // =====================================================
    // 🧪 Test Harness
    // =====================================================
    public static void main(String[] args) {

        // -------------------------
        // ✅ Happy Case
        // -------------------------
        int[][] logs1 = {
                {20190101,0,1},
                {20190104,3,4},
                {20190107,2,3},
                {20190211,1,5},
                {20190224,2,4},
                {20190301,0,3},
                {20190312,1,2}
        };
        runTest("Happy 1", logs1, 6, 20190301);

        // -------------------------
        // ❌ Impossible Case
        // -------------------------
        int[][] logs2 = {
                {1,0,1},
                {2,2,3}
        };
        runTest("Impossible", logs2, 4, -1);

        // -------------------------
        // 🟡 Edge Case: Already minimal
        // -------------------------
        int[][] logs3 = {
                {1,0,1}
        };
        runTest("Edge Minimal", logs3, 2, 1);

        // -------------------------
        // 🟡 Edge Case: Single chain
        // -------------------------
        int[][] logs4 = {
                {1,0,1},
                {2,1,2},
                {3,2,3}
        };
        runTest("Edge Chain", logs4, 4, 3);

        // -------------------------
        // 🚀 Stress Test (Large)
        // Will TLE if naive O(n²)
        // -------------------------
        int n = 10000;
        int[][] stress = new int[n-1][3];
        for (int i = 0; i < n-1; i++) {
            stress[i][0] = i;
            stress[i][1] = i;
            stress[i][2] = i + 1;
        }
        runTestWithTimeout("Stress 10k Chain", stress, n, n-2);
    }

    // =====================================================
    // 🧪 Normal Test Runner
    // =====================================================
    private static void runTest(String name, int[][] logs, int n, int expected) {
        int result = earliestAcq(logs, n);
        String status = (result == expected) ? "Match ✅" : "Not Match ❌";
        System.out.println(name + " -> " + status);
    }

    // =====================================================
    // 🧪 Timeout-aware Runner
    // =====================================================
    private static void runTestWithTimeout(String name, int[][] logs, int n, int expected) {

        long start = System.currentTimeMillis();
        int result = earliestAcq(logs, n);
        long duration = System.currentTimeMillis() - start;

        // 2 second threshold
        if (duration > 2000) {
            System.out.println(name + " -> TLE ⏳");
            return;
        }

        String status = (result == expected) ? "Match ✅" : "Not Match ❌";
        System.out.println(name + " -> " + status);
    }
}
