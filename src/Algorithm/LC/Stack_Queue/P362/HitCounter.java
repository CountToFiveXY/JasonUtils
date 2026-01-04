package Algorithm.LC.Stack_Queue.P362;/*
LeetCode 362: Design Hit Counter

Design a hit counter which counts the number of hits received
in the past 5 minutes (300 seconds). Implement the HitCounter class:

1. HitCounter()
   - Initializes the counter.

2. void hit(int timestamp)
   - Records a hit at the given timestamp (in seconds).
   - Multiple hits can occur at the same timestamp.

3. int getHits(int timestamp)
   - Returns the number of hits in the past 5 minutes (i.e., from timestamp - 299 to timestamp).

Notes:
- Timestamps are given in non-decreasing order.
- The earliest timestamp starts at 1.

Example:
HitCounter counter = new HitCounter();
counter.hit(1);
counter.hit(2);
counter.hit(3);
counter.getHits(4);   // returns 3
counter.hit(300);
counter.getHits(300); // returns 4
counter.getHits(301); // returns 3
*/

public class HitCounter {

    // Constructor
    public HitCounter() {
        // TODO: implement initialization
    }

    // Record a hit at the given timestamp
    public void hit(int timestamp) {
        // TODO: implement logic
    }

    // Return the number of hits in the past 5 minutes
    public int getHits(int timestamp) {
        // TODO: implement logic
        return 0;
    }

    public static void main(String[] args) {
        // Example test
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));   // 3
        counter.hit(300);
        System.out.println(counter.getHits(300)); // 4
        System.out.println(counter.getHits(301)); // 3
    }
}
