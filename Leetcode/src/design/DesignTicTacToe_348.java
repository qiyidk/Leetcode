package design;

/**
 * <p>
 * DesignTicTacToe_348
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ21ÈÕ
 */
public class DesignTicTacToe_348 {
    private int[][] board; //for move2
    private int[] rows; //for move, store the count of moves that have already made for each row
    private int[] cols; //for move
    private int diag1; //for move
    private int diag2; //for move
    private int n;
    /** Initialize your data structure here. */
    public DesignTicTacToe_348(int n) {
        board = new int[n][n];
        rows = new int[n];
        cols = new int[n];
        diag1 = 0;
        diag2 = 0;
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int v = player == 1 ? 1 : -1; // use positive number for player1, use nagetive number for player2
        if (rows[row] * v >= 0) {
            rows[row]+= v;
            if (Math.abs(rows[row]) == n) return player;
        }
        if (cols[col] * v >= 0) {
            cols[col]+= v;
            if (Math.abs(cols[col]) == n) return player;
        }
        if (row == col && diag1 * v >= 0){
            diag1+= v;
            if (Math.abs(diag1) == n) return player;
        }
        if (row == n - 1 - col && diag2 * v >= 0) {
            diag2+= v;
            if (Math.abs(diag2) == n) return player;
        }
        return 0;
    }
    public int move2(int row, int col, int player) {
        board[row][col] = player;
        boolean win = true;
        for (int i = 0; i < n; i++) if (board[i][col] != player) win = false;
        if (win) return player;
        win = true;
        for (int j = 0; j < n; j++) if (board[row][j] != player) win = false;
        if (win) return player;
        if (row == col) { //note that one point can conform both styles. e.g. n = 3, point = 1,1
            win = true;
            for (int i = 0; i < n; i++) if (board[i][i] != player) win = false; 
            if (win) return player;
        }
        if (row == n - 1 - col) { // note that j = n - 1 - i, not n - i
            win = true;
            for (int i = 0; i < n; i++) if (board[i][n - 1 - i] != player) win = false;
            if (win) return player;
        }
        return 0;
    }
}
