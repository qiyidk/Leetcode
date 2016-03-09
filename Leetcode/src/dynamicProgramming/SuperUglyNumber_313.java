package dynamicProgramming;

/**
 * <p>
 * SuperUglyNumber_313
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ8ÈÕ
 */
public class SuperUglyNumber_313 {
    // It is my second time to do this problem(saved)
    // All the super ugly numbers come from the old ones time one of the k primes 
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int k = primes.length;
        int[] index = new int[k];
        ugly[0] = 1;
        for (int j = 1; j < n; j++){
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++){
                int v = ugly[index[i]] * primes[i];
                if (v < min) min = v;
            }
            for (int i = 0; i < k; i++){
                int v = ugly[index[i]] * primes[i];
                if (v == min) index[i]++;
            }
            ugly[j] = min;
        }
        return ugly[n - 1];
    }
}
