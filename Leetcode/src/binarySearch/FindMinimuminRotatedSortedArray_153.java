package binarySearch;

/**
 * <p>
 * FindMinimuminRotatedSortedArray_153
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class FindMinimuminRotatedSortedArray_153 {
    public int findMin(int[] nums) {
        // 56781234  section1:5678 section2:1234
        int l = 0, r = nums.length - 1;
        while (l < r){
            int m = (l + r) / 2; // m cannot be equal to r
            if (nums[m] > nums[r]) l = m + 1; // m must in section 1
            else{
                // m in section 2 and l in section 1
                r = m;
            }
        }
        return nums[r];
    }
}
