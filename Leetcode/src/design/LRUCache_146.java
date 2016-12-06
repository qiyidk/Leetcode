package design;

import java.util.HashMap;

/**
 * <p>
 * LRUCache_146
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ27ÈÕ
 */
public class LRUCache_146 {
    // we need to locate the key, and when setting or getting a key, switch the key to the front
    // therefore we need to maintain a linkedList to update the position of key
    // to locate the key, we can use a hashmap
    private class Node{
        private int key;
        private int val;
        private Node prev;
        private Node next;
        public Node(int key, int val){
            this.val = val;
            this.key = key;
        }
    }
    private int capacity;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> locater;
    
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        locater = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node n = locater.get(key);
        if (n == null) return -1;
        // update linkedlist
        update(n);
        return n.val;
    }
    
    public void set(int key, int value) {
        Node n = locater.get(key);
        if (n == null){
            n = new Node(key, value);
            locater.put(key, n);
            if (capacity == 0) locater.remove(tail.prev.key);
        }
        else n.val = value;
        // update linkedlist
        update(n);
    }
    private void update(Node n){
        if (n.next != null){
            // not insert, need connect adjacent nodes
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        else{
            // insert, need to check capacity
            if (capacity == 0){
                //eliminate lru node
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
            }
            else capacity--;
        }
        // put current node in the front
        head.next.prev = n;
        n.next = head.next;
        n.prev = head;
        head.next = n;
    }
}
