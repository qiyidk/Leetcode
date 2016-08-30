package dynamicProgramming;

import java.util.Arrays;

/**
 * <p>
 * RussianDollEnvelopes_354
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ24ÈÕ
 */
public class RussianDollEnvelopes_354 {
    public int maxEnvelopes(int[][] envelopes) {
        // sorting the envelopes by width so that if we pick envelopes from left to right, the first limitation(width limitation) will be satisfied automatically. Note that if have the same width, picking envelopes from left to right cannot guarantee the width limitation. Therefore if have the same width, we use reverse order to sort the envelopes, so that if we pick from left to right, when we apply the height limitation, due to the height is in descending order, we won't pick the elements that have the same width.
        // finally the original problem converts into longestIncreasingSubstring problem(use height for comparison).
        Arrays.sort(envelopes, (int[] i1, int[] i2) -> 
            {
                if (i1[0] != i2[0]) return i1[0] - i2[0];
                else return i2[1] - i1[1];
            });
        return longestIncreasingSubstring(envelopes);
    }
    
    private int longestIncreasingSubstring(int[][] nums){
        if (nums.length == 0) return 0;
        int[] lis = new int[nums.length];
        lis[0] = nums[0][1];
        int p = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i][1] > lis[p - 1]) lis[p++] = nums[i][1];
            else{
                int index = Arrays.binarySearch(lis, 0, p, nums[i][1]);
                if (index < 0){
                    //need update
                    lis[-(index + 1)] = nums[i][1];
                } 
            }
        }
        return p;
    }
}
