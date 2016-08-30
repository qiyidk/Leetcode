package palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * PalindromePairs_336
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ16ÈÕ
 */
public class PalindromePairs_336 {
    //if two words can make up a palindrome, we can denote one as the longer one, the other one is the shorter one. 
    //the shorter one will maintain the same with its original string, the longer one may contain the reverse order of the shorter one plus a smaller palindrome with length of 0 or more.
    //the smaller palindrome may appear in the front or at the end.
    //two possible compositions:
    //A'P + A  |  A + PA'
    //1. we use every word as the longer one, generate all possible prefixes and suffixes.(A'P or PA')
    //2. then we use every word as the shorter one and place it in the front and at the end, if there's a match(has the same string), we find one solution.
    //for one certain position(front or end) one as the shorter one can have at most one composition with another word. There's no redundant.
    //if two words have reverse order, there will be redundant results, and at this case both of the two words are shorter ones. To avoid this, we only add the original string to prefix groups.
    //we can use hashtable or to a trie to implement the solution, it will take nk^2 time where k is the average length of the word
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        HashMap<String, List<Integer>> prefix = new HashMap<String, List<Integer>>();
        HashMap<String, List<Integer>> suffix = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            put(prefix, i, word);
            for (int j = word.length() - 1; j >= 0; j--){
                if (isP(word, j, word.length() - 1)) put(prefix, i, word.substring(0, j));
            }
            for (int j = 0; j < word.length(); j++){
                if (isP(word, 0, j)) put(suffix, i, word.substring(j + 1));
            }
        }
        for (int i = 0; i < words.length; i++){
            String word = new StringBuilder(words[i]).reverse().toString();
            collect(res, true, i, suffix.get(word));
            collect(res, false, i, prefix.get(word));
        }
        return res;
    }
    
    private void collect(List<List<Integer>> res, boolean isFront, int i, List<Integer> list){
        if (list == null) return;
        if (isFront){
            for (int j : list){
                if (i == j) continue;
                List<Integer> r = new ArrayList<Integer>();
                res.add(r);
                r.add(i);
                r.add(j);
            }
        }
        else{
            for (int j : list){
                if (i == j) continue;
                List<Integer> r = new ArrayList<Integer>();
                res.add(r);
                r.add(j);
                r.add(i);
            }
        }
    }
    private void put(HashMap<String, List<Integer>> map, int i, String str){
        List<Integer> list = map.get(str);
        if (list == null) {
            list = new ArrayList<Integer>();
            map.put(str, list);
        }
        list.add(i);
    }
    private boolean isP(String str, int i, int j){
        while(i < j){
            if (str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
