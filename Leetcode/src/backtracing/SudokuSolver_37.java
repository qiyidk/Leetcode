package backtracing;

import baseDataStructure.MatrixGenerator;

/**
 * <p>
 * SudokuSolver_37
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ22ÈÕ
 */
public class SudokuSolver_37 {
    public void solveSudoku(char[][] board) {
        boolean[][][] used = new boolean[3][9][9]; //0 : row 1 : column 2 : square
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                char v = board[i][j];
                if (v != '.'){
                    used[0][i][v - '1'] = true; // note that the values start with 1
                    used[1][j][v - '1'] = true;
                    used[2][getSquare(i, j)][v - '1'] = true;
                }
            }
        }
        solve(used, board, 0);
    }
    private int getSquare(int i, int j){
        return (i / 3) * 3 + (j / 3);
    }
    private boolean solve(boolean[][][] used, char[][] board, int p){
        if (p == 81) return true;// solved all blocks
        int i = p / 9;
        int j = p % 9;
        // if (board[i][j] != '.') if (solve(used, board, p + 1)) return true; cannot ignore {}; otherwise the second if will make a pair with next else
        if (board[i][j] != '.') {
            if (solve(used, board, p + 1)) return true;
        }
        else{
            //try to solve this one
            for (char v = '1'; v <= '9'; v++){
                if (!used[0][i][v - '1'] 
                   && !used[1][j][v - '1'] 
                   && !used[2][getSquare(i, j)][v - '1']){
                     board[i][j] = v;
                     used[0][i][v - '1'] = true;
                     used[1][j][v - '1'] = true;
                     used[2][getSquare(i, j)][v - '1'] = true;

                     if (solve(used, board, p + 1)) return true;

                     board[i][j] = '.';
                     used[0][i][v - '1'] = false;
                     used[1][j][v - '1'] = false;
                     used[2][getSquare(i, j)][v - '1'] = false;
                }
            }
        }
        return false;        
    }
    public static void main(String[] args){
        String s = "[..9748...],[7........],[.2.1.9...],[..7...24.],[.64.1.59.],[.98...3..],[...8.3.2.],[........6],[...2759..]";
        char[][] board = MatrixGenerator.getCharMatrix(s);
        new SudokuSolver_37().solveSudoku(board);
    }
}
