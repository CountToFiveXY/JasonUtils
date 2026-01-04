package Algorithm.LC.Intervals.P254; /**
 * LeetCode 253 — Meeting Rooms II
 *
 * Problem:
 * Given an array of meeting time intervals where each interval = [start, end],
 * return the MINIMUM number of conference rooms required.
 *
 * A new room must be allocated if a meeting starts before another ends.
 *
 * Example:
 * Input: [[0, 30], [5, 10], [15, 20]]
 * Output: 2
 *
 * Diagram:
 *
 *   Meeting A: [0---------30]
 *   Meeting B:      [5---10]
 *   Meeting C:           [15------20]
 *
 * Rooms timeline:
 *   Room 1: A
 *   Room 2: B, C
 *
 * Answer: 2 rooms
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        // TODO: Implement logic (do NOT include solution here)
        if (intervals == null || intervals.length == 0 ) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int min = 0, count = 1, end = intervals[0][1];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(end);
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], endi = intervals[i][1];

            while (!pq.isEmpty() && start >= pq.peek()) {
                pq.poll();
                count--;
            }
            count++;
            min = Math.max(count, min);
            pq.offer(endi);
        }

        return min;
    }

    // -------------------------------------------------------
    // Main method (placed ABOVE runTest)
    // -------------------------------------------------------
    public static void main(String[] args) {
        Solution sol = new Solution();

        runTest(sol, new int[][]{{0,30},{5,10},{15,20}}, 2, "Test1");
        runTest(sol, new int[][]{{7,10},{2,4}}, 1, "Test2");
        runTest(sol, new int[][]{{1,2},{2,3},{3,4}}, 1, "Test3 (sequential)");
        runTest(sol, new int[][]{{1,5},{2,6},{3,7}}, 3, "Test4 (all overlapping)");
        runTest(sol, new int[][]{{1,5},{6,10},{11,15}}, 1, "Test5 (non-overlapping)");
        runTest(sol, new int[][]{}, 0, "Test6 (empty input)");
    }

    // -------------------------------------------------------
    // Test helper
    // -------------------------------------------------------
    private static void runTest(Solution sol, int[][] intervals, int expected, String testName) {
        int result = sol.minMeetingRooms(intervals);
        String status = (result == expected) ? "PASS" : "FAIL";

        System.out.println(
                testName + " expects " + expected +
                        ", got " + result +
                        " → " + status
        );
    }
}
