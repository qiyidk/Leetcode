package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Combinations_77
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ26ÈÕ
 */
public class Combinations_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        collect(n, k, res, tmp);
        return res;
    }
    private void collect(int n, int k, List<List<Integer>> res, List<Integer> tmp){
        if (k == 0) {
            List<Integer> r = new ArrayList<Integer>(tmp);
            res.add(r);
            return;
        }
        if (n < k) return;// cannot have legal combination.
        //use n
        tmp.add(n);
        collect(n - 1, k - 1, res, tmp);
        tmp.remove(tmp.size() - 1);
        //not use n
        collect(n - 1, k, res, tmp);
    }
}
