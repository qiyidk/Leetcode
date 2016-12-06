package hashTable;

import java.util.HashMap;
import java.util.Map;

import baseDataStructure.RandomListNode;

/**
 * <p>
 * CopyListwithRandomPointer_138
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ27ÈÕ
 */
public class CopyListwithRandomPointer_138 {
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>(); // old -> new
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode p = dummy;
        while(head != null){
            RandomListNode n = map.get(head);
            if (n == null){
                n = new RandomListNode(head.label);
                map.put(head, n);
            }
            if (head.random != null){
                n.random = map.get(head.random);
                if (n.random == null) {
                    n.random = new RandomListNode(head.random.label);
                    map.put(head.random, n.random);
                }
            }
            p.next = n;
            p = p.next;
            head = head.next;
        }
        return dummy.next;
    }
}
