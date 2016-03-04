package sort;

/**
 * <p>
 * WiggleSort_280
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ4ÈÕ
 */
public class WiggleSort_280 {
    // It is my second time to do this problem
    // a, b, c if a <= b, then if c <= b, we don't need to change, but if c > b, we can swap b and c, and at this time, c must greater than a, which also conform the wiggled order.
    // b, c, d if b >= c, but c > d, we can swap c and d, then b must greater than d, so it also works.
    // We just need to traverse the array and when encounterint a violation, we can always swap the adjacent elements and fix that violation
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++){
            if (i % 2 == 1){
                if (nums[i] < nums[i - 1]) swap(nums, i, i - 1);
            }
            else{
                if (nums[i] > nums[i - 1]) swap(nums, i, i - 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
