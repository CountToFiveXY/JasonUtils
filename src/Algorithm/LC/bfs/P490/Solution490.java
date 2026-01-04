package Algorithm.LC.bfs.P490;

import java.util.*;

/**
 * LeetCode 490: The Maze
 *
 * You are given a m x n binary matrix maze (0 = empty, 1 = wall), and two arrays start = [sr, sc]
 * and destination = [dr, dc] where start is the starting cell and destination is the target cell.
 *
 * The ball can only roll up, down, left, or right, and it won't stop until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Return true if the ball can stop at the destination, otherwise return false.
 *
 * Example:
 * Input:
 * maze = [
 *  [0,0,1,0,0],
 *  [0,0,0,0,0],
 *  [0,0,0,1,0],
 *  [1,1,0,1,1],
 *  [0,0,0,0,0]
 * ],
 * start = [0,4], destination = [4,4]
 *
 * Output: true
 *
 * Notes:
 * - Typical solutions use BFS or DFS while simulating rolling until hitting a wall.
 * - Time: O(m*n) in practice (each cell visited a constant number of times).
 * - Space: O(m*n).
 */

public class Solution490 {

    /**
     * Determine whether the ball can stop at destination.
     * @param maze binary grid (0 empty, 1 wall)
     * @param start [sr, sc]
     * @param destination [dr, dc]
     * @return true if reachable (stoppable at destination)
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // TODO: implement BFS or DFS that "rolls" until a wall
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{-1, 0}, {1, 0},{0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int[] dir: dirs) {
                int x = curr[0], y = curr[1];
                while(x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                int newX = x - dir[0], newY = y - dir[1];

                if (newX == destination[0] && newY == destination[1]) {
                    return true;
                }
                if (visited[newX][newY]) continue;
                q.offer(new int[] {newX, newY});
                visited[newX][newY] = true;
            }

        }
        return false;
    }

    private void addNextStop(int[][] maze, int[] start, int[] direction, boolean[][] visited, Queue<int[]> q) {
        int row = start[0], col = start[1], m = maze.length, n = maze[0].length;
        int x = direction[0], y = direction[1];

        while (row >=0 && row < m  && col >=0 && col < n && maze[row][col] != 1) {
            row += x;
            col += y;
        }
        int[] stop = new int[] {row - x, col - y};
        if (!visited[row-x][col-y]) {
           q.add(stop);
        }
    }

    // ---------- Simple test harness ----------
    public static void main(String[] args) {
        Solution490 sol = new Solution490();

        int[][] maze1 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };
        int[] start1 = {0,4};
        int[] dest1  = {4,4};
        System.out.println("Example 1 expected=true, got=" + sol.hasPath(maze1, start1, dest1));

        // another test: unreachable
        int[][] maze2 = {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}
        };
        int[] start2 = {0,4};
        int[] dest2  = {3,2}; // choose a spot that cannot be stopped at
        System.out.println("Example 2 expected=false, got=" + sol.hasPath(maze2, start2, dest2));
    }
}
