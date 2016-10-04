package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * CountUnivalueSubtrees_250
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ4ÈÕ
 */
public class CountUnivalueSubtrees_250 {
    // if both of subtrees are uni-value subtrees and sub.root.val = root.val, the tree is a uni-value tree
    // we make a post-order traversal and check the tree
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        return count(root)[0];
    }
    // return int[2] : 0 : the number of uni-value tree of current tree, 1 : whether current tree is a uni-value tree; 0 : false, 1 : true
    private int[] count(TreeNode root){
        boolean left = true; // whether left subtree conforms the rule for uni-value
        boolean right = true; // whether right subtree conforms the rule for uni-value
        int sum = 0;
        if (root.left != null){
            int[] res1 = count(root.left);
            sum += res1[0];
            left = res1[1] == 1 && root.val == root.left.val;
        }
        if (root.right != null){
            int[] res2 = count(root.right);
            sum += res2[0];
            right = res2[1] == 1 && root.val == root.right.val;
        }
        int uni = left && right ? 1 : 0;
        sum += uni;
        return new int[]{sum, uni};
    }
}
