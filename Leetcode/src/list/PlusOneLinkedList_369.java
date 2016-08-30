package list;

import baseDataStructure.ListNode;

/**
 * <p>
 * PlusOneLinkedList_369
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ30ÈÕ
 */
public class PlusOneLinkedList_369 {
    public ListNode plusOne(ListNode head) {
        int carry = plusOneCarry(head);
        if (carry == 0) return head;
        else {
            ListNode root = new ListNode(1);
            root.next = head;
            return root;
        }
    }
    private int plusOneCarry(ListNode head){
        int carry = 0;
        if (head.next == null) carry = 1; 
        else carry = plusOneCarry(head.next);
        head.val = (head.val + carry) % 10;
        //if (head.val == 0) return 1; if head.val = 0 and carry = 0, the new carry should be 0.
        if (carry == 1 && head.val == 0) return 1;
        else return 0; 
    }
}
