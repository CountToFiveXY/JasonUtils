package Algorithm.LC.P1429;

import java.util.*;

 /*
    LeetCode 1429: First Unique Number

    Design a data structure that keeps track of the first unique number
    in a stream of numbers. Implement the following methods:

    1. FirstUnique(int[] nums)
       - Initializes the object with the numbers in the array nums.

    2. int showFirstUnique()
       - Returns the first unique number in the queue.
       - If there is no such number, return -1.

    3. void add(int value)
       - Inserts value to the queue.

    Example:

    Input:
    ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
    [[[2,3,5]],[],[5],[],[2],[],[3],[]]

    Output:
    [null,2,null,2,null,3,null,-1]

    Explanation:
    FirstUnique firstUnique = new FirstUnique([2,3,5]);
    firstUnique.showFirstUnique(); // return 2
    firstUnique.add(5);            // the queue is now [2,3,5,5]
    firstUnique.showFirstUnique(); // return 2
    firstUnique.add(2);            // the queue is now [2,3,5,5,2]
    firstUnique.showFirstUnique(); // return 3
    firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
    firstUnique.showFirstUnique(); // return -1

    Constraints:
    - 1 <= nums.length <= 10^5
    - 1 <= nums[i] <= 10^8
    - 1 <= value <= 10^8
    - Most calls will be to showFirstUnique() and add().
    */

public class FirstUnique {

    Map<Integer, Integer> map;
    Queue<Integer> q;

    // Constructor: initialize with an array of numbers
    public FirstUnique(int[] nums) {
        // TODO: implement initialization
        map = new HashMap<>();
        q = new LinkedList<>();
        for (int num: nums) {
            q.offer(num);
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
    }

    // Return the first unique number in the queue
    public int showFirstUnique() {
        // TODO: implement logic

        while (!q.isEmpty() && map.get(q.peek()) > 1) {
            q.poll();
        };
        return q.isEmpty()? -1 : q.peek();
    }

    // Add a number to the queue
    public void add(int value) {
        // TODO: implement logic
        q.add(value);
        if (!map.containsKey(value)) {
            map.put(value, 0);
        }
        map.put(value, map.get(value) + 1);
    }

    public static void main(String[] args) {
        // Example test
        FirstUnique firstUnique = new FirstUnique(new int[]{2, 3, 5});
        System.out.println(firstUnique.showFirstUnique()); // 2
        firstUnique.add(5);
        System.out.println(firstUnique.showFirstUnique()); // 2
        firstUnique.add(2);
        System.out.println(firstUnique.showFirstUnique()); // 3
        firstUnique.add(3);
        System.out.println(firstUnique.showFirstUnique()); // -1
    }
}
