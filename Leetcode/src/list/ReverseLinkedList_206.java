package list;

import baseDataStructure.ListNode;

/**
 * <p>
 * ReverseLinkedList_206
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ8ÈÕ
 */
public class ReverseLinkedList_206 {
    public ListNode reverseList(ListNode head) {
        // saved
        // if we always try to find the correct position and insert, it will take O(n)time to do each insertion, and use O(n^2)time to do the whole process.
        // actually, we don't need to find the final position for each elements.
        // if we always add new element in the front, it will display the elements in reverse order.
        // 1 2 3 4
        // 2 1 3 4
        // 3 2 1 4
        // 4 3 2 1
        ListNode p = head;//last parent node
        if (p == null) return null;
        while(p.next != null){
            ListNode insert = p.next;// the element to be inserted
            p.next = insert.next;
            insert.next = head;
            head = insert;
        }
        return head;
    }
}
