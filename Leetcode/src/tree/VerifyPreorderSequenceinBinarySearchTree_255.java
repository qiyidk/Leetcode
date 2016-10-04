package tree;

import java.util.Stack;

/**
 * <p>
 * VerifyPreorderSequenceinBinarySearchTree_255
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ8ÈÕ
 */
public class VerifyPreorderSequenceinBinarySearchTree_255 {
    //      10
    //   3       12
    // 1   5  11   16
    // root - left - right
    // keep going left and when entering a new subtree, store the root. if cannot go left, try to go right, when going to right subtree, pop the root.
    // if element is < root, go left(only valid position is under current parent). it should satisfy the left boundary
    // if element is > root, pop root until find the last root < element, go right.
    // both choices will enter into a new subtree, store the root
    // check every elements by min and max
    
    public boolean verifyPreorder2(int[] preorder) {
        if (preorder.length == 0) return true;
        Stack<Integer> s = new Stack<Integer>();
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        int i = 0;//next element to be checked
        s.push(preorder[i++]);
        while(i < preorder.length){
            if (preorder[i] < s.peek()){
                // go left subtree, use root as right boundary
                max = s.peek();
            }
            else {// go right subtree, find the appropriate parent, and set left boundary
                while(!s.isEmpty() && preorder[i] > s.peek()) {
                    min = s.pop();
                    max = s.isEmpty() ? Long.MAX_VALUE : s.peek();
                }
            }
            if (preorder[i] > max || preorder[i] < min) return false;
            s.push(preorder[i]);
            i++;
        }
        return true;
    }
    
    // 1.right boundary is always closest ancestor which has a left subtree that the current element belongs to. Since we keep our stack only store the parents that we traverse their left subtrees(when going into the right subtree of the parent, pop the parent). right boundary is always equal to s.peek().
    // 2.we don't need check right boundary when going left, more clearly, on the left subtree(the if condition already test that, and that's how we choose going left)
    // 3.if we go right, whenever the right boundary is violated, we adjust the position to put the right subtree, it will be always valid.(right boundary don't need to check, and it is > right boundary, it will be definely > left bound)
    // 4.therefore, we don't need to check right boundary at all.
    // 5.we can use the original array to act as a stack
    public boolean verifyPreorder(int[] preorder) {
        long min = Long.MIN_VALUE;
        int s = -1;//stack top
        for (int n : preorder){
            if (n < min) return false;// we can check before or after we do a while loop. after is straightforward. proof for before : if go left, should check directly, if go right, it must pass the checking condition.(since it > max)
            while (s != -1 && n > preorder[s]){
                //pop
                min = preorder[s--];
            }
            preorder[++s] = n; 
        }
        return true;
    }
}
