package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    // run a bfs, if it has already generated before, pass it.
    // to save memory, use pathTo instead of the whole path
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        HashSet<String> prev = new HashSet<String>();
        HashSet<String> cur = new HashSet<String>();
        HashSet<String> next = new HashSet<String>();
        HashMap<String, List<String>> pathTo = new HashMap<String, List<String>>();
        if (!wordList.contains(beginWord) || !wordList.contains(endWord)) return res;
        if (beginWord.equals(endWord)){
            List<String> t = new ArrayList<String>();
            t.add(beginWord);
            res.add(t);
            return res;
        }
        cur.add(endWord);
        prev.add(endWord); // add to prev
        boolean found = false;
        while(cur.size() != 0){
            for (String s : cur){
                char[] c = s.toCharArray();
                for (int i = 0; i < c.length; i++){
                    char t = c[i];
                    for (char cc = 'a'; cc <= 'z'; cc++){
                        if (cc == t) continue;
                        c[i] = cc;
                        String str = new String(c);
                        if (prev.contains(str) || !wordList.contains(str)) continue;
                        List<String> list = pathTo.get(str);
                        if (list == null) {
                            list = new ArrayList<String>();
                            pathTo.put(str, list);
                        }
                        list.add(s);
                        next.add(str);
                        if (str.equals(beginWord)) found = true;
                    }
                    c[i] = t;
                }
            }
            if (found) break;
            for (String str : next) prev.add(str);// note add next
            cur = next;
            next = new HashSet<String>();
        }
        if (found){ // check found
            List<String> tmp = new ArrayList<String>();
            collect(pathTo, beginWord, res, tmp, endWord);
        }
        return res;
    }
    private void collect(HashMap<String, List<String>> pathTo, String str, List<List<String>> res, List<String> tmp, String target){
        tmp.add(str);
        if (str.equals(target)){
            // reach the start
            List<String> r = new ArrayList<String>(tmp);
            res.add(r);
        }
        else {
            List<String> list = pathTo.get(str);
            for (String s : list) collect(pathTo, s, res, tmp, target);
        }
        tmp.remove(tmp.size() - 1);
    }
    
    public static void main(String[] args){
        String b = "hot";
        String e = "dog";
        HashSet<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dog");
        dict.add("cog");
        dict.add("tot");
        dict.add("hog");
        dict.add("hop");
        dict.add("pot");
        dict.add("dot");
        new WordLadderII_126().findLadders(b, e, dict);
    }
}
