package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * LargestNumber_179
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ8ÈÕ
 */
public class LargestNumber_179 {
    public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) str[i] = String.valueOf(nums[i]);
        Arrays.sort(str, new Comparator<String>(){
            public int compare(String x, String y){
                // x > y return -1
                // [121,12] at this time 12 > 121 since 12|121 > 121|12
                String str1 = x + y;
                String str2 = y + x;
                return str2.compareTo(str1);//reverse order
            }
        });
        StringBuilder res = new StringBuilder();
        if (str.length == 0) return "";
        else if (str[0].equals("0")) return "0";
        for (int i = 0; i < str.length; i++) res.append(str[i]);
        return res.toString();
    }
}
