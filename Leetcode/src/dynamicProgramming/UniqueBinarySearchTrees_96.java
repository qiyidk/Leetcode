package dynamicProgramming;

/**
 * <p>
 * UniqueBinarySearchTrees_96
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ28ÈÕ
 */
public class UniqueBinarySearchTrees_96 {
 // if we choose x as the root
    // all elements that are greater than x won't affect the elements that are less than x
    // f(x) = f(x1) * f(x2)
    // we can use dp[i] to store the intermediate result. i is the number of elements of the subtree
    // it can be seen that dp[i] = dp[0] * dp(i - 1) + dp[1] * dp[i - 2] + ... 
    // dp[i] = dp[0] * dp(i - 1) + dp[1] * dp[i - 2] + ... dp[0] * dp[i - 1]
    // = dp[0] * (dp[0] * dp[i - 2] + dp[1] * dp[i - 3] + ... + dp[i - 2] * dp[0]) 
    // + dp[1] * (dp[0] * dp[i - 3] + dp[1] * dp[i - 4] + ... + dp[i - 3] * dp[0])
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++){
            int mid = (i - 1) / 2;
            for(int j = 0; j <= mid; j++){
                dp[i] += dp[j] * dp[i - 1 - j]; 
            }
            dp[i] = dp[i] * 2 - ((i - 1) % 2 == 0 ? dp[mid] * dp[mid] : 0);
        }
        return dp[n];
    }
    
    // it is a Catalan number, f(n) = (1 / n + 1) * C(n, 2n) 
    // http://blog.csdn.net/hemeinvyiqiluoben/article/details/11320419
    // http://baike.baidu.com/link?url=s_n7-Djbmclx4eAO88mDWMbPh6DZDYhxmknFSSZsi3ql0pMNfHD9y5HEwaL4lRDUVpTzTfE_e4U_LaoAqE_AFfewdqi1D3OLXbxPSIwrTRSRjrGNEfHSkyEPok0xFVzFuuA3TZJoBiu8CSyRtKNeliqzl5NNyeFuoFyww7iLznc4eCMNQBVbinDXSSXd_VDvWX7ST-EgMnhMhangUpjh9a
    public int numTrees(int n) {
        long v =  1;
        int i = 1;
        for (; i <= n; i++){
            v = v * (n + i) / i;
        }
        return (int)(v / i);
    }
}
