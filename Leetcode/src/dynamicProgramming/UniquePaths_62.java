package dynamicProgramming;

/**
 * <p>
 * UniquePaths_62
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ16ÈÕ
 */
public class UniquePaths_62 {
    public int uniquePaths2(int m, int n) {
        int[] sum_cur = new int[n];
        int[] sum_next = new int[n];
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                int right = j == n - 1 ? 0 : sum_cur[j + 1];
                int bottom = i == m - 1 ? 0 : sum_next[j];
                if (i == m - 1 && j == n - 1) sum_cur[j] = 1;
                else sum_cur[j] = right + bottom;
            }
            sum_next = sum_cur;
            sum_cur = new int[n];
        }
        return sum_next[0];
    }
    // math solution
    // we have (m - 1) + (n - 1) movements to move, we have m + n - 2 positions to place these m + n - 2 movements
    // we can choose the positions of m - 1 down movements from the m + n - 2 positions
    // after we choose the positions of m - 1 down movements, the positions of right movements have been fixed
    // therefore, we have C(m + n - 2, m - 1) potential combinations
    // C(m, n) = m!/(n! * (m - n)!) = m * .. * (m - n + 1) / (n!)
    // = (m / n) * ((m - 1) / (n - 1)) ... * ((m - n + 1) / 1) 
    // = ((m - n + 1) / 1) * ((m - n + 2) / 2) * ... (m / n) use reverse order so that each multiplication can be divisible by divisor
    // C(m + n - 2, m - 1) = (n / 1) * ((n + 1) / 2) * ... * (m + n - 2 / m - 1)
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        // if (m == 1 || n == 1 || m + n == 2) return 1; 
        // corner case, 0! = 1, C(m + n - 2, m - 1) = (m + n - 2)! / ((n - 1)! * (m - 1)!)
        // but if we use the derived equation, we won't face this problem
        double res = 1;
        for (int i = 1; i <= m - 1; i++){
            res = res * (n - 1 + i) / i;
        }
        return (int)res; // don't forget cast to int.
    }
}
