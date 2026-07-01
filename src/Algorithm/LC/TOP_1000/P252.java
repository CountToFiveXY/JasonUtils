package Algorithm.LC.TOP_1000;

import java.util.Arrays;

/**
 * LeetCode 252 — Meeting Rooms
 *
 * Problem Statement:
 * Given an array of meeting time intervals where each interval[i] = [start, end],
 * determine if a person could attend all meetings.
 *
 * A person can attend all meetings if and only if
 * no two intervals overlap in time.
 *
 * Example:
 * Input: [[0, 30], [5, 10], [15, 20]]
 * Output: false   // because [0,30] overlaps with [5,10]
 *
 * Input: [[7, 10], [2, 4]]
 * Output: true    // no overlap
 *
 * -------------------------------------------------------
 * Diagram (Interval Overlap Visualization):
 *
 * Case 1:
 *   [0--------30]
 *         [5----10]
 *   Overlap exists → cannot attend all meetings
 *
 * Case 2:
 *   [2---4]
 *               [7----10]
 *   No overlap → OK
 * -------------------------------------------------------
 *
 * Function Signature:
 *    public boolean canAttendMeetings(int[][] intervals)
 *
 * Constraints:
 *  - intervals.length can be 0 or more
 *  - Each interval: start < end
 *  - Return true if no meetings overlap
 */
public class P252 {

    public boolean canAttendMeetings(int[][] intervals) {
        // TODO: Implement the logic (do not include solution here)
        if (intervals == null || intervals.length == 0 ) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0], end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int starti = intervals[i][0], endi= intervals[i][1];
            if (starti <= end) {
                return false;
            } else {
                start = starti;
                end = endi;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        P252 sol = new P252();

        runTest(sol, new int[][]{{0, 30}, {5, 10}, {15, 20}}, false, "Test1");
        runTest(sol, new int[][]{{7, 10}, {2, 4}}, true, "Test2");
        runTest(sol, new int[][]{{1, 2}, {3, 4}, {5, 6}}, true, "Test3");
        runTest(sol, new int[][]{{1, 5}, {2, 6}}, false, "Test4");
        runTest(sol, new int[][]{}, true, "Test5 (empty input)");
    }

    private static void runTest(P252 sol, int[][] intervals, boolean expected, String testName) {
        boolean result = sol.canAttendMeetings(intervals);

        String status = (result == expected) ? "PASS" : "FAIL";

        System.out.println(
                testName + " expects " + expected +
                        ", got " + result +
                        " → " + status
        );
    }
}
