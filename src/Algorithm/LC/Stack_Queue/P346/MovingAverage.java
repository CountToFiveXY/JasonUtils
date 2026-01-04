package Algorithm.LC.Stack_Queue.P346;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Stream;

/*
    Given a stream of integers and a window size, calculate the
    moving average of all integers in the sliding window.

    MovingAverage m = new MovingAverage(3);
    m.next(1) = 1
    m.next(10) = (1 + 10) / 2
    m.next(3) = (1 + 10 + 3) / 3
    m.next(5) = (10 + 3 + 5) / 3
*/

public class MovingAverage {
    Queue<Integer> q;
    int size;
    double sum = 0;

    public MovingAverage(int size) {
        this.size = size;
        q = new LinkedList<>();
    }

    public double next(int val) {
        if (q.size() == size) {
            sum -= q.poll();
        }
        sum += val;
        q.offer(val);
        return sum/q.size();
    }

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        List<Double> result =  Stream.of(m.next(1), m.next(10), m.next(3), m.next(5)).toList();
        List<Double> expected = Arrays.asList(1.0, 5.5, 14.0/3, 18.0/3);
        System.out.println(expected.equals(result));
    }
}
