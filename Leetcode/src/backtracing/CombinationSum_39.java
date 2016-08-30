package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * CombinationSum_39
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ26ÈÕ
 */
public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates.length == 0) return res;
        List<Integer> tmp = new ArrayList<Integer>();
        collect(res, tmp, candidates, target, candidates.length - 1);
        return res;
    }
    private void collect(List<List<Integer>> res, List<Integer> tmp, int[] candidates, int target, int p){
        if (target == 0) {
            List<Integer> r = new ArrayList<Integer>(tmp);
            res.add(r);
            return;
        }
        for (int i = p; i >= 0; i--){
            if (target >= candidates[i]) {
                tmp.add(candidates[i]);
                collect(res, tmp, candidates, target - candidates[i], i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
