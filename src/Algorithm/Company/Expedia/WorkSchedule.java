package Algorithm.Company.Expedia;

import java.util.*;

/**
 * P0000 - Work Schedule
 *
 * Problem Description:
 *
 * An employee wants to find all possible work schedules for their week.
 * They must work exactly their total weekly hours, and no day can exceed
 * the maximum daily hours, though days off (0 hours) are allowed.
 * Some days may already be fixed by their employer.
 *
 * The current workweek is represented as a 7-character string.
 *
 * Example: "08??840"
 *
 * Each character represents one day:
 *  - digits (0-8) → already fixed working hours
 *  - '?' → flexible day
 *
 * Implement a function that returns all possible valid schedules.
 *
 * Example:
 *
 * pattern = "08??840"
 * work_hours = 24
 * day_hours = 4
 *
 * Known hours:
 *
 *   0 + 8 + 8 + 4 + 0 = 20
 *
 * Remaining hours:
 *
 *   24 - 20 = 4
 *
 * Flexible days = 2
 *
 * Possible assignments:
 *
 *   (0,4)
 *   (1,3)
 *   (2,2)
 *   (3,1)
 *   (4,0)
 *
 * Result:
 *
 * [
 *   "0804840",
 *   "0813840",
 *   "0822840",
 *   "0831840",
 *   "0840840"
 * ]
 *
 *
 * ----------------------------------------------------
 * Visual Diagram
 * ----------------------------------------------------
 *
 * pattern:  0 8 ? ? 8 4 0
 * index:    0 1 2 3 4 5 6
 *
 * fixed hours = 20
 * remaining = 4
 *
 * distribute remaining hours across '?' positions
 *
 * tree example:
 *
 *               start
 *            /  /  /  /  /
 *          0   1  2  3  4
 *         /         ...
 *      remaining
 *
 * build combinations whose sum = remaining
 *
 *
 * Constraints
 * -----------
 * 1 ≤ work_hours ≤ 56
 * 1 ≤ day_hours ≤ 8
 * pattern length = 7
 *
 */

public class WorkSchedule {

    public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        // TODO: implement
        List<String> res = new ArrayList<>();
        int len = 0;
        for (char c : pattern.toCharArray()) {
            if (c == '?') {
               len++;
            } else {
                workHours -=  c - '0';
            }
        }
        // impossible cases
        if (workHours < 0 || workHours > len * dayHours) {
            return new ArrayList<>();
        }

        dfs(dayHours, new StringBuilder(), workHours, len, res);
        //given * * *, find all comb where sum = workhours and each 0 <= * <= dayhours
        return convert(res, pattern);
    }

    private static List<String> convert(List<String> res, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String s: res) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) != '?') {
                    sb.append(pattern.charAt(i));
                } else {
                    sb.append(s.charAt(index));
                    index++;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }


    private static void dfs(int dayHours, StringBuilder curr, int sum, int len, List<String> res) {
        if (curr.length() == len ) {
            if (sum == 0) {
                res.add(curr.toString());
            }
            return;
        }
        for (int i = 0 ; i <= Math.min(dayHours, sum); i++) {
            curr.append(i);
            dfs(dayHours, curr, sum - i, len, res);
            curr.deleteCharAt(curr.length() - 1);
        }
    }



    // ---------------------------------------------------
    // Test Harness
    // ---------------------------------------------------

    public static void main(String[] args) {

        runTest(
                "Normal Case",
                24,
                4,
                "08??840",
                Arrays.asList(
                        "0804840",
                        "0813840",
                        "0822840",
                        "0831840",
                        "0840840"
                )
        );



        runTest(
                "Tricky Case - many flexible days",
                10,
                3,
                "???????",
                null // too many outputs to verify manually
        );

        runTest(
                "Edge Case - no flexible",
                20,
                8,
                "8882000",
                Arrays.asList("8882000")
        );

        runTest(
                "Edge Case - single ?",
                5,
                8,
                "1234?00",
                Arrays.asList("1234500")
        );

        runStressTest();


    }


    private static void runTest(
            String name,
            int workHours,
            int dayHours,
            String pattern,
            List<String> expected
    ) {

        long start = System.currentTimeMillis();

        List<String> result = findSchedules(workHours, dayHours, pattern);

        long time = System.currentTimeMillis() - start;

        boolean match = true;

        if (expected != null) {
            Collections.sort(result);
            Collections.sort(expected);
            match = result.equals(expected);
        }

        System.out.println("[" + name + "] " +
                (match ? "Match" : "Not Match") +
                " | result size=" + result.size() +
                " | time=" + time + "ms");
    }


    // ---------------------------------------------------
    // Stress Test (TLE detection)
    // ---------------------------------------------------

    private static void runStressTest() {

        System.out.println("\n[Stress Test]");

        int workHours = 40;
        int dayHours = 8;
        String pattern = "???????";

        long start = System.currentTimeMillis();

        List<String> res = findSchedules(workHours, dayHours, pattern);

        long time = System.currentTimeMillis() - start;

        if (time > 3000) {
            System.out.println("TLE | time=" + time + "ms");
            return;
        }

        System.out.println("Match | size=" + res.size() + " | time=" + time + "ms");
    }

}


/*
====================================================
Hints (Do NOT read if practicing)
====================================================

Key Observations

1. Pattern length is fixed at 7.
2. '?' positions represent flexible slots.
3. Each slot value must satisfy:
       0 ≤ hours ≤ day_hours

4. Compute:
       fixed_sum = sum of known digits
       remaining = workHours - fixed_sum

5. If remaining < 0 → impossible.

6. Let k = number of '?'.

Goal:
Find all combinations of k numbers
such that:

       sum = remaining
       each ≤ day_hours

This becomes a classic:

       bounded integer composition

Typical approach:

DFS / Backtracking

state:

   index of '?'
   remaining hours
   current assignment

pruning:

   remaining < 0
   remaining > remainingSlots * day_hours

7. When DFS finishes (k assignments):
       remaining must be 0

Then rebuild schedule string.

Time complexity worst case:

   O(day_hours^k)

But k ≤ 7 so acceptable.

====================================================
*/