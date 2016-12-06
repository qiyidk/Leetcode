package greedy;

/**
 * <p>
 * J132Pattern_456
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ16ÈÕ
 */
public class J132Pattern_456 {
    // if we only have a1, when meet a number < a1, n will be better than a1, replace a1 with n
    // if we have n > a1, we have a2 now
    // if we have n > a2, n will be better than a2, replace a2 with n
    // if a1 < n < a2, return true
    // if n < a1, hard to deal with
    
    // actually a3 is in the middle of a1 and a2, it's hard to maintain
    // a1 is the smallest one, it's relatively easy to deal with
    // so we traverse from end to front and maintain a min array to know whether there's valid a1 before index i
    // each time we pick a a3, and try to optimize a3 each round until find a valid combination or return false
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        int[] min = new int[nums.length]; // min before index i inclusively
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (nums[i] < min[i - 1]) min[i] = nums[i];
            else min[i] = min[i - 1];
        }
        int a3 = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 1; i--){
            if (nums[i] > a3){
                if (min[i - 1] < a3) return true;
                else a3 = nums[i];//a3 is too small, update a3
            }
            else {
                if (i < 2 || nums[i] <= min[i - 2]) continue;// cannot choose new a3
                else a3 = nums[i]; // optimize with smaller a3 
            }
        }
        return false;
    }
}
