package dynamicProgramming;

/**
 * <p>
 * MaximalSquare_221
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ2ÈÕ
 */
public class MaximalSquare_221 {
    /*
    1 0 1 0 0
    1 0 1 1 1
    1 1 1 d b
    1 0 1 c a
    */
    // define dp[i, j] as length of the side of the biggest square that ends with i, j
    // if dp[a] = 3 dp[b] >= 2, dp[c] >= 2 dp[d] >= 2
    // if a = 1 dp[a] = min(dp[b], dp[c], dp[d]) + 1; else dp[a] = 0
    // since we only need upper and left elements, we can reduce the space to O(n)
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int[] prev = new int[n]; // previous dp value of last row
        int max = 0;
        for (int i = 0; i < m; i++){
            int[] cur = new int[n];
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '0') cur[j] = 0;
                else{
                    int up = prev[j];
                    int upperLeft = j == 0 ? 0 : prev[j - 1];
                    int left = j == 0 ? 0 : cur[j - 1];
                    cur[j] = Math.min(Math.min(up, upperLeft), left) + 1;
                    if (cur[j] > max) max = cur[j];
                }
            }
            prev = cur;
        }
        return max * max;
    }
}
