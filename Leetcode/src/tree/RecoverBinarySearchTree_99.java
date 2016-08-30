package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * RecoverBinarySearchTree_99
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class RecoverBinarySearchTree_99 {
    //    5               3
    //  2    9          2    9
    //1   3    13     1   5    13
    //in-order traversal 1 2 3 5 9 13  
    //if switch 3, 5 ->1 2 5 3 9 13, 5 will be a legal number during traversal, only 3 is illegal
    //if switch 2, 5 ->1 5 3 2 9 13, 3 and 2 will be illegal (the next number of the smaller one and the smaller one) 
    //if switch 1, 5 ->5 2 1 3 9 13, 2 and 1 will be illegal (the next number of the smaller one and the smaller one) 
    //if switch 5, 13 ->1 2 3 13 9 5, 9 and 5 will be illegal (the next number of the smaller one and the smaller one) 
    // if a,b are adjacent, only smaller one will be illegal, illegal number and the previous one are the switched numbers
    // if a,b are not adjacent, both the next number of smaller one and the smaller one will be illegal
    // the previous number of the first illegal number and the second illegal number are the switched numbers
    public void recoverTree2(TreeNode root) {
        TreeNode[] res = new TreeNode[2];
        inOrder(new TreeNode(Integer.MIN_VALUE), root, res);
        swap(res[0], res[1]);
    }
    private TreeNode inOrder(TreeNode lastNode, TreeNode root, TreeNode[] res){
        if (root.left != null) lastNode = inOrder(lastNode, root.left, res);
        check(root, lastNode, res);
        lastNode = root;
        if (root.right != null) lastNode = inOrder(lastNode, root.right, res);
        return lastNode;
    } 
    private void swap(TreeNode node1, TreeNode node2){
        int v = node1.val;
        node1.val = node2.val;
        node2.val = v;
    }
    private void check(TreeNode root, TreeNode lastNode, TreeNode[] res){
        if (root.val < lastNode.val) {
            if (res[0] == null) {
                res[0] = lastNode;
                res[1] = root;
            }
            else res[1] = root;
        } 
    }
    /*
    Morris-traversal is similar to recursive/iterative traversal, but we need to modify the tree structure during the traversal. before we visiting the left tree of a root, we will build a back-edge between rightmost node in left tree and the root. So we can go back to the root node after we are done with the left tree. Then we locate the rightmost node in left subtree again, cut the back-edge, recover the tree structure and start visit right subtree. The detection of two incorrect TreeNodes is similar to iterative/recursive in-order traversal.
    */
    public void recoverTree(TreeNode root) {
        TreeNode[] res = new TreeNode[2];
        TreeNode p = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        while(root != null){
            if (root.left != null){// maintain a back edge before go left
                p = root.left;
                // find rightmost node
                while(p.right != null && p.right != root) p = p.right;
                if (p.right == null){//first visit
                    p.right = root;
                    root = root.left;
                }
                else{//all left subtrees are visited, visit cur node and recover the tree and go right
                    check(root, pre, res);
                    pre = root;
                    p.right = null;
                    root = root.right;
                }
            }
            else {
                check(root, pre, res);
                pre = root;
                root = root.right;
            }
        }
        swap(res[0], res[1]);
    }
}
