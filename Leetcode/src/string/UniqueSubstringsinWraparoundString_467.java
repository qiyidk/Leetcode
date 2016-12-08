/**
 * 
 */
package string;

/**
 * @author qiyi
 *
 */
public class UniqueSubstringsinWraparoundString_467 {
    // if a substring k in s, all substrings of k will be in s
    // we need to find all ks that have the maximum length
    // if k1 is contained in another k2, k1 is not the maximal k; therefore should not be considered
    // if there are overlapping
    // abcd
    //  bcde
    //   cdef
    // cannot handle directly
    
    // if we fix one letter, say, the end letter
    // we only generate substring that ends with that letter
    // since the end letter is fixed and different, there's no way to cause a collision
    // furthermore, since we fixed the end letter, the longest substring that ends with e must include all shorter substrings ends with e, we don't need care about the shorter ones
    public int findSubstringInWraproundString(String p) {
        int start = 0;
        int n = p.length();
        if (n == 0) return 0;
        int[] max = new int[26];
        int res = 0;
        for (int i = 1; i < n; i++){
            int cur = p.charAt(i) - 'a';
            int pre = p.charAt(i - 1) - 'a';
            if (max[pre] < i - start) max[pre] = i - start;
            if (cur == (pre + 1) % 26) continue;
            else start = i;
        }
        if (max[p.charAt(n - 1) - 'a'] < n - start) max[p.charAt(n - 1) - 'a'] = n - start;
        for (int i = 0; i < 26; i++){
            res += max[i];
        }
        return res;
    }
}
