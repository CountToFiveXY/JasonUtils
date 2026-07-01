package Algorithm.LC;

import java.util.*;

/**
 * LeetCode 2533 — Number of Good Binary Strings
 *
 * You are given four integers:
 *   low, high, zero, one.
 *
 * A binary string is considered "good" if:
 *   1. Its length is between low and high (inclusive).
 *   2. It can be constructed by repeatedly appending:
 *        - '0' exactly zero times
 *        - '1' exactly one times
 *
 * Return the number of different good binary strings that can be constructed.
 * Since the answer may be large, return it modulo 1e9 + 7.
 *
 * ------------------------------------------------------------
 * Example 1:
 * Input:  low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 *
 * Explanation:
 * All binary strings of length 3 are valid.
 *
 * ------------------------------------------------------------
 * Example 2:
 * Input:  low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 *
 * ------------------------------------------------------------
 * Conceptual View (Length Construction):
 *
 * Start from length = 0
 *
 * length
 *   0
 *  / \
 * +0   +1
 *  |     |
 * length + zero
 * length + one
 *
 * Count all ways to reach lengths in [low, high]
 *
 * ------------------------------------------------------------
 * Constraints:
 * 1 <= low <= high <= 10^5
 * 1 <= zero, one <= low
 *
 * Expected Time Complexity: O(high)
 */

public class P2533 {

    private static final int MOD = 1_000_000_007;

    // =====================================================
    // 🚫 DO NOT IMPLEMENT (Skeleton Only)
    // =====================================================
    public static int countGoodStrings(int low, int high, int zero, int one) {
        // TODO: Implement solution
        return -999; // placeholder
    }

    // =====================================================
    // 🧪 Main Test Harness
    // =====================================================
    public static void main(String[] args) {

        // -------------------------
        // ✅ Happy Case
        // -------------------------
        runTest("Happy 1", 3, 3, 1, 1, 8);
        runTest("Happy 2", 2, 3, 1, 2, 5);

        // -------------------------
        // 🟡 Edge Case: minimal bounds
        // -------------------------
        runTest("Edge Minimal", 1, 1, 1, 1, 2);

        // -------------------------
        // 🟡 Edge Case: zero == one
        // -------------------------
        runTest("Edge Same Step", 4, 4, 2, 2, -1); // expected unknown

        // -------------------------
        // ❌ Bad Case (intentionally wrong expected)
        // -------------------------
        runTest("Bad Case", 5, 6, 2, 3, 0);

        // -------------------------
        // 🚀 Stress Test
        // TLE if naive recursion without memo
        // -------------------------
        runStressTest();
    }

    // =====================================================
    // 🧪 Normal Runner
    // =====================================================
    private static void runTest(String name,
                                int low,
                                int high,
                                int zero,
                                int one,
                                int expected) {

        int result = countGoodStrings(low, high, zero, one);

        String status = (result == expected)
                ? "Match ✅"
                : "Not Match ❌";

        System.out.println(name + " -> " + status);
    }

    // =====================================================
    // 🧪 Timeout-aware Stress Runner
    // =====================================================
    private static void runStressTest() {

        int low = 1;
        int high = 100000;
        int zero = 1;
        int one = 2;

        long start = System.currentTimeMillis();

        int result = countGoodStrings(low, high, zero, one);

        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println("Stress 100k -> TLE ⏳");
            return;
        }

        System.out.println("Stress 100k -> Completed (" + duration + "ms)");
    }

    /*
     =====================================================
     💡 HINT SECTION (No Direct Solution)
     =====================================================

     1. Think in terms of dynamic programming by length.
        Let dp[i] = number of ways to build string of length i.

     2. Base case:
            dp[0] = 1  (empty string)

     3. Transition:
            dp[i] += dp[i - zero]
            dp[i] += dp[i - one]

     4. Final answer:
            sum dp[i] for i in [low, high]

     5. Use modulo 1e9 + 7 at every step.

     6. Avoid naive recursion without memo —
        it will explode exponentially.

     Target complexity:
            O(high)

     */
}
