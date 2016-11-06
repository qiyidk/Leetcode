package binarySearch;

import java.util.Arrays;
import java.util.Comparator;

import baseDataStructure.Interval;

/**
 * <p>
 * FindRightInterval_436
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ3ÈÕ
 */
public class FindRightInterval_436 {
    public int[] findRightInterval(Interval[] intervals) {
        Interval[] intervals2 = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++){
            intervals2[i] = new Interval(intervals[i].start, i);// use end to store original index
        }
        Arrays.sort(intervals2, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
            });
        int [] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++){
            // for each intervals[i].end, find the closest start 
            if (intervals[i].end > intervals2[intervals2.length - 1].start){
                res[i] = -1;
            }
            else{
                int index = Arrays.binarySearch(intervals2, intervals[i], new Comparator<Interval>(){
                    public int compare(Interval i1, Interval i2){
                        return i1.start - i2.end;
                    }
                    });// index in intervals2
                if (index < 0) {
                    // index = -((index of closest element that greater than key) + 1)
                    index = -index - 1;
                }
                res[i] = intervals2[index].end;// retrieve original index
            }
        }
        return res;
    }
}
