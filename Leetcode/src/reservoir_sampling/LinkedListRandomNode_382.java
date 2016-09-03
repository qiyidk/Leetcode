package reservoir_sampling;

import java.util.Random;

import baseDataStructure.ListNode;

/**
 * <p>
 * LinkedListRandomNode_382
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ2ÈÕ
 */
public class LinkedListRandomNode_382 {
    // Reservoir_sampling:
    // https://en.wikipedia.org/wiki/Reservoir_sampling
    // Reservoir_sampling will be more efficient if we pick k random elements or reading elements from a file. 
    
    private ListNode head;
    private Random rand;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode_382(ListNode head) {
        this.head = head;
        rand = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode root =  head;
        int i = 1;
        int v = 0;
        while(root != null){
            int p = rand.nextInt(i++);
            if (p == 0) v = root.val;
            root = root.next;
        }
        return v;
    }
}
