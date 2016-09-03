package dynamicProgramming;

import java.util.List;

/**
 * <p>
 * Triangle_120
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ1ÈÕ
 */
public class Triangle_120 {
    // prev[i], cur[i], minimum sum that ends with column i
    // if i = 0 cur[i] = prev[i] + nums[i],
    // if i = cur.size() - 1; cur[i] = prev[i - 1] + nums[i]; 
    // else cur[i] = min(prev[i], prev[i - 1]) + nums[i]
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        int[] prev = new int[]{triangle.get(0).get(0)}; // minimum sum that ends with column i of previous row
        for (int i = 1; i < triangle.size(); i++){
            List<Integer> nums= triangle.get(i);
            int[] cur = new int[nums.size()];
            for (int j = 0; j < nums.size(); j++){
                if (j == 0) cur[j] = prev[j] + nums.get(j);
                else if (j == nums.size() - 1) cur[j] = prev[j - 1] + nums.get(j);
                else cur[j] = Math.min(prev[j], prev[j - 1]) + nums.get(j);
            }
            prev = cur;
        }
        int min = Integer.MAX_VALUE;
        for (int i : prev){
            if (i < min) min = i;
        }
        return min;
    }
}
