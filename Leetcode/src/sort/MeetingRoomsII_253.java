package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import baseDataStructure.Interval;

/**
 * <p>
 * MeetingRoomsII_253
 * </p>
 *
 * @author qiyi
 * @version 2016��3��10��
 */
public class MeetingRoomsII_253 {
    public int minMeetingRooms(Interval[] intervals) {
        // saved
        int n = intervals.length;
        if (n <= 1) return n;
        // for better preformance, we can use a radix sort.
        // instead of a pq, we can use a sorted array
        // ����˼�룺��Ϊ������Σ�start������interval��Ҫ�����һ�Σ���endֻҪ��С�����Ҫ��start���������ﾡ�����ø�С��start��ǰ�����Ҿ�������end���������room��
        // ʹ��pq��ʵ����ÿ���ҵ�ǰ��С��end����������һ��start�������Դﵽ�������
        // ʵ���ϣ������Ա�����ʱ����ǰ��С��endͬʱҲ��������������С��end����Ϊ��ʱcurrent end< next start < next end ����Ϊͬһ��interval��startһ��������������end��start��ԶС��end�����������ǿ��Բ�������end�������ĸ�interval��һ�������ź�����ʵ���Ż���������Ҫpq�Ķ�̬����
        
        // room
        // 1     start end  start end 
        // 2     start end  start end 
        // 3     start end  start end 
        // every time we try to take the best advantege of current room, always choose the earliest meeting to visit, if the meeting can be appended to the end of one of the current picked meetings, we don't need a new room; otherwise, we need a new room
        // we need to keep track of the left most end point of current rooms, and always try to put the earliest meeting to that room in order to get the best usage of that room.
        // if we don't distinguish which interval is the start point and end point belong to and just sort the start points and end points, we can do the process above.
        // a. smallest start < smallest end, we need add a new room, and the smallest end point won't be changed(all the end points that smaller than the current smallest end points have already used)
        // b. smallest start >= smallest end, we don't need a new room, the current end point has been used, and then the next end point will be the smallest end point so far.
        // whether we can ignore which interval is the start point and end point belong to.
        // one potential problem is the start point and end point of the same meeting should be added together, but we don't
        // end point is visited but the start point has not been visited yet. we will visit next end point only when current smallest end point < current visited start point, since the start point of the meeting must be smaller than the end point, the start point of that meeting must have been visited, it won't happen
        // start point is visited but the end point has not been visited yet. each start point at most consume one end point, the end point must be greater than current left most end point, we don't need to care about that
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++){
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int room = 0;
        int endIndex = 0; // left most end point so far
        for (int i = 0; i < n; i++){
            // use start represent interval, try to put each interval after each "end"
            if (start[i] < end[endIndex]){
                // need one more room, and current "end" keep the same
                room++;
            }
            else{
                // use current room and current end. After using this end, this end cannot be used any more.(it will change to another end in the latter position of the end array)
                endIndex++;
            }
        }
        return room;
    }
    public int minMeetingRooms2(Interval[] intervals) {
        int n = intervals.length;
        if (n <= 1) return n;
        Arrays.sort(intervals, new Comparator<Interval>(){
        
            public int compare(Interval o1, Interval o2){
                return o1.start - o2.start;
            }
        });
        // for better preformance, we can build our own pq to support update
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int room = 1;
        pq.add(intervals[0].end);
        for (int i = 1; i < n; i++){
            int end = pq.peek();
            if (intervals[i].start < end){
                // need one more room
                room++;
                pq.add(intervals[i].end);
            }
            else{
                // update current room
                pq.remove();
                pq.add(intervals[i].end);
            }
        }
        return room;
    }
}
