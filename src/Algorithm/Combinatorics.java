package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class Combinatorics {

    //Permutation-----------------//
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

    //PutBall-----------------//
    public List<List<Integer>> putBall(int ballNumber, int boxNumber) {
        List<List<Integer>> result = new ArrayList<>();
        int[] inputBox = new int[boxNumber];
        put(inputBox, 0, ballNumber, result);
        return result;
    }

    private void put(int[] temp, int index, int total, List<List<Integer>> result) {
        int len = temp.length;
        if (total == 0 || index == len - 1) {
            temp[len-1] = total;
            result.add(convertArrayToList(temp));
        } else {
            for (int i = 0; i <= total;i++) {
                temp[index] = i;
                put(temp, index +1,total -i, result);
                temp[index+1] = 0;
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