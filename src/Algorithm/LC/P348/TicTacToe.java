package Algorithm.LC.P348;

/**
 * LeetCode 348: Design Tic-Tac-Toe
 *
 * Implement a TicTacToe class:
 * - TicTacToe(int n) Initializes the object the size of the board n x n.
 * - int move(int row, int col, int player) Indicates that player {player}
 *   makes a move at (row, col). Returns 0 if no one wins, or the player number if they win.
 */

    public class TicTacToe {

        // TODO: Add any member variables needed
        int score;
        int[] rows;
        int[] cols;
        int diag;
        int anti_diag;
        int n;


        // Constructor
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
            diag = 0;
            anti_diag = 0;
            score = 1;
            this.n = n;
        }

        // Player makes a move
        public int move(int row, int col, int player) {
            // TODO: implement move logic
            // player win when they make the final move

            rows[row] += score;
            cols[col] += score;
            if (row == col) {
                diag+= score;
            }

            if (row + col == n-1) {
                anti_diag+= score;
            }

            if (Math.abs(rows[row]) ==n || cols[col] == n ||diag == n || anti_diag == n) {
                return player;
            }

            score = -score;
            return 0;
        }
    public static void main(String[] args) {
        // Initialize a 3x3 TicTacToe board
        TicTacToe ticTacToe = new TicTacToe(3);

        // Players make moves
        System.out.println(ticTacToe.move(0, 0, 1)); // Expected 0 (no winner yet)
        System.out.println(ticTacToe.move(0, 2, 2)); // Expected 0
        System.out.println(ticTacToe.move(2, 2, 1)); // Expected 0
        System.out.println(ticTacToe.move(1, 1, 2)); // Expected 0
        System.out.println(ticTacToe.move(2, 0, 1)); // Expected 0
        System.out.println(ticTacToe.move(1, 0, 2)); // Expected 0
        System.out.println(ticTacToe.move(2, 1, 1)); // Expected 1 (player 1 wins)
    }
}
