package dynamicProgramming;

/**
 * <p>
 * BestTimetoBuyandSellStockIV_188
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ3ÈÕ
 */
public class BestTimetoBuyandSellStockIV_188 {
    public int maxProfit(int k, int[] prices) {
        int lastProfit = 0;
        if (prices.length == 0) return 0;
        //optimization
        if (k * 2 >= prices.length){
            // k is not a limitation at all
            for (int i = 1; i < prices.length; i++){
                if (prices[i] >= prices[i - 1]) lastProfit += (prices[i] - prices[i - 1]);
            }
            return lastProfit;
        }
        int[] profits1 = new int[prices.length]; // max profits of last round for prices we have seen so far
        int[] profits2 = new int[prices.length]; // max profits of current round for prices we have seen so far
        for (int i = 0; i < k; i++){
            int maxProfit = -prices[0]; // max profits(max profits of last round plus one buy) for prices we have seen so far
            for (int j = 1; j < prices.length; j++){
                profits2[j] = Math.max(profits2[j - 1], maxProfit + prices[j]);// either j - 1 prices with k transactions or j - 1 prices with k - 1 transactions and a new transaction by selling at price j
                maxProfit = Math.max(maxProfit, profits1[j - 1] - prices[j]);
            }
            if (lastProfit == profits2[prices.length - 1]) break; // there's no need to continue
            else {
                lastProfit = profits2[prices.length - 1];
                profits1 = profits2;
                profits2 = new int[prices.length];
            }
        }
        return lastProfit;
    }
}
