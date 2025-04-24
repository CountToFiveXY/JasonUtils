package Algorithm.scan_line;

/*
Description
Given a list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?

Example 1:

Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
Output: 3
Explanation:
The first airplane takes off at 1 and lands at 10.
The second airplane takes off at 2 and lands at 3.
The third airplane takes off at 5 and lands at 8.
The forth airplane takes off at 4 and lands at 7.
During 5 to 6, there are three airplanes in the sky.

Example 2:

Input: [(1, 2), (2, 3), (3, 4)]
Output: 1
Explanation: Landing happen before taking off.
 */

import java.util.ArrayList;
import java.util.List;

public class Number_of_Airplanes_in_the_Sky {

    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class CheckPoint {
        int time, count;
        CheckPoint(int time, int count) {
            this.time = time;
            this.count = count;
        }
    }

    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        List<CheckPoint> checkPoints = new ArrayList<>(airplanes.size() * 2);
        for (Interval i : airplanes) {
            CheckPoint cp1 = new CheckPoint(i.start, 1);
            CheckPoint cp2 = new CheckPoint(i.end, -1);
            checkPoints.add(cp1);
            checkPoints.add(cp2);
        }

        checkPoints.sort((CheckPoint cp1, CheckPoint cp2) -> {
            if (cp1.time == cp2.time) {
                return cp1.count - cp2.count;
            }
            return cp1.time - cp2.time;
        });

        int count = 0;
        int res = 0;
        for (CheckPoint cp : checkPoints) {
            if (cp.count == 1) {
                count +=1;
            } else {
                count -=1;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public void run() {
        List<Interval> input1 = new ArrayList<>();

        // Adding intervals to the list
        input1.add(new Interval(1, 10));
        input1.add(new Interval(2, 3));
        input1.add(new Interval(5, 8));
        input1.add(new Interval(4, 7));

        System.out.println(countOfAirplanes(input1));


        List<Interval> input2 = new ArrayList<>();

        // Adding intervals to the list
        input2.add(new Interval(1, 2));
        input2.add(new Interval(2, 3));
        input2.add(new Interval(3, 4));

        System.out.println(countOfAirplanes(input2));
    }
}
