package math;

import java.util.Arrays;

/**
 * <p>
 * MinimumMovestoEqualArrayElements_453
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ7ÈÕ
 */
public class MinimumMovestoEqualArrayElements_453 {
    // at the end, all elements should have the same value
    // for the min value, if we don't hold second min value, there's no way for it to catch up with the second min value, since they will increase together
    // each time we convert min value(s) to second min value(s), then we have new min value(s) and do the same thing
    //          #
    // #        #
    // #   #    #        #
    // #   #    #    #   #   #
    // for increase min to second min, needs second - min step
    // for increase second min to third min, needs third min - min step(when keep second min unchanged to increase min to second min, the third min will increase with min together, their difference will maintain the same)
    // ...therefore, we don't need to sort the array.
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++){
            int second = nums[i] + move; // after each move the elements remaining will be increased by 1
            move += second - min;
            min = second;
        }
        return move;
    }
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int n : nums) if (n < min) min = n;
        int s = 0;
        for (int n : nums) s += n - min;
        return s;
    }
}
