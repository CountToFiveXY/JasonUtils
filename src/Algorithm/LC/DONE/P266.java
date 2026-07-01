package Algorithm.LC.DONE;

/**
 * P266 — Palindrome Permutation
 *
 * Problem:
 * Given a string s, return true if a permutation of the string could form a palindrome.
 *
 * A string can form a palindrome if:
 * - At most ONE character has odd frequency
 *
 * Example:
 * Input:  "code"  -> false
 * Input:  "aab"   -> true   ("aba")
 * Input:  "carerac" -> true ("racecar")
 *
 * -------------------------
 * Diagram:
 *
 * Even length:
 *   a a b b  -> valid
 *
 * Odd length:
 *   a a b b c -> only ONE odd (c) allowed
 *
 * Invalid:
 *   a a b c d -> more than 1 odd
 *
 * -------------------------
 */

public class P266 {

    static class Solution {
        public boolean canPermutePalindrome(String s) {

            // TODO: implement
            int[] freq = new int[26];

            for (char c: s.toCharArray()) {
                freq[c - 'a']++;
            }

            int countOfOdd = 0;

            for (int x: freq) {
                if (x % 2 == 1) {
                    countOfOdd++;
                }
            }
            return countOfOdd <= 1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("====== Normal Tests ======");
        test(sol, "aab", true);
        test(sol, "carerac", true);
        test(sol, "code", false);

        System.out.println("\n====== Tricky Tests ======");
        test(sol, "aabbccdde", true);   // one odd
        test(sol, "aabbccddee", true);  // all even
        test(sol, "abc", false);        // all odd

        System.out.println("\n====== Edge Tests ======");
        test(sol, "", true);            // empty string
        test(sol, "a", true);           // single char
        test(sol, "aa", true);
        test(sol, "ab", false);

        System.out.println("\n====== Stress Test ======");
        stressTest(sol);
    }

    private static void test(Solution sol, String s, boolean expected) {
        boolean result = sol.canPermutePalindrome(s);
        System.out.println(
                "Input: \"" + s + "\" | Expected: " + expected +
                        " | Result: " + result + " => " +
                        (result == expected ? "Match ✅" : "Not Match ❌")
        );
    }

    private static void stressTest(Solution sol) {
        StringBuilder sb = new StringBuilder();
        int size = 100_000;

        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (i % 26)));
        }

        long start = System.currentTimeMillis();
        boolean result = sol.canPermutePalindrome(sb.toString());
        long end = System.currentTimeMillis();

        if (end - start > 2000) {
            System.out.println("Stress Test: TLE ❌");
        } else {
            System.out.println("Stress Test: Completed in " + (end - start) + "ms => " + result);
        }
    }
}

/*
==================== HINT ====================

1. Count frequency of each character
2. Count how many characters have odd frequency
3. Condition:
   - If odd count > 1 → return false
   - Else → return true

Alternative:
- Use a Set:
  - Add char if not present
  - Remove char if present
  - At the end:
      size <= 1 → valid palindrome

=============================================
*/