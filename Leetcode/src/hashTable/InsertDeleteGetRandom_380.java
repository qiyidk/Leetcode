package hashTable;

import java.util.HashMap;
import java.util.Random;

/**
 * <p>
 * InsertDeleteGetRandom_380
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ31ÈÕ
 */
public class InsertDeleteGetRandom_380 {
    // use hashmap to deal with first two requirement
    // use an extra hashmap to label current elements, when removing an element, switch indices so that the indices can still be continuous, just like shuffle
    /** Initialize your data structure here. */
    private HashMap<Integer, Integer> indices; // val -> index
    private HashMap<Integer, Integer> values; // index -> val
    private Random rand;
    public InsertDeleteGetRandom_380() {
        indices = new HashMap<Integer, Integer>();
        values = new HashMap<Integer, Integer>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        Integer index = indices.get(val);
        if (index == null) {
            indices.put(val, values.size());
            values.put(values.size(), val);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = indices.remove(val);
        if (index == null) return false;
        int lastIndex = values.size() - 1;
        if (indices.isEmpty() || index == lastIndex){ // note index == lastIndex
            // don't need to swap
            values.remove(index);
        }
        else{
            int lastValue = values.get(lastIndex);
            values.remove(lastIndex);
            values.put(index, lastValue);
            indices.put(lastValue, index);
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = rand.nextInt(indices.size());
        return values.get(index);
    }
}
