package divide_conquer;

/**
 * <p>
 * DifferentWaystoAddParentheses_241
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ3ÈÕ
 */

import java.util.ArrayList;
import java.util.List;

public class DifferentWaystoAddParentheses_241 {
    public List<Integer> diffWaysToCompute(String input) {
        char[] c = input.toCharArray();
        List<Integer> res = diffWaysToCompute(c, 0, c.length - 1);
        return res;
    }
    private List<Integer> diffWaysToCompute(char[] c, int start, int end){
        List<Integer> res = new ArrayList<Integer>();
        for (int e = start; e < end; e++){
            if (c[e] < '0') {// ascii of op is less than '0'
                // have a new split
                List<Integer> res1 = diffWaysToCompute(c, start, e - 1);
                List<Integer> res2 = diffWaysToCompute(c, e + 1, end);
                merge(res1, res2, res, c[e]);
            }
        }
        if (res.size() == 0){
            // no split can be made, single value
            int s = 0;
            for (int i = start; i <= end; i++) s = s * 10 + c[i] - '0';
            res.add(s);
        }
        return res;
    }
    private void merge(List<Integer> res1, List<Integer> res2, List<Integer> res, char op){
        switch(op){
            case '+': for (int i : res1) for (int j : res2) res.add(i + j); break;
            case '-': for (int i : res1) for (int j : res2) res.add(i - j); break;
            case '*': for (int i : res1) for (int j : res2) res.add(i * j); break;
            default : break;
        }
    }
}
