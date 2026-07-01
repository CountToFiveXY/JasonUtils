package Algorithm.LC.P1485;

/**
 * LeetCode 1485. Clone Binary Tree With Random Pointer
 *
 * Problem Statement:
 * A binary tree is given such that each node contains an integer value,
 * a left child, a right child, and an additional random pointer which
 * could point to any node in the tree or null.
 *
 * You need to create a deep copy of the tree.
 *
 * Example:
 * Input:
 *     1
 *    / \
 *   2   3
 *  random -> 3
 *
 * Output:
 * A cloned tree identical in structure and values, with random pointers preserved.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 1000].
 * - Each node's value is unique.
 */

import java.util.*;

class Node {
    int val;
    Node left;
    Node right;
    Node random;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.random = null;
    }
}

class Solution {

    HashMap<Node, Node> map = new HashMap<>();
    public Node cloneTree(Node root) {

        if (root == null) {
            return null;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        Node clone = new Node(root.val);
        map.put(root, clone);

        clone.left = cloneTree(root.left);
        clone.right = cloneTree(root.right);
        clone.random = cloneTree(root.random);

        return clone;
    }

    public static void main(String[] args) {
        // Example test case

        // Build original tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.random = root.right; // random pointer from 1 -> 3

        Solution sol = new Solution();
        Node cloned = sol.cloneTree(root);

        // Simple validation
        System.out.println("Original root: " + root.val);
        System.out.println("Cloned root: " + (cloned != null ? cloned.val : "null"));

        System.out.println("Original random points to: " + (root.random != null ? root.random.val : "null"));
        System.out.println("Cloned random points to: " + (cloned.random != null ? cloned.random.val : "null"));
    }
}

