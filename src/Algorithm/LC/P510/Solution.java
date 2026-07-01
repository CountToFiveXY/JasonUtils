package Algorithm.LC.P510;

/**
 * LeetCode 510. Inorder Successor in BST II
 *
 * Problem Statement:
 * Given a binary search tree (BST) where each node has a reference to its parent,
 * find the in-order successor of a given node.
 *
 * The successor of a node is the node with the smallest key greater than the current node's value.
 *
 * Each node has the following structure:
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 *
 * Example:
 * Input: node = 5 (in the tree [2,1,3,null,null,4,6,null,null,null,null,null,7])
 * Output: 6
 *
 * Explanation: The in-order traversal of the tree is [1,2,3,4,5,6,7].
 * The successor of node 5 is node 6.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^5 <= Node.val <= 10^5
 * - All Node.val are unique.
 */

public class Solution {

    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * Finds the inorder successor of a given node in a BST with parent pointers.
     *
     * @param node the node whose inorder successor is to be found
     * @return the inorder successor node, or null if none exists
     */
    public Node inorderSuccessor(Node node) {
        // TODO: Implement the logic

        Node parent = getSmallestParent(node);
        Node child = getRightSmallNode(node);

        if (parent == null && child ==null) {
            return null;
        } else if (parent == null) {
            return child;
        }
        return parent;
    }

    private Node getSmallestParent(Node node) {
        Node curr = node.parent;
        while(curr != null) {
            if (curr.val > node.val) {
                return curr;
            }
            curr = curr.parent;
        }
        return null;
    }

    private Node getRightSmallNode(Node node) {
        Node curr = node.right;
        Node res = null;
        while(curr != null) {
            res = curr;
            curr = curr.left;
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // TODO: Create sample tree and test cases
        Node root = new Node(2);
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        root.left = node1;
        root.right = node3;
        node1.parent = root;
        node3.parent = root;

        // Example test
        Node result = solution.inorderSuccessor(node1);
        System.out.println("Inorder successor of " + node1.val + " is: " + (result != null ? result.val : "null"));
    }
}
