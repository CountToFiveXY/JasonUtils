package Algorithm.LC.topoSort.P444;
/**
 * 444. Sequence Reconstruction
 *
 * Problem Statement:
 * You are given an integer array nums of length n which is a permutation of the integers from 1 to n.
 * You are also given a list of sequences, where each sequence is a subsequence of nums.
 *
 * Check whether nums is the one and only shortest sequence that can be reconstructed from sequences.
 * In other words, if there exists a sequence different from nums that can be constructed
 * from sequences, return false.
 *
 * Constraints:
 * - 1 <= n <= 10^4
 * - nums is a permutation of [1, 2, ..., n]
 * - 1 <= sequences.length <= 10^4
 * - 1 <= sequences[i].length <= 10^4
 * - The sum of sequences[i].length will not exceed 10^5
 *
 * Example:
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
 * Output: false
 *
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * Output: true
 */

import java.util.*;

class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        // TODO: Implement the algorithm (graph + topological sort)
        int n = nums.length;
        int[] degrees = new int[n+1];

        List<List<Integer>> neighbors = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            neighbors.add(new ArrayList<>());
        }

        for (List<Integer> list : sequences) {
            for (int i = 0; i < list.size() - 1; i++) {
                int curr = list.get(i);
                int next = list.get(i + 1);
                neighbors.get(curr).add(next);
                degrees[next]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n;i++) {
            if (degrees[i] == 0) {
                q.offer(i);
            }
        }

        System.out.println(q.peek() + " " + neighbors);
        int index = 0;
        while(!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int curr = q.poll();
            if (curr != nums[index]) {
                return false;
            }
            index++;
            for (int x: neighbors.get(curr)) {
                degrees[x]--;
                if (degrees[x] == 0) {
                    q.offer(x);
                }
            }
        }

        return index == n; // placeholder
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> sequences1 = new ArrayList<>();
        sequences1.add(Arrays.asList(1, 2));
        sequences1.add(Arrays.asList(1, 3));
        System.out.println(sol.sequenceReconstruction(nums1, sequences1)); // false

        // Test case 2
        int[] nums2 = {1, 2, 3};
        List<List<Integer>> sequences2 = new ArrayList<>();
        sequences2.add(Arrays.asList(1, 2));
        sequences2.add(Arrays.asList(1, 3));
        sequences2.add(Arrays.asList(2, 3));
        System.out.println(sol.sequenceReconstruction(nums2, sequences2)); // true
    }
}

