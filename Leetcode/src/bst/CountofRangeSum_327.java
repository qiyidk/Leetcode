package bst;

/**
 * <p>
 * CountofRangeSum_327
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ6ÈÕ
 */
public class CountofRangeSum_327 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 1 2 3 4 5 6 7  sum from 1 to 1, 1 to 2, 1 to 3, 1 to 4 
        //   2 3 4 5 6 7  sum from         2 to 2, 2 to 3, 2 to 4 
        //     3 4 5 6 7  sum from                 3 to 3, 3 to 4
        //       4 5 6 7  sum from                         4 to 4
        // if the first set of sums fall in rangeA + num1, the second set of sums fall in rangeA
        // if we insert in reverse order, we don't need remove node
        long sum = 0; //notice overflow
        int count = 0;
        long offset = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        BST t = new BST();
        for (int i = nums.length - 1; i >= 0; i--){
             t.insert(sum);
             sum -= nums[i];
             offset = sum;
             count += t.range(offset + lower, offset + upper);
        }
        return count;
    }
    private class BST{
        private class Node{
            private int count; // number of elements of the same val
            private int size; // the size of the tree
            private Node left;
            private Node right;
            private long val;
            public Node(long val){
                this.val = val;
                size = 1;
                count = 1;
            }
        }
        private Node root = null;
        public void insert(long val){
            root = insert(root, val);
        }
        private Node insert(Node root, long val){
            if (root == null) root = new Node(val);
            else{
                root.size++;
                if (val > root.val) root.right = insert(root.right, val);
                else if (val < root.val) root.left = insert(root.left, val);
                else root.count++;
            }
            return root;
        }
        public int range(long lower, long upper){
            return greater(root, lower) + less(root, upper) - root.size;
        }
        private int greater(Node root, long val){
            if (root == null) return 0;
            int count = 0;
            if (root.val < val) count += greater(root.right, val);
            else {
                count += root.count;
                count += root.right == null ? 0 : root.right.size;
                count += greater(root.left, val);
            }
            return count;
        }
        private int less(Node root, long val){
            if (root == null) return 0;
            int count = 0;
            if (root.val > val) count += less(root.left, val);
            else {
                count += root.count;
                count += root.left == null ? 0 : root.left.size;
                count += less(root.right, val);
            }
            return count;
        }
    }
    public static void main(String[] args){
        CountofRangeSum_327 t = new CountofRangeSum_327();
        int[] nums = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;
        t.countRangeSum(nums, lower, upper);
    }
}
