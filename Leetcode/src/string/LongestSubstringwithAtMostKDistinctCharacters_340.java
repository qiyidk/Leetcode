package string;

/**
 * <p>
 * LongestSubstringwithAtMostKDistinctCharacters_340
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ25ÈÕ
 */
public class LongestSubstringwithAtMostKDistinctCharacters_340 {
    //saved
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int count = 0; // the number of distinct charaters in the current substring
        int max = 0;
        int start = 0;
        int end = 0;
        int[] counts = new int[128];//need ask whether can assume all the characters are belong to 0 to 127(stardard ascii);
        //otherwise, use hashmap instead
        //HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char[] c = s.toCharArray();
        for (;end < c.length; end++){
            if (counts[c[end]] == 0){
                // encounter a new character
                counts[c[end]]++;
                count++;
                if (count > k){
                    max = Math.max(max, (end - 1) - start + 1);
                    // remove characters from the front
                    while(count > k){
                        counts[c[start]]--;
                        if (counts[c[start]] == 0) count--;
                        start++;
                    }
                }
            }
            else counts[c[end]]++;
        }
        // deal with the last substring
        max = Math.max(max, (end - 1) - start + 1);
        return max;
    }
}
