package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * InsertDeleteGetRandom_380
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ31ÈÕ
 */
public class InsertDeleteGetRandom_380 {
    private HashMap<Integer, Integer> indexMap;// (val, index)
    private List<Integer> valueMap;// (index, val)
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom_380() {
        indexMap = new HashMap<Integer, Integer>();
        valueMap = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) return false;
        int index = indexMap.size();
        indexMap.put(val, index);
        valueMap.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) return false;
        //swap the last element and current element, then remove
        int lastIndex = indexMap.size() - 1;
        int lastV = valueMap.get(lastIndex);
        int index = indexMap.remove(val);
        valueMap.remove(lastIndex);
        if (index != lastIndex){
            indexMap.put(lastV, index);
            valueMap.set(index, lastV);
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = (int)(Math.random() * valueMap.size());
        return valueMap.get(index);
    }
}
