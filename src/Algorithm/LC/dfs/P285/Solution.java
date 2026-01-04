package Algorithm.LC.dfs.P285;

import java.util.Stack;

/**
 * LeetCode 285. Inorder Successor in BST
 *
 * Problem Statement:
 * Given the root of a binary search tree (BST) and a node `p` in it,
 * return the inorder successor of that node in the BST.
 * If the given node has no inorder successor in the tree, return null.
 *
 * The inorder successor of a node is the node with the smallest key
 * greater than the key of `p`.
 *
 * Example:
 * Input:
 *          5
 *         / \
 *        3   6
 *       / \
 *      2   4
 *     /
 *    1
 *
 * p = 3
 * Output: 4
 *
 * Explanation:
 * The inorder traversal of the tree is [1, 2, 3, 4, 5, 6].
 * The successor of node 3 is 4.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - All Node.val are unique.
 * - p is a node in the BST.
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // TODO: Implement the logic to find inorder successor
        Stack<TreeNode> stack  = new Stack<>();
        TreeNode curr = root;
        boolean next = false;

        while(curr != null || !stack.isEmpty()) {
            while(curr != null ) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            if (next) {
                return curr;
            }

            if (curr.val == p.val) {
                next = true;
            }
            curr = curr.right;
        }

        return null;
    }

    public static void main(String[] args) {
        /*
                5
               / \
              3   6
             / \
            2   4
           /
          1
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4));
        root.right = new TreeNode(6);

        Solution sol = new Solution();

        TreeNode p = root.left; // Node with value 3
        TreeNode successor = sol.inorderSuccessor(root, p);

        if (successor != null) {
            System.out.println("Inorder successor of " + p.val + " is " + successor.val);
        } else {
            System.out.println("Inorder successor of " + p.val + " does not exist.");
        }
    }
}
