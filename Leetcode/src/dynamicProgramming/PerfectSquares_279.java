package dynamicProgramming;

/**
 * <p>
 * PerfectSquares_279
 * </p>
 *
 * @author qiyi
 * @version 2016年3月10日
 */
public class PerfectSquares_279 {
    public int numSquares(int n) {
        // for each n, it can always be represented by one num + one perfect square numbers, and the sqrNumber of n must come from one of combinations.
        // 可以知道，要得到n，其实从1到n - 1都必须得到，所以我们只需要从1一直算到n，这样同时还减少了不必要的判断（判断是否已经缓存）
        int[] sqrNumbers = new int[n + 1]; // don't need a hashMap, from 1 to n 
        for (int i = 1; i <= n; i++){
            int maxSqr = (int)Math.sqrt(i);
            int min = Integer.MAX_VALUE;
            for (int j = maxSqr; j >= 1; j--){
                int another = i - j * j;
                if (another == 0) {// if already a perfect square number, do not need to split.
                    min = 0;
                    break;
                }
                else{
                    int sqrNumber = sqrNumbers[another];
                    if (sqrNumber < min) min = sqrNumber;
                    if (min == 1) break;
                }
            }
            sqrNumbers[i] = min + 1;
        }
        return sqrNumbers[n];
    }
}
