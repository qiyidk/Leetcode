package dynamicProgramming;

/**
 * <p>
 * PartitionEqualSubsetSum_416
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ25ÈÕ
 */
public class PartitionEqualSubsetSum_416 {
    // if can partition, each set must hava a sum of total sum / 2
    // if one set can add up to sum / 2, the remaining set must also add up to sum / 2
    // therefore the original question is equivalent to find a set whose sum is sum / 2
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        return canPartition(nums, 0, 0, target);
    }
    public boolean canPartition(int[] nums, int p, int sum, int target){
        if (p == nums.length) return false;
        if (sum == target) return true;
        if (sum > target) return false;
        // choose p
        if (canPartition(nums, p + 1, sum + nums[p], target)) return true;
        // not choose p
        if (canPartition(nums, p + 1, sum, target)) return true;
        return false;
    } 
    // since we assume the sum of array won't be too large, we can get some solution that is proportional to sum, which will be much more efficient than a 2^n time solution
    // if we define dp[i] is the count of sum of elements we have seen so far. i will be less than or equal to sum / 2
    // each time we have to update at most sum / 2 time(not 2 ^ round time, must exist lots of duplicate sums)
    // since we don't care the count of sum, so we can use boolean[] instead(actually, the nums are not distinct, count works fine for this problem, but the statistic itself is not accurate)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] exist = new boolean[target + 1]; // existence of sum ranges from 0 to target
        exist[0] = true; //always has 0 when picking no element
        int end = 0;
        for (int i = 0; i < nums.length; i++){
            end += nums[i];
            end = Math.min(target, end);
            for (int j = end; j >= nums[i]; j--){
                exist[j] |= exist[j - nums[i]];
            }
            if (exist[target]) return true;
        }
        return false;
    }
}
