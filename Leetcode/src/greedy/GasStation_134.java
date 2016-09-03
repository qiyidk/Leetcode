package greedy;

/**
 * <p>
 * GasStation_134
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ1ÈÕ
 */
public class GasStation_134 {
    //   1        -2         3        -2        5       -3 ...
    // gas[i]   cost[i]                        gas[j]  cost[j]
    // if i can reach j, for all nodes through path, sum(gas[i..k]) >= sum(cost[i..k]) k = i + 1...j - 1
    // we denote sum[i, j] = sum[i - 1, j] + (gas[i] - cost[i]) = sum[i, j - 1] + (gas[j - 1] - cost[j - 1]).
    // for a starting point i, if a path is valid from i to j, starting point i is better than j, since each sum(i, k) (k = i + 1, ... , j) is >= 0
    // so we scan start from i to n - 1 and when encountering a invalid point j, which means, we cannot start at i to j.then we try from i - 1 to i - w, in which way we can reuse the sum of i to j
    // when we encountering a point that the sum (i', j) >= 0, first we know all the points from i' - 1 to j cannot be starting points. then for any point t, i' < t <= i, sum(t, j) < 0, sum(i', j) >= 0
    // therefore sum(i', j) - sum(t, j) = sum(i', t) >= 0 , so t is valid, every points along the path is valid.
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // notice the direction is from i -> i + 1
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while(start != end){
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                end++;
            }
            else {
                start--;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }
}
