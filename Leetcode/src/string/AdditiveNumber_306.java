package string;

/**
 * <p>
 * AdditiveNumber_306
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ3ÈÕ
 */
public class AdditiveNumber_306 {
    public boolean isAdditiveNumber(String num) {
        // try to find first valid two numbers and try the subsequent numbers
        char[] c = num.toCharArray();
        for (int e1 = 0; e1 < c.length; e1++){// end of num1
            for (int e2 = e1 + 1; e2 < c.length; e2++){// end of num2
                if (check(0, e1 + 1, e1, e2, c)) return true;
                if (c[e1 + 1] == '0') break; //only have one way to split
            }
            if (c[0] == '0') break; //only have one way to split
        }
        return false;
    }
    private boolean check(int s1, int s2, int e1, int e2, char[] num){
        char[] sum = getSum(s1, s2, e1, e2, num);
        int s3 = e2 + 1;
        int e3 = sum.length - 1 + s3;
        if (e3 > num.length - 1) return false;
        for (int i = 0, j = s3; i < sum.length; i++, j++){
            if (sum[i] != num[j]) return false;
        }
        if (e3 != num.length - 1) return check(s2, s3, e2, e3, num);
        else return true;
    }
    private char[] getSum(int s1, int s2, int e1, int e2, char[] num){
        int l = Math.max(e1 - s1 + 1, e2 - s2 + 1) + 1;
        char[] res = new char[l];
        int carryBit = 0;
        int p = l - 1;
        while(e1 >= s1 || e2 >= s2){
            int v1 = e1 < s1 ? 0 : num[e1--] - '0';
            int v2 = e2 < s2 ? 0 : num[e2--] - '0';
            int v = v1 + v2 + carryBit;
            res[p--] = (char)(v % 10 + '0');
            carryBit = v / 10;
        }
        if (carryBit == 1) {
            res[p] = '1';
            return res;
        }
        else {
            char[] r = new char[l - 1];
            System.arraycopy(res, 1, r, 0, r.length);
            return r;
        }
    }
    public static void main(String[] args){
        String num = "19910011992";
        System.out.println(new AdditiveNumber_306().isAdditiveNumber(num));
    }
}
