package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * MinimumNumberofArrowstoBurstBalloons_452
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ9ÈÕ
 */
public class MinimumNumberofArrowstoBurstBalloons_452 {
    //-----------------
    //   -------------------
    //              -----------
    //                            
    //                       -------
    //-----
    // if no overlap, need n shots
    // it's hard to find a greedy method directly
    // if we eliminate balloons, and leave non-overlapping balloons as many as we can(eliminate as few balloons as we can), the remaining balloons(size of m) are non-overlapping, we must use m shots to get rid of them
    // consider how we get non-overlapping balloons eliminating the least number of balloons
    //-------  d
    //  ------  a
    //  ---     b
    //    ----  c
    // we keep b and eliminate a, c, d
    // a,c,d must overlap b, we can shot at the end of b and eliminate a,c,d together
    // therefore we only need to find m, use the same algorithm as we do before
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                if (i1[0] == i2[0]) return i1[1] - i2[1];
                else return i1[0] - i2[0];
            }
            });
        int lastEnd = points[0][1];
        int remove = 0;
        for (int i = 1; i < points.length; i++){
            if (points[i][0] <= lastEnd){
                if (points[i][1] <= lastEnd){//remove previous one
                    lastEnd = points[i][1];
                    remove++;
                }
                else remove++; // remove current one
            }
            else{
                lastEnd = points[i][1];
            }
        }
        return points.length - remove;
    }
}
