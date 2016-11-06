package dynamicProgramming;

/**
 * <p>
 * DistinctSubsequences_115
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ30ÈÕ
 */
public class DistinctSubsequences_115 {
    // the question is to ask us to find the number of subsequences of s which are equal to t
    // define f(n) is the number of subsequences of s which are equal to t till n
    // we try to calculate f(n - 1) -> f(n)
    // if s(n) != t(last), no valid subsequences ends with n, f(n) = f(n - 1)
    // if s(n) = t(last), f(n) = f(n - 1) + g(n - 1, last - 1) 
    // where g(i, j) is the number of subsequences of s(0...i) which are equal to t(0...j)
    // assume we already know g(n - 1, last - 1), then we can calculate f(n)
    // what we need to do is to update g(n, last - 1) from g(n - 1, last - 1)
    // if s(n) != t(last - 1) g(n, last - 1) = g(n - 1, last - 1)
    // if equal, g(n, last - 1) = g(n - 1, last - 1) + g(n - 1, last - 2)
    //
    // therefore we also need to know g(n - 1, last - 2), and then we need to update g(n, last - 2)
    // g(n, i) = g(n - 1, i) + s(n) == t(i) ? g(n - 1, i - 1): 0
    // ...
    // g(n, 1) = g(n - 1, 1) + s(n) == t(1) ? g(n - 1, 0) : 0
    // g(n, 0) = g(n - 1, 0) + s(n) == t(0) ? g(n - 1, -1) : 0. it can be seen that g(n - 1, -1) = 1
    // it can be seen that we need maintain g(n - 1, last - 1...0) and f(n - 1), which is actually g(n - 1, last)
    // therefore we need maintain g(n - 1, last...0)
    // therefore we need a cur array to store the cur g(0...last)

    public int numDistinct(String s, String t) {
        if (t.length() > s.length()) return 0;
        int[] cur = new int[t.length()]; // for cur prefix of s, count of subsequences which are equal to t(0..i)
        for (char c : s.toCharArray()){
            for (int i = t.length() - 1; i >= 0 ; i--){//update g(i)
                int pre = (i == 0 ? 1 : cur[i - 1]);
                cur[i] += (c == t.charAt(i) ? pre : 0);
            }
        }
        return cur[t.length() - 1];
    }
    
    
    // faster solution in general
    public int numDistinct2(String s, String t) {
        // arr works as a hash table
        int[][] arr = new int[256][t.length()+1];
        int[] cnt = new int[t.length()+1];
        cnt[0] = 1;
        char c;
        for(int i = 0; i < t.length(); i++ ) {
            // arr[c] is a list of all the positions character c appears
            // arr[c][0] records how many times character c appears
            c = t.charAt(i);
            arr[c][arr[c][0]+1] = i+1;
            arr[c][0]++;
        }
        // DP
        for( char a: s.toCharArray() ) {
            if( arr[a][0] != 0 ) {
                for( int i = arr[a][0]; i > 0; i-- ) {
                    cnt[arr[a][i]] += cnt[arr[a][i]-1];
                }
            }
        }
        return cnt[t.length()];
    }
}
