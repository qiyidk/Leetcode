package stack;

/**
 * <p>
 * TernaryExpressionParser_439
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ1ÈÕ
 */
public class TernaryExpressionParser_439 {
    // scan from left to right
    // whenever meet a question mark, start a new evaluation, each evaluation must have two values, we stop current evaluation until we get two single values, then we can return the final result
    // T?2:3
    // F?1:T?4:5
    // T?T?F:5:3
    // F
    public String parseTernary(String expression) {
        return String.valueOf(parseTernary(expression, new int[]{0}));
    }
    private char parseTernary(String expression, int[] p){
        int cur = p[0];
        if (cur != expression.length() - 1 && expression.charAt(cur + 1) == '?'){
            // another expression
            p[0] += 2; // skip "?"
            char res = parseTernary(expression, p);
            char res2 = parseTernary(expression, p);
            if (expression.charAt(cur) == 'T') return res;
            else return res2;
        }
        else{
            // a single value
            p[0] += 2; // skip ":"
            return expression.charAt(cur);
        }
    }
}
