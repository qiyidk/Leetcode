package binarySearch;

/**
 * <p>
 * ValidPerfectSquare_367
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ27ÈÕ
 */
public class ValidPerfectSquare_367 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        int start = 0;
        int end = 2;
        for (int i = 0; i < 4; i++){
            // try 2^1 2^2 2^4 2^8
            if (end * end > num) break; // sqrt(num) at most = end - 1
            end = end * end;
        }
        end--; // avoid overflow
        while(start <= end){
            int m = start + (end - start) / 2;
            int v = m * m;
            if (v == num) return true;
            else if (v > num) end = m - 1;
            else start = m + 1;
        }
        return false;
    }
}
