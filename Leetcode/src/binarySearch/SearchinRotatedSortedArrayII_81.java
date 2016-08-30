package binarySearch;

/**
 * <p>
 * SearchinRotatedSortedArrayII_81
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ14ÈÕ
 */
public class SearchinRotatedSortedArrayII_81 {
    // 0  1  2  4  4  4  4  4  4  4  7
    // 4  4  4  4  4  4  7  0  1  2  4   middle belongs to section 1, should go right
    // 4  7  0  1  2  4  4  4  4  4  4   middle belongs to section 2, should go left
    // we cannot decide m belongs to which section, cannot determine which side should we go to
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        int l = 0;
        int r = n - 1;
        while (l <= r){
            if (nums[l] == target) return true;
            int m = (l + r) / 2;
            if (nums[m] == target) return true;
            if (nums[m] < nums[l]) {
                // m belongs to the second part
                // l r m r
                if (target > nums[m] && target < nums[l]) l = m + 1;
                else r = m - 1;
            }
            else if (nums[m] < nums[l]){
                // m belongs to the first part
                // l m l r
                if (target < nums[m] && target > nums[l]) r = m - 1;
                else l = m + 1;
            }
            else{
                // cannot decide which way to go to, have to traverse one by one
                l++;
            }
        }
        return false;
    }
}
