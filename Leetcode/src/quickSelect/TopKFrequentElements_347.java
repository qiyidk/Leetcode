package quickSelect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * TopKFrequentElements_347
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ25ÈÕ
 */
public class TopKFrequentElements_347 {
    @SuppressWarnings("unchecked")
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) map.put(num, 1);
            else map.put(num, count + 1);
        }
        HashMap.Entry<Integer, Integer>[] e = new HashMap.Entry[map.size()];
        int p = 0;
        for (HashMap.Entry<Integer, Integer> en : map.entrySet()){
            e[p++] = en;
        }
        List<Integer> res = new ArrayList<Integer>();
        int kthV = qs(e, k, 0, e.length - 1);
        for (HashMap.Entry<Integer, Integer> en : map.entrySet()){
            if (en.getValue() >= kthV) res.add(en.getKey());
        }
        return res;
    }
    private int qs(HashMap.Entry<Integer, Integer>[] e, int k, int start, int end){
        int pivot = (int) (Math.random() * (end - start + 1)) + start;
        int val = e[pivot].getValue();
        swap(e, start, pivot);
        int i = start + 1;
        int l = start;
        int r = end;
        while (i <= r){
            int c = e[i].getValue();
            if (c > val){
                swap(e, i, l);
                l++;
                i++;
            }
            else if (c < val){
                swap(e, i, r);
                r--;
            }
            else i++;
        }
        if (k > r + 1) return qs(e, k, r + 1, end);
        else if (k < l + 1) return qs(e, k, start, l - 1);
        else return val;
    }
    private void swap(HashMap.Entry<Integer, Integer>[] e, int i, int j){
        HashMap.Entry<Integer, Integer> t = e[i];
        e[i] = e[j];
        e[j] = t;
    }
}
