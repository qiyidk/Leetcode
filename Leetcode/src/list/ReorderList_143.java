package list;

import baseDataStructure.ListNode;

/**
 * <p>
 * ReorderList_143
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ8ÈÕ
 */
public class ReorderList_143 {
    // we need reverse the second half part and then we can traverse all the elements we need in order
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow pointer points to the last element of the first half part.
        ListNode head2 = slow.next == null ? null : reverse(slow.next);//reverse
        slow.next = null; //cut off the first part so that we won't get a cyclic reference
        // insert the second half part into the first half part one by one
        while(head2 != null){
            ListNode newHead2 = head2.next;
            head2.next = head.next;
            head.next = head2;
            head = head2.next;
            head2 = newHead2;
        }
    }
    private ListNode reverse (ListNode head){
        ListNode p = head;
        while (p.next != null){
            ListNode insert = p.next;
            p.next = insert.next;
            insert.next = head;
            head = insert;
        }
        return head;
    }
}
