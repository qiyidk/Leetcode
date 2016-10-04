package dynamicProgramming;

/**
 * <p>
 * ScrambleString_87
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ11ÈÕ
 */
public class ScrambleString_87 {
    // we can only swap the two branches, the binary tree is fixed when we choose a way to cut
    // if we cut str into str1 and str2, scramble str can be str1' str2' or str2' str1' (str1' means scramble str for str1) , we can store the intermediate result.
    // define dp(s1, e1, s2) as the match result, for str1(s1, e1) to str2(s2, e2), where e2 can be deducted from s1, e1, s2.
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (s1.length() != s2.length()) return false;
        if (s1.length() == 1) return s1.charAt(0) == s2.charAt(0); // no cut is needed
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][][] dp = new int[n][n][n];// 0 : unknown, 1 : matched 2 : unmatched 
        return isScramble(c1, 0, n - 1, c2, 0, dp);
    }
    
    private boolean isScramble(char[] c1, int s1, int e1, char[] c2, int s2, int[][][] dp){
        if (dp[s1][e1][s2] != 0) return dp[s1][e1][s2] == 1 ? true : false;
        if (s1 == e1) {
            dp[s1][e1][s2] = c1[s1] == c2[s2] ? 1 : 2;
            return dp[s1][e1][s2] == 1 ? true : false;
        }
        //pruning
        int[] count = new int[26];
        for (int i = s1; i <= e1; i++) count[c1[i] - 'a']++;
        for (int i = s2; i <= s2 + e1 - s1; i++) {
            count[c2[i] - 'a']--;
            if (count[c2[i] - 'a'] < 0) {
                dp[s1][e1][s2] = 2;
                return false;
            }
        }
        for (int i = s1; i < e1; i++){
            // cut by index i
            if (
                (isScramble(c1, s1, i, c2, s2, dp) && isScramble(c1, i + 1, e1, c2, s2 + i - s1 + 1, dp))
                ||
                (isScramble(c1, s1, i, c2, s2 + e1 - i, dp) && isScramble(c1, i + 1, e1, c2, s2, dp))
                ){
                dp[s1][e1][s2] = 1;
                return true;
            }
        }
        dp[s1][e1][s2] = 2;
        return false;
    }
}
