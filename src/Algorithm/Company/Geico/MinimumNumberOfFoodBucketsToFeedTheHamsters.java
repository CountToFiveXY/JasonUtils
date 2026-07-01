package Algorithm.Company.Geico;

/**
 * LeetCode 2086 (Premium)
 * Minimum Number of Food Buckets to Feed the Hamsters
 *
 * You are given a string where:
 *  - 'H' represents a hamster
 *  - '.' represents an empty spot
 *
 * A food bucket can be placed on an empty spot and can feed
 * adjacent hamsters (left and/or right).
 *
 * Return the minimum number of buckets required to feed all hamsters,
 * or -1 if impossible.
 *
 * Diagram:
 * Input: ".H.H."
 * Index:  0 1 2 3 4
 *         . H . H .
 * Buckets:   B   B
 */
public class MinimumNumberOfFoodBucketsToFeedTheHamsters {

    public static int minimumBuckets(String hamsters) {
        // TODO: implement
        int maxReach = -1,  n = hamsters.length(), count = 0;

        for (int i =0 ; i < n; i++) {
            if (hamsters.charAt(i) == 'H') {
                if (maxReach >= i) {
                    continue;
                }
                if (!isEmpty(i - 1, hamsters) && !isEmpty(i +1, hamsters)) {
                    return -1;
                } else if (isEmpty(i +1, hamsters)) {
                    count++;
                    maxReach = i + 2;
                } else if (isEmpty(i - 1, hamsters)) {
                    count++;
                    maxReach = i;
                }
            }
        }
        return count;
    }

    private static boolean isEmpty(int i, String hamsters) {
        int n = hamsters.length();

        if (i < 0 || i >= n) {
            return false;
        }
        return hamsters.charAt(i) == '.';
    }

    public static void main(String[] args) {

        // Edge: no hamsters
        run("Edge-empty", ".", 0);

        // Edge: single hamster, no place for bucket
        run("Edge-single-hamster", "H", -1);

        // Basic: bucket to the right
        run("Basic-left", "H.", 1);

        // Basic: bucket to the left
        run("Basic-right", ".H", 1);

        // Basic: one bucket feeds both
        run("Basic-middle", "H.H", 1);

        // Basic: same as above with padding
        run("Basic-padding", ".H.H.", 1);

        // Spacing: too far apart to share
        run("Spacing-two-dots", "H..H", 2);

        // Spacing: still cannot share
        run("Spacing-three-dots", "H...H", 2);

        // Multiple: one shared, one separate
        run("Multi-alternating-3", "H.H.H", 2);

        // Multiple: first two share, last alone
        run("Multi-mixed", "H.H..H", 2);

        // Multiple: centered pair can share
        run("Multi-centered", "..H.H..", 1);

        // Impossible: no empty cell
        run("Bad-adjacent-2", "HH", -1);

        // Impossible: middle hamster blocked
        run("Bad-adjacent-3", "HHH", -1);

        // Impossible: first hamster blocked
        run("Bad-left-blocked", "HH.", -1);

        // Impossible: last hamster blocked
        run("Bad-right-blocked", ".HH", -1);

        // Impossible: trailing cluster
        run("Bad-cluster", "H.HH", -1);

        // Stress: alternating, odd count
        run("Stress-alternating-5", "H.H.H.H.H", 3);

        // Stress: alternating, even count
        run("Stress-alternating-6", "H.H.H.H.H.H", 3);

        // Stress: empty long string
        run("All-empty", "........", 0);
    }

    private static void run(String name, String s, int expected) {
        int actual = minimumBuckets(s);
        if (actual == expected) {
            System.out.printf("✅ %-22s expected=%d actual=%d%n", name, expected, actual);
        } else {
            System.out.printf("❌ %-22s expected=%d actual=%d  <-- INCORRECT%n",
                    name, expected, actual);
        }
    }


}
