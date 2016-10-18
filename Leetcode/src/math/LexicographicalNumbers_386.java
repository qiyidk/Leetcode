package math;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * LexicographicalNumbers_386
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ7ÈÕ
 */
public class LexicographicalNumbers_386 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(1);
        int cur = 1;
        for(int i = 1; i < n; i++) {
            int next = getNext(cur, n);
            res.add(next);
            cur = next;
        }
        return res;
    }
    private int getNext(int cur, int n){
        // n:2299 
        // 1299
        // 2299
        if (cur * 10 <= n) return cur * 10;// go forward one digit
        if (cur % 10 == 9 || cur + 1 > n) {// go back
            cur = cur / 10;
            while(cur % 10 == 9) cur = cur / 10;
            return cur + 1;
        }
        return cur + 1;
    }
}
