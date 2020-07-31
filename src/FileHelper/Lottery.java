package FileHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Lottery {

    //抽签次数
    private static int lotteryTime = 500;
    //报名人数
    private static int candidates;
    //参赛人数
    private static int attendees;

    public Lottery(int candidateNum, int attendeeNum) {
        candidates = candidateNum;
        attendees = attendeeNum;
    }

    public void ballot() {

        List<Integer> list = new ArrayList<>();

        //抽1000次
        for (int i = 0; i < lotteryTime; i++) {
            int temp = new Random().nextInt(candidates);
            list.add(temp);
        }
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < candidates; i++) {
            map.put(i, 0);
        }
        //count each recurrence
        list.forEach(num -> {
            map.put(num, map.get(num) + 1);
        });

        List<Integer> attendee = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> entry.getKey() + 1)
                .collect(Collectors.toList());

        System.out.println("参赛选手: " + attendee.subList(0, attendees) + " 替补选手: " + attendee.subList(attendees, candidates));
        System.out.println("队伍1: " + attendee.subList(0, 3) + " -> [ , , ]");
        System.out.println("队伍2: " + attendee.subList(3, 6) + " -> [ , , ]");
        System.out.println("队伍3: " + attendee.subList(6, 9) + " -> [ , , ]");
        System.out.println("队伍4: " + attendee.subList(9, 12) + " -> [ , , ]");
    }
}
