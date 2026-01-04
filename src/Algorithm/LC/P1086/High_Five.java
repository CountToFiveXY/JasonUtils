package Algorithm.LC.P1086;

import java.util.*;

/**
 * LeetCode 1086: High Five
 *
 * Problem:
 * Given a list of scores of different students, items[i] = [ID, score], where each student
 * ID has at least five scores. Return the result table where each element is [ID, topFiveAverage].
 * The topFiveAverage is the average of the student's top five scores (integer division).
 * The result should be sorted by ID in ascending order.
 *
 * Example:
 * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 *
 * Constraints:
 * - 1 <= items.length <= 1000
 * - items[i].length == 2
 * - 1 <= ID <= 1000
 * - 0 <= score <= 100
 * - Each ID appears at least 5 times in items.
 */
public class High_Five {

    // TODO: implement this
    public int[][] highFive(int[][] items) {
        // Placeholder: return empty array for now
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int[] item: items) {
            if (!map.containsKey(item[0])) {
                map.put(item[0], new PriorityQueue<Integer>((a, b) -> b - a));
            }
            map.get(item[0]).offer(item[1]);
        }

        List<int[]> list  = new ArrayList<>();

        for (int id: map.keySet()) {
           int sum = 0;
           for (int i = 0; i < 5; i++) {
               sum += map.get(id).poll();
           }
           list.add(new int[]{id, sum/5});
        }

        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        Arrays.sort(items, (a , b) -> a[0] - b[0]);
        return res;
    }

    // ----- Test Harness -----
    public static void main(String[] args) {
        High_Five sol = new High_Five();

        // Test 1: Sample from prompt
        int[][] items1 = {
                {1,91},{1,92},{2,93},{2,97},{1,60},
                {2,77},{1,65},{1,87},{1,100},{2,100},{2,76}
        };
        System.out.println("Test 1 Input: " + deepToString(items1));
        int[][] out1 = sol.highFive(items1);
        System.out.println("Test 1 Output: " + deepToString(out1));
        System.out.println("Expected (order by ID): [[1,87],[2,88]]\n");

        // Test 2: Multiple students, more than 5 scores each
        int[][] items2 = {
                {3,70},{3,80},{3,90},{3,60},{3,85},{3,95},     // top five avg = (95+90+85+80+70)/5 = 84
                {4,100},{4,90},{4,80},{4,70},{4,85},{4,88},    // top five avg = (100+90+88+85+80)/5 = 88
                {2,50},{2,60},{2,70},{2,80},{2,90},{2,75}      // top five avg = (90+80+75+70+60)/5 = 75
        };
        System.out.println("Test 2 Input: " + deepToString(items2));
        int[][] out2 = sol.highFive(items2);
        System.out.println("Test 2 Output: " + deepToString(out2));
        System.out.println("Expected: [[2,75],[3,84],[4,88]]\n");

        // Test 3: Exactly 5 scores per student
        int[][] items3 = {
                {1,10},{1,20},{1,30},{1,40},{1,50},
                {2,100},{2,100},{2,100},{2,100},{2,100}
        };
        System.out.println("Test 3 Input: " + deepToString(items3));
        int[][] out3 = sol.highFive(items3);
        System.out.println("Test 3 Output: " + deepToString(out3));
        System.out.println("Expected: [[1,30],[2,100]]\n");
    }

    // Utility pretty-printer for int[][]
    private static String deepToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            sb.append(Arrays.toString(arr[i]));
            if (i < arr.length - 1) sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }
}
