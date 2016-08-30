package dynamicProgramming;

import java.util.Arrays;

/**
 * <p>
 * CombinationSumIV_377
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ27ÈÕ
 */
public class CombinationSumIV_377 {
    // Note that different sequences are counted as different combinations.
    // for each round, we pick one element from nums, it will generate a new group of combinations (uniquely identified by its last element and the sum of all elements) 
    // dp[n] = dp[n - nums[1]]  + dp[n - nums[2]]  + ...
    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.sort(nums);
        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            for (int j = 0; j < nums.length && nums[j] <= i; j++){
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
    
    // top-down will be more efficient in this case, but may cause stack overflow if the final result is too big
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return combinationSum4(dp, nums, target);
    }
    private int combinationSum4(int[] dp, int[] nums, int target) {
        if (dp[target] != -1) return dp[target];
        int v = 0;
        for (int i = 0; i < nums.length && nums[i] <= target; i++){
            v += combinationSum4(dp, nums, target - nums[i]);
        }
        dp[target] = v;
        return v;
    }

    // In order to allow negative integers, the length of the combination sum needs to be restricted, or the search will not stop.(a negative number and a positive number can form a sum = 0)
    // to reduce the usage of memory, we can use hashmap instead of array, in this case, negative numbers won't affect hashMap solution
}
