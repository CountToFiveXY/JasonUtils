package Algorithm.LC.P1060;

/*
LeetCode 1060: Missing Element in Sorted Array

Given a sorted array `nums` (strictly increasing) and an integer `k`,
return the k-th missing number starting from the first element of `nums`.

Example 1:
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.

Example 2:
Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: Missing numbers are [5,6,8], so the 3rd is 8.

Example 3:
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: Missing numbers are [3,5,6], so the 3rd is 6.

Constraints:
- 1 <= nums.length <= 5 * 10^4
- 1 <= nums[i] <= 10^7
- nums is strictly increasing.
- 1 <= k <= 10^8
*/

class Missing_Element {
    public int missingElement(int[] nums, int k) {
        // TODO: Implement this method

        int n = nums.length;

        int left = 0 , right = nums.length - 1;

        int totalMissing = nums[n - 1] - nums[0] - (n - 1);

        // If kth missing number is beyond the last element
        if (k > totalMissing) {
            return nums[n - 1] + (k - totalMissing);
        }

        while (left <= right) {
            int mid = left + (right - left)/2;
            //nums[mid] is expected to be  = nums[0] + mid;
            int missing  = nums[mid] - nums[0] - mid;
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        // now we find the index, the missing count is x = nums[right] - (nums[0] + right)
        // there are still k - x we need to fill
        return nums[right] + (k - (nums[right] - (nums[0] + right)));
    }

    // ----- Test Harness -----
    public static void main(String[] args) {
        Missing_Element sol = new Missing_Element();

        // Test 1
        int[] nums1 = {4, 7, 9, 10};
        int k1 = 1;
        System.out.println("Test 1: " + sol.missingElement(nums1, k1)); // Expected: 5

        // Test 2
        int[] nums2 = {4, 7, 9, 10};
        int k2 = 3;
        System.out.println("Test 2: " + sol.missingElement(nums2, k2)); // Expected: 8

        // Test 3
        int[] nums3 = {1, 2, 4};
        int k3 = 3;
        System.out.println("Test 3: " + sol.missingElement(nums3, k3)); // Expected: 6
    }
}
