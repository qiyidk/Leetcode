package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * CombinationSumII_40
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ26ÈÕ
 */
public class CombinationSumII_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates.length == 0) return res;
        List<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(candidates);
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
            // for duplicate candidates, each recursive call only collect one candidate
            if (i != p && candidates[i] == candidates[i + 1]) continue;
            if (target >= candidates[i]) {
                tmp.add(candidates[i]);
                collect(res, tmp, candidates, target - candidates[i], i - 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
