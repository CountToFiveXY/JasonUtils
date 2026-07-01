package Algorithm.LC.TOP_1000; /**
 * LeetCode 159 — Longest Substring with At Most Two Distinct Characters
 *
 * Problem:
 * Given a string s, return the length of the longest substring
 * that contains at most TWO distinct characters.
 *
 * Example:
 * Input:  "eceba"
 * Output: 3
 * Explanation:
 *   The substring "ece" has only 2 distinct characters ('e' and 'c').
 *
 * Another Example:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation:
 *   The substring "aabbb" has only 'a' and 'b'.
 *
 * ---------------------------------------------------------
 * DIAGRAM (Sliding Window Visualization)
 *
 *   String: e  c  e  b  a
 *            \____/
 *            window = "ece"
 *            distinct chars = {e, c}
 *            length = 3
 *
 *   Moving window when too many distinct chars:
 *
 *   e  c  e  b
 *         ↑
 *   When adding 'b', window has 3 distinct chars → shrink from left.
 *
 * ---------------------------------------------------------
 *
 * Function signature:
 *   public int lengthOfLongestSubstringTwoDistinct(String s)
 *
 * Solution Ideas (Not Implemented Here):
 *   - Sliding window with hashmap or frequency count
 *   - Track window start, end, and count of distinct chars
 */

public class P159 {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // TODO: Implement sliding window logic (do NOT include solution)

        if (s == null || s.isEmpty()) {
            return 0;
        }

        int slow = 0, max = 0;
        int[] freq = new int[26];
        for (int fast = 0; fast < s.length();fast++) {
            freq[s.charAt(fast) - 'a']++;

            while(getDistinct(freq) > 2) {
                freq[s.charAt(slow) - 'a']--;
                slow++;
            }

            max = Math.max(max, fast - slow + 1);
        }
        return max;
    }

    private int getDistinct(int[] freq) {
        int count = 0;
        for (int num: freq) {
            if (num > 0) {
                count++;
            }
        }
        return count;
    }

    // -------------------------------------------------------
    // Main Method (appears ABOVE runTest)
    // -------------------------------------------------------
    public static void main(String[] args) {
        P159 sol = new P159();

        runTest(sol, "eceba", 3, "Test1");
        runTest(sol, "ccaabbb", 5, "Test2");
        runTest(sol, "abcabcabc", 2, "Test3 (only 2 allowed)");
        runTest(sol, "aaaa", 4, "Test4 (single character)");
        runTest(sol, "", 0, "Test5 (empty string)");
        runTest(sol, "ab", 2, "Test6 (exactly two characters)");
        runTest(sol, "aabbcc", 4, "Test7");
    }

    // -------------------------------------------------------
    // Test Helper
    // -------------------------------------------------------
    private static void runTest(P159 sol, String s, int expected, String testName) {
        int result = sol.lengthOfLongestSubstringTwoDistinct(s);
        String status = (result == expected ? "PASS" : "FAIL");

        System.out.println(
                testName + " expects " + expected +
                        ", got " + result +
                        " → " + status
        );
    }
}
