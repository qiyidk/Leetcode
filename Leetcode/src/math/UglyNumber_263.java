package math;

/**
 * <p>
 * UglyNumber_263
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ5ÈÕ
 */
public class UglyNumber_263 {
    // It is my second time to do this problem(saved)
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while(num % 2 == 0) num = num / 2;
        while(num % 3 == 0) num = num / 3;
        while(num % 5 == 0) num = num / 5;
        return num == 1;
    }
}
