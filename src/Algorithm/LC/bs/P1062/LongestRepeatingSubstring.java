package Algorithm.LC.bs.P1062;

/*
LeetCode 1062: Longest Repeating Substring

Given a string s, return the length of the longest substring that occurs
at least twice in s. The occurrences may overlap.

Example 1:
Input: s = "banana"
Output: 3
Explanation: The longest repeating substring is "ana".

Example 2:
Input: s = "abcd"
Output: 0
Explanation: No repeating substring exists.

Constraints:
- 2 <= s.length <= 10^5
- s consists of lowercase English letters.
*/

public class LongestRepeatingSubstring {

    public int longestRepeatingSubstring(String s) {
        // TODO: Implement this method
        return 0;
    }

    // ----- Test Harness -----
    public static void main(String[] args) {
        LongestRepeatingSubstring sol = new LongestRepeatingSubstring();

        String s1 = "banana";
        System.out.println("Test 1: " + sol.longestRepeatingSubstring(s1)); // Expected: 3

        String s2 = "abcd";
        System.out.println("Test 2: " + sol.longestRepeatingSubstring(s2)); // Expected: 0

        String s3 = "aaaaa";
        System.out.println("Test 3: " + sol.longestRepeatingSubstring(s3)); // Expected: 4
    }
}
