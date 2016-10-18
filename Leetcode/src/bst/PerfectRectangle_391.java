package bst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * <p>
 * PerfectRectangle_391
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ7ÈÕ
 */
public class PerfectRectangle_391 {
    // we should do two things: 
    // first check whether have enough units to fill up the rectangle
    // second check whether there're some overlaps, to deal with this, we use a sweep-line algorithm
    private class Event implements Comparable<Event>{
        int time;
        int[] rect;
        public Event(int time, int[] rect){
            this.time = time;
            this.rect = rect;
        }
        public int compareTo(Event e){
            if (this.time != e.time) return this.time - e.time;
            else return this.rect[0] - e.rect[0]; // make sure the removing event will always appear earlier than adding event when the two events have the same time.
        }
    }
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        if (n < 2) return true;
        PriorityQueue<Event> events = new PriorityQueue<Event>();
        int x1 = Integer.MAX_VALUE;
        int x2 = 0;
        int y1 = Integer.MAX_VALUE;
        int y2 = 0;
        int s = 0; //area
        for (int[] rectangle : rectangles){
            if (rectangle[0] < x1) x1 = rectangle[0];
            if (rectangle[1] < y1) y1 = rectangle[1];
            if (rectangle[2] > x2) x2 = rectangle[2];
            if (rectangle[3] > y2) y2 = rectangle[3];
            s+= (rectangle[3] - rectangle[1]) * (rectangle[2] - rectangle[0]);
            events.add(new Event(rectangle[0], rectangle));
            events.add(new Event(rectangle[2], rectangle));
        }
        if (s != (y2 - y1) * (x2 - x1)) return false;
        TreeSet<int[]> intervalST = new TreeSet<int[]>(
            //use y coordinate of bottom boundary as a key
            //a trick is that we can also use this comparison to check intersection
            //we redefine the equality of two keys 
            //if has intersection, the keys are the same
            new Comparator<int[]>(){
                public int compare(int[] r1, int[] r2){
                    if (r1[3] <= r2[1]) return -1;
                    else if (r1[1] >= r2[3]) return 1;
                    else return 0;
                }
            }
            );

        while(!events.isEmpty()){
            Event e = events.remove();
            if (e.rect[0] == e.time){ //add interval
                if (!intervalST.add(e.rect)) return false;
            }
            else intervalST.remove(e.rect);
        }
        return true;
    }
    // O(n)solution, needs lots of proof. just give an intuitive proof
    // if the area conforms the perfect cover, then non-perfect cover must have some overlapping.
    // the overlapping must cause a hold inside the cover, which will cause odd number corners:
    //   111111     111111
    // 0011  11     11  11
    //   111111     111221
    // you can add extra overlapping to take care of the hole, the new overlapping rectangles will cause new odd number corners. 
    // you can keep adding overlapping rectangles to get rid of odd number corners, but each time you will generate two new odd number corners until you reach the corners of the cover, which should occur only once.
    // the same checking logic is also correct in general rectangle intersection detection. But in general rectangle intersection detection, it may have too many covers, which are very hard to determine the corners of each cover.
    // what if the non-perfect cover was separated into many unconnected covers, the area cannot the same
    // 1    1
    //  2222
    // 1    1
    public boolean isRectangleCover2(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE;
        int x2 = 0;
        int y1 = Integer.MAX_VALUE;
        int y2 = 0;
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        int s = 0;
        for (int[] rectangle : rectangles){
            if (rectangle[0] < x1) x1 = rectangle[0];
            if (rectangle[1] < y1) y1 = rectangle[1];
            if (rectangle[2] > x2) x2 = rectangle[2];
            if (rectangle[3] > y2) y2 = rectangle[3];
            s += (rectangle[3] - rectangle[1]) * (rectangle[2] - rectangle[0]);
            String bottomLeft = rectangle[0] + "_" + rectangle[1];
            String bottomRight = rectangle[2] + "_" + rectangle[1];
            String upLeft = rectangle[0] + "_" + rectangle[3];
            String upRight = rectangle[2] + "_" + rectangle[3];
            put(bottomLeft, count);
            put(bottomRight, count);
            put(upLeft, count);
            put(upRight, count);
        }
        if (s != (y2 - y1) * (x2 - x1)) return false;
        Integer v = count.remove("" + x1 + "_" + y1);
        if (v == null || v != 1) return false;
        v = count.remove("" + x1 + "_" + y2);
        if (v == null || v != 1) return false;
        v = count.remove("" + x2 + "_" + y1);
        if (v == null || v != 1) return false;
        v = count.remove("" + x2 + "_" + y2);
        if (v == null || v != 1) return false;
        for (int c : count.values()){
            if (c % 2 != 0) return false;
        }
        return true;
    }
    private void put(String coordinate, HashMap<String, Integer> map){
        Integer count = map.get(coordinate);
        if (count == null) map.put(coordinate, 1);
        else map.put(coordinate, count + 1);
    }
}
