package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * RepeatedDNASequences_187
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class RepeatedDNASequences_187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> count = new HashMap<String, Integer>();
        List<String> res = new ArrayList<String>();
        for (int i = 9; i < s.length(); i++){
            String str = s.substring(i - 9, i + 1);
            Integer c = count.get(str);
            if (c == null) count.put(str, 1);
            else{
                if (c == 1) res.add(str);
                count.put(str, c + 1);
            }
        }
        return res;
    }
    /*some optimization
    1. we can use 4bit to represent one letter, so that 10-letter-long sequence can be represented as one integer, it will save time for hashing. Also save memory.
    2. we can calculate cur integer value based on previous integer value, so that it will save some time for calculating integer value
    3. we can use a big int array to represent hashMap, more memory(may huge), but more efficient.
    */
}
