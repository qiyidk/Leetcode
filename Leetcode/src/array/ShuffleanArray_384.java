package array;

/**
 * <p>
 * ShuffleanArray_384
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ12ÈÕ
 */
public class ShuffleanArray_384 {
    private int[] nums;
    public ShuffleanArray_384(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = new int[nums.length];
        System.arraycopy(nums, 0, res, 0, nums.length);
        for (int i = res.length - 1; i >= 0; i--){
            int index = (int)(Math.random() * (i + 1));
            swap(res, index, i);
        }
        return res;
    }
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
