package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * CombinationSumIII_216
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ26ÈÕ
 */
public class CombinationSumIII_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n > 45 || n > k * 9 || k > 9) return res;
        List<Integer> tmp = new ArrayList<Integer>();
        collect(res, tmp, k, n, 9);
        return res;
    }
    private void collect(List<List<Integer>> res, List<Integer> tmp, int k, int target, int p){
        if (target < 0 || p < k) return;
        if (k == 0) {
            if (target == 0){
                List<Integer> r = new ArrayList<Integer>(tmp);
                res.add(r);
            }
            return;
        }
        // use p
        tmp.add(p);
        collect(res, tmp, k - 1, target - p, p - 1);
        tmp.remove(tmp.size() - 1);
        // not use p
        collect(res, tmp, k, target, p - 1);
    }
}
