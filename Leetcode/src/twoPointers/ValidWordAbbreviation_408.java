package twoPointers;

/**
 * <p>
 * ValidWordAbbreviation_408
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ4ÈÕ
 */
public class ValidWordAbbreviation_408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int p = 0; //pointer for word
        int s = 0; //number appeared in the abbr
        for(int i = 0; i < abbr.length(); i++){
            char c = abbr.charAt(i);
            if (c > '9'){
                p += s;// move forward s steps to pass abbr
                s = 0;
                if (p > word.length() - 1 || abbr.charAt(i) != word.charAt(p)) return false;
                p++; // move forward 1 step to pass current character
            }
            else {
                s = s * 10 + c - '0';
                if (s == 0) return false;// corner case, abbr number cannot start with 0
            }
        }
        return p + s == word.length();
    }
}
