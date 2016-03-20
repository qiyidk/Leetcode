package trie;

/**
 * <p>
 * ImplementTrie_PrefixTree_208
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
class TrieNode {
    // saved
    // Initialize your data structure here.
    boolean value = false;
    TrieNode[] next = new TrieNode[26];
}

public class ImplementTrie_PrefixTree_208 {
    private TrieNode root;

    public ImplementTrie_PrefixTree_208() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        put(root, word, 0);
    }
    private TrieNode put(TrieNode root, String word, int d){
        if (root == null) root = new TrieNode();
        if (d == word.length()){
            root.value = true;
            return root;
        }
        int index = word.charAt(d) - 'a';
        root.next[index] = put(root.next[index], word, d + 1);
        return root;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return get(root, word, 0, true);
    }
    private boolean get(TrieNode root, String word, int d, boolean completeMatch){
        if (root == null) return false;
        if (d == word.length()) {
            if (completeMatch) return root.value;
            else return true;
        }
        else return get(root.next[word.charAt(d) - 'a'], word, d + 1, completeMatch);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return get(root, prefix, 0, false);
    }
}