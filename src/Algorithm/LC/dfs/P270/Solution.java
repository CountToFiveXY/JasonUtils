package Algorithm.LC.dfs.P270;

/**
 * LeetCode 270. Closest Binary Search Tree Value
 *
 * Problem:
 * Given the root of a binary search tree and a target value, return the value
 * in the BST that is closest to the target.
 *
 * Notes:
 * - The tree is a BST (left < node < right).
 * - There will be only one unique value in the tree that is closest to the target.
 *
 * Example:
 * Input: root = [4,2,5,1,3], target = 3.714286
 *    4
 *   / \
 *  2   5
 * / \
 *1   3
 *
 * Output: 4
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^9 <= Node.val <= 10^9
 * - target is a floating point.
 *
 * Goal:
 * Provide a clean implementation of:
 *   public int closestValue(TreeNode root, double target)
 *
 * Typical approaches:
 * - Iterative BST walk using the BST property to move left/right,
 *   tracking the closest value encountered (O(h) time, O(1) space).
 * - Recursive traversal with the same idea.
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

class Solution {
    /**
     * Return the value in the BST that is closest to target.
     * TODO: implement (iterative or recursive).
     */


    int diff;
    public int closestValue(TreeNode root, double target) {

        int closest = root.val;
        while(root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }

            if (root.val < target) {
                root = root.right;
            } else if (root.val == target) {
                return root.val;
            } else {
                root = root.left;
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        //
        //      4
        //     / \
        //    2   5
        //   / \
        //  1   3
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(5));
        double target1 = 3.714286;
        System.out.println("Expected: 4, Got: " + sol.closestValue(root1, target1));

        // Example 2: single-node tree
        TreeNode root2 = new TreeNode(1);
        double target2 = 0.42;
        System.out.println("Expected: 1, Got: " + sol.closestValue(root2, target2));

        // Example 3: negative values
        TreeNode root3 = new TreeNode(0,
                new TreeNode(-10),
                new TreeNode(10));
        double target3 = 6.7;
        System.out.println("Expected: 10, Got: " + sol.closestValue(root3, target3));
    }
}
