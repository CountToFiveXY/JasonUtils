package Algorithm.LC.TwoPointer.P340;
/*
LeetCode 340: Longest Substring with At Most K Distinct Characters

Problem Statement:
- Given a string s and an integer k, return the length of the longest
  substring of s that contains at most k distinct characters.

Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: The longest substring is "ece" with length 3.

Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: The longest substring is "aa" with length 2.
*/

public class LongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // TODO: implement sliding window
        int[] freq = new int[26];
        int left = 0, right = 0, max = 0, distinct = 0;
        while(right < s.length()) {
            if (freq[s.charAt(right) - 'a'] == 0) {
                distinct++;
            }
            freq[s.charAt(right) - 'a']++;

            if (distinct <= k) {
                max = Math.max(max, right - left + 1);
            } else {
                while (distinct > k) {
                    freq[s.charAt(left) - 'a']--;
                    if (freq[s.charAt(left) - 'a'] == 0) {
                        distinct --;
                    }
                    left++;
                }
            }
            right++;
        }
        return max;
    }

    private int getCount(int[] freq) {
        int count = 0;
        for (int i: freq) {
            if (i > 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LongestSubstringKDistinct sol = new LongestSubstringKDistinct();

        // Test cases
        String s1 = "eceba";
        int k1 = 2;
        System.out.println("Input: " + s1 + ", k=" + k1 +
                " → Output: " + sol.lengthOfLongestSubstringKDistinct(s1, k1));

        String s2 = "aa";
        int k2 = 1;
        System.out.println("Input: " + s2 + ", k=" + k2 +
                " → Output: " + sol.lengthOfLongestSubstringKDistinct(s2, k2));

        String s3 = "aabbcc";
        int k3 = 2;
        System.out.println("Input: " + s3 + ", k=" + k3 +
                " → Output: " + sol.lengthOfLongestSubstringKDistinct(s3, k3));
    }
}
