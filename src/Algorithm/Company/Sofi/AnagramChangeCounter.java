package Algorithm.Company.Sofi;

/**
 * Problem: Making Anagrams (Character Change Count)
 *
 * You are given a list of word pairs.
 * For each pair of words, determine how many characters must be changed
 * (replaced) to make the two words anagrams of each other.
 *
 * Rules:
 * - You may change (replace) characters, but NOT insert or delete characters.
 * - If the two words have different lengths, they can NEVER be anagrams.
 *   In that case, return -1 for that pair.
 *
 * ------------------------------------------------------------
 * Definition:
 * Two strings are anagrams if they contain the same characters
 * with the same frequencies (order does not matter).
 *
 * ------------------------------------------------------------
 * Example:
 *
 * Input:
 *   pairs = {
 *     {"tea", "ate"},
 *     {"night", "thing"},
 *     {"apple", "pear"}
 *   }
 *
 * Output:
 *   [0, 0, -1]
 *
 * Explanation:
 * - "tea" <-> "ate": already anagrams → 0 changes
 * - "night" <-> "thing": already anagrams → 0 changes
 * - "apple" <-> "pear": different lengths → -1
 *
 * ------------------------------------------------------------
 * Visual Example (frequency-based thinking):
 *
 *   "abbc" vs "abdd"
 *
 *   a: 1 vs 1  ✔
 *   b: 2 vs 1  ❌
 *   c: 1 vs 0  ❌
 *   d: 0 vs 2  ❌
 *
 *   Total mismatched characters determine how many changes are required.
 *
 * ------------------------------------------------------------
 * Constraints (typical):
 * - Words contain only lowercase English letters (a–z)
 * - 1 <= number of pairs <= 10^5
 * - 1 <= word length <= 10^4
 *
 * ------------------------------------------------------------
 * Task:
 * Implement the method that returns an int[] where:
 * - result[i] = number of character changes required for pair i
 * - result[i] = -1 if the pair cannot be made into anagrams
 *
 * IMPORTANT:
 * - Do NOT implement the solution here.
 * - Only provide method signature and test scaffolding.
 */
public class AnagramChangeCounter {

    /**
     * TODO:
     * For each pair of words, compute the number of character changes
     * required to make them anagrams.
     *
     * @param pairs pairs[i][0] and pairs[i][1] are the two words in the i-th pair
     * @return int[] result array
     */
    public static int[] changesToMakeAnagrams(String[][] pairs) {
        // NOT IMPLEMENTED
        int n = pairs.length;
        int[] res = new int[n];

        for (int i = 0; i < n ; i++) {
            String s1 = pairs[i][0], s2 = pairs[i][1];

            if (s1 == null && s2 == null) {
                res[i] = 0;
                continue;
            }

            if (s1 == null || s2 == null || s1.length() != s2.length()) {
                res[i] = -1;
                continue;
            }

            int[] freq1 = new int[26];

            for (char c : s1.toCharArray()) {
                freq1[c - 'a'] ++;
            }

            for (char c : s2.toCharArray()) {
                freq1[c - 'a'] --;
            }

            for (int occur : freq1) {
                if (occur > 0) {
                    res[i] += occur;
                }
            }
        }

        return res;
    }

    /* =======================
       Assertion Helper
       ======================= */

    private static void assertArrayEquals(int[] expected, int[] actual, String testName) {
        if (expected.length != actual.length) {
            throw new AssertionError("❌ " + testName + " FAILED (length mismatch)");
        }
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                throw new AssertionError(
                        "❌ " + testName + " FAILED at index " + i +
                                " | expected=" + expected[i] +
                                ", actual=" + actual[i]
                );
            }
        }
        System.out.println("✅ " + testName + " PASSED");
    }

    /* =======================
       Test Cases
       ======================= */

    public static void main(String[] args) {

        // 1️⃣ Basic anagrams
        String[][] test1 = {
                {"tea", "ate"},
                {"night", "thing"}
        };
        runTest("Basic anagrams", test1, new int[]{0, 0});

        // 2️⃣ Different lengths
        String[][] test2 = {
                {"apple", "pear"}
        };
        runTest("Different lengths", test2, new int[]{-1});

        // 3️⃣ One change needed
        String[][] test3 = {
                {"ab", "ac"}
        };
        runTest("One change", test3, new int[]{1});

        // 4️⃣ Multiple changes needed
        String[][] test4 = {
                {"aabb", "abcc"}
        };
        runTest("Multiple changes", test4, new int[]{2});

        // 5️⃣ Already identical strings
        String[][] test5 = {
                {"hello", "hello"}
        };
        runTest("Identical strings", test5, new int[]{0});

        // 6️⃣ Single-character strings
        String[][] test6 = {
                {"a", "b"}
        };
        runTest("Single character", test6, new int[]{1});

        // 7️⃣ Empty string edge case
        String[][] test7 = {
                {"", ""}
        };
        runTest("Empty strings", test7, new int[]{0});

        // 8️⃣ Mixed valid and invalid pairs
        String[][] test8 = {
                {"abc", "bca"},
                {"abcd", "abc"},
                {"xxyy", "yyzz"}
        };
        runTest("Mixed cases", test8, new int[]{0, -1, 2});

        // 9️⃣ Large repetition difference
        String[][] test9 = {
                {"aaaa", "bbbb"}
        };
        runTest("All characters different", test9, new int[]{4});

        System.out.println("\n🎉 All tests executed (solution not implemented).");
    }

    /* =======================
       Test Runner
       ======================= */

    private static void runTest(String testName, String[][] input, int[] expected) {
        try {
            int[] result = changesToMakeAnagrams(input);
            assertArrayEquals(expected, result, testName);
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠️  " + testName + " skipped (solution not implemented)");
        }
    }
}
