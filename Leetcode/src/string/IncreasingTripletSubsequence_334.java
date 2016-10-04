package string;

/**
 * <p>
 * IncreasingTripletSubsequence_334
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ9ÈÕ
 */
public class IncreasingTripletSubsequence_334 {
    // find whether exist a longest increasing subsequence that has length >= 3
    public boolean increasingTriplet(int[] nums) {
        int[] lis = new int[3];
        int p = -1;
        for (int num : nums){
            if (p == -1 || num > lis[p]) {
                lis[++p] = num;
                if (p == 2) return true;
            }
            else {
                // try to find the best place to replace a cached number so that it can be more easily to get a longer increasing subsequnce
                int i = p; // position to be replaced
                while(i > 0 && num <= lis[i - 1]) i--;
                lis[i] = num;
            }
        }
        return false;
    }
}
