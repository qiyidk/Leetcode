package reservoir_sampling;

import java.util.Random;

/**
 * <p>
 * RandomPickIndex_398
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ29ÈÕ
 */
public class RandomPickIndex_398 {
    // if have k of targets, picking each of them should have a possibility of 1 / k
    // for ith target, the ith round, it has 1 / i possibility to be picked, i + 1th round, has i / i + 1 possibility to be removed from the reservoir, ..., the kth round, has k - 1/k possibility to be removed from the reservior
    // the total possibility is (1 / i) * (i / i + 1) * (i + 1 / i + 2) * ... * k - 1 / k = 1 / k
    private int[] nums;
    public RandomPickIndex_398(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int size = 0; // size of elements that have a value of target we have seen so far
        int pick = 0;
        Random r = new Random();
        for (int i = 0; i < nums.length ; i++){
            if (nums[i] == target){
                size++;
                if (r.nextInt(size) == 0) pick = i;
            }
        }
        return pick;
    }
}
