package dynamicProgramming;

/**
 * <p>
 * InterleavingString_97
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ12ÈÕ
 */
public class InterleavingString_97 {
    // pure backtracing, tle
    // if we define dp[i][j] as suffix of str1 starts at i and suffix of str2 starts at j can or cannot match the corresponding part of str3
    // when reaching the same situation, we can determine the result immediately
    // to make the process simpler, we redefine the dp in opposite way, we define dp as whether has visited i, j
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        return isInterleave(c1, c2, c3, 0, 0, dp);
    }
    private boolean isInterleave(char[] c1, char[] c2, char[] c3, int p1, int p2, boolean[][] dp){
        if (dp[p1][p2]) return false;
        if (p1 + p2 == c3.length) return true;
        if (p1 < c1.length && c1[p1] == c3[p1 + p2]){
            if (isInterleave(c1, c2, c3, p1 + 1, p2, dp)) return true;
        }
        if (p2 < c2.length && c2[p2] == c3[p1 + p2]){
            if (isInterleave(c1, c2, c3, p1, p2 + 1, dp)) return true;
        }
        dp[p1][p2] = true;
        return false;
    }
    
    // can we get dp[i][j] from other dp values, say, dp[i + 1][j] dp[i][j + 1]?
    // dp[i][j] == true 
    // iff (c1[i] == c3[i + j] && dp[i + 1][j] = true) 
    //     || (c2[j] == c3[i + j] && dp[i][j + 1] = true)
    // therefore we only need i + 1 and j + 1 which belongs to current row and last row
    // we can reduce the space to O(n)
    // but we have to traverse all dp values, in pratical, it will be slower than previous solution.
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        boolean[] dp = new boolean[s2.length() + 1];
        dp[s2.length()] = true;
        for (int i = s1.length(); i >= 0 ; i--){// i = s1.length() means not use i at all.
            for (int j = s2.length(); j >= 0; j--){
                if (i < s1.length()) dp[j] = (c1[i] == c3[i + j]) && dp[j];
                if (j < s2.length()) dp[j] = dp[j] || (c2[j] == c3[i + j] && dp[j + 1]);
            }
        }
        return dp[0];
    }
}
