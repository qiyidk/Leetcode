package string;

/**
 * @author qiyi
 *
 */
public class NumberofSegmentsinaString_434 {
    public int countSegments(String s) {
        int r = 0;
        int start = 0;
        int i = 0;
        for (; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                if (i - start > 0) r++;
                start = i + 1;
            }
        }
        if (i - start > 0) r++;// don't forget the last chunk
        return r;
    }
}
