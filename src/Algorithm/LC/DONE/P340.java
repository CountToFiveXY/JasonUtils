package Algorithm.LC.DONE;

import java.util.*;

/**
 * LeetCode 340 - Longest Substring with At Most K Distinct Characters
 *
 * Given a string s and an integer k, return the length of the longest substring
 * that contains at most k distinct characters.
 *
 * -------------------------------------------------------
 * Example:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: "ece"
 *
 * Input: s = "aa", k = 1
 * Output: 2
 *
 * -------------------------------------------------------
 * Diagram (Sliding Window):
 *
 * s = "eceba", k = 2
 *
 * Window expands →
 *
 * [e]          distinct=1
 * [e c]        distinct=2
 * [e c e]      distinct=2 ✅
 * [e c e b]    distinct=3 ❌ → shrink
 *
 * Shrink ←
 * [c e b]
 *
 * Maintain:
 *   - window with ≤ k distinct chars
 *   - track max length
 *
 * -------------------------------------------------------
 */

public class P340 {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] freq = new int[26];
        int slow = 0, n = s.length(), max = 0, currDistinct = 0;
        for (int fast = 0; fast < n; fast++) {
            char curr = s.charAt(fast);
            if (freq[curr - 'a'] == 0) {
                currDistinct++;
            }
            freq[curr - 'a']++;

            while(currDistinct > k) {
                char left = s.charAt(slow);
                freq[left - 'a']--;
                if (freq[left - 'a'] == 0) {
                    currDistinct--;
                }
                slow++;
            }
            max = Math.max(max, fast - slow + 1);
        }
        return max;
    }



    // ===================== Test Harness =====================
    public static void main(String[] args) {
        System.out.println("=== LeetCode 340 Tests ===");

        testNormal();
        testTricky();
        testEdge();
        testStress();
    }

    // -------- Normal Test --------
    private static void testNormal() {
        P340 sol = new P340();

        int res = sol.lengthOfLongestSubstringKDistinct("eceba", 2); // 3

        printResult("Normal", res == 3, res, 3);
    }

    // -------- Tricky Test --------
    private static void testTricky() {
        P340 sol = new P340();

        int res1 = sol.lengthOfLongestSubstringKDistinct("aa", 1); // 2
        int res2 = sol.lengthOfLongestSubstringKDistinct("aabbcc", 2); // 4 ("aabb" or "bbcc")

        boolean ok = (res1 == 2 && res2 == 4);

        printResult("Tricky", ok, res1 + "," + res2, "2,4");
    }

    // -------- Edge Test --------
    private static void testEdge() {
        P340 sol = new P340();

        int res1 = sol.lengthOfLongestSubstringKDistinct("", 2); // 0
        int res2 = sol.lengthOfLongestSubstringKDistinct("abc", 0); // 0

        boolean ok = (res1 == 0 && res2 == 0);

        printResult("Edge", ok, res1 + "," + res2, "0,0");
    }

    // -------- Stress Test (TLE detection) --------
    private static void testStress() {
        P340 sol = new P340();

        int n = 200000;
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            sb.append((char) ('a' + rand.nextInt(5)));
        }

        String s = sb.toString();

        long start = System.currentTimeMillis();

        int res = sol.lengthOfLongestSubstringKDistinct(s, 3);

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
 *   Sliding Window + HashMap
 *
 * Maintain:
 *   - left pointer
 *   - right pointer expands
 *   - map<char, count>
 *
 * When distinct chars > k:
 *   - shrink from left until valid
 *
 * Track:
 *   max window length
 *
 * Pattern:
 *   "Longest substring with constraint" → Sliding Window
 *
 * Optimization:
 *   - Use array[256] if only ASCII
 *
 */