package bitManipulate;

/**
 * <p>
 * BitwiseANDofNumbersRange_201
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ2ÈÕ
 */
public class BitwiseANDofNumbersRange_201 {
    // 00000000
    // 00000001
    // 00000010
    // 00000011
    // 00000100
    // 00000101
    // 00000110
    // 00000111
    // 1. for digit i(0...31, from right to left), if the number of range is greater than 2 ^ i, the value of this digit must be changed, must have 1 and 0, so the final result must be 0.
    // 2. for digit that the number of range is less than 2 ^ i, there are 3 situations
    // all 0 -> 0 the digit of the first and last number = 0 the digit of the first & that of last number = 0
    // all 1 -> 1 the digit of the first and last number = 1 the digit of the first & that of last number = 0
    // 0 and 1 -> 0 the digit of the first and last number = 0 and 1, the digit of the first & that of last number = 0
    // for the first part of digits, the final result will be 0
    // for the second part of digits, the final result will be equal to the digit of the first & that of last number
    public int rangeBitwiseAnd2(int m, int n) {
        int range = n - m + 1;
        int mask = 0xffffffff;
        long radix = 1;
        while (range > radix){
            mask = mask << 1;
            radix = radix << 1;
        }
        return m & n & mask;
    }
    //this problem is asking us to find the common prefix of m and n 's binary code.
    // 101
    // 110
    // 111
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) n &= (n-1); //eliminate the rightmost 1
        return n;
    }
}
