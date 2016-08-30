package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * SubstringwithConcatenationofAllWords_30
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ10ÈÕ
 */
public class SubstringwithConcatenationofAllWords_30 {
    //barfoothefoobarman
    //barfoo
    // arfoot
    //  rfooth
    //   foothe
    //when we move from i to i + w, only the first word of f(i) and the last word of f(i + w) are different
    @SuppressWarnings("unchecked")
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        if (words.length == 0 || words[0].length() == 0){
            for (int i = 0; i < s.length(); i++) res.add(i);
            return res;
        }
        int w = words[0].length();
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for (String word : words) {
            Integer v = count.get(word);
            if (v == null) count.put(word, 1);
            else count.put(word, v + 1);
        }
        int upper = s.length() - words.length * w;
        for (int i = 0; i < w && i <= upper; i++){//left bound
            HashMap<String, Integer> count2 = (HashMap<String, Integer>)count.clone();
            int remaining = count.size();// word patterns that need to be covered
            for (int j = i; j <= s.length() - w; j += w){
                // scan j as the start point of the last word of window
                // note that the last start point is s.length() - w, not s.length() - 1 - w
                String word1 = s.substring(j, j + w); // last word
                String word2 = null;// word to be eliminated from the window
                if (j >= i + words.length * w) 
                   word2 = s.substring(j - words.length * w, j - words.length * w + w);           
                if (!word1.equals(word2)){
                    remaining += delta(count2, word1, true); 
                    if (word2 != null) remaining += delta(count2, word2, false); 
                }
                if (remaining == 0) res.add(j - words.length * w + w);
            }
        }
        return res;
    }
    // return the delta of remaining
    private int delta(HashMap<String, Integer> count, String word, boolean newWord){
        Integer v = count.get(word);
        int delta = newWord ? -1: 1;
        if (v != null) {
            count.put(word, v + delta);
            if (v + delta == 0) return -1;
            else if (v == 0) return 1; 
        }
        return 0;
    }
    
    public static void main(String[] args){
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        new SubstringwithConcatenationofAllWords_30().findSubstring(s, words);
    }
}
