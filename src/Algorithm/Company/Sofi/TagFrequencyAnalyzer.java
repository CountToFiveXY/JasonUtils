package Algorithm.Company.Sofi;

import java.util.*;

/**
 * ============================================================
 * Problem: Second Most Frequent Tag (with tie rule)
 * ============================================================
 *
 * Input:
 *   A List<String> input representing records in fixed groups of 3:
 *
 *     [id1, name1, tag1, id2, name2, tag2, ...]
 *
 * Each 3 strings form one record:
 *   - index 0: id
 *   - index 1: name
 *   - index 2: tag
 *
 * Task:
 *   Find the tag with the SECOND highest frequency.
 *
 * Special Tie Rule:
 *   If there are MULTIPLE tags tied for the HIGHEST frequency,
 *   then we consider there is NO second highest tag.
 *   In that case, return "notag".
 *
 * Additional Notes:
 *   - If there is only 0 or 1 unique tag overall, return "notag".
 *   - Time complexity requirement: O(N) where N is input.size().
 *
 * ============================================================
 *
 * Example 1:
 *   input tags: [t1, t2, t1, t3, t3]
 *   freq: t1=2, t3=2, t2=1
 *   highest frequency ties (t1 and t3) -> return "notag"
 *
 * Example 2:
 *   input tags: [t1, t2, t1, t3, t1, t2]
 *   freq: t1=3, t2=2, t3=1
 *   second most frequent -> "t2"
 *
 * ============================================================
 *
 * Diagram (frequency ranking):
 *
 *   Case A:
 *     t1: 5   <-- max (unique)
 *     t2: 3   <-- second
 *     t3: 1
 *
 *   Case B:
 *     t1: 5   <-- max tie
 *     t2: 5   <-- max tie  -> no second -> "notag"
 *     t3: 1
 *
 * ============================================================
 */
public class TagFrequencyAnalyzer {

    /**
     * Returns the second most frequent tag.
     * If multiple tags tie for the highest frequency, return "notag".
     *
     * @param input list of strings: [id, name, tag, id, name, tag, ...]
     * @return second most frequent tag or "notag"
     */
    public String findSecondMostFrequentTag(List<String> input) {
        if (input.size() % 3 !=0) {
            return "notag";
        }
        HashMap<String, Integer> map = new HashMap<>();
        //freq map
        for (int i = 2; i < input.size(); i = i + 3) {
            map.put(input.get(i), map.getOrDefault(input.get(i), 0) +1);
        }

        int max = 0, second = 0, maxCount = 0;
        String maxTag = "notag", secondTag = "notag";
        for (String tag: map.keySet()) {
            int count = map.get(tag);
            if (count > max) {
                second = max;
                secondTag = maxTag;
                max = count;
                maxTag = tag;
                maxCount = 1;
            } else if (count < max && count > second) {
                second = count;
                secondTag = tag;
            } else if (count == max) {
                maxCount++;
            }
        }


        return maxCount == 1? secondTag : "notag";
    }

    // ============================================================
    // Test Cases (NO solution logic used here)
    // ============================================================
    public static void main(String[] args) {

        TagFrequencyAnalyzer solver = new TagFrequencyAnalyzer();

        // Case 1: tie for highest -> notag
        List<String> input1 = Arrays.asList(
                "id1", "name1", "t1",
                "id2", "name2", "t2",
                "id3", "name3", "t1",
                "id4", "name4", "t3",
                "id5", "name5", "t3"
        );
        // freq: t1=2, t3=2, t2=1 -> max tie -> "notag"
        System.out.println("Case 1 expected: notag");
        System.out.println("Case 1 actual:   " + solver.findSecondMostFrequentTag(input1));
        System.out.println();

        // Case 2: unique highest, has second -> t2
        List<String> input2 = Arrays.asList(
                "id1", "name1", "t1",
                "id2", "name2", "t2",
                "id3", "name3", "t1",
                "id4", "name4", "t3",
                "id5", "name5", "t1",
                "id6", "name6", "t2"
        );
        // freq: t1=3, t2=2, t3=1 -> second is t2
        System.out.println("Case 2 expected: t2");
        System.out.println("Case 2 actual:   " + solver.findSecondMostFrequentTag(input2));
        System.out.println();

        // Case 3: only one unique tag -> notag
        List<String> input3 = Arrays.asList(
                "id1", "name1", "t1",
                "id2", "name2", "t1",
                "id3", "name3", "t1"
        );
        System.out.println("Case 3 expected: notag");
        System.out.println("Case 3 actual:   " + solver.findSecondMostFrequentTag(input3));
        System.out.println();

        // Case 4: empty / too short -> notag
        List<String> input4 = Arrays.asList("id1", "name1");
        System.out.println("Case 4 expected: notag");
        System.out.println("Case 4 actual:   " + solver.findSecondMostFrequentTag(input4));
    }
}
