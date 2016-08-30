package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * MovingAveragefromDataStream_346
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class MovingAveragefromDataStream_346 {
    private Queue<Integer> q = new LinkedList<Integer>(); 
    private int size;
    private int sum = 0;
    /** Initialize your data structure here. */
    public MovingAveragefromDataStream_346(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        int s = q.size();
        q.add(val);
        if (s != size){
            sum += val;
            return 1.0 * sum / (s + 1);
        }
        else{
            sum = sum + val - q.remove();
            return 1.0 * sum / size;
        }
    }
}
