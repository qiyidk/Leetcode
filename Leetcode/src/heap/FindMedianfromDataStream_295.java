package heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * <p>
 * FindMedianfromDataStream_295
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ6ÈÕ
 */
public class FindMedianfromDataStream_295 {
    // if we separate the original array into two sub-arrays by the median, we will get two arrays which roughly have the same size.
    // if the number of the original array is even, the median will be equal to the biggest number of the first array and the smallest number of the second array
    // if odd, we define the first array is a little bigger, the biggest number of the first array will be the median
    // if illegal(the first array has at least 0, at most 1 element more than the second array), readjust the two arrays

    private PriorityQueue<Integer> smaller = new PriorityQueue<Integer>(Collections.reverseOrder());
    private PriorityQueue<Integer> bigger = new PriorityQueue<Integer>();
    // Adds a number into the data structure.
    public void addNum(int num) {
        if (smaller.isEmpty()) smaller.add(num);
        else{
            if (smaller.size() > bigger.size()){
                if (num >= smaller.peek()) bigger.add(num);
                else {
                    smaller.add(num);
                    bigger.add(smaller.remove());
                }
            }
            else {
                if (num <= bigger.peek()) smaller.add(num);
                else {
                    bigger.add(num);
                    smaller.add(bigger.remove());
                }
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return (bigger.size() + smaller.size()) % 2 == 1 ? smaller.peek() : (bigger.peek() + smaller.peek()) / 2.0;
    }
}
