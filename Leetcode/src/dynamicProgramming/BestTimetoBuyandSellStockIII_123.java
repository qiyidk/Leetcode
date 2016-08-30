package dynamicProgramming;

/**
 * <p>
 * BestTimetoBuyandSellStockIII_123
 * </p>
 *
 * @author qiyi
 * @version 2016年8月3日
 */
public class BestTimetoBuyandSellStockIII_123 {
    // [1,2,4,2,5,7,2,4,9,0] wrong solution: 虽然下面的算法会产生在“没有交易次数限制时”最大的两个间隔，但是并不是最大的两个间隔
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) return 0;
        int[] v= new int[2];//0: biggest 1: second biggest
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++){
            if (prices[i] > prices[i - 1]) continue;// keep going
            else{
                if (prices[i - 1] == buy) buy = prices[i]; // buy and sell at the same day, no profit
                else{
                    updateV(v, prices[i - 1] - buy);
                    buy = prices[i];
                }    
            }
        }
        // deal with the last value
        if (prices[prices.length - 1] != buy) updateV(v, prices[prices.length - 1] - buy);
        return v[0] + v[1];
    }
    private void updateV(int[] v, int value){
        if (value > v[0]){
            v[1] = v[0];
            v[0] = value;
        }
        else if (value > v[1]) v[1] = value;
    }
    // 上一个解法中通过拆分大的间隔获得了更高的profit
    // profit1    maxProfit  profit2   如果两个间隔选取都在maxprofit间隔之外，则一定不是最大，因为可以用max替代其中一个得到更大的值
    // profit1 profit2       如果任何一个profit和max重叠， 则该profit 一定可以更大，取maxprofit的第一个点购买或者最后一个点进行出售
    //   maxProfit
    // 则要么两个profit都在最大profit里，要么一个完全在里面，一个完全在外面
    // 第一种情况，找第一个间隔的售出点-第二个间隔的买入点的最大值m，res = maxProfit + diff
    // 第二种情况，在maxprofit里的那个一定取maxprofit，res = maxProfit + anotherProfit, 在前后两端找max profit
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int profit = 0;
        int start = 0;
        int end = 0;
        int[] res = getMaxProfit(prices,0, n - 1);
        if (res == null) return 0;
        profit += res[2];
        start = res[0];
        end = res[1];
        int v = 0;
        res = getMaxProfit(prices, 0, start - 1);
        if (res != null && res[2] > v) v = res[2];
        res = getMaxProfit(prices, end + 1, n - 1);
        if (res != null && res[2] > v) v = res[2];
        if (end - start + 1 >= 4){
            int diff = getMaxDiff(prices, start + 1, end - 1);
            v = Math.max(v, diff);
        }
        profit += v;
        return profit;  
    }
    private int[] getMaxProfit(int[] prices, int start, int end){
        int[] res = new int[3];
        int s = 0;
        int e = 0;
        int max = 0;
        int minIndex = start;
        for (int i = start + 1; i <= end; i++){
            if (prices[i] - prices[minIndex] > max){
                max = prices[i] - prices[minIndex];
                s = minIndex;
                e = i;
            }
            else if (prices[i] < prices[minIndex]) minIndex = i;
        }
        if (max == 0) return null;
        res[0] = s;
        res[1] = e;
        res[2] = max;
        return res;
    }
    private int getMaxDiff(int[] prices, int start, int end){
        int max = 0;
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++){
            if (prices[maxIndex] - prices[i] > max) max = prices[maxIndex] - prices[i];
            else if (prices[i] > prices[maxIndex]) maxIndex = i;
        }
        return max;
    }
    // we can think the whole process as four separate processes. buy1 sell1 buy2 sell2
    // when we get a new price, we update the four values in a greedy way
    // the update order is quite important, when we updating sell, we cannot use the current price as sell value, since we cannot buy and sell at the same day. Therefore we must update sell before buy.
    // when we update sell2, it must use the previous buy2 which must use the previous sell1 which must use the previous buy1
    public int maxProfit4(int[] prices) {
        int buy1 = Integer.MIN_VALUE; // the profit we have after buying one stock
        int sell1 = 0; // the profit we have after selling one stock
        int buy2 = Integer.MIN_VALUE; // the profit we have after buying the second stock
        int sell2 = 0; // the profit we have after selling the second stock
        for (int p : prices){
            sell2 = Math.max(sell2, buy2 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell1 = Math.max(sell1, buy1 + p);
            buy1 = Math.max(buy1, -p);
        }
        return sell2;
    }
    // one main problem of last solution is if k is very big, we have to run k times which may be quite unnecessary(maybe we can at most make 10 transactions, but k is 10000000)
    // if k is very big, we can use another dp solution which uses extra memory
    public int maxProfit(int[] prices) {
        int k = 2;
        int lastProfit = 0;
        if (prices.length == 0) return 0;
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
