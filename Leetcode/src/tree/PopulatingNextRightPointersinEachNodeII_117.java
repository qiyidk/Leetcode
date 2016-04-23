package tree;

import baseDataStructure.TreeLinkNode;

/**
 * <p>
 * PopulatingNextRightPointersinEachNodeII_117
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ22ÈÕ
 */
public class PopulatingNextRightPointersinEachNodeII_117 {
    public void connect(TreeLinkNode root) {
        TreeLinkNode start = new TreeLinkNode(0); //dummy head, "next" pointer points to the next start. 
        while(root != null){
            start.next = null;
            TreeLinkNode handler = start; //handler, deal with the "next" attribute of children including dummy head
            do{
                if (root.left != null){
                    handler.next = root.left; //when find a new child, we also get a new "next"value
                    handler = handler.next;
                }
                if (root.right != null){
                    handler.next = root.right; //when find a new child, we also get a new "next"value
                    handler = handler.next;
                }
                root = root.next;
            }while(root != null);
            root = start.next;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TreeLinkNode root = TreeLinkNode.getNode();
        new PopulatingNextRightPointersinEachNodeII_117().connect(root);
        System.out.println(1);
    }

}
