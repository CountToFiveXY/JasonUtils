package Algorithm.LC.bs.P1891;

/*
LeetCode 1891: Cutting Ribbons

You are given a 0-indexed integer array ribbons, where ribbons[i] represents the length of the ith ribbon,
and an integer k. You may cut any of the ribbons into any number of smaller ribbons of positive integer length.

Return the maximum integer length you can cut all the ribbons into such that you can obtain at least k ribbons.
If it is not possible to obtain k ribbons, return 0.

Example 1:
Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon of length 9 into lengths [5,4].
- Cut the second ribbon of length 7 into lengths [5,2].
- Third ribbon of length 5 remains as is.
- We have 3 ribbons of length 5. No way to get longer ribbons and at least 3 pieces.

Example 2:
Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut ribbons to [4,3], [4,1], [5,4] → at least 4 ribbons of length 4.

Constraints:
- 1 <= ribbons.length <= 10^5
- 1 <= ribbons[i] <= 10^5
- 1 <= k <= 10^9
*/

class CuttingRibbons {

    public int maxLength(int[] ribbons, int k) {
        // TODO: implement binary search solution
        int n = ribbons.length;
        int sum = 0, max = 0;

        for (int ribbon: ribbons) {
            sum += ribbon;
            max = Math.max(ribbon, max);
        }

        if(k > sum) {
            return 0;
        }

        int left = 1, right = max;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if (getCount(mid, ribbons) < k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private int getCount(int len, int[] ribbons) {
        int count = 0;
        for (int ribbon: ribbons) {
            if (ribbon < len) {
                continue;
            }
            count += ribbon / len;
        }
        return count;
    }

    // ----- Test Harness -----
    public static void main(String[] args) {
        CuttingRibbons sol = new CuttingRibbons();

        int[] ribbons1 = {9, 7, 5};
        int k1 = 3;
        System.out.println("Test 1: " + sol.maxLength(ribbons1, k1)); // Expected: 5

        int[] ribbons2 = {7, 5, 9};
        int k2 = 4;
        System.out.println("Test 2: " + sol.maxLength(ribbons2, k2)); // Expected: 4

        int[] ribbons3 = {5, 7, 9};
        int k3 = 22;
        System.out.println("Test 3: " + sol.maxLength(ribbons3, k3)); // Expected: 0
    }
}

