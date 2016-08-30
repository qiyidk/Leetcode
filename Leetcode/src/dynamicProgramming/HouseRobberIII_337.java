package dynamicProgramming;

import baseDataStructure.TreeNode;

/**
 * <p>
 * HouseRobberIII_337
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ17ÈÕ
 */
public class HouseRobberIII_337 {
    // consider a two-level tree it may have two max value either using its root or not using its root(m1, m2)
    // if we use two two-level trees to make up a three level tree, if the new tree:
    // 1. use the root, the sub-trees cannot use their roots, the max value = tree1's m2 + tree2's m2
    // 2. do not use the root, the sub-trees may or may not use their roots, the max value = max(tree1's m1, tree1's m2) + max( tree2's m1, tree2's m2)
    // at the end we will calculate the value = max(root's m1, root's m2)
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int m1 = root.val;//use root
        int m2 = 0;//do not use root
        int[] res1 = dfs(root.left);
        int[] res2 = dfs(root.right);
        m1 = m1 + res1[1] + res2[1];
        m2 = Math.max(res1[0], res1[1]) + Math.max(res2[0], res2[1]);
        return new int[]{m1, m2};
    }
}
