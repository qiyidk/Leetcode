package string;

/**
 * <p>
 * DecodeString_394
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ6ÈÕ
 */
public class DecodeString_394 {
    // when meeting a [, end of number, get number k, start a new decoding = decoding result * number 
    // when meeting a ], end a decoding, return.
    public String decodeString(String s) {
        char[] c = (s + "]").toCharArray();
        StringBuilder sb = new StringBuilder();
        decode(c, 0, sb);
        return sb.toString();
    }
    // return next index to be visited
    private int decode(char[] c, int p, StringBuilder sb){
        int k = 0;
        while(p < c.length){
            if (c[p] == ']') return p + 1;
            if (c[p] >= '0' && c[p] <= '9') {
                k = k * 10 + c[p] - '0';
                p++;
            }
            else if (c[p] == '['){
                // end of number and start a new decoding
                StringBuilder s1 = new StringBuilder();
                p = decode(c, p + 1, s1);
                for (int i = 0; i < k; i++) sb.append(s1);
                k = 0;
            }
            else{
                sb.append(c[p]);
                p++;
            }
        }
        return p;
    }
}
