package design;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * DesignHitCounter_362
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ21ÈÕ
 */
public class DesignHitCounter_362 {
    //to deal with multi-thread, we must either synchronize the whole object(not method, since more than 1 method will affect the list) or use synchronizedList. 
    //if hits are dense, the methods below will both take O(1) time; but if the hits are sparse, it would be better to use method2(it will guarantee O(1) for hit(), O(300) for getHits()). 
    //method2: use two array, one for time, one for count. use time % 300 to get index to accumulate the count, and traverse the array to get hits. if for index i, it already has a value for a different time, that original value will be replaced. One thing should be noticed is that the replaced value won't affect the correctness of the final result, since the value must belong to a time that is already expired.
    /** Initialize your data structure here. */
    private class Node{
        private int time;
        private int hits;
        public Node(int time, int hits){
            this.time = time;
            this.hits = hits;
        }
    }
    private Deque<Node> deque;
    private int hits;
    private Node currentNode;
    public DesignHitCounter_362() {
        deque = new LinkedList<Node>();
        hits = 0;
        currentNode = new Node(0, 0);
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits++;
        if (timestamp != currentNode.time){
            deque.addLast(currentNode);
            currentNode = new Node(timestamp, 1);
        }
        else{
            currentNode.hits++;
        }
        int least = timestamp - 300 + 1;
        while(!deque.isEmpty() && deque.peekFirst().time < least){
            hits -= deque.removeFirst().hits;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        if (timestamp != currentNode.time){
            deque.addLast(currentNode);
            currentNode = new Node(timestamp, 0);
        }
        int least = timestamp - 300 + 1;
        while(!deque.isEmpty() && deque.peekFirst().time < least){
            hits -= deque.removeFirst().hits;
        }
        return hits;
    }
}
