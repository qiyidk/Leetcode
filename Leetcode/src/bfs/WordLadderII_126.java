package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * WordLadderII_126
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ17ÈÕ
 */
public class WordLadderII_126 {
    // basic idea see world ladder1
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (beginWord.equals(endWord)) {
            List<String> r =  new ArrayList<String>();
            r.add(beginWord);
            res.add(r);
            return res;
        }
        if (!wordList.contains(endWord)) return res;
        Set<String> cur= new HashSet<String>();
        Set<String> next = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        Map<String, List<String>> parents = new HashMap<String, List<String>>();// use parents to retrieve the word ladders
        cur.add(endWord);
        visited.add(endWord);
        while (!cur.isEmpty()){
            for(String word : cur){
                if (beginWord.equals(word)){
                    // retrieve word ladders
                    List<String> ladder = new ArrayList<String>(); 
                    retrieve(word, parents, ladder, res, endWord);
                    return res;
                }
                char[] str = word.toCharArray();
                for(int i = 0; i < word.length(); i++){
                    char tmp = str[i];
                    for (char j = 'a'; j <= 'z'; j++){
                        // try all possible words that differ with word by one letter
                        str[i] = j;
                        String word2 = new String(str); 
                        if (wordList.contains(word2)){
                            if (!visited.contains(word2)){
                                next.add(word2);
                                visited.add(word2);
                            }
                            if (!visited.contains(word2) || next.contains(word2)){ 
                                // word2 is just added in current round
                                List<String> parent = parents.get(word2);
                                if (parent == null) {
                                    parent = new ArrayList<String>();
                                    parents.put(word2, parent);
                                }
                                parent.add(word);
                            }                           
                        }
                    }
                    str[i] = tmp;
                }
            }
            cur = next;
            next = new HashSet<String>();
        }
        return res;
    }
    private void retrieve(String word, Map<String, List<String>> parents, List<String> ladder, List<List<String>> res, String endWord){
        if (word.equals(endWord)){
            List<String> r = new ArrayList<String>();
            for(String s : ladder) r.add(s);
            r.add(word);
            res.add(r);
            return;
        }
        ladder.add(word);
        List<String> parent = parents.get(word);
        for (String w : parent){
            retrieve(w, parents, ladder, res, endWord);
        }
        ladder.remove(ladder.size() - 1);
    }
}
