package dynamicProgramming;

/**
 * <p>
 * PalindromePartitioningII_132
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ22ÈÕ
 */
public class PalindromePartitioningII_132 {
    // O(n ^ 3) solution
    public int minCut2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // mincut used by substring [i, j] inclusively
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) dp[i][j] = -1;
        // collect dp first to make it faster
        char[] c = s.toCharArray();
        int start = 0;
        for (int i = 0; i < n; i++){
            if (i!= n - 1 && c[i] == c[i + 1]) dp[start][i + 1] = 0;
            else{    
                extend(c, dp, start, i);
                start = i + 1;
            }
        }
        return minCut(c, 0, n - 1, dp);
    }
    private int minCut(char[] c, int start, int end, int[][] dp){
        if (dp[start][end] != -1) return dp[start][end];
        int min = Integer.MAX_VALUE;
        for (int i = start; i < end; i++){// cannot equal to end
            min = Math.min(min, minCut(c, start, i, dp) + minCut(c, i + 1, end, dp) + 1);
        }
        dp[start][end] = min;
        return min;
    }
    private void extend(char[] c, int[][] dp, int start, int end){
        while (start >= 0 && end <= c.length - 1 && c[start] == c[end]){
            dp[start][end] = 0;
            start--;
            end++;
        }
    }
    // if we scan from all i, j, we need calculate n^2 dp value for n^3 times comparisons.
    // actually, we don't need to calculate all the dp value.
    // if we define dp[i] as the minCut that ends with i.
    // dp[i] = dp[0, i] = dp[0, k] + dp[k + 1, j], dp[0, k] = dp[k] we already know that.
    // but we still don't know dp[k + 1, j].
    // but if we define substring(k + 1, j) must be a palindrome
    // we can see it still can cover all the situations. if (k + 1, j) is not a palindrome, this 
    // situation must be counted into the following steps, say, (k + t, j) where substring(k + t, j) is a 
    // palindrome.
    // therefore we can do this problem in O(n^2) time
    // we need to store dp[i] and also store p[k] for if c[k][i] is palindrome in round i
    // since p[k][i] can be deducted from p[k + 1][i - 1], p[k + 1][i - 1] can get from last round.
    // we calculate from 1 to i
    // cur p[k] = c[k] == c[i] && last p[k + 1] 
    // therefore we only need one one-dimension array for p
    public int minCut(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n]; // mincut for substring that ends with i
        boolean[] p = new boolean[n]; // isPalindrome for substring starts with i for current round
        char[] c = s.toCharArray();
        for (int end = 0; end < n; end++){
            int min = Integer.MAX_VALUE;
            // if do not make a cut
            if (c[0] == c[end] && (end < 2 || p[1])) min = 0;
            // if make a cut
            for (int start = 1; start <= end; start++){// start of the second section
                if (c[start] == c[end] && (end - start < 2 || p[start + 1])){
                   p[start] = true;
                   min = Math.min(min, dp[start - 1] + 1);
                }
                else p[start] = false;
            }
            dp[end] = min;
        }
        return dp[n - 1];
    }
}
