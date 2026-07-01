package Algorithm.LC;

import java.util.*;

/**
 * LeetCode 2168 — Unique Substrings With Equal Digit Frequency
 *
 * You are given a string s consisting of digits ('0' to '9').
 *
 * A substring is valid if all digits that appear in the substring
 * have the same frequency.
 *
 * Return the number of unique valid substrings of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * ------------------------------------------------------------
 * Example 1:
 * Input:  s = "1212"
 * Output: 5
 *
 * Valid substrings:
 * "1", "2", "12", "21", "1212"
 *
 * ------------------------------------------------------------
 * Example 2:
 * Input:  s = "12321"
 * Output: 9
 *
 * ------------------------------------------------------------
 * Visualization:
 *
 * s = "1212"
 *
 * All substrings:
 * 1
 * 12
 * 121
 * 1212
 * 2
 * 21
 * 212
 * 1
 * 12
 * 2
 *
 * Check frequency equality per substring.
 *
 * ------------------------------------------------------------
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists only of digits.
 *
 * Expected Complexity: O(n^2 * 10) or optimized variant
 */

public class P2168 {

    // =====================================================
    // 🚫 DO NOT IMPLEMENT (Skeleton Only)
    // =====================================================
    public static int equalDigitFrequency(String s) {
        Set<String> set = new HashSet<>();
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                set.add(s.substring(i, j));
            }
        }

        System.out.println("set: " + set );
        int count = 0;
        for (String sub: set) {
            if (isValid(sub)) {
                count++;
            }
        }
        return count; // placeholder
    }

    private static boolean isValid(String s) {
        int[] freq = new int[10];

        char[] arr = s.toCharArray();

        for (char c:  arr) {
            freq[c - '0']++;
        }

        int common = -1;

        for (int x: freq) {
            if (x == 0) {
                continue;
            }

            if (common == -1) {
                common = x;
            } else {
                if (common != x) {
                    return false;
                }
            }
        }
        return true;
    }

    // =====================================================
    // 🧪 Main Test Harness
    // =====================================================
    public static void main(String[] args) {

        // -------------------------
        // ✅ Happy Case
        // -------------------------
        runTest("Happy 1", "1212", 5);

        runTest("Happy 2", "12321", 9);

        // -------------------------
        // 🟡 Edge Case: Single digit
        // -------------------------
        runTest("Edge Single", "7", 1);

        // -------------------------
        // 🟡 Edge Case: All same digit
        // -------------------------
        runTest("Edge All Same", "1111", 4);

        // -------------------------
        // 🚀 Stress Test
        // TLE if naive O(n^3)
        // -------------------------
        int n = 8000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i % 10);
        }

        runTestWithTimeout("Stress 8k Mixed", sb.toString(), -1);
    }

    // =====================================================
    // 🧪 Normal Runner
    // =====================================================
    private static void runTest(String name, String input, int expected) {
        int result = equalDigitFrequency(input);
        String status = (result == expected)
                ? "Match ✅"
                : "Not Match ❌";
        System.out.println(name + " -> " + status);
    }

    // =====================================================
    // 🧪 Timeout-aware Runner
    // =====================================================
    private static void runTestWithTimeout(String name, String input, int expected) {

        long start = System.currentTimeMillis();
        int result = equalDigitFrequency(input);
        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println(name + " -> TLE ⏳");
            return;
        }

        String status = (result == expected)
                ? "Match ✅"
                : "Not Match ❌";

        System.out.println(name + " -> " + status + " (" + duration + "ms)");
    }


    /*
     =====================================================
     💡 HINT SECTION (No Direct Solution)
     =====================================================

     1. Enumerate substrings using two pointers (start index i).

     2. Maintain a frequency array of size 10 (digits 0–9).

     3. While expanding substring:
        - Track:
          • number of distinct digits
          • maximum frequency among digits

     4. Key Observation:
        A substring is valid if:
            maxFrequency * distinctCount == substringLength

     5. Use a HashSet to store unique substrings.

     6. Complexity target:
            O(n^2 * 10)

     7. Avoid O(n^3) substring reconstruction inside loops.

     */
}
