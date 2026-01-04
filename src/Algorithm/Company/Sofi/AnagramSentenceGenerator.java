package Algorithm.Company.Sofi;

import java.util.*;

public class AnagramSentenceGenerator {

    /**
     * @param wordSet   list of words that may contain anagrams
     * @param sentences list of input sentences
     * @return          all possible sentences after anagram replacement
     */
    public List<String> generateSentences(
            List<String> wordSet,
            List<String> sentences) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String word: wordSet) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            String pattern = new String(arr);

            map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
        }

        for (String word: sentences) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            String pattern = new String(arr);

            List<String> replace = map.get(pattern);

        }

        return Collections.emptyList();
    }

    // ---------- helper methods ----------

    // Sort characters of a word to get anagram key
    private String getKey(String word) {
        // TODO
        return "";
    }

    // DFS helper
    private void dfs(
            String[] words,
            int index,
            Map<String, List<String>> anagramMap,
            List<String> path,
            List<String> result) {

        // TODO
    }
}
