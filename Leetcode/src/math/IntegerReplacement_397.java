package math;

/**
 * <p>
 * IntegerReplacement_397
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ3ÈÕ
 */
public class IntegerReplacement_397 {
    // similar to bit shift
    // 010010111
    // for 0, right shift
    // if encountering k(k>=1) consecutive 1s, 
    // 01111.....111
    // 01111.....110
    // 10000.....000
    // if decrement current 1, it will take 1 + 1 + f(k - 1) 1s  f(k) = 2 + f(k - 1) = 2k
    // if increment current 1, it will take 1 + k 0s, but leave a f(1)(carry bit) in the higher bit, at most have 1 extra cost(we can just use one extra round to turn it to 0)
    // 2k = 1 + k k = 1, if k < 1 , decrement is better; if k = 1, decrement is better; if k > 1, increment is better
    // corner case: if the consecutive 1s include the highest non-0 bit of the number
    // for decrement it will take 2k - 2
    // for increment it will take 1 + k
    // balance point is k = 3
    public int integerReplacement2(int n) {
        int step = 0;
        int ones = 0;// number of consecutive ones
        while (n != 0){
            if ((n & 1) == 1) ones++;
            else{
                // deal with consecutive ones
                if (ones > 1) {
                    step += 1 + ones;
                    ones = 1;// have one new 1 bit
                }
                else {
                    step += 2 * ones;
                    ones = 0;
                    step++; // pass 0 bit
                }
            }
            n >>= 1;
        }
        if (ones != 0) {
            if (ones > 3) step += 1 + ones;
            else step += 2 * ones - 2;
        }
        return step;
    }
    // if encountering k(k>=1) consecutive 1s,
    // 2k = 1 + k k = 1, if k < 1, decrement is better; if k = 1, decrement is better; if k > 1, increment is better
    // this is to say, if k = 1, decrement; otherwise, increment. k = 1 means the previous bit is 0; we don't need to track k, we can get the result in current loop. 
    // this can be seen in a sample way: 
    // current bit = 1
    // if previous is 1, n + 1 at least get rid of two 1s and add one new 1, at least get rid of one 1, better than n - 1, which is always getting rid of one 1
    // if previous is 0, n + 1 get rid of zero 1, n - 1 get rid of one 1, n - 1 is better
    // if current bit is the highest non-0 bit, don't need to do extra work
    // if current bit is the next bit of the highest non-0 bit, it must be 11..., n - 1 is better 
    public int integerReplacement(int n) {
        int step = 0;
        if (n == Integer.MAX_VALUE) return 32;// 31 1s => 1 + 31
        while(n > 1){
            if (n % 2 == 0) n >>= 1;
            else{
                if (n == 3) return step + 2;
                if ((n & 2) == 2) n++; // don't forget the parentheses, also check the second bit(2, not 1)
                else n--;
            }
            step++;
        }
        return step;
    }
}
