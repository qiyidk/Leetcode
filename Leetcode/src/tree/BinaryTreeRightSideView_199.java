package tree;

import java.util.ArrayList;
import java.util.List;

import baseDataStructure.TreeNode;

/**
 * <p>
 * BinaryTreeRightSideView_199
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ11ÈÕ
 */
public class BinaryTreeRightSideView_199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        collect(root, res, 0);
        return res;
    }
    private void collect(TreeNode root, List<Integer> res, int depth){
        if (root == null) return;
        // collect root
        if (depth == res.size()) res.add(root.val); // have a value for new depth
        collect(root.right, res, depth + 1);
        collect(root.left, res, depth + 1);
    }
}
