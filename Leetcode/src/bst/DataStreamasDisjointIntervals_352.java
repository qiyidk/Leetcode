package bst;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import baseDataStructure.Interval;

/**
 * <p>
 * DataStreamasDisjointIntervals_352
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ23ÈÕ
 */
public class DataStreamasDisjointIntervals_352 {
    private TreeSet<Interval> intervals;
    /** Initialize your data structure here. */
    public DataStreamasDisjointIntervals_352() {
        intervals = new TreeSet<Interval>((Interval i1, Interval i2) -> {return i1.start - i2.start;}); 
    }
    
    public void addNum(int val) {
        Interval key = new Interval(val, val);
        Interval floor = intervals.floor(key);
        Interval ceiling = intervals.ceiling(key);
        // i1   i2  i3
        //   val
        if ((floor == null || floor.end < val - 1)
             && 
            (ceiling == null || ceiling.start > val + 1)){
            // create a new interval
            Interval i = new Interval(val, val);
            intervals.add(i);
        }
        else{
            if (floor != null && floor.end > val - 1) return;
            // merge
            int start = val;
            int end = val;
            if (floor != null && floor.end == val - 1){
                start = floor.start;
                intervals.remove(floor);
            }
            if (ceiling != null && ceiling.start == val + 1){
                end = ceiling.end;
                intervals.remove(ceiling);
            }
            Interval i = new Interval(start, end);
            intervals.add(i);
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> res = new ArrayList<Interval>(intervals);
        return res;
    }
}
