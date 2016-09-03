package binarySearch;

/**
 * <p>
 * SearchInsertPosition_35
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = (l + r) / 2;
            if (nums[m] > target) r = m - 1;
            else if (nums[m] < target) l = m + 1;
            else return m;
        }
        return r + 1;
    }
}
