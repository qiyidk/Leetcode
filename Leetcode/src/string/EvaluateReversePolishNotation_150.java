package string;

/**
 * <p>
 * EvaluateReversePolishNotation_150
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ8ÈÕ
 */
public class EvaluateReversePolishNotation_150 {
    // each operator starts a new expression to be evaluated
    // a number is also an expression
    // we evaluate the tokens in reverse order
    public int evalRPN(String[] tokens) {
        return evalRPN(tokens, tokens.length - 1)[0];
    }
    private int[] evalRPN(String[] tokens, int p){
        char c = tokens[p].charAt(0);
        if (tokens[p].length() > 1   // note that the value can be negative
            || (c >= '0' && c <= '9')) return new int[]{Integer.parseInt(tokens[p]), p - 1}; 
        int[] r1 = evalRPN(tokens, p - 1);
        int[] r2 = evalRPN(tokens, r1[1]);
        switch(c){// note that since we traverse the array in reverse order, the r2[0] stores the value of the first expression.
            case '+' : return new int[]{r2[0] + r1[0], r2[1]};
            case '-' : return new int[]{r2[0] - r1[0], r2[1]};
            case '*' : return new int[]{r2[0] * r1[0], r2[1]};
            case '/' : return new int[]{r2[0] / r1[0], r2[1]};
            default: return null;
        }
    }
}
