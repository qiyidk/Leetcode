package trie;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * MaximumXORofTwoNumbersinanArray_421
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ25ÈÕ
 */
public class MaximumXORofTwoNumbersinanArray_421 {
    // to get the biggest number, the result should have more leading 1s in more significant digit if its binary representation
    // only 1 xor 0 can generate a 1
    // 101000111
    // 100100011
    // 001111011
    // 011000111
    // for each digit from most significant to least significant digit
    // we try to get a 1 and 0 from two numbers
    // we try to fulfill the need of higher digit first. Higher digit has a higher priority
    // we can use a trie to store all the numbers, therefore search the matching result for a specific num will take O(32) time, in total it will take O(32n) = O(n) time
    // to make it even faster, we can calculate xor during putting number in the trie. each round we put the element into one subtree and also check whether the other subtree is null, if not null, the corresponding digit can get a 1
    public int findMaximumXOR2(int[] nums) {
        Trie trie = new Trie();
        for (int num : nums) trie.put(num);
        int v = 0;
        for (int num : nums){
            int optimal = trie.search(num);
            int xor = num ^ optimal;
            if (xor > v) v = xor;
        }
        return v;
    }
    private class Trie{
        private class Node{
            private Node[] next = new Node[2];
            private int val;
        }
        private Node root;
        private void put(int v){
            root = put(root, v, 30); //starts at the 31th digit(index = 30)
        }
        private Node put(Node root, int v, int p){
            if (root == null) root = new Node();
            if (p == -1) {
                root.val = v;
                return root;
            }
            int d = v << (31 - p) >>> 31;
            root.next[d] = put(root.next[d], v, p - 1);
            return root;
        }
        private int search(int v){
            return search(root, v, 30);
        }
        private int search(Node root, int v, int p){
            if (p == -1) return root.val;
            int d = v << (31 - p) >>> 31;
            int optimal = d ^ 1;
            if (root.next[optimal] != null) return search(root.next[optimal], v, p - 1);
            else return search(root.next[d], v, p - 1);
        }
    }
    
    //if we think vertically, say, we consider the final result bit by bit
    //if we define res[i] is the biggest prefix of result that ends at i bit.
    //for a certain bit i, if num ^ any number in the array = res(i - 1) + "1", it must the biggest res(i)
    //if no number can get res(i - 1) + "1", the res(i) = res(i - 1) + "0"
    //we want to check num ^ any number = res(i - 1) + "1" efficiently
    //when we get num, we can compute what any number shoule be and then check it in a hashtable
    //one thing left is how to compute target num
    //let res(i - 1) + "1" = optimal
    //num ^ any number = optimal
    //num ^ num ^ any number = num ^ optimal
    //any number = num ^ optimal, note that number, num, optimal all end with ith bit
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 30; i >= 0; i--){
            Set<Integer> prefix = new HashSet<Integer>();
            for (int num : nums) prefix.add(num >>> i);
            max = max << 1;
            int optimal = max | 1; 
            for (int num : prefix){
                if (prefix.contains(num ^ optimal)){
                    max = optimal;
                    break;
                }
            }
        }
        return max;
    }
}
