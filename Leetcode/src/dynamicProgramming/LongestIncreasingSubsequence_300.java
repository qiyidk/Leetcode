package dynamicProgramming;

import java.util.Arrays;

/**
 * <p>
 * LongestIncreasingSubsequence_300
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ23ÈÕ
 */
public class LongestIncreasingSubsequence_300 {
  //n^2: record each length of LIS which starts with i. For i, scan all results after i to update the length
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int l = 1;
        int[] lis = new int[n];
        lis[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--){
            int maxL = 1; //max Length of lis that starts with i
            for (int j = i + 1; j < n; j++){
                if (nums[i] < nums[j]) maxL = Math.max(maxL, 1 + lis[j]);
            }
            l = Math.max(l, maxL);
            lis[i] = maxL;
        }
        return l;
    }

    //nlogn 
    //increase the length of LIS iff the new element is larger than the last element of the LIS
    //if cannot increase the length of LIS, optimize the composition of the LIS to make it easier to extend length(replace the first element in the LIS greater than the current element with the current element)
    //Notice that the LIS is not the exact real LIS(have some optimization which may not take into effect in the real LIS).
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int[] lis = new int[n];
        lis[0] = nums[0];
        int p = 1; //next position to be inserted
        for (int i = 1; i < n; i++){
            if (nums[i] > lis[p - 1]){
                //extend lis
                lis[p++] = nums[i];
            }
            else{
                //optimize lis
                int index = Arrays.binarySearch(lis, 0, p, nums[i]); 
                if (index < 0){
                    // not found, and if not found, the system method returns -(index) - 1
                    lis[-(index + 1)] = nums[i];
                } 
            }
        }
        return p;
    }
}
