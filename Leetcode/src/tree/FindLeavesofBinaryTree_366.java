package tree;

import java.util.ArrayList;
import java.util.List;

import baseDataStructure.TreeNode;

/**
 * <p>
 * FindLeavesofBinaryTree_366
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ26ÈÕ
 */
public class FindLeavesofBinaryTree_366 {
    // let the height of node is the round that we remove the node.(e.g. all the leaves have a height of 0)
    // we can know the height of each node only when we reach the leaf and go back
    // therefore when we go back from the leaf to its parant, we collect leaves and put it into a corresponding list.
    // note that the height of the parent is equal to the max height of its children + 1.
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        findLeaves(root, res);
        return res;
    }
    private int findLeaves(TreeNode root, List<List<Integer>> res){
        int h = -1;
        if (root.left != null) h = findLeaves(root.left, res);
        if (root.right != null) h = Math.max(h, findLeaves(root.right, res));
        h++;
        List<Integer> list = null;
        if (res.size() - 1 < h) {
            list = new ArrayList<Integer>();
            res.add(list);
        }
        else list = res.get(h);
        list.add(root.val);
        return h;
    }
}
