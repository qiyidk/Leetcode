package string;

/**
 * <p>
 * ShortestPalindrome_214
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ8ÈÕ
 */
public class ShortestPalindrome_214 {
    // let s = str + remaining
    // r = reverseOrder of remaining + str + remaining 
    // if and only if str is a Palindrome, r is a Palindrome
    // if the length of str is longer, the final result will be shorter
    // so what we need to do is to find the longest Palindrome that starts with the first character of the original string s

    // we construct a str t = s + separator + reverse order of s. 
    // we need to find the longest overlapping between prefix and suffix, which is the longest Palindrome (according to the definition, a Palindrome is the number whose original order and the reverse order are the same)
    // we add a separator so that the overlapping cannot be longer than s

    // we can do it in O(n) time by using a prefix array which is usually used in KMP algorithm
    // for prefix array, we need to calculate from p[i] to p[i + 1]
    //        i  for suffix array
    //abcabcabcd 
    //
    //        j  for prefix array
    //   abcabcabcd
    //        j
    //      abcabcabcd
    // if the element of i + 1 is the same, p[i + 1] = p[i] + 1;
    // if not the same, we need to move forward the prefix array(decrement j).
    // to be more efficient, we can move backward j to prefix[j - 1], not j - 1.
    // since for phase i, the last p[i] elements of suffix array must be equal to the first p[i] elements of prefix array(that's why we get p[i]), therefore, we can move backwards j to p[j] to reach the next legal position.

     public String shortestPalindrome(String s) {
         String t = s + " " + new StringBuilder(s).reverse().toString();
         int[] prefix = new int[t.length()];
         for (int i = 1; i < t.length(); i++){
             int j = prefix[i - 1];
             while(j > 0 && t.charAt(i) != t.charAt(j)){
                 j = prefix[j - 1];
             }
             if (t.charAt(i) == t.charAt(j)) j++;
             prefix[i] = j; 
         }
         return new StringBuilder(s.substring(prefix[t.length() - 1])).reverse().append(s).toString();
     }
}
