package Algorithm.Company.Sofi;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Problem: Peak Bandwidth Usage Across TV Channels
 *
 * Each TV show is represented as:
 *
 *   [startTime, endTime, bandwidth]
 *
 * where:
 * - startTime: show start time (minutes)
 * - endTime:   show end time (minutes)
 * - bandwidth: bandwidth needed during this time (Mbps)
 *
 * Shows from different channels may overlap in time.
 * A channel may have no show in a time slot, contributing 0 bandwidth.
 *
 * Task:
 *   Find the MAXIMUM total bandwidth required at any time.
 *
 * ---------------------------------------------------------
 * Example:
 *
 * int[][] shows = {
 *   {1, 30, 2}, {31, 60, 4}, {61, 120, 3},
 *   {1, 20, 2}, {21, 40, 4}, {41, 60, 5}, {61, 120, 3},
 *   {1, 60, 4}, {61, 120, 4}
 * };
 *
 * Output: 13
 *
 * Explanation:
 *   Time range [41, 60):
 *     4 + 5 + 4 = 13
 *
 * ---------------------------------------------------------
 * Constraints:
 * - Min show duration: 2 minutes
 * - Max show duration: 720 minutes
 * - Max bandwidth per show: 100 Mbps
 * - Total number of shows < 1000
 *
 * ---------------------------------------------------------
 * Visual Timeline (simplified)
 *
 * Time  -------------------------------------------------------------->
 *       1        20    30    40    60             120
 *
 * Ch1   [====2====][==4==][====3====]
 * Ch2   [==2==][==4==][==5==][====3====]
 * Ch3   [==========4==========][====4====]
 *
 * Peak (41–60): 4 + 5 + 4 = 13
 *
 * ---------------------------------------------------------
 * Notes:
 * - Assume intervals are [start, end) (start inclusive, end exclusive)
 * - No overlapping shows within the same channel
 * - Do NOT implement the solution here
 */
public class PeakBandwidthCalculator {

    /**
     * TODO:
     * Compute the maximum total bandwidth required at any time.
     *
     * @param shows int[][] where each row is [startTime, endTime, bandwidth]
     * @return maximum bandwidth at peak time
     */
    static class Channel {
        int start;
        int end;
        int band;
        Channel(int start, int end, int band) {
            this.start  = start;
            this.end = end;
            this.band = band;
        }
    }

    public static int maxBandwidthAtPeak(int[][] shows) {
        // NOT IMPLEMENTED
        if (shows == null || shows.length == 0 ) {
            return 0;
        }

        Arrays.sort(shows, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        PriorityQueue<Channel> pq = new PriorityQueue<>(
                (a, b) -> a.end - b.end
        );
        pq.offer(new Channel(shows[0][0], shows[0][1], shows[0][2]));
        int max = shows[0][2], curr = shows[0][2];
        for (int i  = 1; i < shows.length; i ++) {
            while(!pq.isEmpty() && shows[i][0] >= pq.peek().end) {
                curr -= pq.poll().band;
            }
            curr += shows[i][2];
            pq.offer(new Channel(shows[i][0], shows[i][1], shows[i][2]));
            max = Math.max(max, curr);
        }

        return max;
    }

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected != actual) {
            throw new AssertionError(
                    "❌ " + testName + " FAILED | expected=" + expected + ", actual=" + actual
            );
        }
        System.out.println("✅ " + testName + " PASSED");
    }

    /* =======================
       Test Cases
       ======================= */

    public static void main(String[] args) {

        // 1️⃣ Provided example
        int[][] test1 = {
                {1, 30, 2}, {31, 60, 4}, {61, 120, 3},
                {1, 20, 2}, {21, 40, 4}, {41, 60, 5}, {61, 120, 3},
                {1, 60, 4}, {61, 120, 4}
        };
        runTest("Example case", test1, 13);

        // 2️⃣ Single show
        int[][] test2 = {
                {10, 20, 5}
        };
        runTest("Single show", test2, 5);

        // 3️⃣ Non-overlapping shows
        int[][] test3 = {
                {1, 10, 3},
                {10, 20, 4},
                {20, 30, 5}
        };
        runTest("Non-overlapping shows", test3, 5);

        // 4️⃣ Fully overlapping shows
        int[][] test4 = {
                {1, 50, 3},
                {1, 50, 4},
                {1, 50, 5}
        };
        runTest("Fully overlapping shows", test4, 12);

        // 5️⃣ Partial overlaps
        int[][] test5 = {
                {1, 10, 2},
                {5, 15, 3},
                {10, 20, 4}
        };
        runTest("Partial overlaps", test5, 7);

        // 6️⃣ Same start time, different end times
        int[][] test6 = {
                {1, 10, 2},
                {1, 20, 3},
                {1, 30, 4}
        };
        runTest("Same start time", test6, 9);

        // 7️⃣ Same end time, different start times
        int[][] test7 = {
                {1, 30, 2},
                {10, 30, 3},
                {20, 30, 4}
        };
        runTest("Same end time", test7, 9);

        // 8️⃣ Sparse schedule with gaps (zero bandwidth periods)
        int[][] test8 = {
                {1, 5, 3},
                {10, 15, 4},
                {20, 25, 2}
        };
        runTest("Sparse schedule", test8, 4);

        // 9️⃣ Max boundary values
        int[][] test9 = {
                {1, 720, 100},
                {100, 200, 50},
                {300, 400, 30}
        };
        runTest("Max boundary values", test9, 150);

        System.out.println("\n🎉 All tests executed (solution not implemented).");
    }

    /* =======================
       Test Runner
       ======================= */

    private static void runTest(String testName, int[][] input, int expected) {
        try {
            int result = maxBandwidthAtPeak(input);
            assertEquals(expected, result, testName);
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠️  " + testName + " skipped (solution not implemented)");
        }
    }
}
