package Algorithm.LC.DONE;

import java.util.*;

/**
 * LC 359 - Logger Rate Limiter
 *
 * Design a logger system that receives a stream of messages along with timestamps.
 * Each message should be printed if and only if it has NOT been printed in the last 10 seconds.
 *
 * Implement the Logger class:
 * - Logger() Initializes the logger object.
 * - boolean shouldPrintMessage(int timestamp, String message)
 *      Returns true if the message should be printed at the given timestamp,
 *      otherwise returns false.
 *
 * Constraints:
 * - 0 <= timestamp <= 10^9
 * - Messages may arrive in non-decreasing timestamp order
 *
 * -----------------------
 * Example:
 * Input:
 * ["Logger","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage","shouldPrintMessage"]
 * [[],[1,"foo"],[2,"bar"],[3,"foo"],[8,"bar"],[10,"foo"]]
 *
 * Output:
 * [null,true,true,false,false,true]
 *
 * -----------------------
 * Diagram (Sliding Window of 10 seconds):
 *
 * time →
 * 1   2   3   8   10
 * foo bar foo bar foo
 *
 * foo printed at t=1 → next allowed at t>=11
 * bar printed at t=2 → next allowed at t>=12
 *
 * -----------------------
 */
public class P359 {

    static class Logger {

        Map<String, Integer> map;
        public Logger() {
            // TODO: initialize data structure
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            // TODO: implement

            if (!map.containsKey(message)) {
                map.put(message, timestamp);
                return true;
            }

            int lastTime = map.get(message);

            if (timestamp - lastTime >= 10) {
                map.put(message, timestamp);
            } else {
                return false;
            }


            return true;
        }
    }

    public static void main(String[] args) {
        boolean allPass = true;

        // ===== Normal Test =====
        try {
            Logger logger = new Logger();
            boolean[] result = new boolean[]{
                    logger.shouldPrintMessage(1, "foo"),
                    logger.shouldPrintMessage(2, "bar"),
                    logger.shouldPrintMessage(3, "foo"),
                    logger.shouldPrintMessage(8, "bar"),
                    logger.shouldPrintMessage(10, "foo"),
                    logger.shouldPrintMessage(11, "foo")
            };
            boolean[] expected = new boolean[]{true, true, false, false, false, true};

            boolean match = Arrays.equals(result, expected);
            System.out.println("Normal: " + (match ? "Match ✅" : "Not Match ❌"));
            allPass &= match;
        } catch (Exception e) {
            System.out.println("Normal: Exception ❌");
            allPass = false;
        }

        // ===== Tricky Test =====
        try {
            Logger logger = new Logger();
            boolean[] result = new boolean[]{
                    logger.shouldPrintMessage(1, "a"),
                    logger.shouldPrintMessage(11, "a"), // exactly 10 apart
                    logger.shouldPrintMessage(20, "a")
            };
            boolean[] expected = new boolean[]{true, true, false};

            boolean match = Arrays.equals(result, expected);
            System.out.println("Tricky: " + (match ? "Match ✅" : "Not Match ❌"));
            allPass &= match;
        } catch (Exception e) {
            System.out.println("Tricky: Exception ❌");
            allPass = false;
        }

        // ===== Edge Test =====
        try {
            Logger logger = new Logger();
            boolean[] result = new boolean[]{
                    logger.shouldPrintMessage(0, "x"),
                    logger.shouldPrintMessage(0, "x"),
                    logger.shouldPrintMessage(10, "x")
            };
            boolean[] expected = new boolean[]{true, false, true};

            boolean match = Arrays.equals(result, expected);
            System.out.println("Edge: " + (match ? "Match ✅" : "Not Match ❌"));
            allPass &= match;
        } catch (Exception e) {
            System.out.println("Edge: Exception ❌");
            allPass = false;
        }

        // ===== Stress Test (TLE safe break) =====
        try {
            Logger logger = new Logger();
            long start = System.currentTimeMillis();

            boolean ok = true;
            for (int i = 0; i < 100000; i++) {
                boolean res = logger.shouldPrintMessage(i, "msg" + (i % 100));
                // no strict validation, just ensure it runs fast
                if (System.currentTimeMillis() - start > 2000) {
                    System.out.println("Stress: TLE ❌");
                    ok = false;
                    break;
                }
            }

            if (ok) {
                System.out.println("Stress: Match ✅");
            }
            allPass &= ok;

        } catch (Exception e) {
            System.out.println("Stress: Exception ❌");
            allPass = false;
        }

        // ===== Final Result =====
        System.out.println(allPass ? "Overall: Match ✅" : "Overall: Not Match ❌");
    }
}

/*
HINTS:

1. Use a HashMap<String, Integer> to store the last printed timestamp for each message.
2. When a new message comes:
   - If it does not exist → print
   - If currentTime - lastTime >= 10 → print
   - Otherwise → skip
3. Only store the latest timestamp per message.
4. This is essentially a sliding window constraint problem.

(Do NOT implement solution here — keep it for practice)
*/