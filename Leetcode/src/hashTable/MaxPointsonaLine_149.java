package hashTable;

import java.util.HashMap;
import java.util.Map;

import baseDataStructure.Point;

/**
 * <p>
 * MaxPointsonaLine_149
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ9ÈÕ
 */
public class MaxPointsonaLine_149 {
    // a line can be uniquely represented by y=ax+b or x=c (vertical-line case).
    // for n points, we can form n^2 lines, if two lines can be represented by the same a,b,c, the 
    // points of the two lines are in the same line. Therefore what we need is to model each line, store the 
    // count of points of the same line 
    // y1 = ax1 + b
    // y2 = ax2 + b
    // a = (y1 - y2) / (x1 - x2)
    // b = y1 - (y1 - y2) / (x1 - x2) * x1
    // each time we choose a point as an end point, and test all the lines that contain that point. note that one point and slope can uniquely determine a line, so that we don't need to care about "b".
    // note that there may be some points have the same x,y. therefore we count duplicate
    public int maxPoints2(Point[] points) {
        int max = 0;
        for (int i = 0; i < points.length; i++){
            Map<Double, Integer> slopes = new HashMap<Double, Integer>();
            int x1 = points[i].x;
            int y1 = points[i].y;
            int duplicate = 1;
            for (int j = i + 1; j < points.length; j++){
                double slope = 0;
                int x2 = points[j].x;
                int y2 = points[j].y;
                if (x1 == x2 && y1 == y2) {
                    duplicate++;
                    continue;
                }
                if (x1 == x2) slope = Float.POSITIVE_INFINITY;
                else{
                    slope = (double) (y1 - y2) / (x1 - x2);
                    if (slope == 0) slope = 0; // to avoid -0.0
                }
                Integer c = slopes.get(slope);
                if (c == null) slopes.put(slope, 1);
                else slopes.put(slope, c + 1);
            }
            int m = 0;
            for (Integer c : slopes.values()) m = Math.max(m, c);
            max = Math.max(max, m + duplicate);
        }
        return max;
    }
    // one problem is that the previous solution may encounter precision problem
    // one solution is to track the coprime y1 - y2 and x1 - x2 instead of slope
    public int maxPoints(Point[] points) {
        int max = 0;
        for (int i = 0; i < points.length; i++){
            Map<String, Integer> slopes = new HashMap<String, Integer>();
            int x1 = points[i].x;
            int y1 = points[i].y;
            int duplicate = 1;
            for (int j = i + 1; j < points.length; j++){
                String slope = null;
                int x2 = points[j].x;
                int y2 = points[j].y;
                if (x1 == x2 && y1 == y2) {
                    duplicate++;
                    continue;
                }
                if (x1 == x2) slope = "INF";
                else{
                    int gcd = gcd(y1 - y2, x1 - x2);
                    slope = ((y1 - y2) / gcd) + " " + ((x1 - x2) / gcd);
                }
                Integer c = slopes.get(slope);
                if (c == null) slopes.put(slope, 1);
                else slopes.put(slope, c + 1);
            }
            int m = 0;
            for (Integer c : slopes.values()) m = Math.max(m, c);
            max = Math.max(max, m + duplicate);
        }
        return max;
    }
    private int gcd(int x, int y){
        if (y == 0) return x;
        else return gcd(y, x % y);
    }
    public static void main(String[] args){
        Point a = new Point(3, 1);
        Point b = new Point(12, 3);
        Point c = new Point(3, 1);
        Point d = new Point(-6, -1);
        Point[] points = new Point[]{a, b, c, d};
        System.out.println(new MaxPointsonaLine_149().maxPoints(points));
    }
}
