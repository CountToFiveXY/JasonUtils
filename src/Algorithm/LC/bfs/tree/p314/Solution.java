package Algorithm.LC.bfs.tree.p314;

/**
 * LeetCode 314: Binary Tree Vertical Order Traversal
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 */

import java.util.*;

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashMap<TreeNode, Integer> col = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        int min = 0, max = 0;
        // TODO: implement solution

        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        col.put(root, 0);

        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                int num = col.get(curr);
                map.computeIfAbsent(num, k -> new ArrayList<>()).add(curr.val);
                if (curr.left != null) {
                    q.offer(curr.left);
                    col.put(curr.left, num - 1);
                    min = Math.min(min, num - 1);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                    col.put(curr.right, num + 1);
                    max = Math.max(max, num + 1);
                }
            }
        }
        for(int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example test case
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );

        List<List<Integer>> result = solution.verticalOrder(root);
        System.out.println(result); // Expected: [[9],[3,15],[20],[7]]
    }
}

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}