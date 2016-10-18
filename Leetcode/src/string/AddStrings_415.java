package string;

/**
 * <p>
 * AddStrings_415
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ9ÈÕ
 */
public class AddStrings_415 {
    public String addStrings(String num1, String num2) {
        if (num2.length() > num1.length()) return addStrings(num2, num1);
        char[] r = new char[num1.length() + 1];
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >=0; i--, j--){
            int d1 = num1.charAt(i) - '0';
            int d2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int s = d1 + d2 + carry;
            r[i + 1] = (char)((s % 10) + '0');
            carry = s / 10;
        }
        if (carry == 1) {
            r[0] = '1';
            return new String(r);
        }
        else return new String(r, 1, r.length - 1);
    }
}
