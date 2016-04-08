package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * GenerateParentheses_22
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ6ÈÕ
 */
public class GenerateParentheses_22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        char[] c = new char[n * 2];
        generateParenthesis(res, c, 0, 0, 0);
        return res;
    }
    private void generateParenthesis(List<String> res, char[] c, int p, int left, int right){
        if (p == c.length){
            // get a new result
            res.add(new String(c));
            return;
        }
        if (left - right > 0){
            if (left < c.length / 2){
                c[p] = '(';
                generateParenthesis(res, c, p + 1, left + 1, right);
            }
            c[p] = ')';
            generateParenthesis(res, c, p + 1, left, right + 1);
        }
        else{
            c[p] = '(';
            generateParenthesis(res, c, p + 1, left + 1, right);
        }
    }
}
