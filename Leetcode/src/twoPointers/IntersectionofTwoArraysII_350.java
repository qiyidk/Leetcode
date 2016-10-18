package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * IntersectionofTwoArraysII_350
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ16ÈÕ
 */
public class IntersectionofTwoArraysII_350 {
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> c1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> c2 = new HashMap<Integer, Integer>();
        for(int i: nums1){
            Integer c = c1.get(i);
            c = c == null? 1 : c + 1;
            c1.put(i, c);
        }
        for(int i: nums2){
            Integer c = c2.get(i);
            c = c == null? 1 : c + 1;
            c2.put(i, c);
        }
        HashMap<Integer, Integer> l = c1.size() > c2.size()? c1 : c2;
        HashMap<Integer, Integer> s = c1.size() > c2.size()? c2 : c1;
        List<Integer> list = new ArrayList<Integer>();
        for (Integer k : s.keySet()){
            int count1 = s.get(k);
            int count2 = l.get(k) == null ? 0 : l.get(k);
            int c = Math.min(count1, count2);
            for(int i = 0; i < c; i++) list.add(k);
        }
        int[] res = new int[list.size()];
        int p = 0;
        for(int i : list) res[p++] = i;
        return res;
    }
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int p = 0;
        int[] aux = new int[Math.min(nums1.length, nums2.length)];
        while(i < nums1.length && j < nums2.length){
            if (nums1[i] == nums2[j]){
                aux[p++] = nums1[i];
                i++;
                j++;
            }
            else if (nums1[i] > nums2[j]) j++;
            else i++;
        }
        int[] res = new int[p];
        System.arraycopy(aux, 0, res, 0, p);
        return res;
    }
    public int[] intersect3(int[] nums1, int[] nums2) {
        // follow-up2: (nums1.length = m, nums2.length = n, m < n)
        // we can only store num1 for hashing and traverse num2
        // it will save space, the time will be almost the same
        // follow-up3:
        // if nums1 and nums2 are both too big, use external sort
        // if only nums2 is too big, put nums1 into a hashmap
        return null;
    }
}
