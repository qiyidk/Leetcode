package greedy;

import java.util.Arrays;

/**
 * <p>
 * AssignCookies_455
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ16ÈÕ
 */
public class AssignCookies_455 {
    // 1 2 3 3
    // 1 2 2 4
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int n = 0;
        while(i < g.length && j < s.length){
            if (s[j] >= g[i]){
                n++;
                i++;
                j++;
            }
            else j++;
        }
        return n;
    }
}
