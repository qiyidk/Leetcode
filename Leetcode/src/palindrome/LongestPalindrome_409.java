package palindrome;

/**
 * <p>
 * LongestPalindrome_409
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ4ÈÕ
 */
public class LongestPalindrome_409 {
    // if count is even, all these characters can be used to form Palindrome
    // if odd, count - 1 can be used
    // Palindrome can accept at most one odd count number, for the first odd count number, all the characters can be used
    public int longestPalindrome(String s) {
        char[] count = new char[128];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i)]++;
        int l = 0;
        boolean hasOdd = false;
        for (int i = 64; i < 128; i++){
            if (count[i] % 2 == 0) l += count[i];
            else {
                if (hasOdd) l += count[i] - 1;
                else{
                    hasOdd = true;
                    l += count[i];
                }
            }
        } 
        return l;
    }
}
