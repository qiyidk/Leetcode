package bitManipulate;

/**
 * <p>
 * MaximumProductofWordLengths_318
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class MaximumProductofWordLengths_318 {
    // saved
    // & bitwise and   << left shift 
    // The only difficulty is to how to check two words efficiently
    // first we need find a way to represent the composition of each word.
    // if we consider each word have 26 bit, each bit represent the word has the corresponding character or not.
    // no common character means if we do an and operation of each bit, none of them will return 1, so that the final result is 0, so we find a way to check the two words; otherwise, at least one bit is 1, which means the two words has the same character corresponding to that bit.
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] com = new int[n];
        for (int i = 0; i < n; i++){
            String word = words[i];
            int c = 0;
            for (int j = 0; j < word.length(); j++){
                c = c | (1 << (word.charAt(j) - 'a'));
            }
            com[i] = c;
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            int curL = words[i].length();
            for (int j = i + 1; j < n; j++){
                if ((com[i] & com[j]) == 0 && curL * words[j].length() > max) max = curL * words[j].length();
            }
        }
        return max;
    }
}
