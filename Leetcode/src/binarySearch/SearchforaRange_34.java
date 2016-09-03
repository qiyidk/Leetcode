package binarySearch;

/**
 * <p>
 * SearchforaRange_34
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class SearchforaRange_34 {
    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        int i = 0;
        int j = nums.length - 1;
        while(i <= j){// try to find the last element that is less than target
            int m = (i + j) / 2;
            if (nums[m] >= target) j = m - 1;
            else i = m + 1;
        }// j will always be the last element that conforms the rule
        start = j + 1;
        i = 0;
        j = nums.length - 1;
        while(i <= j){// try to find the first element that is greater than target
            int m = (i + j) / 2;
            if (nums[m] <= target) i = m + 1;
            else j = m - 1;
        }// i will always be the last element that conforms the rule
        end = i - 1;
        if (end < start) return new int[]{-1, -1};
        return new int[]{start, end};
    }
}
