package Algorithm.LC.DONE;

import java.util.*;

/**
 * LeetCode 3696 - Maximum Distance Between Unequal Words in Array I
 *
 * Problem:
 * Given an array of strings words.
 *
 * Return the maximum distance between two indices i and j such that:
 *
 *     words[i] != words[j]
 *
 * Distance:
 *
 *     |i - j|
 *
 * --------------------------------------------------------
 * Example 1:
 *
 * Input:
 * words = ["a","b","a","c"]
 *
 * Output:
 * 3
 *
 * Explanation:
 * words[0] = "a"
 * words[3] = "c"
 *
 * They are different.
 * Distance = 3
 *
 * --------------------------------------------------------
 * Example 2:
 *
 * Input:
 * words = ["same","same","same"]
 *
 * Output:
 * 0
 *
 * --------------------------------------------------------
 * Constraints:
 *
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 20
 *
 * --------------------------------------------------------
 * Diagram:
 *
 * Index:   0    1    2    3
 * Words:  "a"  "b"  "a"  "c"
 *
 * Compare farthest unequal pair:
 *
 * "a" -------------------- "c"
 *            distance = 3
 *
 */
public class P3696 {

    public int maxDistance(String[] words) {

        // TODO: Implement solution

        int i = 0, j = words.length - 1;

        String a = words[i];
        String b = words[j];

        if (!a.equals(b)) {
            return Math.abs(i - j);
        }

        int tempi = i, tempj = j;

        while(tempi < j && words[tempi].equals(a)) {
            tempi++;
        }

        int candiA = j - tempi;

        while(i < tempj && words[tempj].equals(b)) {
            tempj--;
        }

        int candiB = tempj - i;


        return Math.max(candiA, candiB);
    }

    public static void main(String[] args) {

        P3696 solution = new P3696();

        // ----------------------------------------------------
        // Normal Test
        // ----------------------------------------------------
        String[] normal = {"a", "b", "a", "c"};
        int expected1 = 3;

        int result1 = solution.maxDistance(normal);

        System.out.println(
                "[Normal] Expected: " + expected1 +
                        ", Actual: " + result1 +
                        " -> " +
                        (expected1 == result1 ? "Match ✅" : "Not Match ❌")
        );

        // ----------------------------------------------------
        // Tricky Test
        // ----------------------------------------------------
        String[] tricky = {"same", "same", "same"};
        int expected2 = 0;

        int result2 = solution.maxDistance(tricky);

        System.out.println(
                "[Tricky] Expected: " + expected2 +
                        ", Actual: " + result2 +
                        " -> " +
                        (expected2 == result2 ? "Match ✅" : "Not Match ❌")
        );

        // ----------------------------------------------------
        // Edge Test
        // ----------------------------------------------------
        String[] edge = {"x"};
        int expected3 = 0;

        int result3 = solution.maxDistance(edge);

        System.out.println(
                "[Edge] Expected: " + expected3 +
                        ", Actual: " + result3 +
                        " -> " +
                        (expected3 == result3 ? "Match ✅" : "Not Match ❌")
        );

        // ----------------------------------------------------
        // Stress Test
        // ----------------------------------------------------
        int n = 100_000;

        String[] stress = new String[n];

        Arrays.fill(stress, "a");

        stress[0] = "start";
        stress[n - 1] = "end";

        long startTime = System.currentTimeMillis();

        int stressResult = solution.maxDistance(stress);

        long duration = System.currentTimeMillis() - startTime;

        if (duration > 3000) {
            System.out.println("[Stress] TLE ❌");
        } else {
            System.out.println(
                    "[Stress] Expected: " + (n - 1) +
                            ", Actual: " + stressResult +
                            " -> " +
                            ((n - 1) == stressResult ? "Match ✅" : "Not Match ❌") +
                            ", Time: " + duration + "ms"
            );
        }
    }
}

/*
Hint:

1. The maximum distance usually comes from:
      - comparing with the first word
      - or comparing with the last word

2. Try checking:
      - farthest index different from words[0]
      - farthest index different from words[n - 1]

3. Think about why only boundary comparisons may be enough.

4. Target:
      O(N) time
      O(1) extra space
 */