package string;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * StrobogrammaticNumberII_247
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ8ÈÕ
 */
public class StrobogrammaticNumberII_247 {
    // It is my second time to do this problem(saved)
    // first we need to find the number that can form a strobogrammatic number
    // 0, 1, 6(9), 8 , 9(6)
    // if n is even
    // ab ba
    // n is odd
    // abc ba c can only come from 0, 1, 8
    // if n == 1, 0 can appear at the first digit; otherwise, it cannot appear at the first digit
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<String>();
        char[] str = new char[n];
        char[][] num = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};// store the possible number for each digit and its corresponding number in the symmetric position
        findStrobogrammatic(str, res, num, 0, n);
        return res;
    }
    private void findStrobogrammatic(char[] str, List<String> res, char[][] num, int digit, int n){
        if (digit == n / 2){
            if (n % 2 == 0){
                // get a new result
                res.add(new String(str));
                return;
            }
            else{
                // need one extra digit
                if (n == 1 || digit != 0) {
                    str[digit] = '0';
                    res.add(new String(str));
                }
                str[digit] = '1';
                res.add(new String(str));
                str[digit] = '8';
                res.add(new String(str));
                return;
            }
        }
        int i = 0;
        if (n != 1 && digit == 0) i = 1;
        for (; i < 5; i++){
            // pick one possible value for current digit and also set the value of the corresponding digit at the symmetric position
            str[digit] = num[i][0];
            str[str.length - 1 - digit] = num[i][1];
            findStrobogrammatic(str, res, num, digit + 1, n);
        }
    }
}
