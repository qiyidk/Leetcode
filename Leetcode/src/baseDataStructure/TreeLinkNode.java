package baseDataStructure;

/**
 * <p>
 * TreeLinkNode
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ21ÈÕ
 */
 public class TreeLinkNode {
     public int val;
     public TreeLinkNode left, right, next;
     public TreeLinkNode(int x) { val = x; }
     
     public static TreeLinkNode getNode(){
         TreeLinkNode root = new TreeLinkNode(0);
         TreeLinkNode n1 = new TreeLinkNode(2);
         TreeLinkNode n2 = new TreeLinkNode(4);
         root.left = n1;
         root.right = n2;
         
         TreeLinkNode n3 = new TreeLinkNode(1);
         n1.left = n3;
         
         TreeLinkNode n4 = new TreeLinkNode(3);
         TreeLinkNode n5 = new TreeLinkNode(-1);
         n2.left = n4;
         n2.right = n5;
         
         TreeLinkNode n6 = new TreeLinkNode(5);
         TreeLinkNode n7 = new TreeLinkNode(1);
         n3.left = n6;
         n3.right = n7;
         
         TreeLinkNode n8 = new TreeLinkNode(6);
         n4.right = n8;
         
         TreeLinkNode n9 = new TreeLinkNode(8);
         n5.right = n9;
         return root;
     }
 }
