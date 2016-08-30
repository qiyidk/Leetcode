package twoPointers;

import java.util.HashMap;

/**
 * <p>
 * MinimumWindowSubstring_76
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ9ÈÕ
 */
public class MinimumWindowSubstring_76 {
    // array solution
    public String minWindow(String s, String t) {
        char[] c = s.toCharArray();
        int count = 0;
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) need[t.charAt(i)]++;
        for (int i = 0; i < 128; i++) if (need[i] == 0) count++;
        int min = Integer.MAX_VALUE;
        int start = 0; // start index of the min window
        int end = 0; // end index of the min window
        int p = 0; // start pointer
        for (int i = 0; i < c.length; i++){
            need[c[i]]--;
            if (need[c[i]] == 0) count++;
            while(p <= i && need[c[p]] < 0) {
                need[c[p]]++;
                p++; // eliminate unnecessary character
            }   
            if (count == 128 && i - p + 1 < min){
                min = i - p + 1;
                start = p;
                end = i;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }
    public String minWindow2(String s, String t) {
        char[] c = s.toCharArray();
        int count = 0;
        HashMap<Character, Integer> need = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            Integer v = need.get(t.charAt(i));
            need.put(t.charAt(i), v == null ? 1 : v + 1);
        }
        int min = Integer.MAX_VALUE;
        int start = 0; // start index of the min window
        int end = 0; // end index of the min window
        int p = 0; // start pointer
        for (int i = 0; i < c.length; i++){
            Integer v = need.get(c[i]);
            if (v == null) continue;
            need.put(c[i], v - 1);
            if (v == 1) count++;
            while(p <= i && ((v = need.get(c[p])) == null 
                              || v < 0)) {
                if (v != null) need.put(c[p], v + 1);
                p++; // eliminate unnecessary character
            }   
            if (count == need.size() && i - p + 1 < min){
                min = i - p + 1;
                start = p;
                end = i;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }
}
