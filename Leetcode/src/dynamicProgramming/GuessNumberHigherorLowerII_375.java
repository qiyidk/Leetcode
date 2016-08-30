package dynamicProgramming;

/**
 * <p>
 * GuessNumberHigherorLowerII_375
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ11ÈÕ
 */
public class GuessNumberHigherorLowerII_375 {
    // only if we don't have a value for (start, end), we call getMoneyAmount. we will call getMoneyAmount O(n^2) times. each getMoneyAmount will do O(n) comparisons. It will finally take O(n^3) time
    public int getMoneyAmount(int n) {
        int[][] max = new int[n + 1][n + 1];
        return getMoneyAmount(max, 1, n);
    }
    private int getMoneyAmount(int[][] max, int start, int end){
        if (start >= end) return 0;//check first to avoid array out of bound
        if (max[start][end] != 0) return max[start][end];
        //if number of elements is 3, choose the middle one, the val is middle one
        //if number of elements is 2, choose the second one, the val is the first one
        if (end - start + 1 <= 3) {
            max[start][end] = end - 1;
            return end - 1;
        }
        // calculate the new val, pivot is the first wrong try.
        int min = Integer.MAX_VALUE;
        for (int pivot = start; pivot <= end; pivot++){
            int left = getMoneyAmount(max, start, pivot - 1);
            int right = getMoneyAmount(max, pivot + 1, end);
            min = Math.min(min, pivot + Math.max(left, right));
            if (left >= right) break; //cannot have new value less than previous min
        }
        max[start][end] = min;
        return min;
    }
}
