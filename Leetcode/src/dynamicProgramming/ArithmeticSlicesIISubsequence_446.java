package dynamicProgramming;

import java.util.HashMap;

/**
 * <p>
 * ArithmeticSlicesIISubsequence_446
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ8ÈÕ
 */
public class ArithmeticSlicesIISubsequence_446 {
    // A subsequence slice (P0, P1, ..., Pk) p1, ... pk may be not continuous
    // each difference must be a difference of one element and the other element
    // we can try every possible sub-sequences
    // it will take O(2^n) time
    // for 4, 6, 8, 10 it has 3 valid sequences
    // now consider 2, 4, 6, 8, 10, 
    // for all valid sequences length >= 2, start with 4 and has a diff of 2, now we also have new sequences start with 2
    // the same with sequences starts with 6 and has a diff of 4
    // therefore we can store a dp[startpoint][diff] for valid sequences whose length is >= 2
    // we can use a hashtable to represent diff, since it may be too big.
    // it will take O(n ^ 2) time
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if (n < 3) return 0;
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer>[] dp = new HashMap[n];//(diff, count)
        int res = 0;
        for (int i = n - 1; i >= 0; i--){// new start point
            dp[i] = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++){
                // no way to form valid sub-sequence
                if (0L + A[i] - A[j] > Integer.MAX_VALUE || 0L + A[i] - A[j] < Integer.MIN_VALUE) continue;
                int diff = A[i] - A[j];
                Integer count = dp[j].get(diff);
                Integer count2 = dp[i].get(diff);
                if (count == null) {// only have sequence(i,j) whose length = 2
                    if (count2 == null) dp[i].put(diff, 1); 
                    else dp[i].put(diff, 1 + count2);
                }
                else {
                    res += count; // each sequence whose length >= 2 will generate a new valid sequence
                    if (count2 == null) dp[i].put(diff, count + 1); // don't forget sequence(i,j)
                    else dp[i].put(diff, count2 + count + 1); // don't forget sequence(i,j)
                }
            }
        }
        return res;
    }
}
