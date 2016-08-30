package dynamicProgramming;

/**
 * <p>
 * BombEnemy_361
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class BombEnemy_361 {
    // we can make a horizontal scan and then vertical scan
    // if we meet a 0, add this position to a bomb list
    // if we meet an enemy, increment the num of enemies
    // if we meet a wall, record the num of enemies for all the positions in the bomb list and reset the bomb list and enemy count.
    // we cannot use the grid to save the result, since it may finally equal to "W" or "E", and collide with them.
    // we can find a way to save the space for extra matrix as well as the time for saving the intermediate result for each '0'.
    // we only scan horizontally, and record vertical enemies can be destroyed for each column so far, so that we can get the final result for each bomb right away.  
    public int maxKilledEnemies(char[][] grid) {
        int max = 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        int[] column = new int[n];
        int row = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (j == 0 || grid[i][j - 1] == 'W'){
                    // a new row section
                    row = 0; //don't forget to reset it first.
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') row++;
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W'){
                    // a new column section
                    column[j]  = 0; //don't forget to reset it first.
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') column[j]++;
                    }
                }
                if (grid[i][j] == '0' && row + column[j] > max) max = row + column[j];
            }
        }
        return max;
    }
}
