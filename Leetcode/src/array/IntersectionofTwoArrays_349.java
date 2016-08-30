package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * IntersectionofTwoArrays_349
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ16ÈÕ
 */
public class IntersectionofTwoArrays_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for (int n1 : nums1) set.add(n1);
        for (int n2 : nums2){
            if (set.contains(n2)){
                set.remove(n2);
                list.add(n2);
            }
        }
        int[] res = new int[list.size()];
        int k = 0;
        for (int i : list) res[k++] = i;
        return res;
    }
}
