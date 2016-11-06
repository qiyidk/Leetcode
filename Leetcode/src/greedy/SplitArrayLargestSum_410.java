package greedy;

/**
 * <p>
 * SplitArrayLargestSum_410
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ5ÈÕ
 */
public class SplitArrayLargestSum_410 {
    // max >= avg
    // 1sum -----        i to j - 1
    // 2sum -------      i to j
    // 3avg ---------
    // 4sum -----------  i to j + 1
    // 5sum------------- i to j + 2
    // it is easy to see that 2 will always be better than 1
    // for a certain max, f(5) > max f(4) <= max
    // 4 will be better than 2,3
    // if we use this greedy way to split array by a certain max1, if cannot have a split max <= max1
    // a. either one single element > max1 
    // b. or only the last section may have a sum > max1
    // if we choose max = max2 < max1
    // a. a single element > max1 > max2
    // b. each section whose sum <= max1 will maintain the same or shrink
    // the sum of last section >= max1 > max2
    // therefore we cannot split by max2, neither
    // therefore we can use a binary search way to try possible max and for each max, we use greedy way to choose the best cut and check
    // in total, it will take O(log(SumN)n) time
    public int splitArray(int[] nums, int m) {
        long sum = 0;
        int biggest = 0;
        for (int n : nums) {
            if (n > biggest) biggest = n;
            sum += n;
        }
        if (m == 1) return (int)sum;
        long l = Math.max(sum / m, biggest);
        long r = sum;
        while(l <= r){
            long max = (l + r) / 2;
            long left = sum;// sum of elements left in that last section
            long s = 0;
            int split = m - 1;// number of split can be used
            for (int n : nums){
                if (s + n > max){
                    //split
                    left -= s;
                    s = n;
                    split--;
                    if (split == 0) break;
                }
                else s += n;
            }
            if (left > max) l = max + 1;
            else r = max - 1;
        }
        return (int)(r + 1);
    }
}
