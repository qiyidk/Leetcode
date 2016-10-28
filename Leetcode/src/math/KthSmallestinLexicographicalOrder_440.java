package math;

/**
 * <p>
 * KthSmallestinLexicographicalOrder_440
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ25ÈÕ
 */
public class KthSmallestinLexicographicalOrder_440 {
    
    public int findKthNumber(int n, int k) {
        int l = 0;
        int v = n;
        while (v > 0){
            v = v / 10;
            l++;
        }
        int prefix = 1;
        int d = 1; //digits of prefix
        while(k != 0){
            if (k == 1) return prefix; // note that the prefix itself is kind of special
            int total = getNumber(l, d, prefix, n);
            if (k > total) {
                k -= total; // all number of this prefix can be passed
                prefix++;
            }
            else{
                // k is within current prefix, change next digit of current prefix to form a sub-prefix
                k--; //pass prefix itself first
                prefix = prefix * 10;
                d++;
            }
        }
        return prefix;
    }
    // prefix 129
    // n      12345
    private int getNumber(int l, int d, int prefix, int n){
        int radix = (int)Math.pow(10, l - d);
        int total = 0;
        if (n / radix <= prefix){
            // calculate digit by digit
            int num = prefix;
            for (int s = d; s <= l; s++){ //digit of current number
                if (s < l) total += (int)(Math.pow(10, s - d));
                else if (n >= num) total += n - num + 1;
                num = num * 10;
            }
        }
        else {
            total = 1;
            //total = 10^m + 10^m - 1 +... + 10^0
            for (int i = 0; i < l - d; i++){
                total = total * 10;
                total++;
            }
        }
        return total;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 4289384;
        int k = 1922239;
        System.out.println(new KthSmallestinLexicographicalOrder_440().findKthNumber(n, k));
    }

}
