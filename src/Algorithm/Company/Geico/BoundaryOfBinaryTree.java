package Algorithm.Company.Geico;

import java.util.*;

/**
 * LeetCode (Premium)
 * Boundary of Binary Tree
 *
 * The boundary of a binary tree consists of:
 * 1) Root
 * 2) Left boundary (excluding leaves)
 * 3) All leaves (left to right)
 * 4) Right boundary (excluding leaves, reversed)
 *
 * Return the boundary as a list of node values.
 *
 * Diagram:
 *
 *        1
 *       / \
 *      2   3
 *     / \   \
 *    4   5   6
 *       / \
 *      7   8
 *
 * Boundary: [1, 2, 4, 7, 8, 6, 3]
 */

public class BoundaryOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        if (!isLeaf(root)) res.add(root.val);

        addLeft(root.left, res);
        addLeaves(root, res);
        addRight(root.right, res);

        return res;
    }

    private static void addLeft(TreeNode curr, List<Integer> res) {
        while(curr != null) {
            if (!isLeaf(curr)) res.add(curr.val);
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    private static void addLeaves(TreeNode curr, List<Integer> res) {
        if (curr == null) {
            return;
        }
        if (isLeaf(curr)) {
            res.add(curr.val);
            return;
        }
        addLeaves(curr.left, res);
        addLeaves(curr.right, res);
    }

    private static void addRight(TreeNode curr, List<Integer> res) {
        Stack<Integer> stack = new Stack<>();
        while(curr != null) {
            if (!isLeaf(curr)) stack.push(curr.val);
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        while(!stack.isEmpty()) {
            res.add(stack.pop());
        }
    }

    private static boolean isLeaf(TreeNode curr) {
        return curr.left == null && curr.right == null;
    }



    private static void assertResult(String testName, List<Integer> actual, List<Integer> expected) {
        System.out.println(testName + ": " +
                (actual.equals(expected) ? "MATCH ✅" : "NOT MATCH ❌ (got " + actual + ")"));
    }

    public static void main(String[] args) {
        // Happy

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);



        assertResult("Happy-1",
                boundaryOfBinaryTree(root),
                Arrays.asList(1, 2, 4, 7, 8, 6, 3));

        // Edge
        TreeNode single = new TreeNode(1);
        assertResult("Edge-single-node",
                boundaryOfBinaryTree(single),
                Arrays.asList(1));

        // Bad
        assertResult("Bad-null-root",
                boundaryOfBinaryTree(null),
                Collections.emptyList());

        // Stress
        TreeNode chain = new TreeNode(1);
        TreeNode cur = chain;
        for (int i = 2; i <= 1000; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertResult("Stress-deep-tree",
                boundaryOfBinaryTree(chain),
                null); // boundary shape-dependent
    }
}
