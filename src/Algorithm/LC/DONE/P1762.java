package Algorithm.LC.DONE;

/**
 * P1762 — Buildings With an Ocean View
 *
 * Problem:
 * There are n buildings in a line. You are given an integer array heights where heights[i]
 * represents the height of the ith building.
 *
 * A building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 * -------------------------
 * Example:
 * Input:  heights = [4,2,3,1]
 * Output: [0,2,3]
 *
 * Explanation:
 * Building 0 (4) → no taller/equal building to right → ✅
 * Building 1 (2) → blocked by 3 → ❌
 * Building 2 (3) → right is 1 → ✅
 * Building 3 (1) → nothing right → ✅
 *
 * -------------------------
 * Diagram:
 *
 * heights:   4   2   3   1
 * index:     0   1   2   3
 *
 * ocean → → → → →
 *
 * View:
 * [4] ✔
 * [2] ✖ (blocked by 3)
 * [3] ✔
 * [1] ✔
 *
 * -------------------------
 * Constraints:
 * - 1 <= heights.length <= 1e5
 * - 1 <= heights[i] <= 1e9
 *
 * -------------------------
 */

import java.util.*;

public class P1762 {

    static class Solution {
        public int[] findBuildings(int[] heights) {
            // TODO: implement
            ArrayDeque<Integer> arr = new ArrayDeque<>();
            int n = heights.length;
            int currMax = Integer.MIN_VALUE;
            for (int i = n -1; i >= 0; i-- ) {
                int curr = heights[i];
                if (curr > currMax) {
                    arr.push(i);
                    currMax = curr;
                }
            }

            int[] ans = new int[arr.size()];
            int i = 0;
            while(!arr.isEmpty()) {
                ans[i] = arr.pop();
                i++;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("====== Normal Tests ======");
        test(sol, new int[]{4,2,3,1}, new int[]{0,2,3});
        test(sol, new int[]{1,3,2,4}, new int[]{3});
        test(sol, new int[]{2,2,2,2}, new int[]{3});

        System.out.println("\n====== Tricky Tests ======");
        test(sol, new int[]{5,4,3,2,1}, new int[]{0,1,2,3,4}); // strictly decreasing
        test(sol, new int[]{1,2,3,4,5}, new int[]{4});         // strictly increasing
        test(sol, new int[]{10,3,7,4,12,2}, new int[]{4,5});

        System.out.println("\n====== Edge Tests ======");
        test(sol, new int[]{1}, new int[]{0});
        test(sol, new int[]{1000000000}, new int[]{0});

        System.out.println("\n====== Stress Test ======");
        stressTest(sol);
    }

    private static void test(Solution sol, int[] input, int[] expected) {
        int[] result = sol.findBuildings(input);
        boolean match = Arrays.equals(result, expected);

        System.out.println(
                "Input: " + Arrays.toString(input) +
                        " | Expected: " + Arrays.toString(expected) +
                        " | Result: " + Arrays.toString(result) +
                        " => " + (match ? "Match ✅" : "Not Match ❌")
        );
    }

    private static void stressTest(Solution sol) {
        int n = 200_000;
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1_000_000_000);
        }

        long start = System.currentTimeMillis();
        int[] res = sol.findBuildings(arr);
        long end = System.currentTimeMillis();

        if (end - start > 2000) {
            System.out.println("Stress Test: TLE ❌");
        } else {
            System.out.println("Stress Test: Completed in " + (end - start) + "ms | Output size: " + res.length);
        }
    }
}

/*
==================== HINT ====================

1. Traverse from RIGHT to LEFT
2. Keep track of max height seen so far
3. If current building > maxRight → it has ocean view
4. Collect indices and reverse at the end

Alternative:
- Use a stack to maintain decreasing sequence

Time: O(n)
Space: O(1) or O(n) depending on approach

=============================================
*/