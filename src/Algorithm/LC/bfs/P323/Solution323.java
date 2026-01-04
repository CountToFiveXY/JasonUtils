package Algorithm.LC.bfs.P323;

/**
 * LeetCode 323: Number of Connected Components in an Undirected Graph
 *
 * You have a graph of n nodes labeled from 0 to n-1. You are given an integer n
 * and an array edges where edges[i] = [ai, bi] indicates that there is an
 * undirected edge between nodes ai and bi.
 *
 * Return the number of connected components in the graph.
 *
 * Example:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Notes:
 * - Solutions typically use either BFS, DFS, or Union-Find (Disjoint Set).
 * - Time complexity depends on the approach:
 *   - BFS/DFS: O(n + e)
 *   - Union-Find: O(n + e * α(n)) where α(n) is inverse Ackermann function
 */

public class Solution323 {

    /**
     * Returns the number of connected components in the graph.
     * @param n number of nodes (0 to n-1)
     * @param edges undirected edges
     * @return number of connected components
     */
    public int countComponents(int n, int[][] edges) {
        // TODO: implement BFS / DFS / Union-Find
        return 0;
    }


    // ---------- Simple test harness ----------
    public static void main(String[] args) {
        Solution323 sol = new Solution323();

        int n1 = 5;
        int[][] edges1 = {{0,1},{1,2},{3,4}};
        System.out.println("Example 1 expected=2, got=" + sol.countComponents(n1, edges1));

        int n2 = 5;
        int[][] edges2 = {{0,1},{1,2},{2,3},{3,4}};
        System.out.println("Example 2 expected=1, got=" + sol.countComponents(n2, edges2));
    }
}
