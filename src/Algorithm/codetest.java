package Algorithm;

import java.sql.Array;
import java.util.*;

public class codetest {


    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //sum to inex map

        int n = nums.length, count = 0;

        List<Integer> start = new ArrayList<>();
        start.add(-1);
        map.put(0, start);

        int[] prefix = new int[n];

        for (int i = 0 ; i < n; i ++) {
            prefix[i] = i == 0? nums[i] : nums[i] + prefix[i-1];

            System.out.println("i=" + i + ", prefix[i]=" +  prefix[i]);
            if (map.containsKey(prefix[i] - k)) {
                count += map.get(prefix[i] - k).size();
            }
            map.computeIfAbsent(prefix[i], key -> new ArrayList<>()).add(i);
        }
        return count;
    }
}
