package Algorithm.Company.Sofi;

import java.util.*;

/**
 * ============================================================
 * Problem: Loyal Members from Web Logs
 * ============================================================
 *
 * We track page/product views on a website for business metrics.
 * Each log entry has the following format:
 *
 *     (timestamp, pageId, customerId)
 *
 * At the end of each day, we have a log file containing many
 * such entries. Every day produces a new log file.
 *
 * Given two log files (Day 1 and Day 2), generate a list of
 * "loyal members" who satisfy BOTH conditions:
 *
 *   1) The customer appears in BOTH Day 1 and Day 2 logs.
 *   2) The customer has visited at least TWO UNIQUE pages
 *      across the two days combined.
 *
 * Notes:
 *   - Page visits by the same user to the same page should be
 *     counted only once (unique pages).
 *   - Output order is not specified.
 *   - Time complexity should be linear in the total number of logs.
 *
 * ============================================================
 */
public class LoyalMembersSkeleton {

    /**
     * Data model representing a single log entry.
     */
    static class LogEntry {
        long timestamp;
        String pageId;
        String customerId;

        LogEntry(long timestamp, String pageId, String customerId) {
            this.timestamp = timestamp;
            this.pageId = pageId;
            this.customerId = customerId;
        }
    }

    /**
     * Find loyal members from two days of logs.
     *
     * @param day1Logs log entries from day 1
     * @param day2Logs log entries from day 2
     * @return         list of loyal customerIds
     */
    public List<String> findLoyalMembers(
            List<LogEntry> day1Logs,
            List<LogEntry> day2Logs) {
        List<String> res = new ArrayList<>();
        Set<String> day1 = new HashSet<>();
        Set<String> day2 = new HashSet<>();

        Map<String, Set<String>> pages = new HashMap<>();

        for (LogEntry entry: day1Logs) {
            day1.add(entry.customerId);
            pages.computeIfAbsent(entry.customerId, k -> new HashSet<>()).add(entry.pageId);
        }

        for (LogEntry entry: day2Logs) {
            day2.add(entry.customerId);
            pages.computeIfAbsent(entry.customerId, k -> new HashSet<>()).add(entry.pageId);
        }

        for (String customerId: pages.keySet()) {
            if (day1.contains(customerId) && day2.contains(customerId)  && pages.get(customerId).size() >= 2) {
                res.add(customerId);
            }
        }

        return res;
    }

    // ============================================================
    // Test Cases (NO solution logic used here)
    // ============================================================
    public static void main(String[] args) {

        LoyalMembersSkeleton solver = new LoyalMembersSkeleton();

        List<LogEntry> day1 = Arrays.asList(
                new LogEntry(1, "PageA", "User1"),
                new LogEntry(2, "PageB", "User1"),
                new LogEntry(3, "PageA", "User2")
        );

        List<LogEntry> day2 = Arrays.asList(
                new LogEntry(4, "PageA", "User1"),
                new LogEntry(5, "PageC", "User2"),
                new LogEntry(6, "PageD", "User2")
        );

        // Expected loyal members:
        // User1 (visited PageA, PageB; present both days)
        // User2 (visited PageA, PageC, PageD; present both days)

        List<String> loyalMembers = solver.findLoyalMembers(day1, day2);

        System.out.println("Loyal members:");
        for (String user : loyalMembers) {
            System.out.println(user);
        }
    }
}
