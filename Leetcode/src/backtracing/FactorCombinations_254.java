package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * FactorCombinations_254
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ27ÈÕ
 */
public class FactorCombinations_254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n == 1) return res; //corner case
        List<Integer> tmp = new ArrayList<Integer>();
        collect(res, tmp, n, 2);
        return res;
    }
    private void collect(List<List<Integer>> res, List<Integer> tmp, int n, int f){
        if (n == 1){
            if (tmp.size() == 1) return; // only has the number itself
            List<Integer> r = new ArrayList<Integer>(tmp);
            res.add(r);
            return;
        }
        for (; f <= n; f++){
            if (f * f > n){ // no need to try other elements 
                tmp.add(n);
                collect(res, tmp, 1, n);
                tmp.remove(tmp.size() - 1);
                break;
            }
            else if (n % f == 0){
                tmp.add(f);
                collect(res, tmp, n / f, f);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
