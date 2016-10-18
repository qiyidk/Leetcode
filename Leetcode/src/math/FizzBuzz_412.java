package math;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * FizzBuzz_412
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ16ÈÕ
 */
public class FizzBuzz_412 {
    public List<String> fizzBuzz2(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++){
            if (i % 3 == 0 && i % 5 == 0) res.add("FizzBuzz"); 
            else if (i % 3 == 0) res.add("Fizz");
            else if (i % 5 == 0) res.add("Buzz");
            else res.add(String.valueOf(i));
        }
        return res;
    }
    private static String[] pattern = {null, null, "Fizz", null, "Buzz", "Fizz", null, null, "Fizz", "Buzz", null, "Fizz", null, null, "FizzBuzz"};
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n;){
            for (int j = 0; j < 15 && i <= n; i++, j++){
                if (pattern[j] == null) res.add(String.valueOf(i));
                else res.add(pattern[j]);
            }
        }
        return res;
    }
}
