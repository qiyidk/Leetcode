package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * KthSmallestElementinaBST_230
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class KthSmallestElementinaBST_230 {
    // the inorder traversal of a BST is in ascending order
    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[1]; // store the final value
        inOrderTraversal(root, k, res);
        return res[0];
    }
    private int inOrderTraversal(TreeNode root, int k, int[] res){
        if (root.left != null) k = inOrderTraversal(root.left, k, res);
        if (k == 0) return 0;
        else{
            // visit root
            k--;
            if (k == 0) {
                res[0] = root.val;
                return k;
            }
        }
        if (root.right != null) k = inOrderTraversal(root.right, k, res);
        return k;
    }
}
