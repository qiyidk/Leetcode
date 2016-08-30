package dynamicProgramming;

/**
 * <p>
 * CountNumberswithUniqueDigits_357
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class CountNumberswithUniqueDigits_357 {
    public int countNumbersWithUniqueDigits(int n) {
        // if n == 0 only has 0
        // if n == 1 has 0-9 (a little special, since 0 can be put in the first position)
        // when n == 2 we put 1-9 in the front and add one digit to form a new number has a length of 2
        // when n == 3 we put all the unique digits numbers have a length of 2 in the front and add one digit to form a new number has a length of 3
        // when n == k(k >= 2) let g(k) is the unique digits numbers has a length of k,f(k) = f(k - 1) + g(k - 1) * (10 - (k - 1))
        // note that g(1) == 9 not 10.
        // if (k > 10) there's no unique digits number that has a length of more than 10.
        if (n == 0) return 1;
        if (n == 1) return 10;
        int last = 9;
        int m = 10;
        for (int i = 2; i <= Math.min(n, 10); i++){
            last = last * (10 - (i - 1));
            m = m + last;
        }
        return m;
    }
}
