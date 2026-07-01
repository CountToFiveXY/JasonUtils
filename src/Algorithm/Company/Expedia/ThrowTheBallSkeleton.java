package Algorithm.Company.Expedia;

import java.util.*;

/**
 * ============================================================
 * Throw The Ball
 * ============================================================
 *
 * Description
 *
 * Track a ball being passed between friends after a specific time.
 *
 * ------------------------------------------------------------
 *
 * Task
 *
 * You are given:
 *
 * • An array receiver of length n where receiver[i] indicates
 *   which friend the iᵗʰ friend throws the ball to.
 *
 * • An integer k, representing the number of seconds.
 *
 * Determine which friend (numbered 1 to n) has the ball
 * after exactly k seconds have passed, assuming:
 *
 * 1. Friends are numbered starting with 1.
 * 2. Friend 1 starts with the ball.
 * 3. Each friend throws the ball to another friend every second.
 *
 * ------------------------------------------------------------
 *
 * Function Description
 *
 * Complete the function throwTheBall in the editor
 * with the following parameters:
 *
 *   int receiver[n]:
 *       the iᵗʰ friend will throw the ball
 *       to the friend indicated in receiver[i]
 *
 *   int k:
 *       the time in seconds that the game lasts
 *
 * Returns
 *
 *   int:
 *       the friend holding the ball at time = k
 *
 * ------------------------------------------------------------
 *
 * Constraints
 *
 *   2 ≤ n ≤ 2 × 10^5
 *   1 ≤ receiver[i] ≤ n   (receiver[i] ≠ i)
 *   1 ≤ k ≤ 10^12
 *
 * ------------------------------------------------------------
 *
 * Sample Case 0
 *
 * Sample Input For Custom Testing
 *
 *   receiver[] size n = 4
 *   receiver = [3, 1, 4, 2]
 *   k = 5
 *
 * Sample Output
 *
 *   3
 *
 * ------------------------------------------------------------
 *
 * Explanation of Sample
 *
 * receiver = [3, 1, 4, 2]
 *
 * 1 → 3
 * 3 → 4
 * 4 → 2
 * 2 → 1
 *
 * The ball movement:
 *
 * Second 0: 1
 * Second 1: 3
 * Second 2: 4
 * Second 3: 2
 * Second 4: 1
 * Second 5: 3
 *
 * Therefore, after 5 seconds, friend 3 has the ball.
 *
 * ------------------------------------------------------------
 *
 * Graph Insight:
 *
 * Each friend has exactly ONE outgoing edge.
 * The structure forms chains that eventually enter cycles.
 *
 * Because k can be as large as 10^12,
 * a naive simulation will not pass.
 *
 * ============================================================
 */

public class ThrowTheBallSkeleton {

    /**
     * DO NOT implement logic here.
     */
    public static int throwTheBall(List<Integer> receiver, long k) {
        // TODO: Implement solution
        int startTime = 0;
        int thrower = 1;

        while(startTime < k) {
            int next = receiver.get(thrower - 1);
            thrower = next;

            startTime++;
        }
        return thrower;
    }

    // ------------------------------------------------------------
    // TLE threshold (milliseconds)
    private static final long TIME_LIMIT_MS = 2000;

    private static void runTest(String name, List<Integer> receiver, long k, int expected) {

        long start = System.currentTimeMillis();
        int actual = throwTheBall(receiver, k);
        long end = System.currentTimeMillis();

        long duration = end - start;

        System.out.println("Test: " + name);
        System.out.println("n = " + receiver.size() + ", k = " + k);
        System.out.println("Execution time: " + duration + " ms");

        if (duration > TIME_LIMIT_MS) {
            System.out.println("Result: TLE ⛔ (Execution exceeded " + TIME_LIMIT_MS + " ms)");
        } else if (actual == expected) {
            System.out.println("Result: Match ✅");
        } else {
            System.out.println("Result: Not Match ❌");
            System.out.println("Expected: " + expected + ", Actual: " + actual);
        }

        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {

        // ========================================================
        // 1️⃣ Sample Case
        // ========================================================
        runTest(
                "Sample Case",
                Arrays.asList(3, 1, 4, 2),
                5,
                3
        );

        // ========================================================
        // 2️⃣ Pure Cycle
        // 1→2→3→1
        // ========================================================
        runTest(
                "Pure Cycle",
                Arrays.asList(2, 3, 1),
                4,
                2
        );

        // ========================================================
        // 3️⃣ Chain → Cycle
        // 1→2→3→4→5→3
        // ========================================================
        runTest(
                "Chain Into Cycle",
                Arrays.asList(2, 3, 4, 5, 3),
                7,
                4
        );

        // ========================================================
        // 4️⃣ Edge Case - k = 0
        // ========================================================
        runTest(
                "k = 0",
                Arrays.asList(2, 3, 1),
                0,
                1
        );

        // ========================================================
        // 5️⃣ Minimum n = 2
        // ========================================================
        runTest(
                "Minimum n",
                Arrays.asList(2, 1),
                3,
                2
        );

        // ========================================================
        // 6️⃣ 🔥 Large k (will TLE if brute force)
        // Cycle length = 3
        // k = 10^12
        // ========================================================
        runTest(
                "Large k (10^12)",
                Arrays.asList(2, 3, 1),
                1_000_000_000_000L,
                1
        );

        // ========================================================
        // 7️⃣ 🔥 Long Chain Before Cycle
        // 1→2→3→...→1000→500 (cycle from 500)
        // ========================================================
        List<Integer> chainCycle = new ArrayList<>();
        int size = 1000;
        for (int i = 1; i < size; i++) {
            chainCycle.add(i + 1);
        }
        chainCycle.add(500); // create cycle

        runTest(
                "Long Chain Before Cycle",
                chainCycle,
                1_000_000_000_000L,
                -1  // placeholder expected
        );

        // ========================================================
        // 8️⃣ 🚨 Extreme Stress Case
        // n = 200,000 (max constraint)
        // single large cycle
        // k = 10^12
        // brute force would completely fail
        // ========================================================
        int n = 200_000;
        List<Integer> stress = new ArrayList<>(n);
        for (int i = 1; i < n; i++) {
            stress.add(i + 1);
        }
        stress.add(1); // close cycle

        runTest(
                "Extreme Stress - Max n + Huge k",
                stress,
                1_000_000_000_000L,
                1
        );
    }
}