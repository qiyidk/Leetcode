package design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * <p>
 * AllOoneDataStructure_432
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ31ÈÕ
 */
public class AllOoneDataStructure_432 {
    // a hashtable can deal with the 1 and 2
    // a deque can get min and max in O(1) time
    // if we maintain a deque of bag, each bag store the keys that have the same count
    // let the hashtable point to the big
    // we can get the big in O(1) time, and let bag has a count attribute, they we can calculate count + 1 and count - 1 in O(1) time, then we can use deque to find adjacent bag, see if we can just add new key to the adjacent bag or build a new bag, both operations will take O(1) time
    // since we need to remove element in the bag in O(1) time, therefore the bag should also be a hashtable
    // since we cann't access next / previous element in build-in deque, we have to implement a deque and we can store the count and LinkedHashSet(get key in O(1) time) in the deque node and the first hashtable point to the node. 
    /** Initialize your data structure here. */
    private class Node{
        private int count;
        private LinkedHashSet<String> set;
        private Node next;
        private Node prev;
        public Node(int count, String str){
            set = new LinkedHashSet<String>();
            set.add(str);
            this.count = count;
        }
    }
    private Node head;
    private Node tail;
    private Map<String, Node> keys;
    public AllOoneDataStructure_432() {
        keys = new HashMap<String, Node>();
        head = new Node(-1, null);
        tail = new Node(-1, null);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        setKey(keys.get(key), key, true);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        setKey(keys.get(key), key, false);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (tail.prev != head) return tail.prev.set.iterator().next();
        else return "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next != tail) return head.next.set.iterator().next();
        else return "";
    }
    
    // remove key from deque
    private void removeKey(Node node, String key){
        node.set.remove(key);
        if (node.set.size() == 0){
            // remove node
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    
    // set key to a specific count
    private void setKey(Node node, String key, boolean increment){
        if (node == null){// new key
            if (!increment) return; // do nothing
            if (head.next.count != 1){
                // add a new count = 1 node
                keys.put(key, insertNodeAfter(head, key, 1));
            }
            else {// already have count = 1 node
                keys.put(key, head.next);
                head.next.set.add(key);
            }
        }
        else{
            removeKey(node, key);
            if (increment){
                int c = node.count + 1;
                if (c != node.next.count) keys.put(key, insertNodeAfter(node.next.prev, key, c)); 
                else{
                    node.next.set.add(key);
                    keys.put(key, node.next);
                }
            }
            else{
                int c = node.count - 1;
                if (c == 0) {
                    keys.remove(key);
                    return;
                }
                if (c != node.prev.count) keys.put(key, insertNodeAfter(node.prev, key, c)); 
                else{
                    node.prev.set.add(key);
                    keys.put(key, node.prev);
                }
            }
        }
    }
    private Node insertNodeAfter(Node node, String key, int count){
        Node n = new Node(count, key);
        node.next.prev = n;
        n.next = node.next;
        n.prev = node;
        node.next = n;
        return n;
    }
}
