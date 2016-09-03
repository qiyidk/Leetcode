package bitManipulate;

/**
 * <p>
 * FindtheDifference_389
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class FindtheDifference_389 {
    public char findTheDifference(String s, String t) {
        int r = 0; // 0 ^ 0 = 0; 0 ^ 1 = 1; 0 ^ A = A
        for (int i = 0; i < s.length(); i++) r = r ^ s.charAt(i);
        for (int i = 0; i < t.length(); i++) r = r ^ t.charAt(i);
        return (char) r;
    }
}
