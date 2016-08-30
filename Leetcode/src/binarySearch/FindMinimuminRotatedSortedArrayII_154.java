package binarySearch;

/**
 * <p>
 * FindMinimuminRotatedSortedArrayII_154
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class FindMinimuminRotatedSortedArrayII_154 {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int m = (l + r) / 2;
            if (nums[m] < nums[r]){
                // belong to the second section
                r = m;
            }
            else if (nums[m] > nums[r]){
                // belong to the first section
                l = m + 1;
            }
            else {
                //we can eliminate this point, since at least we have one copy(nums[m])
                r--;
            }
        }
        return nums[l];
    }
}
