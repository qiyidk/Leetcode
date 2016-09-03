package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * SubsetsII_90
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ31ÈÕ
 */
public class SubsetsII_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(nums);
        collect(nums, res, tmp, 0);
        return res;
    }
    private void collect(int[] nums, List<List<Integer>> res, List<Integer> tmp, int p){
        // do not use any other numbers
        List<Integer> r = new ArrayList<Integer>(tmp);
        res.add(r);
        // use other numbers
        int start = p;
        for(; p < nums.length; p++){
            if (p == start || nums[p] != nums[p - 1]){
                tmp.add(nums[p]);
                collect(nums, res, tmp, p + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
