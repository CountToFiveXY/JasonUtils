package Algorithm.LC.TOP_1000;

import java.util.Stack;

/**
 * LC 255 — Verify Preorder Sequence in Binary Search Tree
 *
 * Problem:
 * Given an integer array preorder, return true if it is possible to construct
 * a Binary Search Tree (BST) with the given preorder traversal sequence.
 *
 * A BST is defined as:
 *  - Left subtree values < root
 *  - Right subtree values > root
 *  - Both subtrees are BST
 *
 * Example 1:
 * Input:  [5, 2, 1, 3, 6]
 * Output: true
 *
 * BST formed:
 *
 *          5
 *        /   \
 *       2     6
 *      / \
 *     1   3
 *
 * Preorder = Root → Left → Right
 *
 * Example 2:
 * Input:  [5, 2, 6, 1, 3]
 * Output: false
 *
 * Explanation:
 * 6 appears in right subtree of 5,
 * but later 1 < 5 violates BST property.
 *
 *
 * Constraints:
 * 1 <= preorder.length <= 10^4
 * All values are unique.
 *
 * Follow up:
 * Can you solve it using only O(1) extra space?
 *
 */

public class P255 {

    // ======================================================
    // 🚫 DO NOT IMPLEMENT (Skeleton Only)
    // ======================================================
    public static boolean verifyPreorder(int[] preorder) {
        // TODO: Implement solution here
        Stack<Integer> stack = new Stack<>();

        if (preorder == null || preorder.length == 0) {
            return true;
        }

        int root = -1;
        for (int x: preorder) {
            if (x < root) {
                return false;
            }
            while (!stack.isEmpty() && x > stack.peek()) {
                root = stack.pop();
            }
            stack.push(x);
        }
        return true;
    }

    // ======================================================
    // 🧪 Test Harness
    // ======================================================
    public static void main(String[] args) {

        // -------------------------
        // ✅ Happy Case
        // -------------------------
        int[] happy1 = {5, 2, 1, 3, 6};
        boolean expected1 = true;
        runTest("Happy 1", happy1, expected1);

        // -------------------------
        // ❌ Invalid Case
        // -------------------------
        int[] bad1 = {5, 2, 6, 1, 3};
        boolean expected2 = false;
        runTest("Invalid 1", bad1, expected2);

        // -------------------------
        // 🟡 Edge Case: Single Node
        // -------------------------
        int[] edge1 = {10};
        boolean expected3 = true;
        runTest("Edge Single", edge1, expected3);

        // -------------------------
        // 🟡 Edge Case: Increasing Order
        // Right-skewed BST
        // -------------------------
        int[] edge2 = {1, 2, 3, 4, 5};
        boolean expected4 = true;
        runTest("Edge Increasing", edge2, expected4);

        // -------------------------
        // 🟡 Edge Case: Decreasing Order
        // Left-skewed BST
        // -------------------------
        int[] edge3 = {5, 4, 3, 2, 1};
        boolean expected5 = true;
        runTest("Edge Decreasing", edge3, expected5);

        // -------------------------
        // ❌ Subtle Violation
        // -------------------------
        int[] bad2 = {8, 5, 1, 7, 10, 12, 6};
        boolean expected6 = false;
        runTest("Invalid 2", bad2, expected6);

        // -------------------------
        // 🚀 Stress Test (Large Increasing)
        // This can cause TLE if solution is O(n²)
        // -------------------------
        int n = 10000;
        int[] stress = new int[n];
        for (int i = 0; i < n; i++) {
            stress[i] = i + 1;
        }
        boolean expectedStress = true;
        runTest("Stress Increasing 10k", stress, expectedStress);
    }

    private static void runTest(String name, int[] input, boolean expected) {
        boolean result = verifyPreorder(input);
        String status = (result == expected) ? "Match ✅" : "Not Match ❌";
        System.out.println(name + " -> " + status);
    }
}
