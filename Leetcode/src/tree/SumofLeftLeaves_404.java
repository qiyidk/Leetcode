package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * SumofLeftLeaves_404
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ7ÈÕ
 */
public class SumofLeftLeaves_404 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return sumOfLeftLeaves(root, false);
    }
    private int sumOfLeftLeaves(TreeNode root, boolean isLeftChild){
        if (root.left == null && root.right == null){
            if (isLeftChild) return root.val;
            else return 0;
        }
        int v = 0;
        if (root.left != null) v += sumOfLeftLeaves(root.left, true);
        if (root.right != null) v += sumOfLeftLeaves(root.right, false);
        return v;
    }
}
