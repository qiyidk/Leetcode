package dynamicProgramming;

/**
 * <p>
 * MinimumPathSum_64
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ16ÈÕ
 */
public class MinimumPathSum_64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        int[] min_cur = new int[n];
        int[] min_last = new int[n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int sum = 0; // minimum sum before block i, j
                if (i != 0 && j != 0){
                    sum = Math.min(min_cur[j - 1], min_last[j]);
                }
                else if (i != 0) sum = min_last[j];
                else if (j != 0) sum = min_cur[j - 1];
                else sum = 0;
                min_cur[j] = grid[i][j] + sum;
            }
            min_last = min_cur;
            min_cur = new int[n];
        }
        return min_last[n - 1];   
    }
}
