package Algorithm.LC;

/**
 * LeetCode 408 - Valid Word Abbreviation
 *
 * Given a non-empty string word and an abbreviation abbr, return whether the
 * string matches the given abbreviation.
 *
 * Rules:
 * 1. Numbers in abbr represent how many characters are skipped.
 * 2. Leading zeros are NOT allowed.
 * 3. The abbreviation must consume the entire word.
 *
 * Examples:
 *
 * word = "internationalization"
 * abbr = "i12iz4n"
 * => true
 *
 * Explanation:
 * i + (12 skipped chars) + iz + (4 skipped chars) + n
 *
 * --------------------------------------------------
 *
 * word = "apple"
 * abbr = "a2e"
 * => false
 *
 * --------------------------------------------------
 *
 * Diagram:
 *
 * word : i n t e r n a t i o n a l i z a t i o n
 *        ^
 * abbr : i 1 2 i z 4 n
 *        ^
 *
 * Read both strings from left to right.
 *
 * Constraints:
 * 1 <= word.length <= 20
 * 1 <= abbr.length <= 20
 * word consists of lowercase English letters.
 * abbr consists of lowercase English letters and digits.
 *
 * Follow-up:
 * Can you solve it in O(N) time and O(1) space?
 */
public class P408 {

    public static boolean validWordAbbreviation(String word, String abbr) {

        int i = 0,  j = 0, m = word.length(), n = abbr.length();

        int count = 0;
        while(i < m && j < n) {

            while(j < n && Character.isDigit(abbr.charAt(j))) {
                if (abbr.charAt(j) == '0' && count == 0) {
                    return false;
                }
                count = count * 10 + abbr.charAt(j) - '0';
                j++;
            }
            i = i + count;

            if (j ==n) {
                break;
            }

            if (i >= m) {
                return false;
            }

            if (word.charAt(i) != abbr.charAt(j)) {
                return false;
            }
            count = 0;
            i++;
            j++;
        }

        if (i < m || j < n) {
            return false;
        }

        return i == m && j == n;
    }

    public static void main(String[] args) {

        System.out.println("========== Normal Cases ==========");
        runTest("internationalization", "i12iz4n", true);
        runTest("substitution", "s10n", true);
        runTest("substitution", "sub4u4", true);
        runTest("apple", "a2e", false);

        System.out.println("\n========== Tricky Cases ==========");
        runTest("word", "4", true);
        runTest("word", "3d", true);
        runTest("word", "2r1", true);
        runTest("word", "5", false);
        runTest("word", "w03d", false); // leading zero
        runTest("word", "w0rd", false); // leading zero
        runTest("a", "1", true);
        runTest("a", "2", false);

        System.out.println("\n========== Edge Cases ==========");
        runTest("", "", true);
        runTest("", "1", false);
        runTest("abc", "", false);
        runTest("abc", "abc", true);
        runTest("abc", "3", true);

        System.out.println("\n========== Stress Cases ==========");
        stressTest();
    }

    private static void runTest(String word,
                                String abbr,
                                boolean expected) {

        long start = System.currentTimeMillis();

        boolean actual = validWordAbbreviation(word, abbr);

        long cost = System.currentTimeMillis() - start;

        System.out.printf(
                "[%s] word=\"%s\", abbr=\"%s\" | expected=%s actual=%s | time=%dms%n",
                actual == expected ? "Match" : "Not Match",
                word,
                abbr,
                expected,
                actual,
                cost
        );
    }

    private static void stressTest() {

        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100_000; i++) {

            if (System.currentTimeMillis() - start > 3000) {
                System.out.println("[TLE] Stress test exceeded 3 seconds.");
                return;
            }

            sb.append('a');
        }

        String word = sb.toString();

        boolean result = validWordAbbreviation(
                word,
                String.valueOf(word.length())
        );

        long cost = System.currentTimeMillis() - start;

        System.out.printf(
                "[Stress] result=%s | length=%d | time=%dms%n",
                result,
                word.length(),
                cost
        );
    }
}

/*
===========================================================
Hints
===========================================================

Hint 1:
Use two pointers:
- i -> word
- j -> abbr

Hint 2:
When abbr[j] is a letter:
- It must exactly match word[i].

Hint 3:
When abbr[j] is a digit:
- Parse the entire number.
- Advance i by that amount.

Hint 4:
A number cannot start with '0'.

Hint 5:
At the end:
- Both pointers must reach the end simultaneously.

Target Complexity:
Time  : O(word.length + abbr.length)
Space : O(1)
*/