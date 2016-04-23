package math;

/**
 * <p>
 * PowerofFour_342
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ22ÈÕ
 */
public class PowerofFour_342 {
    public boolean isPowerOfFour(int num) {
        //one way is to check whether the sqrt is a power of 2, but actually sqrt requires loops
        //we can check whether num is equal to 2 to the power of x, where x is an even number
        //in binary representation, the condition above indicates we can only have one bit is 1 and that bit is at the odd position if we count from right. 
        //e.g. ...010000000100
        //mask1   101010101010 check whether only odd position has 1
        //mask2   010000000011 (010000000100 - 1) check whether only has one "1"
        return num > 0 && ((num & 0xAAAAAAAA) == 0) && ((num & (num - 1)) == 0);
    }
}
