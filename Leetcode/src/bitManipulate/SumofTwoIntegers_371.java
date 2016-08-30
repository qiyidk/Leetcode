package bitManipulate;

/**
 * <p>
 * SumofTwoIntegers_371
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ30ÈÕ
 */
public class SumofTwoIntegers_371 {
    // one more concise solution
    // a ^ b can represent sum without carry
    // a & b can represent carry, the carry should be used for the higher bit(left shift).
    // each time we generate a pure sum and all the carry bits.
    // the final result = the pure sum + carrybits
    // since the new sum may result in some new carrybits, we need to continue this process until no carry bit is left.
    public int getSum(int a, int b){
        if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }
    public int getSum2(int a, int b) {
        int carry = 0;
        int mask = 1;
        int sum = 0;
        for (int i = 0; i < 32; i++){
            if (((a & mask) == mask) && ((b & mask) == mask)){
                sum = sum | (carry == 1 ? mask : 0);
                carry = 1;
            }
            else if (((a & mask) == mask) && (carry == 1)){
                sum = sum | ((b & mask) == mask ? mask : 0);
                carry = 1;
            }
            else if (((b & mask) == mask) && (carry == 1)){
                sum = sum | ((a & mask) == mask ? mask : 0);
                carry = 1;
            }
            else{
                sum = sum | (carry == 1 || ((a & mask) == mask) || ((b & mask) == mask) ? mask : 0);
                carry = 0;
            }
            mask = mask << 1;
        }
        return sum;
    }
}
