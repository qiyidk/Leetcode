package string;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * FindAllAnagramsinaString_438
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ25ÈÕ
 */
public class FindAllAnagramsinaString_438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if (s.length() < p.length()) return res;
        int[] countP = new int[26];
        int[] count = new int[26];
        int diff = 0;
        for (char c : p.toCharArray()) countP[c - 'a']++;
        for (int i = 0; i < p.length(); i++) count[s.charAt(i) - 'a']++; // initiate window
        for (int i = 0; i < 26; i++) if (countP[i] != count[i]) diff++; // initiate diff
        if (diff == 0) res.add(0);
        for (int i = 1; i <= s.length()- p.length(); i++){
            // each round get rid of i - 1, and add i + p.length() - 1
            int remove = s.charAt(i - 1) - 'a';
            int add = s.charAt(i + p.length() - 1) - 'a';
            count[remove]--;
            if (countP[remove] == count[remove]) diff--;
            else if (countP[remove] == count[remove] + 1) diff++;
            count[add]++;
            if (countP[add] == count[add]) diff--;
            else if (countP[add] == count[add] - 1) diff++;
            if (diff == 0) res.add(i);
        }
        return res;
    }
}
