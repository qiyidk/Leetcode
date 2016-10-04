package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import baseDataStructure.TreeNode;

/**
 * <p>
 * BinaryTreeVerticalOrderTraversal_314
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ9ÈÕ
 */
public class BinaryTreeVerticalOrderTraversal_314 {
    // we can do run a bfs to traverse all the nodes and put them into appropriate lists
    // if we use dfs, the order within each list cannot guarantee.
    //              a
    //        a           a
    //              a
    //                    a
    // what we need to do is to calculate the correct list number the node belongs to
    // when go left, the list number decrease 1, when go right, the list number increase 1
    // since we start in the middle(root), we need a data structure to add and visit elements from both side
    // deque can be used for storing, but cannot visit from an arbitrary position in O(1)time
    // we have two ways:
    // 1. hashtable
    // 2. two arraylists to store node and its corresponding offset to root 
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        if (root == null) return res;
        int min = 0;
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        q1.add(root);
        q2.add(0);
        while(!q1.isEmpty()){
            TreeNode n = q1.remove();
            int index = q2.remove();
            if (index < min) min = index;
            List<Integer> list = map.get(index);
            if (list == null){
                list = new ArrayList<Integer>();
                map.put(index, list);
            }
            list.add(n.val);
            if (n.left != null) {
                q1.add(n.left);
                q2.add(index - 1);
            }
            if (n.right != null){
                q1.add(n.right);
                q2.add(index + 1);
            }
        }
        for (int i = min; i < map.size() + min; i++) res.add(map.get(i));
        return res;
    }
}
