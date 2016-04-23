package math;

/**
 * <p>
 * IntegerBreak_343
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ22ÈÕ
 */
public class IntegerBreak_343 {
    // saved
    // an integer may use as a whole or separate into some small integers, we need choose the biggest one
    public int integerBreak2(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i < n; i++) res[i] = i;// all the nums except the last one can either use it as a whole or separate into some small integers
        res[n] = 0;// the last num must separate into some small integers
        for (int i = 2; i <= n; i++){
            for (int firstNum = 1; firstNum <= i / 2; firstNum++){
                if (res[firstNum] * res[i - firstNum] > res[i]) res[i] = res[firstNum] * res[i - firstNum];
            }
        }
        return res[n];
    }
    
/*    Observation and Analysis:

        >     2  1 , 1
        >     3  1 , 2
        >     4  2 , 2
        >     5  3 , 2
        >     6  3 , 3
        >     7  3 , 2 , 2
        >     8  3 , 3 , 2
        >     9  3 , 3 , 3
        >     10 3 , 3 , 2, 2    
        if we split n into k1...kt, for each k(1 <= k <= n)

        >     1. if k <= 3, f(k) <= k, which means if we still have k to be split, we should use it as a whole.
        >     2. if k > 3, f(k) >= k (k can at least split into 2 and k - 2, k * (k - 2) - k = k(k - 3) >= 0)
        >         therefore, if we have k (k > 3), we must split it to get a bigger product 
        >     3. in this situation, if we split k into 1 and k - 1(k - 1 may keep spliting to m1...mt), 
              we can always get a bigger product by adding this "1" to one of the small split number m; 
              therefore, the smallest number that we split is 2
        >     4. Whenever we have a k > 3, we will separate it into small numbers
        >     Finally, all the small numbers will be either 2 or 3.

        Solution:

        >     suppose we can separate k into k1, k2, ... kt to get the biggest product for k. 
        >     1. now we consider k + 1: Since we won't split into k and 1, so we must add this extra 1 to one of the t small numbers.
        >     2. if we add 1 to a "2", then we form a new "3"; if we add 1 to a "3", 
                   we must separate it again into two 2s.
        >     3. for the first choice, we will get f(k) / 2 * 3 = 1.5 f(k); 
                   for the second choice, we will get f(k) / 3 * 4 = 1.3 f(k)
        >     4. therefore, as long as we have 2, we change a "2" to "3" rather than changing a "3" to two 2s.
        >     5. the whole process will tend to get as much 3s as possible to get bigger product.*/

    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n % 3 == 0) return (int)Math.pow(3, n / 3);
        if (n % 3 == 1) return (int)Math.pow(3, (n - 4) / 3) * 4;// we cannot have a small number that is equal to 1. Then use 4 instead.
        return (int)Math.pow(3, (n - 2) / 3) * 2;
    }
}
