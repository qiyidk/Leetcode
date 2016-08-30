package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * PalindromePartitioning_131
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ21ÈÕ
 */
public class PalindromePartitioning_131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> t = new ArrayList<String>();
        partition(s.toCharArray(), 0, t, res);
        return res;
    }
    private void partition(char[] c, int start, List<String> t, List<List<String>> res){
        for (int end = start; end < c.length; end++){
            if (isP(c, start, end)){
                String str = new String(c, start, end - start + 1);
                t.add(str);
                if (end == c.length - 1){
                    List<String> tmp = new ArrayList<String>();
                    for (String s : t) tmp.add(s);
                    res.add(tmp);
                }
                else partition(c, end + 1, t, res);
                t.remove(t.size() - 1);
            }
        }
    }
    private boolean isP(char[] c, int start, int end){
        while (start < end && c[start] == c[end]){
            start++;
            end--;
        }
        return start >= end;
    }
}
