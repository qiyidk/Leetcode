package array;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MissingRanges_163
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class MissingRanges_163 {
    // saved
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if (nums.length == 0) {
            addRange(res, lower, upper);
            return res;
        }
        // deal with the head
        if (nums[0] > lower) addRange(res, lower, nums[0] - 1);
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i - 1] + 1){
                // get a new range
                addRange(res, nums[i - 1] + 1, nums[i] - 1);
            } 
        }
        // deal with the tail
        if (nums[nums.length - 1] < upper) addRange(res, nums[nums.length - 1] + 1, upper);
        return res;
    }
    private void addRange(List<String> res, int start, int end){
        if (start == end) res.add(String.valueOf(start));
        else res.add(start + "->" + end);
    }
}
