package tree;

import java.util.ArrayList;
import java.util.List;

import baseDataStructure.TreeNode;

/**
 * <p>
 * UniqueBinarySearchTreesII_95
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ28ÈÕ
 */
public class UniqueBinarySearchTreesII_95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        @SuppressWarnings("unchecked")
        List<TreeNode>[] dp = new List[n + 1]; // use dp to get better performance(about halve the time), store the subtrees use elements from 1 to i
        dp[0] = new ArrayList<TreeNode>();
        dp[0].add(null);
        for (int i = 1; i <= n; i++){
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++){// pick j as root
                for(TreeNode left : dp[j - 1]){
                    for (TreeNode right : dp[i - j]) {
                        TreeNode res = new TreeNode(j);
                        res.left = clone(left, 0);
                        res.right = clone(right, j);
                        dp[i].add(res);
                    }
                }
            }
        }
        return dp[n];
    }
    private TreeNode clone(TreeNode root, int offset){
        if (root == null) return null;
        TreeNode r = new TreeNode(root.val + offset);
        r.left = clone(root.left, offset);
        r.right = clone(root.right, offset);
        return r;
    }
}
