package string;

/**
 * <p>
 * ReverseVowelsofaString_345
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ25ÈÕ
 */
public class ReverseVowelsofaString_345 {
    //saved
    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] c = s.toCharArray();
        while(i < j){
            while(i < j && !isVowel(c[i])) i++;
            while(i < j && !isVowel(c[j])) j--;
            if (i < j) {
                swap(c, i, j);
                i++;
                j--;
            }
        }
        return new String(c);
    }
    private void swap(char[] c, int i, int j){
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
    private boolean isVowel(char c){
        return c == 'a' || c == 'A' 
        || c == 'e' || c == 'E' 
        || c == 'i' || c == 'I' 
        || c == 'o' || c == 'O' 
        || c == 'u' || c == 'U';
    }
}
