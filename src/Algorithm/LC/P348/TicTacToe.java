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
    int n;
    int[] rows;
    int[] cols;
    int diag;
    int anti;
    // Constructor
    public TicTacToe(int n) {
        // TODO: implement constructor
        this.n = n;
        rows = new int[n];
        cols = new int[n];
        diag = 0;
        anti = 0;
    }

    // Player makes a move
    public int move(int row, int col, int player) {
        // TODO: implement move logic
        int move = player == 1? 1 : -1;
        rows[row] += move;
        cols[col] += move;

        if (row == col) {
            diag += move;
        }
        if (row + col == n-1) {
            anti += move;
        }
        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n ||
                Math.abs(diag) == n || Math.abs(anti) == n ) {
            return 1;
        }
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
