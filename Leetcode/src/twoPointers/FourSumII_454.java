package twoPointers;

import java.util.HashMap;

/**
 * <p>
 * FourSumII_454
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ16ÈÕ
 */
public class FourSumII_454 {
 // since we don't need to store the result pairs and allow element that has same value appear in different pairs as long as has different index
    // it can be done faster than O(n ^ 3)
    // preprocess A,B and C,D
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        HashMap<Integer, Integer> count1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> count2 = new HashMap<Integer, Integer>();
        for (int i : A){
            for (int j : B){
                int v = i + j;
                Integer c = count1.get(v);
                if (c == null) count1.put(v, 1);
                else count1.put(v, c + 1);
            }
        }
        for (int i : C){
            for (int j : D){
                int v = i + j;
                Integer c = count2.get(v);
                if (c == null) count2.put(v, 1);
                else count2.put(v, c + 1);
            }
        }
        for (int v : count1.keySet()){
            int c1 = count1.get(v);
            Integer c2 = count2.get(-v);
            if (c2 != null) res += c1 * c2;
        }
        return res;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] A = {0, 1, -1};
        int[] B = {-1, 1, 0};
        int[] C = {0, 0, 1};
        int[] D = {-1, 1, 1};
        System.out.println(new FourSumII_454().fourSumCount(A, B, C, D));
    }

}
