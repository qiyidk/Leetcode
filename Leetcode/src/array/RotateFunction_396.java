package array;

/**
 * <p>
 * RotateFunction_396
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ21ÈÕ
 */
public class RotateFunction_396 {
    /*
    A = [4, 3, 2, 6]
    F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
    F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
    F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
    F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26 
    
    from f(0) to f(1) : for the first n - 1 elements, the coefficient is increased by 1, for the last element the coefficient is decreased by n - 1
    the delta of final result = A0 +.. An-2 - (n - 1)An - 1 = S - An-1 - (n - 1)An-1
    = S - nAn-1
    for i = k, we get the maximum result = F(0) + kS - n(An-1 + An-2 + ... + An-k)
    */
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        int res = 0;
        int s = 0;
        for (int i = 0; i < A.length; i++){
            s += A[i];
            res += A[i] * i;
        }
        int max = res;
        for (int k = 1; k < A.length; k++){
            res = res + s - n * A[n - k];
            if (res > max) max = res;
        }
        return max;
    }
}
