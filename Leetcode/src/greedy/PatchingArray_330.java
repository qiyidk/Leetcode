package greedy;

/**
 * <p>
 * PatchingArray_330
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class PatchingArray_330 {
    // if we already cover from 1 to m, then if we have another num that is less than or equal to m + 1, all the numbers that are from 1 to m + num will be covered. But if we do not have that kind of number, m + 1 cannot be coverred, we need to add a patch, the number that are greater than m + 1 won't help, and the numbers that are smaller than m + 1, may cover m + 1, but will create a relatively smaller cover range than picking m + 1, so m + 1 is the best choice at this situation
    public int minPatches(int[] nums, int n) {
        long max = 0; //the upper bound of the number that is already coverred  
        int patch = 0;
        int i = 0;
        while(max < n){
            // check whether we can cover max + 1 using all the elements we have so far
            if (i < nums.length && nums[i] <= max + 1){
                // extend the cover range
                max = max + nums[i];
                i++;
            }
            else{
                // we must add a patch
                patch++;
                max = max + max + 1;
            }
        }
        return patch;
    }
}
