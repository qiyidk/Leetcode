package hashTable;

import java.util.HashMap;

/**
 * <p>
 * RansomNote_383
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ12ÈÕ
 */
public class RansomNote_383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int c = 0;
        for (int i = 0; i < ransomNote.length(); i++){
            Integer v = count.get(ransomNote.charAt(i));
            if (v == null){
                c++;
                count.put(ransomNote.charAt(i), 1);
            }
            else count.put(ransomNote.charAt(i), v + 1);
        }
        if (c == 0) return true;
        for (int i = 0; i < magazine.length(); i++){
            char ch = magazine.charAt(i);
            Integer v = count.get(ch);
            if (v != null && v > 0){
                if (v == 1) c--;
                count.put(ch, v - 1);
                if (c == 0) return true;
            }
        }
        return false;
    }
}
