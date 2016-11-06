package math;

/**
 * <p>
 * ArrangingCoins_441
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ31ÈÕ
 */
public class ArrangingCoins_441 {
    // count of coins of full staircase with l row: s = (1 + l) * l / 2
    // what we need to do is to find the biggest l that n >= s 
    // let n = (1 + l) * l / 2 => l^2 + l - 2n = 0 => (l + 1/2) ^ 2 - 1/4 - 2n = 0
    // (l + 0.5) ^ 2 = 2n + 0.25
    public int arrangeCoins(int n) {
        return (int)(Math.sqrt(2.0 * n + 0.25) - 0.5);
    }
}
