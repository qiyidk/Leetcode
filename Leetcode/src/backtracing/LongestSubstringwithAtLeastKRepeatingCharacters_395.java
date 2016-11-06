package backtracing;

import java.util.HashSet;

/**
 * <p>
 * LongestSubstringwithAtLeastKRepeatingCharacters_395
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ4ÈÕ
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters_395 {
    // abbbfffattttceeeecaddde
    // if count of all characters are greater than or equal to k, the whole string can be used
    // if count of some characters are less than k, the result string cannot include these characters
    // note that character of count == 0 cannot be separator
    // these characters can be seen as separate points
    // when we reach separate points, we can know the valid string can only between last separate point to this separate point if it contains any character within this range.
    // within this range, we also know some new separate points, we traverse the array and do the same thing until no separate point was found
    // since we have 26 characters, for each element, it at most traverse 26 times, in total, it is O(n)
    public int longestSubstring(String s, int k) {
        int[] count = new int[26];
        char[] str = s.toCharArray();
        for (char c : str) count[c - 'a']++;
        return longestSubstring(str, k, 0, str.length - 1, count);
    }
    private int longestSubstring(char[] str, int k, int start, int end, int[] count){
        if (end - start + 1 < k) return 0; // no way to form valid string
        HashSet<Character> separator = new HashSet<Character>();
        for (int i = 0; i < 26; i++) if (count[i] != 0 && count[i] < k) separator.add((char)(i + 'a'));
        if (separator.isEmpty()) return end - start + 1;
        int[] count2 = new int[26];
        int max = 0;
        int s = start;
        for (int i = start; i <= end; i++){
            if (separator.contains(str[i])){
                // separate again
                int m = longestSubstring(str, k, s, i - 1, count2);
                if (m > max) max = m;
                s = i + 1;
                count2 = new int[26];
            }
            else count2[str[i] - 'a']++;
        }
        // deal with the last section
        int m = longestSubstring(str, k, s, end, count2);
        if (m > max) max = m;
        return max;
    }
    public static void main(String[] args){
        String s = "aabcabb";
        int k = 3;
        System.out.println(new LongestSubstringwithAtLeastKRepeatingCharacters_395().longestSubstring(s, k));
    }
}
