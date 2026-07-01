package Algorithm.LC;

import java.util.*;

/**
 * LeetCode 694 - Number of Distinct Islands
 *
 * Given a non-empty 2D binary grid, count the number of distinct islands.
 *
 * An island is a group of 1s (land) connected 4-directionally (up, down, left, right).
 *
 * Two islands are considered the same if and only if one island can be translated
 * (not rotated or reflected) to equal the other.
 *
 * -------------------------------------------------------
 * Example:
 *
 * grid =
 * 1 1 0 0
 * 1 0 0 0
 * 0 0 1 1
 * 0 0 1 1
 *
 * Output: 1
 *
 * Explanation:
 * Both islands have the same shape:
 *
 * Shape encoding (relative positions):
 * (0,0) (0,1)
 * (1,0)
 *
 * -------------------------------------------------------
 * Diagram:
 *
 * Island traversal (DFS path encoding):
 *
 * Start at origin (0,0)
 *
 *        (0,0)
 *       /     \
 *   (1,0)   (0,1)
 *
 * Encode path:
 *   "O D U R L B"
 *   O = origin
 *   D/U/R/L = directions
 *   B = backtrack
 *
 * This ensures unique shape signature.
 *
 * -------------------------------------------------------
 */

public class P694 {

    public int numDistinctIslands(int[][] grid) {
        return -1;
    }

    // ===================== Test Harness =====================
    public static void main(String[] args) {
        System.out.println("=== LeetCode 694 Distinct Islands Tests ===");

        testNormal();
        testTricky();
        testEdge();
        testStress();
    }

    // -------- Normal Test --------
    private static void testNormal() {
        P694 sol = new P694();

        int[][] grid = {
                {1,1,0,0},
                {1,0,0,0},
                {0,0,1,1},
                {0,0,1,1}
        };

        int res = sol.numDistinctIslands(grid);

        printResult("Normal", res == 1, res, 1);
    }

    // -------- Tricky Test --------
    private static void testTricky() {
        P694 sol = new P694();

        int[][] grid = {
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };

        int res = sol.numDistinctIslands(grid);
        // expected shapes differ

        printResult("Tricky", res == 3, res, 3);
    }

    // -------- Edge Test --------
    private static void testEdge() {
        P694 sol = new P694();

        int[][] grid1 = {{0,0,0}};
        int[][] grid2 = {{1,1,1}};

        int res1 = sol.numDistinctIslands(grid1); // 0
        int res2 = sol.numDistinctIslands(grid2); // 1

        boolean ok = (res1 == 0 && res2 == 1);

        printResult("Edge", ok, res1 + "," + res2, "0,1");
    }

    // -------- Stress Test (TLE detection) --------
    private static void testStress() {
        P694 sol = new P694();

        int n = 200;
        int[][] grid = new int[n][n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = rand.nextInt(2);
            }
        }

        long start = System.currentTimeMillis();

        int res = sol.numDistinctIslands(grid);

        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println("Stress: TLE (" + duration + " ms)");
        } else {
            System.out.println("Stress: Match (" + duration + " ms, res=" + res + ")");
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
 * Key idea:
 *   Identify island "shape"
 *
 * Two main approaches:
 *
 * 1. DFS path encoding
 *    - Record traversal path (direction + backtrack)
 *    - Same shape → same path string
 *
 * 2. Relative coordinates
 *    - Normalize island by subtracting origin
 *    - Store list of relative positions
 *
 * Important:
 *   - Must include backtracking marker to avoid ambiguity
 *   - Only translation allowed (no rotation/reflection)
 *
 * Pattern:
 *   Graph / DFS + shape canonicalization
 *
 */