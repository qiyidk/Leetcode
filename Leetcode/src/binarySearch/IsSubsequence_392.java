package binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * IsSubsequence_392
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ12ÈÕ
 */
public class IsSubsequence_392 {
    // just match character greedily, O(n)
    public boolean isSubsequence2(String s, String t) {
        int p = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++){
            while(p < t.length() && t.charAt(p) != c[i]) p++;
            if (p == t.length()) return false;
            p++; //match successfully. don't forget this line!!
        }
        return true;
    }
    // if we have many Ss, we can do a pre-processing so that we can deal with each s more efficiently.
    // we can store the occurrence of each character, and use a binary search to find the first legal index
    // it will take s.length() * log(t.length()), since s is relatively small comparing to t, it will much smaller than O(n)
    public boolean isSubsequence(String s, String t) {
        @SuppressWarnings("unchecked")
        List<Integer>[] indices = new List[26];
        for (int i = 0; i < 26; i++) indices[i] = new ArrayList<Integer>();
        for (int i = 0; i < t.length(); i++) indices[t.charAt(i) - 'a'].add(i);
        int index = 0;// next valid index
        for (int i = 0; i < s.length(); i++){
            List<Integer> list = indices[s.charAt(i) - 'a'];
            int index_list = search(index, list);
            if (index_list == list.size()) return false;
            index = list.get(index_list) + 1;
        }
        return true;
    }
    // return the index of the element in the list
    private int search(int v, List<Integer> list){
        int l = 0;
        int r = list.size() - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            int index = list.get(mid);
            if (v == index) return mid;
            else if (v > index) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}
