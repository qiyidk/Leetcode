package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ExpressionAddOperators_282
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class ExpressionAddOperators_282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if (num.length() == 0) return res;
        addOperators(res, "", num.toCharArray(), 0, target, 1, 1); //the first oper can only be "+"
        return res;
    }
    
    //product : product of last multiplication, initiate with 1. 
    //sign : the sign before last multiplication
    private void addOperators(List<String> res, String s, char[] num, int p, double target, int sign, double product){
        int boundary = num.length - 1;
        if (num[p] == '0') boundary = p;// corner case 00
        double val = 0;
        for (int end = p; end <= boundary; end++){
            val = val * 10 + num[end] - '0'; // more efficient way than calling substring for each substring
            if (end == num.length - 1){
                if (product * val * sign == target){
                    res.add(s + (int)val); // cast to int
                }
                return;
            }
            // *
                addOperators(res, s + (int)val + "*", num, end + 1, target, sign, product * val);
            // +
                addOperators(res, s + (int)val + "+", num, end + 1, target - product * val * sign, 1, 1);
            // -
                addOperators(res, s + (int)val + "-", num, end + 1, target - product * val * sign, -1, 1);
        }
    }
}
