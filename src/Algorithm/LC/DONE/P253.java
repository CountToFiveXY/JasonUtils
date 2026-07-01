package Algorithm.LC.DONE;

import java.util.*;

/**
 * LeetCode 253 - Meeting Rooms II
 *
 * Given an array of meeting time intervals where intervals[i] = [start_i, end_i],
 * return the minimum number of conference rooms required.
 *
 * Example:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Explanation:
 * 0------30
 *     5--10
 *        15---20
 *
 * Overlap count max = 2 → need 2 rooms
 *
 * --------------------------------------------------
 * Diagram:
 *
 * Timeline →
 *
 * 0----------------30
 *     5----10
 *          15----20
 *
 * Active meetings at peak = 2
 *
 * --------------------------------------------------
 *
 * Edge Case:
 * intervals = [[7,10],[2,4]]
 *
 * 2----4
 *      7----10
 *
 * No overlap → 1 room
 *
 */
public class P253 {

    /**
     * TODO: Implement this method
     */
    public int minMeetingRooms(int[][] intervals) {
        // write your logic here

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //store end time

        pq.offer(intervals[0][1]);
        int res = 1;

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            while (!pq.isEmpty() && start >= pq.peek()) {
                pq.poll();
            }
            pq.offer(end);
            res = Math.max(res, pq.size());
        }
        return res;
    }

    public static void main(String[] args) {
        P253 solution = new P253();

        runTest(solution,
                new int[][]{{0,30},{5,10},{15,20}},
                2,
                "Happy Case 1");

        runTest(solution,
                new int[][]{{7,10},{2,4}},
                1,
                "Happy Case 2");

        runTest(solution,
                new int[][]{{1,5},{2,6},{3,7},{4,8}},
                4,
                "Heavy Overlap");

        runTest(solution,
                new int[][]{{1,2}},
                1,
                "Single Meeting");

        runTest(solution,
                new int[][]{},
                0,
                "Empty Input");

        // Stress Test (potential TLE if O(N^2))
        stressTest(solution);
    }

    private static void runTest(P253 solution, int[][] input, int expected, String name) {
        long start = System.currentTimeMillis();

        int result = solution.minMeetingRooms(input);

        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println(name + " → TLE");
            return;
        }

        if (result == expected) {
            System.out.println(name + " → Match ✅");
        } else {
            System.out.println(name + " → Not Match ❌ | Expected: "
                    + expected + " | Got: " + result);
        }
    }

    private static void stressTest(P253 solution) {
        int n = 20000;
        int[][] intervals = new int[n][2];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = i;
            intervals[i][1] = i + 10000;  // force heavy overlap
        }

        long start = System.currentTimeMillis();

        int result = solution.minMeetingRooms(intervals);

        long duration = System.currentTimeMillis() - start;

        if (duration > 2000) {
            System.out.println("Stress Test → TLE");
        } else {
            System.out.println("Stress Test → Completed in "
                    + duration + "ms | Result: " + result);
        }
    }
}


/*
====================================================
HINT (Do NOT read if practicing seriously)
====================================================

This is a classic "interval overlap" problem.

Key Observations:

1) If a meeting starts before another ends → need new room.
2) If a meeting starts after or exactly when another ends → reuse room.

Optimal Approaches:

Approach 1:
- Sort intervals by start time
- Use a min heap to track end times
- If current start >= smallest end → poll
- Always push current end
Time: O(N log N)

Approach 2 (Line Sweep):
- Separate start[] and end[]
- Sort both arrays
- Use two pointers
Time: O(N log N)

Avoid:
- O(N^2) checking every overlap pair

Peak overlapping count = answer.

====================================================
*/