package binarySearch;

import baseDataStructure.TreeNode;

/**
 * <p>
 * CountCompleteTreeNodes_222
 * </p>
 *
 * @author qiyi
 * @version 2016-5-30
 */
public class CountCompleteTreeNodes_222 {
    //saved, use bit shift instead of power, use eliminating right part instead of accumulating left part
    public int countNodes(TreeNode root) {
        // find the height of the tree
        if (root == null) return 0;
        TreeNode p = root;
        int height = 0;
        while (p != null){
            height++;
            p = p.left;
        }
        int lastLevel = 1 << height - 1;// num of elements of the last level of the tree
        int base = lastLevel - 1;
        int num = height < 2 ? 0 : 1 << height - 2;//num of the elements of the last level of the current right subtree
        int curLevel = 1;
        // use binary search to find the last element in the tree
        while (root != null){
            if (root.right != null && hasNode(root.right, curLevel + 1, height)){
                root = root.right;
            }
            else {
                root = root.left;
                lastLevel = lastLevel - num;
            }
            curLevel++;
            num= num >> 1;
        }
        return base + lastLevel;
    }
    
    //whether has node in the last level when keep going left through current root.
    private boolean hasNode(TreeNode root, int curLevel, int height){
        while (root.left != null){
            curLevel++;
            root = root.left;
        }
        return curLevel == height;
    }
}
