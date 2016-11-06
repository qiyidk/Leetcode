package matrix;

/**
 * <p>
 * BattleshipsinaBoard_419
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ3ÈÕ
 */
public class BattleshipsinaBoard_419 {
    // if there are consecutive Xs in one row, they must be contained in one ship
    // we only increment count when reaching the first element of this kind of ship
    // if there is a single X in one row, it must belong to a ship, this ship may or may have be counted
    // we only count the vertical ship when we reach the top of this ship
    public int countBattleships(char[][] board) {
        int m = board.length;
        if (m == 0) return 0;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'X'){
                    if ((j == 0 || board[i][j - 1] != 'X') // no horizontal ship has been counted
                        &&
                        (i == 0 || board[i - 1][j] != 'X') // no vertical ship has been counted
                        )
                        count++;
                }
            }
        }
        return count;
    }
}
