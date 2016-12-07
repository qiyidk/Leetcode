/**
 * 
 */
package math;

import java.util.List;

/**
 * @author qiyi
 *
 */
public class ConvexPolygon_469 {
    // use the sign of z coordinate of cross-product to check whether edge1 is always on the same side of edge2
    // cross-product:https://en.wikipedia.org/wiki/Cross_product
    // in this case,  z = 0 for all vectors(edges)
    // z of cross-product = x1y2 - y1x2
    
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        int prev = cp(points.get(n - 1), points.get(0), points.get(1));// cp for index 0
        for (int i = 1; i < points.size(); i++){
            int cur = cp(points.get(i - 1), points.get(i), points.get((i + 1) % n));
            if (cur == 0) continue;// no cp
            if (1L * cur * prev < 0) return false;// notice overflow
            prev = cur; 
        }
        return true;
    }
    private int cp(List<Integer> p1, List<Integer> p2, List<Integer> p3){
        // vector p1p2, p2p3
        int x1 = p2.get(0) - p1.get(0);
        int y1 = p2.get(1) - p1.get(1);
        int x2 = p3.get(0) - p2.get(0);
        int y2 = p3.get(1) - p2.get(1);
        return x1 * y2 - y1 * x2;
    }
}
