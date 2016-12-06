package design;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * LFUCache_460
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ27ÈÕ
 */
public class LFUCache_460 {
    
 // based on LRU, also maintains a linkedlist to connect frequency lists
    private class Node{
        private int key;
        private int val;
        private Node next;
        private Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private class ListNode{
        private int count;
        private ListNode next;
        private ListNode prev;
        private Node head;
        private Node tail;
        public ListNode(int count){
            this.count = count;
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }
    }
    private Map<Integer, Node> nodeLocator = new HashMap<Integer, Node>();
    private Map<Integer, ListNode> listNodeLocator = new HashMap<Integer, ListNode>();
    private ListNode head;
    private ListNode tail;
    private int capacity;
    public LFUCache_460(int capacity) {
        head = new ListNode(0);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = nodeLocator.get(key);
        if (node == null) return -1;
        //remove node from original list
        removeNode(node);
        //add node to new position
        ListNode listNode = addNode(node);
        listNodeLocator.put(key, listNode);
        return node.val;
    }
    
    public void set(int key, int value) {
        if (nodeLocator.size() == 0 && capacity == 0) return;
        Node node = nodeLocator.get(key);
        if (node == null) {//insert
            node = new Node(key, value);
            nodeLocator.put(key, node);
            if (capacity == 0){
                Node remove = head.next.tail.prev;
                removeNode(remove);
                listNodeLocator.remove(remove.key);
                nodeLocator.remove(remove.key);
            }
            else capacity--;
        }
        else {
            node.val = value;
            removeNode(node);
        }
        //set node to new position
        ListNode listNode = addNode(node);
        listNodeLocator.put(key, listNode);
    }
    
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        ListNode listNode = listNodeLocator.get(node.key);
        if (listNode.head.next == listNode.tail) removeListNode(listNode);
    }
    
    private ListNode addNode(Node node){
        ListNode cur = listNodeLocator.get(node.key);
        if (cur == null) cur = head; // don't forget!
        ListNode listNode = createListNode(cur);
        insertNode(node, listNode);
        return listNode;
    }
    
    private void insertNode(Node node, ListNode listNode){
        listNode.head.next.prev = node;
        node.next = listNode.head.next;
        node.prev = listNode.head;
        listNode.head.next = node;
    }
    private ListNode createListNode(ListNode cur){
        ListNode lastNode = cur;
        if (cur != head && cur.head.next == cur.tail) lastNode = cur.prev;// notice cur != head
        if (cur.next.count == cur.count + 1) return cur.next;
        else {
            ListNode l = new ListNode(cur.count + 1);
            lastNode.next.prev = l;
            l.prev = lastNode;
            l.next = lastNode.next;
            lastNode.next = l;
            return l;
        }
    }
    private void removeListNode(ListNode listNode){
        listNode.prev.next = listNode.next;
        listNode.next.prev = listNode.prev;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LFUCache_460 t = new LFUCache_460(2);
        t.set(1, 1);
        t.set(2, 2);
        System.out.println(t.get(1));
        t.set(3, 3);
        System.out.println(t.get(2));
        System.out.println(t.get(3));
        t.set(4, 4);
        System.out.println(t.get(1));
        System.out.println(t.get(3));
        System.out.println(t.get(4));
    }

}
