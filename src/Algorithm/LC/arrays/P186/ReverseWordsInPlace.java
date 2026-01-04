package Algorithm.LC.arrays.P186;

/**
 * LeetCode 186 - Reverse Words in a String II
 *
 * PROBLEM STATEMENT:
 * Given a character array s, reverse the order of the words in-place.
 *
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by a single space.
 *
 * You must modify the input array in-place with O(1) extra space.
 *
 * ----------------------------------------
 * Example 1:
 *
 * Input:
 * s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
 *
 * Output:
 * ['b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e']
 *
 * ----------------------------------------
 * Constraints:
 * - Words are separated by a single space
 * - No leading or trailing spaces
 * - Input is a mutable char array
 *
 * ----------------------------------------
 * Conceptual Diagram:
 *
 * Original:
 * "the sky is blue"
 *
 * Step 1: Reverse entire array
 * "eulb si yks eht"
 *
 * Step 2: Reverse each word
 * "blue is sky the"
 *
 * ----------------------------------------
 * Key considerations (NO implementation here):
 * - How to reverse a subarray in-place?
 * - How to identify word boundaries?
 * - Why reverse whole array first?
 *
 */
public class ReverseWordsInPlace {

    /**
     * Reverses the words in-place in the given character array.
     *
     * @param s Character array representing the string
     */
    public void reverseWords(char[] s) {
        // TODO: implement in-place word reversal logic

        int l = 0, r = s.length - 1;
        swap(l, r, s);

        int slow = 0, fast = 0;

        while(fast < s.length) {
            if (fast == s.length - 1) {
                if (slow == 0) {
                    swap(slow, fast, s);
                } else {
                    swap(slow + 1, fast, s);
                }
            }

            if (s[fast] == ' ') {
                if (slow == 0) {
                    swap(slow, fast - 1, s);
                } else {
                    swap(slow, fast, s);
                }
                slow = fast;
            }
            fast++;
        }
    }

    private void swap(int l, int r, char[] s) {
        while (l < r) {
            char temp = s[r];
            s[r] = s[l];
            s[l] = temp;
            l++;
            r--;
        }
    }

    // ------------------------------
    // Helper (optional, not implemented)
    // ------------------------------
    private void reverse(char[] s, int left, int right) {
        // TODO: helper for reversing a subarray
    }

    // ------------------------------
    // Test Harness
    // ------------------------------
    public static void main(String[] args) {
        ReverseWordsInPlace solution = new ReverseWordsInPlace();

        char[] s1 = "the sky is blue".toCharArray();
        solution.reverseWords(s1);
        System.out.println("Expected: blue is sky the");
        System.out.println("Actual:   " + new String(s1));

        char[] s2 = "hello world".toCharArray();
        solution.reverseWords(s2);
        System.out.println("Expected: world hello");
        System.out.println("Actual:   " + new String(s2));

        char[] s3 = "a b c".toCharArray();
        solution.reverseWords(s3);
        System.out.println("Expected: c b a");
        System.out.println("Actual:   " + new String(s3));
    }
}
