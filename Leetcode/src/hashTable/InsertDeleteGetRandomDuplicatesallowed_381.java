package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * <p>
 * InsertDeleteGetRandomDuplicatesallowed_381
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ31ÈÕ
 */
public class InsertDeleteGetRandomDuplicatesallowed_381 {
    private HashMap<Integer, Set<Integer>> indexMap;// (val, index set)
    private List<Integer> valueMap;// (index, val)
    private Random rand;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomDuplicatesallowed_381() {
        indexMap = new HashMap<Integer, Set<Integer>>();
        valueMap = new ArrayList<Integer>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean res = true;
        int index = valueMap.size();
        Set<Integer> set = indexMap.get(val);
        if (set != null) res = false;
        else {
            set = new LinkedHashSet<Integer>();
            indexMap.put(val, set);
        }
        set.add(index);
        valueMap.add(val);
        return res;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) return false;
        //swap the last element and current element, then remove
        int lastIndex = valueMap.size() - 1;
        int lastV = valueMap.get(lastIndex);
        Set<Integer> indexSet = indexMap.get(val);
        int index = indexSet.iterator().next();
        indexSet.remove(index);
        if (indexSet.isEmpty()) indexMap.remove(val);
        valueMap.remove(lastIndex);
        if (index != lastIndex){
            Set<Integer> indexSetOfLastV = indexMap.get(lastV);
            indexSetOfLastV.remove(lastIndex);
            indexSetOfLastV.add(index);
            valueMap.set(index, lastV);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return valueMap.get(rand.nextInt(valueMap.size()));
    }
}
