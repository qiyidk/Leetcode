package math;

/**
 * <p>
 * ReconstructOriginalDigitsfromEnglish_423
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ4ÈÕ
 */
public class ReconstructOriginalDigitsfromEnglish_423 {
    //                 order, uniquely determined by character
    // zero            1 z 
    // one             9 o
    // two             5 w
    // three           6 t
    // four            8 f
    // five            7 v
    // six             3 x
    // seven           4 s
    // eight           2 g
    // nine            10 i
    public String originalDigits(String s) {
        int[] count = new int[26];
        int[] digits = new int[10]; // number of eahc digit
        for (char c : s.toCharArray()) count[c - 'a']++;
        digits[0] = count['z' - 'a'];
        count['e' - 'a'] -= count['z' - 'a'];
        count['r' - 'a'] -= count['z' - 'a'];
        count['o' - 'a'] -= count['z' - 'a'];
        digits[8] = count['g' - 'a'];
        count['e' - 'a'] -= count['g' - 'a'];
        count['i' - 'a'] -= count['g' - 'a'];
        count['h' - 'a'] -= count['g' - 'a'];
        count['t' - 'a'] -= count['g' - 'a'];
        digits[6] = count['x' - 'a'];
        count['s' - 'a'] -= count['x' - 'a'];
        count['i' - 'a'] -= count['x' - 'a'];
        digits[7] = count['s' - 'a'];
        count['e' - 'a'] -= count['s' - 'a'];
        count['v' - 'a'] -= count['s' - 'a'];
        count['e' - 'a'] -= count['s' - 'a'];
        count['n' - 'a'] -= count['s' - 'a'];
        digits[2] = count['w' - 'a'];
        count['t' - 'a'] -= count['w' - 'a'];
        count['o' - 'a'] -= count['w' - 'a'];
        digits[3] = count['t' - 'a'];
        count['h' - 'a'] -= count['t' - 'a'];
        count['r' - 'a'] -= count['t' - 'a'];
        count['e' - 'a'] -= count['t' - 'a'];
        count['e' - 'a'] -= count['t' - 'a'];
        digits[5] = count['v' - 'a'];
        count['f' - 'a'] -= count['v' - 'a'];
        count['i' - 'a'] -= count['v' - 'a'];
        count['e' - 'a'] -= count['v' - 'a'];
        digits[4] = count['f' - 'a'];
        count['o' - 'a'] -= count['f' - 'a'];
        count['u' - 'a'] -= count['f' - 'a'];
        count['r' - 'a'] -= count['f' - 'a'];
        digits[1] = count['o' - 'a'];
        count['n' - 'a'] -= count['o' - 'a'];
        count['e' - 'a'] -= count['o' - 'a'];
        digits[9] = count['i' - 'a'];
        char[] res = new char[s.length()];
        int p = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < digits[i]; j++) res[p++] = (char)(i + '0');
        }
        return new String(res, 0, p);
    }
}
