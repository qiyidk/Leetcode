package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * BinaryTreeUpsideDown_156
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ5ÈÕ
 */
public class BinaryTreeUpsideDown_156 {
    // left subtree must exsit unless its a leaf node, any right subtree at most has one node
    // right node will be placed to its root.left.left
    // left node will be placed to its left.right
    // if we keep going left, if root is leaf node, we find the root for the new tree
    // if root if not a leaf node(root.left != null), root.left.right = root(deal with left node), root.left.left = root.right
    // note that if we change the subtrees of root before we do a further traversal, the traversal cannot go on properly, so we have to change the subtrees after traversal.
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        return traverse(root);
    }
    private TreeNode traverse(TreeNode root){
        if (root.left == null) return root;
        TreeNode r = traverse(root.left);
        root.left.right = root;
        root.left.left = root.right;
        root.left = null; // don't forget to clear back path
        root.right = null;
        return r;
    }
}
