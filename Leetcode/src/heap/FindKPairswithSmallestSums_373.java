package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>
 * FindKPairswithSmallestSums_373
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ10ÈÕ
 */
public class FindKPairswithSmallestSums_373 {
    // if we think about it, it is the same as 378. Kth Smallest Element in a Sorted Matrix
    // row 1 : a[0] + b[0], a[0] + b[1], ...
    // row 2 : a[1] + b[0], a[1] + b[1], ...
    // row k : ...
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<int[]>();
        // corner case
        k = Math.min(k, nums1.length * nums2.length); 
        if (k == 0) return res;
        int[] index = new int[nums1.length]; // the index of nums2 that is being used for each num1
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
            (Integer i, Integer j) -> {return (nums1[i] + nums2[index[i]]) - (nums1[j] + nums2[index[j]]);}
            );
        for (int i = 0; i < nums1.length; i++) pq.add(i); // if k = 0, cannot do this. this operation assumes index 0 always exists
        while(k > 0){
            int r = pq.remove();
            res.add(new int[]{nums1[r], nums2[index[r]]});
            k--;
            index[r]++;
            if (index[r] < nums2.length) pq.add(r);
        }
        return res;
    }
}
