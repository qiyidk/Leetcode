package greedy;

import java.util.Arrays;
import java.util.Comparator;

import baseDataStructure.Interval;

/**
 * <p>
 * NonoverlappingIntervals_435
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ3ÈÕ
 */
public class NonoverlappingIntervals_435 {
    //
    //  --------
    //   ------
    //----    -----
    // it is kind of hard to decide which to remove
    // but if we consider this process in different point of view
    // we try to find non-overlapping intervals that have the maximum number of intervals instead of minimum removal
    // if one interval contains another interval, one of them must be removed, keep the shorter one.
    // ------------------
    // --------  ------
    // if two intervals are overlapping, one of them must be removed, keep the one has less start
    //    ---------
    //        --------------
    // therefore we already have a complete greedy method to pick best intervals
    // then we need to find an efficient way to do this
    // first we sort the array so that we won't miss any potential overlapping, and then we can do a consecutive process to deal with interval one by one
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if (i1.start != i2.start) return i1.start - i2.start;
                else return i1.end - i2.end;
            }
            });
        int remove = 0;
        int lastStart = intervals[0].start;
        int lastEnd = intervals[0].end;
        // note that the first element will never be removed
        for (int i = 1; i < intervals.length; i++){
            if (intervals[i].start == lastStart) remove++; //remove current one
            else if (intervals[i].start < lastEnd){
                if (intervals[i].end <= lastEnd){
                    // remove previous one
                    remove++;
                    lastStart = intervals[i].start;
                    lastEnd = intervals[i].end;
                }
                else{
                    // remove current one
                    remove++;
                }
            }
            else{
                //no overlapping, update new boundary
                lastStart = intervals[i].start;
                lastEnd = intervals[i].end;
            }
        }
        return remove;
    }
}
