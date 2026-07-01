package Algorithm.LC;

import java.util.*;

/**
 * LeetCode 1151 — Minimum Swaps to Group All 1's Together
 *
 * You are given a binary array data.
 *
 * In one step, you can swap any two elements in the array.
 *
 * Return the minimum number of swaps required to group all 1's together
 * in any location in the array.
 *
 * ------------------------------------------------------------
 * Example 1:
 * Input:  data = [1,0,1,0,1]
 * Output: 1
 *
 * Explanation:
 * There are 3 ones.
 * We slide a window of size 3:
 *
 *  [1 0 1] 0 1
 *   1 [0 1 0] 1
 *   1 0 [1 0 1]
 *
 * Maximum ones inside a window = 2
 * Swaps needed = 3 - 2 = 1
 *
 * ------------------------------------------------------------
 * Example 2:
 * Input:  data = [0,0,0,1,0]
 * Output: 0
 *
 * ------------------------------------------------------------
 * Example 3:
 * Input:  data = [1,1,0,0,1]
 * Output: 1
 *
 * ------------------------------------------------------------
 * Constraints:
 * 1 <= data.length <= 10^5
 * data[i] is either 0 or 1.
 *
 * Expected Time Complexity: O(n)
 */

public class P1151 {

    // =====================================================
    // 🚫 DO NOT IMPLEMENT (Skeleton Only)
    // =====================================================
    public static int minSwaps(int[] data) {
        int sum = 0;

        for (int d: data) {
            if (d==1) {
                sum++;
            }
        }

        int slow = 0, num = 0, res = Integer.MAX_VALUE;

        for (int fast =0; fast < data.length; fast++) {
            if (data[fast] == 1) {
                num++;
            }

            while(fast - slow + 1 == sum) {
                res = Math.min(sum - num, res);
                if (data[slow] == 1) {
                    num--;
                }
                slow++;

            }
        }
        return res;
    }

    // =====================================================
    // 🧪 Main Test Harness
    // =====================================================
    public static void main(String[] args) {

        // -------------------------
        // ✅ Happy Case
        // -------------------------
        runTest("Happy 1",
                new int[]{1,0,1,0,1},
                1);

        // -------------------------
        // 🟡 Edge Case: All zeros
        // -------------------------
        runTest("Edge All Zero",
                new int[]{0,0,0,0},
                0);

        // -------------------------
        // 🟡 Edge Case: All ones
        // -------------------------
        runTest("Edge All One",
                new int[]{1,1,1,1},
                0);

        // -------------------------
        // 🟡 Edge Case: Single element
        // -------------------------
        runTest("Edge Single",
                new int[]{1},
                0);

        // -------------------------
        // ❌ Bad Case (intentionally wrong expected)
        // -------------------------
        runTest("Edge Single",
                new int[]{1},
                0);

        // -------------------------
        // 🔥 Tricky Case
        // -------------------------
        runTest("Tricky Case",
                new int[]{1,0,0,1,0,1},
                1);

        // -------------------------
        // 🚀 Stress Test
        // -------------------------
        int n = 300_000;
        int[] stress = new int[n];
        for (int i = 0; i < n; i++) {
            stress[i] = (i % 2 == 0) ? 1 : 0;
        }

        runStressTest("Stress 300k Alt", stress);
    }

    // =====================================================
    // 🧪 Normal Test Runner
    // =====================================================
    private static void runTest(String name, int[] input, int expected) {
        int result = minSwaps(input);
        String status = (result == expected)
                ? "Match ✅"
                : "Not Match ❌";
        System.out.println(name + " -> " + status);
    }

    // =====================================================
    // 🧪 Timeout-aware Runner
    // =====================================================
    private static void runStressTest(String name, int[] input) {

        long start = System.currentTimeMillis();
        int result = minSwaps(input);
        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println(name + " -> TLE ⏳");
        } else {
            System.out.println(name + " -> Completed ✅ ("
                    + duration + "ms), Result=" + result);
        }
    }
}
