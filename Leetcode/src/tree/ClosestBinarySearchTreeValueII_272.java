package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import baseDataStructure.TreeNode;

/**
 * <p>
 * ClosestBinarySearchTreeValueII_272
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ6ÈÕ
 */
public class ClosestBinarySearchTreeValueII_272 {
    // if we are in a sorted array, after we finding the closest value, we can just use a two-pointers method to get the remaining k - 1 elements
    // only difference with a sorted array is that we need find a way to get the previous and next value of k if we consider the bst as a sorted array.
    // we can do both actions in O(logn)time, the total time complixity will be O(k * logn) = O(klogn).
    /*
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if (k == 0) return res;
        int closest = getClosest(null, root, target);
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        TreeNode node = setStack(s1, s2, root, closest);
        res.add(node.val);
        k--;
        TreeNode t1 = getNext(node, s1); // right pointer
        TreeNode t2 = getPrev(node, s2); // left pointer
        while(k > 0 && (t1 != null || t2 != null)){
            if (t2 == null || (t1 != null && Math.abs(t1.val - target) <= Math.abs(t2.val - target))) {
                res.add(t1.val);
                t1 = getNext(t1, s1);
            }
            else {
                res.add(t2.val);
                t2 = getPrev(t2, s2);
            }
            k--;
        }
        return res;
    }
    private int getClosest(Integer closest, TreeNode root, double target){
        if (root.val == target) return root.val;
        if (closest == null || Math.abs(root.val - target) < Math.abs(closest - target)) closest = root.val;
        if (root.val > target && root.left != null) return getClosest(closest, root.left, target);
        else if (root.val < target && root.right != null) return getClosest(closest, root.right, target);
        else return closest;
    }
    private TreeNode setStack(Stack<TreeNode> s1, Stack<TreeNode> s2, TreeNode root, double target){
        if (root.val == target) return root;
        s1.add(root);
        s2.add(root);
        if (root.val > target) return setStack(s1, s2, root.left, target);
        else return setStack(s1, s2, root.right, target);
    }
    // left - root - right
    private TreeNode getNext(TreeNode root, Stack<TreeNode> s){
        if (root.right != null){// go down
            s.push(root); // cache parent before going down
            root = root.right;
            while(root.left != null) {
                s.push(root);
                root = root.left;
            }
            return root;
        }
        else{// go back
            TreeNode lastRoot = null;
            while(!s.isEmpty() && root.right == lastRoot){ // go back from right subtree
                lastRoot = root;
                root = s.pop();
            }
            if (root.left == lastRoot) return root;// go back from left subtree
            else return null;// end of the tree
        }
    }
    // right - root - left
    private TreeNode getPrev(TreeNode root, Stack<TreeNode> s){
        if (root.left != null){// go down
            s.push(root); // cache parent before going down
            root = root.left;
            while(root.right != null) {
                s.push(root);
                root = root.right;
            }
            return root;
        }
        else{// go back
            TreeNode lastRoot = null;
            while(!s.isEmpty() && root.left == lastRoot){ // go back from left subtree
                lastRoot = root;
                root = s.pop();
            }
            if (root.right == lastRoot) return root;// go back from right subtree
            else return null;// end of the tree
        }
    }
    */
    // make it simpler and faster
    // for s1, we only need to store the treenode whose value is greater than target
    // for s2, we only need to store the treenode whose value is smaller than target
    // also, we can setStack during the first traversal
    // we don't need to find the closest one; instead we can find the smallest one that is greater than target and the biggest one that is smaller than target.
    // for getNext and getPrev, we don't need to check whether it goes back from left or right subtree; instead,when visiting the root, we pop the element. when going back from right, the root is already not on stack, we will find next valid parent directly
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if (k == 0) return res;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        boolean hasEqual = getClosest(root, target, s1, s2);
        TreeNode t1 = getNext(s1);
        TreeNode t2 = getPrev(s2);
        if (hasEqual){
            res.add((int)target);
            k--;
            t1 = getNext(s1);
            t2 = getPrev(s2);
        }
        while(k > 0){
            if (t2 == null || (t1 != null && Math.abs(t1.val - target) <= Math.abs(t2.val - target))) {
                res.add(t1.val);
                t1 = getNext(s1);
            }
            else {
                res.add(t2.val);
                t2 = getPrev(s2);
            }
            k--;
        }
        return res;
    }
    private boolean getClosest(TreeNode root, double target, Stack<TreeNode> s1, Stack<TreeNode> s2){
        if (root == null) return false;
        if (root.val == target){
            s1.add(root);
            s2.add(root);
            return true;
        }
        else if (root.val > target) {
            s1.add(root);
            return getClosest(root.left, target, s1, s2);
        }
        else{
            s2.add(root);
            return getClosest(root.right, target, s1, s2);
        }   
    }
    // left - root - right
    // when entering into a new tree store the root, when visiting the root, pop root.
    private TreeNode getNext(Stack<TreeNode> s){
        if (s.isEmpty()) return null;
        TreeNode next = s.pop(); // visit root
        TreeNode p = next;
        if (p.right == null) return next; // if dont't have right subtree, the stack top will be the next element to be visited
        p = p.right;
        s.push(p);
        while(p.left != null){// go left
            p = p.left;
            s.push(p);
        }
        return next;
    }
    // right - root - left
    private TreeNode getPrev(Stack<TreeNode> s){
        if (s.isEmpty()) return null;
        TreeNode next = s.pop(); // visit root
        TreeNode p = next;
        if (p.left == null) return next;   
        p = p.left;
        s.push(p);
        while(p.right != null){// go right
            p = p.right;
            s.push(p);
        }
        return next;
    }
    // if k is large, we can use this O(n)solution
    // after inorder traversal, we can either get the closest one and check adjacent elements one by one or eliminate elements from left boundary and right boundary like the implementation below
    // a short proof: since the final result must be a consecutive sub-suquence, and when the number of current available elements is larger than k, if we pick current left boundary, we cannot pick current right boundary, in other words, they are mutual exclusive. Therefore if left is better than right, left is kept and right must be eliminated. Therefore we can use this two-pointers way.
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> v = new LinkedList<Integer>();
        inOrder(root, v);
        while(v.size() > k){
            if (Math.abs(v.peekFirst() - target) <= Math.abs(v.peekLast() - target)) v.removeLast();
            else v.removeFirst();
        }
        return v; 
    }
    private void inOrder(TreeNode root, List<Integer> v){
        if (root == null) return;
        inOrder(root.left, v);
        v.add(root.val);
        inOrder(root.right, v);
    }
}
