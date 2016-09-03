package backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * PalindromePermutationII_267
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ3ÈÕ
 */
public class PalindromePermutationII_267 {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 2){// corner case
            res.add(s);
            return res;
        }
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        for (char ch : s.toCharArray()){
            Integer v = count.get(ch);
            if (v == null) count.put(ch, 1);
            else count.put(ch, v + 1);
        }
        int odd = 0;
        int mid = -1;// mid element in a Palindrome
        char[] left = new char[s.length() / 2]; // left substring of palindrome
        int p = 0;
        for (char ch : count.keySet()){
            int num = count.get(ch);
            if (num % 2 == 1) {
                odd++;
                if (odd == 2) return res;
                mid = ch;
            }
            for (int j = 0; j < num / 2; j++) left[p++] = ch; // odd can also be 3,5... still need to be included in the left
        }
        collect(res, left, 0, mid);
        return res;
    }
    // better way to generate str, reduce time complexity from n^n to n!
    // each time pick one unique element and switch the first element to a latter position(so that we won't miss that element)
    // after picking, we only need to check latter positions, don't need to track used array and traverse all the numbers.
    private void collect(List<String> res, char[] left, int p, int mid){
        if (p == left.length){
            res.add(getP(left, mid));
            return;
        }
        collect(res, left, p + 1, mid);// don't swap position p.
        for (int i = p + 1; i < left.length; i++){
            if (left[i] != left[i - 1]){
                swap(left, p, i);
                collect(res, left, p + 1, mid);
                swap(left, i, p);
            }
        }
    }
    @SuppressWarnings("unused")
    private void collect(List<String> res, boolean[] used, char[] left, char[] r, int p, int mid){
        if (p == r.length){
            res.add(getP(r, mid));
            return;
        }
        char lastC = 0;
        for (int i = 0; i < left.length; i++){
            if (!used[i] && left[i] != lastC){// avoid redundancy
                lastC = left[i];
                used[i] = true;
                r[p++] = left[i];
                collect(res, used, left, r, p, mid);
                p--;
                used[i] = false;
            }
        }
    }
    private String getP(char[] r, int mid){
        int l = mid == -1 ? r.length * 2 : r.length * 2 + 1;
        char[] res = new char[l];
        System.arraycopy(r, 0, res, 0, r.length);
        int start = r.length;
        if (mid != -1) res[start++] = (char)mid;
        for (int i = res.length - 1; i >= start; i--) res[i] = r[res.length - 1 - i];
        return new String(res);
    }
    private void swap(char[] c, int i, int j){
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}
