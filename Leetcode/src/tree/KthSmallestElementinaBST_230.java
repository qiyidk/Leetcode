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
        int[] res = new int[]{0, 0};// counter, value
        inOrderTraversal(root, k, res);
        return res[1];
    }
    private void inOrderTraversal(TreeNode root, int k, int[] res){
        if (root.left != null) inOrderTraversal(root.left, k, res);
        if (res[0] == k) return;
        else{
            // visit root
            res[0]++;
            if (res[0] == k) {
                res[1] = root.val;
                return;
            }
        }
        if (root.right != null) inOrderTraversal(root.right, k, res);
    }
}
