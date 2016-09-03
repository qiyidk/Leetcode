package backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * RestoreIPAddresses_93
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ31ÈÕ
 */
public class RestoreIPAddresses_93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        char[] c = s.toCharArray();
        int[] ip = new int[4];// value of each ip address
        collect(res, c, 0, ip, 0);
        return res;
    }
    private void collect(List<String> res, char[] c, int p1, int[] ip, int p2){
        if (c.length - p1 >  3 * (4 - p2) || c.length - p1 < 4 - p2) return;// cannot form a valid ip
        if (p2 == 4){
            if (p1 == c.length){
                // get a new valid ip
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i <= 2; i++) sb.append(ip[i]).append(".");
                sb.append(ip[3]);
                res.add(sb.toString());
            }
            return;
        }
        if (c[p1] == '0'){// only have one way to cut
            ip[p2] = 0;
            collect(res, c, p1 + 1, ip, p2 + 1);
        }
        else{
            for(int end = p1; end <= p1 + 2 && end < c.length; end++){
                int v = 0;
                for(int i = p1; i <= end; i++) v = v * 10 + c[i] - '0';
                if (v <= 255) {
                    ip[p2] = v;
                    collect(res, c, end + 1, ip, p2 + 1);
                }
            }
        }
    }
}
