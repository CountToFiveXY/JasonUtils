package Algorithm.LC.DONE;

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

public class P490 {

    /**
     * Determine whether the ball can stop at destination.
     * @param maze binary grid (0 empty, 1 wall)
     * @param start [sr, sc]
     * @param destination [dr, dc]
     * @return true if reachable (stoppable at destination)
     */

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0 ,-1},{0, 1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // TODO: implement BFS or DFS that "rolls" until a wall
        int m = maze.length, n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0], col = curr[1];

            if (row == destination[0] && col == destination[1]) {
                return true;
            }

            for (int[] dir: dirs) {
                int[] stop = getNextStop(maze, row, col, dir);
                int sr = stop[0], sc = stop[1];
                if (!visited[sr][sc]) {
                    q.offer(stop);
                    visited[sr][sc] = true;
                }
            }
        }
        return false;
    }

    private int[] getNextStop(int[][] maze, int row, int col, int[] dir) {
        int dr = dir[0], dc = dir[1];

        while(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length
                && maze[row][col] == 0) {
            row += dr;
            col += dc;
        }

        //step back
        row -= dr;
        col -= dc;
        return new int[] {row, col};
    }




    // ---------- Simple test harness ----------
    public static void main(String[] args) {
        P490 sol = new P490();

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
