package dynamicProgramming;

/**
 * <p>
 * UniquePathsII_63
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ21ÈÕ
 */
public class UniquePathsII_63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else{
                    if (i == 0 && j == 0) obstacleGrid[i][j] = 1;
                    else if (i == 0) obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                    else if (j == 0) obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                    else obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                }    
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}
