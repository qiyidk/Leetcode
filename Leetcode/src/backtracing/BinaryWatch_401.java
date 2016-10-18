package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * BinaryWatch_401
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ4ÈÕ
 */
public class BinaryWatch_401 {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<String>();
        if (num > 11) return res;
        int[] radix = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        collect(num, 0, 0, 0, radix, res);
        return res;
    }
    
    private void collect(int num, int p, int h, int m, int[] radix, List<String> res){
        if (num == 0){
            String s = gen(h, m);
            if (s != null) res.add(s);
            return;
        }
        // do not put 1 at pos p
        if (10 - p > num) collect(num, p + 1, h, m, radix, res);
        // pick 1 at pos p
        if (p > 3) m += radix[p];
        else h += radix[p];
        num--;
        collect(num, p + 1, h, m, radix, res);
    }
    private String gen(int h, int m){
        if (h > 11 || m > 59) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(h).append(":");
        if (m < 10) sb.append(0);
        sb.append(m);
        return sb.toString();
    }
    
    public static void main(String args[]){
        System.out.println(new BinaryWatch_401().readBinaryWatch(1));
    }
}
