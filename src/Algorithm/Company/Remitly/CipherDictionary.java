package Algorithm.Company.Remitly;

import java.util.*;

/**
 * Cipher Word Search
 *
 * Given a dictionary of words,
 * input one word,
 * return all words that share the same cipher pattern.
 *
 * Cipher Rule:
 * First unique char -> a
 * Second unique char -> b
 * Third unique char -> c ...
 *
 * Example:
 * "paper" -> "abacd"
 * "title" -> "abacd"
 *
 * So:
 * input = "paper"
 * return ["title", "paper"]
 *
 * -----------------------------------
 * Idea:
 * Normalize each word into pattern string.
 * Group dictionary words by pattern.
 * Query input pattern.
 *
 * Build: O(N * K)
 * Query: O(K)
 * N = #words
 * K = avg word length
 */
public class CipherDictionary {

    HashMap<String, List<String>> map;

    public CipherDictionary(List<String> words) {
        // O (NL)
        map = new HashMap<>();
        for (String s: words) {
            String pattern = toPattern(s);
            map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(s);
        }
    }

    public List<String> search(String word) {
        String pattern = toPattern(word);

        return map.get(pattern) == null?  new ArrayList<>(): map.get(pattern);
    }

    //time: O N

    private String toPattern(String s) {
        Map<Character,  Integer> firstIndex = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (firstIndex.containsKey(c)) {
                sb.append("#" + firstIndex.get(c));
            } else {
                firstIndex.put(c, i);
                sb.append("#" + i);
            }
        }
        return sb.toString();
    }

    private boolean isSamePattern(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        int p = 0;

        Map<Character,  Integer> mapS = new HashMap<>(); //last poistion of char in s
        Map<Character,  Integer> mapT = new HashMap<>();//last poistion of char in t
        while(p < s.length()) {
            char cs = s.charAt(p);
            char ct = t.charAt(p);

            int lastS = mapS.getOrDefault(cs, -1);
            int lastT = mapT.getOrDefault(ct, -1);

            if (lastS != lastT) {
                return false;
            }

            mapS.put(cs, p);
            mapT.put(ct, p);
            p++;
        }

        return true;
    }


    public static void main(String[] args) {

        List<String> dict = Arrays.asList(
                "banana",
                "cololo",
                "paper",
                "title",
                "kick",
                "deed",
                "noon",
                "apple",
                "hello",
                "toot"
        );

        CipherDictionary solver = new CipherDictionary(dict);

        // Normal
        test("paper", solver.search("paper"),
                Arrays.asList("paper", "title"));

        // Banana pattern
        test("banana", solver.search("banana"),
                Arrays.asList("banana", "cololo"));

        // Repeated pair pattern
        test("toot", solver.search("toot"),
                Arrays.asList("deed", "noon", "toot"));

        // No match
        test("xyz", solver.search("xyz"),
                Arrays.asList());

        // Single char
        test("a", solver.search("a"),
                Arrays.asList());

        // Same input should include itself if exists
        test("kick", solver.search("kick"),
                Arrays.asList("kick"));

        // Edge empty
        test("", solver.search(""),
                Arrays.asList());
    }

    private static void test(String name,
                             List<String> actual,
                             List<String> expected) {

        Collections.sort(actual);
        Collections.sort(expected);

        if (actual.equals(expected)) {
            System.out.println("✅ " + name + " -> " + actual);
        } else {
            System.out.println("❌ " + name);
            System.out.println("Expected: " + expected);
            System.out.println("Actual  : " + actual);
        }
    }
}