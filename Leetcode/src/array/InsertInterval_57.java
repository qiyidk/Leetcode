package array;

import java.util.ArrayList;
import java.util.List;

import baseDataStructure.Interval;

/**
 * <p>
 * InsertInterval_57
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ5ÈÕ
 */
public class InsertInterval_57 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        boolean reachEnd = false;//reach the end of the newInterval.
        for (Interval i : intervals){
            if (reachEnd || i.end < newInterval.start) {
                // cannot overlap
                res.add(i);
                continue;
                }
            if (i.start > newInterval.end) {
                // the current Interval i won't overlap with the new interval and the new interval reaches the end
                res.add(newInterval);
                res.add(i);
                reachEnd = true;
                continue;
            }
            if (i.start < newInterval.start){
                newInterval.start = i.start;
            }
            if (i.end > newInterval.end){
                newInterval.end = i.end;
                res.add(newInterval);
                reachEnd = true;
            }
        }
        if (!reachEnd) res.add(newInterval);// deal with the last interval
        return res;
    }
}
