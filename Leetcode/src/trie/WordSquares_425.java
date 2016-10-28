package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * WordSquares_425
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ17ÈÕ
 */
public class WordSquares_425 {
    // since the length of words are the same
    // if they can form a word square, the number of words must be the same with the length
    // the naive solution will be attempting all possible combinations. it will take O(n^l * l^2) time
    // it can be seen that if we select the first word abcd, the second words must starts with b, the third word must starts with c and followed by the thrid character of the second word.
    // i. select a
    // II. select b starts with 2th of a
    // III. select c starts with 3th of a, 3th of b
    // IV. select d starts with 4th of a,b,c
    // V. select e starts with 5th of a,b,c,d
    // to select valid words efficiently, we can use a trie
    // to make it more faster, we can store the list of words that starts with some pattern instead of word in the trie, but it will use more memory
    public List<List<String>> wordSquares(String[] words) {
        int l = words[0].length();
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> tmp = new ArrayList<String>();
        Trie trie = new Trie();
        for (String word : words) trie.insert(word);
        collect(tmp, l, res, trie);
        return res;
    }
    private void collect(List<String> tmp, int max, List<List<String>> res, Trie trie){
        int p = tmp.size();
        if (p == max){
            List<String> r = new ArrayList<String>(tmp);
            res.add(r);
            return;
        }
        char[] pattern = new char[p];
        for (int i = 0; i < p; i++) pattern[i] = tmp.get(i).charAt(p);
        List<String> nodes = trie.search(pattern);
        if (nodes == null) return;
        for (String n : nodes){
            tmp.add(n);
            collect(tmp, max, res, trie);
            tmp.remove(tmp.size() - 1);
        }
    }
    private class Trie{
        private class Node{
            private String val = null;
            private Node[] next = new Node[26];
            public Node(String val){
                this.val = val;
            }
        }
        Node root = null;
        private void insert(String str){
            root = insert(str, 0, root);
        }
        private Node insert(String str, int p, Node root){
            if (root == null) root = new Node(null);
            if (p == str.length()) root.val = str;
            else root.next[str.charAt(p) - 'a'] = insert(str, p + 1, root.next[str.charAt(p) - 'a']);
            return root;
        }
        private List<String> search(char[] pattern){
            return search(pattern, 0, root);
        }
        private List<String> search(char[] pattern, int p, Node root){
            if (root == null) return null;
            if (p == pattern.length) {
                List<String> res = new ArrayList<String>();
                collect(root, res);
                return res;
            }
            else return search(pattern, p + 1, root.next[pattern[p] - 'a']);
        }
        private void collect(Node root, List<String> res){
            if (root.val != null) res.add(root.val);
            for (int i = 0; i < 26; i++){
                if (root.next[i] != null) collect(root.next[i], res);
            }
        }
    }
    public static void main(String[] args){
        String[] words = {"area","lead","wall","lady","ball"};
        new WordSquares_425().wordSquares(words);
    }
}
