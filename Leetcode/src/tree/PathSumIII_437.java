package tree;

import java.util.HashMap;
import java.util.Map;

import baseDataStructure.TreeNode;

/**
 * <p>
 * PathSumIII_437
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ24ÈÕ
 */
public class PathSumIII_437 {
    // worst case : O(n^2), copy hashmap will cost O(n) for each node
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) return 0;
        Object[] res = collect(root, sum);
        return (int)res[1];
    }
    // return 0:count of each summation of path starts with root, 1: count of path that sum = target
    @SuppressWarnings("unchecked")
    private Object[] collect(TreeNode root, int sum){
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int c = 0;
        count.put(root.val, 1);
        if (root.left != null) {
            Object[] r = collect(root.left, sum);
            c += (int)r[1];
            for (int key : ((Map<Integer, Integer>)r[0]).keySet()){
                int cc = 0;
                if (count.get(root.val + key) != null) cc = count.get(root.val + key);
                count.put(root.val + key, ((Map<Integer, Integer>)r[0]).get(key) + cc);
            }
        }
        if (root.right != null) {
            Object[] r = collect(root.right, sum);
            c += (int)r[1];
            for (int key : ((Map<Integer, Integer>)r[0]).keySet()){
                int cc = 0;
                if (count.get(root.val + key) != null) cc = count.get(root.val + key);
                count.put(root.val + key, ((Map<Integer, Integer>)r[0]).get(key) + cc);
            }
        }
        if (count.get(sum) != null) c += count.get(sum);
        return new Object[]{count, c};
    }
    
    // to optimize the solution, we try to avoid updating hashmap
    // if we maintain only one hashmap, how can we get prefix sum that starts with its direct sub-nodes
    // we can store all the sum that starts with each node along the backwards path, but since we traverse from top to bottom, once the sum is inserted into the map, there's no way to remove it(if we go to another sub tree, some sum should be removed)
    // we consider store the sum from top to bottom.
    // if we can store the sum that starts with each node along the path, we can get sum of 1, 12, 123..
    // but what we need is 123, 23, 3. 
    // Since we already has 123(sum), check root.val(4) + (123, 23, 3, "") = target is equivalent to check root.val + sum - ("", 1, 12, 123) = target
    // check root.val + sum - target = ("", 1, 12, 123), "" in original map is null, we need extra check
    // when we get "" in the original map, it means root.val + sum = target, the complete sum that subtract nothing. we can add(0, 1) to map to represent this situation
    // therefore we don't need to update the tree
    // when come back from the subtrees, the sum of 123 should be removed
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        count.put(0, 1); // represent the complete path sum that is without subtracting any prefix sum
        return pathSum(root, 0, count, sum);
    }
    private int pathSum(TreeNode root, int sum, Map<Integer, Integer> count, int target) {
        if (root == null) return 0;
        sum += root.val;
        int v = count.get(sum - target) == null ? 0 : count.get(sum - target);
        
        Integer c = count.get(sum);
        if (c == null) count.put(sum, 1);
        else count.put(sum, c + 1);
        
        v += pathSum(root.left, sum, count, target);
        v += pathSum(root.right, sum, count, target);
        count.put(sum, count.get(sum) - 1);
        return v;
    }
}
