package bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * CountofSmallerNumbersAfterSelf_315
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ5ÈÕ
 */
public class CountofSmallerNumbersAfterSelf_315 {
    
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] count = new Integer[n];
        if (n == 0) return new ArrayList<Integer>();
        count[n - 1] = 0;
        BST t = new BST();
        t.insert(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--){
            count[i] = t.insert(nums[i]);
        }
        return Arrays.asList(count);
    }
    private class BST{
        private Node root = null;
        private class Node{
            private int size; // size of the tree
            private int count;// count of nodes of the same val
            private int val;
            private Node left = null;
            private Node right = null;
            public Node(int val){
                this.val = val;
                size = 1;
                count = 1;
            } 
        }
        public int insert(int val){
            if (root == null) {
                root = new Node(val);
                return 0; 
            }
            return insert(root, val);
        }
        // collect value during insert. we can do it separately, but it will take more time
        private int insert(Node root, int val){
            int smaller = 0;
            root.size++;
            if (val > root.val) {
                smaller += root.count; // count root itself
                smaller += root.left == null ? 0 : root.left.size;
                if (root.right == null) root.right = new Node(val);
                else smaller += insert(root.right, val);
            }
            else if (val < root.val) {
                if (root.left == null) root.left = new Node(val);
                else smaller += insert(root.left, val);
            }
            else {
                smaller += root.left == null ? 0 : root.left.size;
                root.count++;
            }
            return smaller;
        }
    }

}
