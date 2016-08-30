package math;

/**
 * <p>
 * DivideTwoIntegers_29
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class DivideTwoIntegers_29 {
    
    // inefficient
    public int divide2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int flag = 1;
        if ((dividend >= 0 && divisor < 0) || (dividend <= 0 && divisor > 0)) flag = -1;
        long d1 = Math.abs(dividend); // notice overflow
        long d2 = Math.abs(divisor);
        long count = 0; // notice overflow
        while(d1 >= d2){
            count++;
            d1 -= divisor;
        }
        return (int)(flag * count);
    }
    
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int flag = 1;
        if ((dividend >= 0 && divisor < 0) || (dividend <= 0 && divisor > 0)) flag = -1;
        long d1 = Math.abs((long)dividend); // notice overflow
        long d2 = Math.abs((long)divisor);
        long res = 0;
        for (int i = 31; i >= 0; i--){
            long v = d2 << i;
            if (d1 == 0) break;
            if (d1 >= v) {
                d1 -= v;
                res |= 1 << i;
            }
        }
        return (int)(flag * res);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(new DivideTwoIntegers_29().divide(5, 2));
    }

}
