package Algorithm.LC.dfs.P333;

/**
 * LeetCode 333. Largest BST Subtree
 *
 * Problem Statement:
 * Given the root of a binary tree, return the size of the largest subtree
 * that is also a Binary Search Tree (BST).
 *
 * A Binary Search Tree (BST) is defined as a binary tree in which,
 * for every node, the values of all the nodes in the left subtree are
 * less than the node’s value, and the values of all the nodes in the
 * right subtree are greater than the node’s value.
 *
 * Example:
 * Input:
 *        10
 *       /  \
 *      5   15
 *     / \    \
 *    1   8    7
 *
 * Output: 3
 * Explanation: The largest BST subtree is:
 *      5
 *     / \
 *    1   8
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -10^4 <= Node.val <= 10^4
 */

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

public class Solution {

    class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(boolean isBST, int size, int min,int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    int max;

    public int largestBSTSubtree(TreeNode root) {
        // TODO: Implement the DFS or helper-based solution
        max = 0;
        dfs(root);
        return max;
    }

    private Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info left = dfs(root.left);
        Info right = dfs(root.right);

        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            int size = 1 + left.size + right.size;
            if (size > max) {
                max = size;
            }
            int min = left.min == Integer.MAX_VALUE? root.val : left.min;
            int max = right.max == Integer.MIN_VALUE? root.val : right.max;
            return new Info(true, size, min, max);
        }
        return new Info(false, 0, 0, 0);
    }

    // Example test cases
    public static void main(String[] args) {
        // Build example tree:
        //        10
        //       /  \
        //      5   15
        //     / \    \
        //    1   8    7
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5, new TreeNode(1), new TreeNode(8));
        root.right = new TreeNode(15, null, new TreeNode(7));

        Solution sol = new Solution();
        int result = sol.largestBSTSubtree(root);

        System.out.println("Largest BST Subtree size: " + result);
    }
}
