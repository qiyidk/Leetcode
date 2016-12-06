package kmp;

/**
 * <p>
 * RepeatedSubstringPattern_459
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ16ÈÕ
 */
public class RepeatedSubstringPattern_459 {
    // 123123123
    //    123123123
    // if a is divisible by 123, if put forward length of repeated pattern, the suffix of a is a prefix of a'
    // it can been proved that it produces the biggest overlapping
    
    // if suffix of a is a prefix of a'
    // xxxxxxaaaaaa
    //    xxxaaaaaaaaa
    // indicates the second portion of a is equal to the first portion of a'=> the first portion of a = the second portion of a
    // =>the second portion of a' = the third portion of a=>the second portion of a = the third portion of a
    // ...
    // if l(xxxaaaaaa) divisible by xxx, all characters left follow the same pattern
    // therefore a is divisible by xxx
    
    // the prefix array can be done in O(n) time based on dp, which is used in KMP
    // 123123123
    //    123123123
    // 1
    // X
    // 12
    // X
    // 123
    // X
    // 1231
    //    1231, no way to move backward; otherwise, the previous overlapping will be more than 1
    
    // xxx222xxx
    //       xxx222xxx  
    
    // xxx222xxx1
    //       xxx222xxxx1
    // note that xxx of a' = xxx of a, and we have already known the place to move forward for xxx
    // since xxx of a' = xxx of a = prefix of a which we have already computed before 
    public boolean repeatedSubstringPattern(String str) {
        char[] c = str.toCharArray();
        int n = c.length;
        int[] prefix = new int[n];// number of characters overlapping for substring ends with i
        int i = 1; // scan pointer
        int j = 0; // first unmatched index of prefix
        while(i < n && j < n){
            if (c[i] == c[j]) {
                j++;
                prefix[i] = j;
                i++;
            }
            else{
                if (j == 0){
                    // all prefix has been used up
                    prefix[i] = 0;
                    i++;
                }
                else j = prefix[j - 1];// move forward a'
            }
        }
        return (prefix[n - 1] > 0) && (prefix[n - 1] % (n - prefix[n - 1]) == 0);
    }
}
