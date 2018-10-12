package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class Combinatorics {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> putBall(int ballNumber, int boxNumber) {
        List<List<Integer>> list = new ArrayList<>();
        int[] result = new int[boxNumber];
        put(result, 0, ballNumber, list);
        return list;
    }

    private void put(int[] temp, int index, int total, List<List<Integer>> result) {
        if (total == 0 || index == 3) {
            temp[3] = total;
            result.add(convertArrayToList(temp));
        } else {
            for (int i = 0; i <= total;i++) {
                temp[index] = i;
                put(temp, index +1,total -i, result);
            }
        }
    }

    private List<Integer> convertArrayToList(int[] num) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        for (int h = 0; h < num.length; h++) {
            item.add(num[h]);
        }
        return item;
    }

}