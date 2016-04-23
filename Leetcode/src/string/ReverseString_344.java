package string;

/**
 * <p>
 * ReverseString_344
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ22ÈÕ
 */
public class ReverseString_344 {
    public String reverseString(String s) {
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length / 2; i++){
            swap(c, i, c.length - 1 - i);
        }
        return new String(c);
    }
    private void swap(char[] c, int i, int j){
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}
