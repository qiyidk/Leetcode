package array;

/**
 * <p>
 * RangeAddition_370
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ30ÈÕ
 */
public class RangeAddition_370 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] n : updates){
            // we can consider the array as a water flow from the start to the end.
            // after each operation, the water level is changed
            res[n[0]] += n[2]; // when entering into the start point of one operation, the water level is increased
            if (n[1] != length - 1) res[n[1] + 1] -= n[2]; // when leaving the end point(reach the next element of the end point), the water level is decreased
        }
        for (int i = 1; i < length; i++) res[i] += res[i - 1];
        return res;
    }
}
