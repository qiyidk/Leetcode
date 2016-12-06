package hashTable;

import java.util.HashMap;

/**
 * <p>
 * NumberofBoomerangs_447
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ7ÈÕ
 */
public class NumberofBoomerangs_447 {
    public int numberOfBoomerangs(int[][] points) {
        int s = 0;
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int[] i : points){
            for (int[] j : points){
                int dist = (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);
                Integer v = count.get(dist);
                if (v == null) count.put(dist, 1);
                else {
                    s += 2 * v;
                    count.put(dist, v + 1);
                }
            }// (v + 1)v - v(v - 1) = 2v. for each increment 1 of v, s increase 2v
            /*
            for (int v : count.values()){
                s += v * (v - 1); 
            }*/
            count.clear();
        }
        return s;
    }
}
