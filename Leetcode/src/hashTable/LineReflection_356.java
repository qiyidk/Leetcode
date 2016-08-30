package hashTable;

import java.util.HashMap;

/**
 * <p>
 * LineReflection_356
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class LineReflection_356 {
    // reflect means symmetry
    // find the possible line parallel to y-axis, and then try to make pairs during traversal.
    // 1. if x == middle line, don't need make up a pair.
    // 2. to make up a pair, we need to find the first point and the second point.
    // if a point is not in the map, we put the current point's corresponding point(second point) into the map, which means we have one extra point to match.
    // if a point is in the map, we make up another pair and decrement the count of that point(second point). if count is equal to 0, remove that point.
    // another way to solve this problem is sorting, first by x, then by y, and then make a traversal to check. 
    public boolean isReflected(int[][] points) {
        if (points.length == 0) return true;
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        long r = (long)Integer.MAX_VALUE - Integer.MIN_VALUE + 1;// must cast to long to avoid overflow
        int cy = points[0][1];
        for (int[] i : points) {
            if (i[1] == cy){
                maxX = Math.max(maxX, i[0]);
                minX = Math.min(minX, i[0]);
            }
        }
        //int m = (maxX + minX) / 2;   note that it may bring double value
        int s = maxX + minX;// use 2m instead of m
        //HashSet<String> set = new HashSet<String>();// hashSet won't work, since there may be many pairs that have the same coordinates
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        for (int[] i : points) {
            int x = i[0];
            int y = i[1];
            if (x == (double)s / 2) continue;// don't need a pair
            long pos = x * r + y;// this may be faster than a string representation.
            Integer count = map.get(pos);
            if (count == null){
                // generate the other str of the pair
                // x1 - m = m - x2 x1 + x2 = 2m 
                //str = (s - x) + "_" + y;
                pos = (s - x) * r + y;
                Integer count2 = map.get(pos);
                if (count2 == null) map.put(pos, 1);
                else map.put(pos, count2 + 1);
            }
            else{
                // find a pair
                count--;
                if (count == 0) map.remove(pos);
                else map.put(pos, count);
            }
        }
        return map.isEmpty();
    }
}
