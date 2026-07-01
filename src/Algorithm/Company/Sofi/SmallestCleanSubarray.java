package Algorithm.Company.Sofi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem: Smallest Clean Subarray
 *
 * You are given an integer array nums and an integer k.
 *
 * A subarray is called "clean" if it contains at least k DISTINCT numbers.
 *
 * Task:
 *   Return the LENGTH of the smallest clean subarray.
 *   If no such subarray exists, return -1.
 *
 * ------------------------------------------------------------
 * Definitions:
 * - A subarray is a contiguous part of the array.
 * - Distinct numbers are counted by unique values.
 *
 * ------------------------------------------------------------
 * Example:
 *
 * nums = [1, 2, 1, 3, 4]
 * k = 3
 *
 * Output: 3
 *
 * Explanation:
 * Subarray [1, 2, 3] or [2, 1, 3] has 3 distinct numbers
 * and length = 3, which is the smallest possible.
 *
 * ------------------------------------------------------------
 * Visual Sliding Window Example:
 *
 * nums:   1   2   1   3   4
 * index:  0   1   2   3   4
 *
 * Window expands →
 * [1]                 distinct=1
 * [1,2]               distinct=2
 * [1,2,1]             distinct=2
 * [1,2,1,3]           distinct=3  ✔ clean
 *
 * Then shrink ← to minimize length
 *
 * ------------------------------------------------------------
 * Constraints (typical):
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= k <= nums.length
 *
 * ------------------------------------------------------------
 * IMPORTANT:
 * - Do NOT implement the solution here
 * - Only provide method signature and test scaffolding
 */
public class SmallestCleanSubarray {

    /**
     * TODO:
     * Return the size of the smallest subarray that contains
     * at least k distinct numbers.
     *
     * @param nums input array
     * @param k required number of distinct values
     * @return length of smallest clean subarray, or -1 if impossible
     */
    public static int smallestCleanSubarray(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        int slow = 0, res = Integer.MAX_VALUE;
        for (int fast = 0; fast < nums.length; fast++) {

            map.put(nums[fast], map.getOrDefault(nums[fast], 0) + 1);

            while(map.size() == k) {
                res = Math.min(res, fast - slow + 1);
                int freq = map.get(nums[slow]);

                if (freq == 1) {
                    map.remove(nums[slow]);
                } else {
                    map.put(nums[slow], freq - 1);
                }
                slow++;
            }
        }
        return res == Integer.MAX_VALUE ? -1: res;
    }

    /* =======================
       Assertion Helper
       ======================= */

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected != actual) {
            throw new AssertionError(
                    "❌ " + testName + " FAILED | expected=" + expected + ", actual=" + actual
            );
        }
        System.out.println("✅ " + testName + " PASSED");
    }

    /* =======================
       Test Cases
       ======================= */

    public static void main(String[] args) {

        // 1️⃣ Basic example
        runTest("Basic case",
                new int[]{1, 2, 1, 3, 4}, 3, 3);

        // 2️⃣ Entire array needed
        runTest("Entire array",
                new int[]{1, 2, 3}, 3, 3);

        // 3️⃣ Single element clean subarray
        runTest("Single element",
                new int[]{5, 5, 5}, 1, 1);

        // 4️⃣ No clean subarray
        runTest("Impossible case",
                new int[]{1, 1, 1, 1}, 2, -1);

        // 5️⃣ k equals array length
        runTest("k equals length",
                new int[]{1, 2, 3, 4}, 4, 4);

        // 6️⃣ Repeating pattern
        runTest("Repeating pattern",
                new int[]{1, 2, 1, 2, 3}, 3, 3);

        // 7️⃣ Large distinct block at end
        runTest("Distinct at end",
                new int[]{1, 1, 1, 2, 3, 4}, 3, 3);

        // 8️⃣ Negative numbers allowed
        runTest("Negative numbers",
                new int[]{-1, -2, -1, -3}, 3, 3);

        // 9️⃣ Edge case: empty array
        runTest("Empty array",
                new int[]{}, 1, -1);

        System.out.println("\n🎉 All tests executed (solution not implemented).");
    }

    /* =======================
       Test Runner
       ======================= */

    private static void runTest(String testName, int[] nums, int k, int expected) {
        try {
            int result = smallestCleanSubarray(nums, k);
            assertEquals(expected, result, testName);
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠️  " + testName + " skipped (solution not implemented)");
        }
    }
}
