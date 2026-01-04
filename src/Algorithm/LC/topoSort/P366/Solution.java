package Algorithm.LC.topoSort.P366;

/**
 * 366. Find Leaves of Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 * - Collect all the leaf nodes.
 * - Remove all the leaf nodes.
 * - Repeat until the tree is empty.
 *
 * Return a list of lists of integers where each inner list contains the values of the removed leaves at each step.
 *
 Example:
 * Input: root = [1,2,3,4,5]
 *
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 *
 * Step 1: remove leaves [4,5,3]
 *        1
 *       /
 *      2
 *
 * Step 2: remove leaves [2]
 *        1
 *
 * Step 3: remove leaf [1]
 *
 * Output: [[4,5,3],[2],[1]]
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 100].
 * - -100 <= Node.val <= 100
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 100].
 * - -100 <= Node.val <= 100
 */

import java.util.*;

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

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // TODO: implement the algorithm
        // childToParent node map
        Map<TreeNode, TreeNode> map = new HashMap<>();

        //child count
        Map<TreeNode, Integer> child = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> leaves = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            child.put(curr, 0);

            if (curr.left == null &&  curr.right == null) {
                leaves.offer(curr);
            }

            if (curr.left != null) {
                map.put(curr.left, curr);
                child.put(curr, child.get(curr) + 1);
                q.offer(curr.left);
            }

            if (curr.right != null) {
                map.put(curr.right, curr);
                child.put(curr, child.get(curr) + 1);
                q.offer(curr.right);
            }
        }

        while(!leaves.isEmpty()) {
            int size = leaves.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = leaves.poll();
                level.add(node.val);

                if (map.containsKey(node)) {
                    TreeNode parent = map.get(node);
                    int count = child.get(parent) - 1;

                    if (count == 0) {
                        leaves.offer(parent);
                    } else {
                        child.put(parent, count);
                    }
                }
            }

            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example test case: [1,2,3,4,5]
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));

        List<List<Integer>> result = sol.findLeaves(root);
        System.out.println(result); // Expected: [[4,5,3],[2],[1]]
    }
}

