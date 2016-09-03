package array;

/**
 * <p>
 * FirstUniqueCharacterinaString_387
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class FirstUniqueCharacterinaString_387 {
    public int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        int[] count = new int[26];
        for (int i = 0; i < c.length; i++){
            count[c[i] - 'a']++;
        }
        for (int i = 0; i < c.length; i++){
            if (count[c[i] - 'a'] == 1) return i;
        }
        return -1;
    }
}
