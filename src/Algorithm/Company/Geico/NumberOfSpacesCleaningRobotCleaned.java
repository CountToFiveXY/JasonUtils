package Algorithm.Company.Geico;

import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * LeetCode 2061 (Premium)
 * Number of Spaces Cleaning Robot Cleaned
 *
 * A robot is placed on a grid:
 *  - 0 = empty space
 *  - 1 = obstacle
 *
 * The robot starts at a given position and direction.
 * It moves forward until blocked, then turns right.
 *
 * The robot stops when it repeats a state
 * (same position + same direction).
 *
 * Return the number of unique cells cleaned.
 *
 * Directions:
 * 0 = up, 1 = right, 2 = down, 3 = left
 *
 * Diagram:
 * Grid:
 *  [0, 0, 1]
 *  [0, 0, 0]
 *  [1, 0, 0]
 *
 * Start at (1,1), facing up
 */


public class NumberOfSpacesCleaningRobotCleaned {

    public static int numberOfCleanedSpaces(int[][] room, int startRow, int startCol, int dir) {
        int m = room.length, n = room[0].length;

        // 🔧 Fix #1: start on obstacle
        if (room[startRow][startCol] == 1) return 0;

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[][][] visited = new boolean[m][n][4];

        while (!visited[startRow][startCol][dir]) {
            visited[startRow][startCol][dir] = true;

            int dr = dirs[dir][0];
            int dc = dirs[dir][1];

            int r = startRow, c = startCol;

            while (r >= 0 && r < m && c >= 0 && c < n && room[r][c] != 1) {
                room[r][c] = 2;
                r += dr;
                c += dc;
            }

            startRow = r - dr;
            startCol = c - dc;
            dir = (dir + 1) % 4;
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (room[i][j] == 2) count++;
            }
        }
        return count;
    }

    private static int getNextDir(int cur) {
        if (cur == 3) {
            return 0;
        }
        return cur + 1;
    }

    private static void assertResult(String testName, int actual, int expected) {
        System.out.println(testName + ": " +
                (actual == expected ? "MATCH ✅" : "NOT MATCH ❌ (got " + actual + ")"));
    }

    public static void main(String[] args) {
        int[][] room1 = {
                {0, 0, 1},
                {0, 0, 0},
                {1, 0, 0}
        };
        // Happy
        assertResult("Happy-1", numberOfCleanedSpaces(room1, 1, 1, 0), 3);
        // Edge
        int[][] single = {{0}};
        assertResult("Edge-single-cell", numberOfCleanedSpaces(single, 0, 0, 0), 1);
        // Bad
        int[][] blocked = {{1}};
        assertResult("Bad-start-on-obstacle", numberOfCleanedSpaces(blocked, 0, 0, 0), 0);

        // Stress
        int[][] bigRoom = new int[50][50];
        assertResult("Stress-large-grid", numberOfCleanedSpaces(bigRoom, 25, 25, 0), 221);
    }
}
