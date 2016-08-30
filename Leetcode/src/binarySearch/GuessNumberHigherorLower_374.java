package binarySearch;

/**
 * <p>
 * GuessNumberHigherorLower_374
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ11ÈÕ
 */
public class GuessNumberHigherorLower_374 {
    public int guessNumber(int n) {
        long l = 1;
        long r = n;
        while(l <= r){
            int m = (int)((l + r) / 2);
            int res = guess(m);// if the target number is lower, return -1
            if (res == 0) return m;
            else if (res == 1) l = m + 1;
            else r = m - 1;
        }
        return (int)((l + r) / 2);
    }
    /* The guess API is defined in the parent class GuessGame.
    @param num, your guess
    @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
       int guess(int num); */
    private int guess(int m){
        return 0;
    }
}
