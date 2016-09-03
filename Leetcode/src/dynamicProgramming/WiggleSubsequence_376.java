package dynamicProgramming;

/**
 * <p>
 * WiggleSubsequence_376
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ1ÈÕ
 */
public class WiggleSubsequence_376 {
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int i = 1; // pointer for nums
        while (i < nums.length && nums[i] == nums[i - 1]) i++;
        if (i == nums.length) return 1;
        dp[1] = nums[i];
        int p = 2; // pointer for dp
        boolean asc = dp[1] > dp[0] ? false : true; // ascending order for next dp value
        i++;
        for (; i < nums.length; i++){
            if (nums[i] > dp[p - 1]){
                if (asc){
                    dp[p++] = nums[i];
                    asc = !asc;
                }
                else dp[p - 1] = nums[i]; //must greater than dp[p - 2] and better than dp[p - 1] 
            }
            else if (nums[i] < dp[p - 1]){ // if equal, cannot do anything
                if (asc) dp[p - 1] = nums[i]; //must less than dp[p - 2] and better than dp[p - 1] 
                else {
                    dp[p++] = nums[i];
                    asc = !asc;
                }
            }
        }
        return p;
    }
    // 1 element longest subsequence ends with n[0]
    // 2 elements if new element > previous element, longest subsequence ends with n[1]
    // 2 elements if new element < previous element, longest subsequence ends with n[1]
    // 3 elements if new element > previous element, if longest subsequence needs go up, increase length and ends with n[2]. if longest subsequence needs go down, replace the last element of longest subsequence with n[2]
    // 3 elements if new element < previous element, if longest subsequence needs go down, increase length and ends with n[2]. if longest subsequence needs go up, replace the last element of longest subsequence with n[2]
    // the longest subsequence always ends with the last element
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int up = 1; // longest subsequence needs go down
        int down = 1; // longest subsequence needs go up
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i - 1]){
                // the original "down" maintain the same, the original "up" increment by 1 and become down. 
                // down = Math.max(up + 1, down). since up is at least = down - 1, up + 1 >= down
                down = up + 1;
            }
            else if (nums[i] < nums[i - 1]){
                up = down + 1;
            }
        }
        return Math.max(up, down);
    }
}
