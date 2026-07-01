package Algorithm.LC.TOP_1000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class P281 {

    /*
    Description:

    Design and implement an iterator to return elements from two lists in zigzag order.

    Given two List<Integer> instances, v1 and v2, return their elements alternately,
    starting with the first list. If one list runs out of elements,
    continue returning elements from the other list.

    Example:
    List<Integer> v1 = Arrays.asList(1, 2);
    List<Integer> v2 = Arrays.asList(3, 4, 5, 6);

    // Zigzag order:
    // next() calls should return: 1, 3, 2, 4, 5, 6

     */

    Queue<Integer> q;
    public P281(List<Integer> v1, List<Integer> v2) {
        // constructor: initialize the iterator
        q =  new LinkedList<>();
        int i = 0;
        while (i < v1.size() && i <v2.size()) {
            q.offer(v1.get(i));
            q.offer(v2.get(i));
            i++;
        }
        while (i < v1.size()) {
            q.offer(v1.get(i));
            i++;
        }
        while (i < v2.size()) {
            q.offer(v2.get(i));
            i++;
        }
    }

    public int next() {
        // return the next element in zigzag order;
        return q.poll();
    }

    public boolean hasNext() {
        // return true if there are remaining elements
        return !q.isEmpty();
    }

    public static void main(String[] args) {
        List<Integer> v1 = Arrays.asList(1, 2);
        List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
        P281 m = new P281(v1, v2);

        List<Integer> result =  Stream.of(m.next(), m.next(), m.next(), m.next(), m.next(), m.next()).toList();
        List<Integer> expected = Arrays.asList(1, 3, 2, 4, 5, 6);
        System.out.println(expected.equals(result));
    }
}
