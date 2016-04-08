package list;

import baseDataStructure.ListNode;

/**
 * <p>
 * ReverseLinkedListII_92
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ8ÈÕ
 */
public class ReverseLinkedListII_92 {
    // saved
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int index = 1;
        ListNode h = head;
        if (m == 1) return reverse(head, n - m + 1);
        // get element indexing m - 1
        while(index != m - 1){
            head = head.next;
            index++;
        }
        head.next = reverse(head.next, n - m + 1);
        return h;
    }
    // return the new head
    private ListNode reverse(ListNode head, int len){
        ListNode p = head;// last parent
        len--;// we only need insert len - 1 element
        for (int i = 0; i < len; i++){
            ListNode insert = p.next;
            p.next = insert.next;
            insert.next = head;
            head = insert;
        }
        return head;
    }
}
