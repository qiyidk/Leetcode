package math;

/**
 * <p>
 * NthDigit_400
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ3ÈÕ
 */
public class NthDigit_400 {
    // numbers have 
    // 1 digit : 9
    // 2 : 2 * 90
    // 3 : 3 * 900
    // 4 : 4 * 9000
    
    public int findNthDigit(int n) {
        int digit = 1;
        long first = 1; //first number of current digit
        long num = first * 9 * digit;
        while (n > num){
            // move to next group
            n -= num;
            first = first * 10;
            digit++;
            num = first * 9 * digit;
        }
        int rank = (n - 1) / digit; // make 1 offset so that when n is divisible by digit, it will still belong to current rank
        long target = first + rank;// target number that holds the value
        return String.valueOf(target).charAt((n - 1) % digit) - '0';
    }
    
    public static void main(String[] args){
        System.out.println(new NthDigit_400().findNthDigit(11));
    }
}
