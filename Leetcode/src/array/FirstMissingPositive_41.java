package array;

/**
 * <p>
 * FirstMissingPositive_41
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class FirstMissingPositive_41 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i < n){
            if (nums[i] > n || nums[i] <= 0) i++; //cannot be the first missing positive
            // mapping i to index nums[i] - 1
            else if (nums[nums[i] - 1] == nums[i]) i++; // no need to swap
            else swap(nums, nums[i] - 1, i);
        }
        for (int j = 0; j < n; j++){
            if (j + 1 != nums[j]) return j + 1;
        }
        return n + 1;// note that all the value may fit the requirement
    }
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
