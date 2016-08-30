package deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * SlidingWindowMaximum_239
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ9ÈÕ
 */
public class SlidingWindowMaximum_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // if we store the numbers of a window
        // the numbers that less than or equal to the rightmost number cannot affect the result
        // if we keep eliminating this kind of numbers.
        // all the numbers we store will be in descending order, which means we can get all max numbers one by one
        if (nums.length == 0) return new int[0];
        Deque<Integer> dq = new LinkedList<Integer>();// store the index of nums
        int[] res = new int[nums.length - k + 1];
        int  p = 0; //index for final result
        for (int i = 0; i < nums.length; i++){
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) dq.removeLast();
            dq.addLast(i);
            if (i - dq.peekFirst() + 1 > k) dq.removeFirst();
            if (i >= k - 1) res[p++] = nums[dq.peekFirst()];
        }
        return res;
    }
}
