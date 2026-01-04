package Algorithm.LC.SlidingWindow.P340; /**
 * LeetCode 340 — Longest Substring with At Most K Distinct Characters
 *
 * Problem:
 * Given a string s and an integer k, return the length of the longest substring
 * that contains at most k DISTINCT characters.
 *
 * Example:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation:
 *   The substring "ece" has only 'e' and 'c'.
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 *
 * ---------------------------------------------------------------
 * DIAGRAM (Sliding Window Visualization)
 *
 *   s = "e c e b a"
 *        \_____/
 *        window = "ece"
 *        distinct = 2 chars → valid
 *
 *   Expand window:
 *        e c e b
 *            ↑
 *   Adding 'b' → now distinct = 3 → shrink window from left
 *
 * This is the generalization of:
 *   LC159 (k = 2)
 *   LC3   (k = infinite)
 *
 * ---------------------------------------------------------------
 * Function Signature:
 *   public int lengthOfLongestSubstringKDistinct(String s, int k)
 *
 * Common Approach (not implemented):
 *   - Sliding window + hashmap/frequency table
 */

public class Solution {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // TODO: implement sliding window logic (DO NOT include solution)
        return 0;
    }

    // -------------------------------------------------------
    // Main method (placed ABOVE runTest)
    // -------------------------------------------------------
    public static void main(String[] args) {
        Solution sol = new Solution();

        runTest(sol, "eceba", 2, 3, "Test1");
        runTest(sol, "aa", 1, 2, "Test2");
        runTest(sol, "aabbcc", 1, 2, "Test3 (k=1)");
        runTest(sol, "aabbcc", 2, 4, "Test4 (k=2)");
        runTest(sol, "aabbcc", 3, 6, "Test5 (k=3)");
        runTest(sol, "", 2, 0, "Test6 (empty)");
        runTest(sol, "abcadcacacaca", 3, 11, "Test7 (classic test)");
    }

    // -------------------------------------------------------
    // Test helper
    // -------------------------------------------------------
    private static void runTest(Solution sol, String s, int k, int expected, String name) {
        int result = sol.lengthOfLongestSubstringKDistinct(s, k);
        String status = (result == expected ? "PASS" : "FAIL");

        System.out.println(
                name + " expects " + expected +
                        ", got " + result +
                        " → " + status
        );
    }
}
