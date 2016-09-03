package tree;

import baseDataStructure.TreeNode;

/**
 * <p>
 * BinaryTreeMaximumPathSum_124
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class BinaryTreeMaximumPathSum_124 {
    //       1
    //   2       4
    //4    9  -6   10
    //we denote f(root) = max value of path that goes through root
    //we denote f1(root) = max value of path that goes from left subtree to "root"(exclusively), f2(root) is that of path that goes from right subtree to 1
    //f(root) = f1(root) + f2(root) + root.val
    //f1(root) = max(root.left + max(f1(root.left). f2(root.left)), 0)
    //f2(root) = max(root.right + max(f1(root.right). f2(root.right)), 0)
    //we can define f as max value as subtree(include a max sub path from one subtree plus the root)
    //we can use a postorder traversal to get all f and find max
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return postOrder(root)[1];
    }
    private int[] postOrder(TreeNode root){
        int[] res = new int[2]; //0 : max value as subtree, 1 : max sum so far 
        int[] res1 = new int[2];
        int[] res2 = new int[2];
        if (root.left != null) res1 = postOrder(root.left);
        if (root.right != null) res2 = postOrder(root.right);
        res[0] = Math.max(0, root.val + Math.max(res1[0], res2[0]));
        res[1] = root.val + res1[0] + res2[0];
        // if no subtree, cannot regard its max value as 0
        if (root.left != null && res1[1] > res[1]) res[1] = res1[1];
        if (root.right != null && res2[1] > res[1]) res[1] = res2[1];
        return res;
    }
}
