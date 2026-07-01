package Algorithm.LC.P505;

import java.util.*;

/**
 * LeetCode 505: The Maze II
 *
 * You are given an m x n maze consisting of 0s (empty spaces) and 1s (walls).
 * The ball starts at `start[] = {sr, sc}` and the goal is to reach
 * `destination[] = {dr, dc}`.
 *
 * The ball can roll in 4 directions: up, down, left, right.
 * BUT it continues rolling **until it hits a wall**, not stopping in between.
 * Once stopped, it may choose another direction.
 *
 * Return:
 *   The minimum distance traveled to reach destination.
 *   If it is impossible, return -1.
 *
 * This is a shortest-path problem with weighted edges
 * (the ball may roll variable distances each move).
 * Typical approach is **Dijkstra with a Min-Heap**.
 *
 * Example:
 * --------
 * maze =
 *   0 0 1 0 0
 *   0 0 0 0 0
 *   0 0 0 1 0
 *   1 1 0 1 1
 *   0 0 0 0 0
 *
 * start = [0, 4]
 * destination = [4, 4]
 *
 * ASCII Visualization:
 * --------------------
 *   (0,4) start → · · █ · S
 *                   · · · · ·
 *                   · · · █ ·
 *                   █ █ · █ █
 *                   · · · · D  ← (4,4) destination
 *
 * Notes:
 * - Dijkstra: O(m*n*log(m*n))
 * - BFS isn't enough because moves are weighted (rolling distance varies).
 */

public class Solution505 {

    /**
     * Computes the shortest rolling distance from start to destination.
     * @param maze grid of 0 = empty, 1 = wall
     * @param start starting cell
     * @param destination target cell
     * @return minimum distance or -1 if unreachable
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // TODO: implement Dijkstra with a min-heap
        int m = maze.length, n = maze[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {1, 0}, {-1, 0}};

        int[][] distance = new int[m][n];

        Queue<int[]> q = new LinkedList<>();

        q.offer(start);

        while(!q.isEmpty()) {
            int[] curr = q.poll();


            for (int[] dir:  dirs) {
                int x = curr[0], y = curr[1];

                int dis = 0;

                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    dis++;
                }
                int endX = x - dir[0], endY = y - dir[1];

                int newDis = distance[curr[0]][curr[1]] + dis - 1;

                if (distance[endX][endY] == 0 ) {
                    distance[endX][endY] = newDis;
                } else if  (newDis >= distance[endX][endY]) {
                    continue;
                } else {
                    distance[endX][endY] = Math.min(distance[endX][endY], newDis);
                }

                q.offer(new int[]{endX, endY});
            }
        }

        return distance[destination[0]][destination[1]] == 0? -1: distance[destination[0]][destination[1]];
    }


    // -------------------------
    // Simple test harness (main)
    // -------------------------
    public static void main(String[] args) {
        Solution505 sol = new Solution505();

        int[][] maze1 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };

        int[] start1 = {0, 4};
        int[] dest1 = {4, 4};

        System.out.println("Test 1 expected=12, got=" +
                sol.shortestDistance(maze1, start1, dest1));

        int[] dest2 = {3, 2};
        System.out.println("Test 2 expected=-1, got=" +
                sol.shortestDistance(maze1, start1, dest2));
    }
}
