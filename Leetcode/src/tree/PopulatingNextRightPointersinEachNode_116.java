package tree;

import baseDataStructure.TreeLinkNode;

/**
 * <p>
 * PopulatingNextRightPointersinEachNode_116
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ21ÈÕ
 */
public class PopulatingNextRightPointersinEachNode_116 {
    // saved
    // for each level, maintain the first node of that level
    // then maintain the "next" field of all children
    // then go to next level use the "next" attribute to do the level traversal
    // we only need to maintain the first node of current level
    public void connect(TreeLinkNode root) {
        while (root != null){
            TreeLinkNode p = root;
            while(p != null){
                if (p.left != null){
                    p.left.next = p.right;
                }
                if (p.right != null && p.next != null){
                    p.right.next = p.next.left;
                }
                p = p.next;
            }
            root = root.left;
        }
    }
}
