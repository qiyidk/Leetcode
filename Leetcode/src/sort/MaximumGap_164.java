package sort;

/**
 * <p>
 * MaximumGap_164
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ8ÈÕ
 */
public class MaximumGap_164 {
    public int maximumGap(int[] nums) {
        if (nums.length <= 1) return 0;
        radixSort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] - nums[i - 1] > gap) gap = nums[i] - nums[i - 1];
        }
        return gap; 
    }
    private void radixSort(int[] nums){
        for (int i = 0; i < 4; i++) countSort(nums, i);
    }
    private void countSort(int[] nums, int bytes){
        int offset = bytes * 8;
        int mask = 0xff << offset;
        int radix = 256;
        int[] count = new int[radix + 1]; // for convenience, use 1 more index
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) tmp[i] = nums[i]; 
        for (int num : nums) {
            int index = (num & mask) >> offset;
            count[index + 1]++;
        }
        for (int i = 1; i < radix; i++) count[i] = count[i] + count[i - 1];
        for (int num : tmp){
            int index = (num & mask) >> offset;
            nums[count[index]] = num;
            count[index]++;
        }
    }
}
