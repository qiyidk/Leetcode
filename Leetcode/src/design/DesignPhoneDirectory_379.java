package design;

import java.util.LinkedList;

/**
 * <p>
 * DesignPhoneDirectory_379
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ1ÈÕ
 */
public class DesignPhoneDirectory_379 {
    private boolean[] used;
    private LinkedList<Integer> nums; // numbers that can be used
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory_379(int maxNumbers) {
        used = new boolean[maxNumbers];
        nums = new LinkedList<Integer>();
        for (int i = 0; i < maxNumbers; i++) nums.add(i);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (nums.isEmpty()) return -1;
        int v = nums.remove();
        used[v] = true;
        return v;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used[number];
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (used[number]){
            nums.add(number);
            used[number] = false;
        }
    }
}
