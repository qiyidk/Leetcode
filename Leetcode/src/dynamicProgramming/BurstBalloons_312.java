package dynamicProgramming;

/**
 * <p>
 * BurstBalloons_312
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ12ÈÕ
 */
public class BurstBalloons_312 {
    // each time pick the biggest product cannot guarantee the max coins
    // 1 5 10 1 4   pick (5) 10 (1) will get smaller max coins than pick (10)1(4)
    // typically, we can use a dp function to store the intermediate result and pick a pivot to divide the bigger question into two small questions.
    // but if we first pick i and then pick the two left parts, the left and right will become adjacent and have effects on the final result
    // if we first pick the two parts, and denote i as the last element to be picked, the section on the left of i and the one on the right of i won't affect each other.
    // we define dp[m][n], m,n are left boundary and right boundary separately(they won't be picked), it ranges from 0 to n + 1
    // then we can traverse each pivot inside the boundary and find the max value
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] nums2 = new int[n + 2];
        for (int i = 0; i < n; i++) nums2[i + 1] = nums[i];
        for (int i = 0; i < n + 2; i++)
            for (int j = 0; j < n + 2; j++) dp[i][j] = -1;
        nums2[0] = nums2[n + 1] = 1;
        return maxCoins(nums2, dp, 0, n + 1);
    }
    private int maxCoins(int[] nums, int[][] dp, int left, int right){
        if (left + 1 == right) return 0;
        if (dp[left][right] != -1) return dp[left][right];
        int max = Integer.MIN_VALUE;
        for (int i = left + 1; i < right; i++){
            max = Math.max(max, nums[left] * nums[i] * nums[right] + maxCoins(nums, dp, left, i) + maxCoins(nums, dp, i, right));
        }
        dp[left][right] = max;
        return max;
    }
}
