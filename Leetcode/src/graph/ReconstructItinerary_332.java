package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>
 * ReconstructItinerary_332
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class ReconstructItinerary_332 {
    // A - B  B - C  C - A  A - C 
    // A - C  C - A  A - B  B - C
    // if don't have cycle, we can find only one solution
    // if have cycle, 
    // a b c d..e..d 
    //       d..f..d 
    //       d e f g
    // if the cycles have the same start points, they can be interchanged, we need to pick the smallest one that have the smallest end point.
    // we can make a traversal from the start point "JFK" to the end, when we cannot go any further, we reach the end and return, if we still have the elements that have the same start point(because of cycle), we need traverse all of them in lexical order. So it's a recursive process
    // if we got stuck at somewhere, we meet an end point which is the end point for the remaining tickets that have not been picked
    // cycle
    // cycle
    // not a cycle  1 2 3
    
    // cycle      
    // not a cycle
    // cycle   1 3 2
    
    // not a cycle
    // cycle
    // cycle   2 3 1
    // we need to maintain the arrival airports that have same departure airport in ascending order, we can use a priority queue, for different departure airports, we can use a hashtable to get them efficiently.
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        for (int i = 0; i < tickets.length; i++){
            String start = tickets[i][0];
            PriorityQueue<String> pq = map.get(start);
            if (pq == null){
                pq = new PriorityQueue<String>();
                map.put(start, pq);
            }
            pq.add(tickets[i][1]);
        }
        LinkedList<String> res = new LinkedList<String>();
        findItinerary("JFK", res, map);
        return res;
    }
    private void findItinerary(String start, LinkedList<String> res, HashMap<String, PriorityQueue<String>> map){
        // do traverse first
        PriorityQueue<String> pq = map.get(start);
        while (pq != null && pq.size() != 0){
            findItinerary(pq.remove(), res, map);
        }
        // reach the end, set current value to the front of the result we got so far(which is the end of the remaining tickets)
        res.addFirst(start);
    }
}
