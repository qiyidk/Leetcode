package greedy;

/**
 * <p>
 * CreateMaximumNumber_321
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ5ÈÕ
 */
public class CreateMaximumNumber_321 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int minL = Math.max(0, k - nums2.length); // min length of nums1 to be used
        int maxL = Math.min(nums1.length, k); // max length of nums1 to be used
        for (int i = minL; i <= maxL; i++){
            int j = k - i; // length of nums2
            int[] tmp = merge(maxArray(nums1, i), maxArray(nums2, j)); 
            if (greater(tmp, 0, res, 0)) res = tmp;
        }
        return res;
    }

    private boolean greater(int[] nums1, int s1, int[] nums2, int s2){
        while(s1 < nums1.length && s2 < nums2.length && nums1[s1] == nums2[s2]){
            s1++;
            s2++;
        }
        return (s1 < nums1.length) && (s2 == nums2.length || nums1[s1] > nums2[s2]);
    }
    private int[] merge(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int[] res = new int[m + n];
        int i = 0; 
        int j = 0;
        int p = 0;
        //[6,7]
        //[6,0,4]
        //k = 5, when there's a tie, we must keep comparing until find a bigger one(which should also be bigger than the first number "6", to make it simple, just ingore the second condition)
        while (i < m || j < n){
            if ((j == n) || ((i < m) && (greater(nums1, i, nums2, j)))) 
                res[p++] = nums1[i++];
            else res[p++] = nums2[j++];
        }
        return res;
    }
    // straightforward way, take more time.
    @SuppressWarnings("unused")
    private int[] maxArray2(int[] nums, int l){
        int[] res = new int[l];
        int p = 0;
        int maxIndex = -1;
        for (int i = 0; i < l; i++){
            maxIndex++;
            for (int j = maxIndex; j <= nums.length - l + i; j++){
                //nums.length - 1 - j + 1 >= l - 1 - i + 1
                if (nums[j] > nums[maxIndex]) maxIndex = j;
            }
            res[p++] = nums[maxIndex];
        }
        return res;
    }
    // use stack
    private int[] maxArray(int[] nums, int l){
        int[] res = new int[l];
        int p = 0;
        for (int i = 0; i < nums.length; i++){
            while(p != 0 && res[p - 1] < nums[i] && nums.length - i >= l - (p - 1)) p--;// pop, notice the stack top is p - 1
            if (p < l) res[p++] = nums[i]; //push
        }
        return res;
    }
}
