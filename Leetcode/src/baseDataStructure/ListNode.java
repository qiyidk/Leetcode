package baseDataStructure;

/**
 * <p>
 * ListNode
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ7ÈÕ
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
    public static ListNode head;
    static{
        head = new ListNode(0);
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(7);
        head.next = a;
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
    }
    public static ListNode getNode(){
        return head;
    }
}
