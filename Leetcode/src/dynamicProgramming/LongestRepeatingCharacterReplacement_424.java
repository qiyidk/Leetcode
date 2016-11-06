package dynamicProgramming;

/**
 * <p>
 * LongestRepeatingCharacterReplacement_424
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ4ÈÕ
 */
public class LongestRepeatingCharacterReplacement_424 {
    // "AAABBAAAABNMAAAAATTAA"
    // we can use each character as a start character, try every possible solutions
    // it will cost(n * n) time
    // actually, when we make another try, we won't need reset the end point, we can maintain a window just simply check whether it can be extended to end + 1, if not, we don't need keep going, it must be shorter than before.
    // it can be done in O(n) time
    public int characterReplacement(String s, int k) {
        if (s.length() == 0) return 0;
        char[] c = s.toCharArray();
        int max = 1;
        int[] count = new int[26];// count of current window
        for (int i = 0; i < c.length; i++){
            // start with i
            int end = i + max - 1; // end of current window
            if (i != 0) count[c[i - 1] - 'A']--;
            if (end < c.length) count[c[end] - 'A']++;
            int left = k - (max - count[c[i] - 'A']); // number of replacements left
            if (left < 0) continue;
            end++;// try to extend
            for (; end < c.length; end++){
                if (c[end] == c[i]){
                    max++;
                    count[c[end] - 'A']++;
                }
                else{
                    if (left > 0){
                        max++;
                        left--;
                        count[c[end] - 'A']++;
                    }
                    else break;
                }
            }
            max = max + left > c.length ? c.length : max + left; // note that it won't miss anything, if go backward "left" step and connect another subsequence, this situation must already been counted before
        }
        return max;
    }
}
