package list;

import baseDataStructure.ListNode;

/**
 * <p>
 * ReverseNodesinkGroup_25
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ7ÈÕ
 */
public class ReverseNodesinkGroup_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // test whether can get a valid node by moving k - 1 steps
        if (!canMove(head, k - 1)) return head;
        ListNode p = head;//last parent node of next reversed list, note that the head node will be the tail node(also the last parent node of next reversed list) after reversing
        head = reverse(head, k);
        while(p.next != null){
            if (!canMove(p.next, k - 1)) break;
            ListNode nextP = p.next;
            p.next = reverse(p.next, k);
            p = nextP;
        }
        return head;
    }
    // return the new head
    private ListNode reverse(ListNode head, int len){
        // after inserting all the nodes except the first node to the front consecutively, we can get a reversed list
        ListNode p = head; // last parent node
        len--;// we only need to do len - 1 insertions
        for (int i = 0; i < len; i++){
            ListNode insert = p.next;
            p.next = insert.next;
            insert.next = head;
            head = insert;
        }
        return head;
    }
    // return null if cannot get a valid node by moving k steps 
    private boolean canMove(ListNode head, int k){
        while(k > 0 && head != null){
            head = head.next;
            k--;
        }
        return head != null;
    }

    public static void main(String[] args){
        int k = 3;
        ListNode head = ListNode.getNode();
        ListNode t = head;
        while(t != null){
            System.out.print(t.val);
            t = t.next;
        }
        System.out.println();
        head = new ReverseNodesinkGroup_25().reverseKGroup(head, k);
        while(head != null){
            System.out.print(head.val);
            head = head.next;
        }
    }
}
