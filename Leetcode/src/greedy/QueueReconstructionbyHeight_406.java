package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * <p>
 * QueueReconstructionbyHeight_406
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ9ÈÕ
 */
public class QueueReconstructionbyHeight_406 {
    // we can find current minimum number and calculate the appropriate position for that number
    // if the number is the minimum number of all numbers, the process is trivial, all the numbers must be greater than or equal to it. Therefore, it should be put at position k + 1(index k).
    // for numbers that have the same value, if Ka < Kb, number a must be in front of number b
    // for the second number we also need to record how many numbers have put in front of it. 
    // but update the auxiliary array will take O(n ^ 2) time, so we give up maintaining this kind of array, and just search for the result when using it.

    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
                public int compare(int[] p1, int[] p2){
                    if (p1[0] != p2[0]) return p1[0] - p2[0];
                    else return p1[1] - p2[1];
                }
            });
        int[][] res = new int[people.length][2];
        for (int i = 0; i < res.length; i++) res[i] = new int[]{Integer.MAX_VALUE, 0};
        for (int[] p : people){
            int index = p[1];
            for (int i = 0; i < index; i++) if (res[i][0] < p[0]) index++;
            while(res[index][0] != Integer.MAX_VALUE){
                index++;
            }
            res[index] = p;
        }
        return res;
    }
    // if we traverse the people from tallest height to shortest, for the current max value, we can know no value will be greater than it, if k elements greater or equal to it, it must be put at the k + 1 position(index k) of current array.
    // for the people that have equal height, if Ka < Kb, person a must appear in front of b, since b won't affect a, we can visit a first, then visit b 
    // we can use binary index tree to get a nlogn solution, but the implementation is non-trivial
    // array search logn insert n
    // linkedlist search n insert n(but n is less than the n of array)
    // bit search logn insert logn(insert and update size of subtree)
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
                public int compare(int[] p1, int[] p2){
                    if (p1[0] != p2[0]) return p2[0] - p1[0];
                    return p1[1] - p2[1];
                }
            });
        LinkedList<int[]> list = new LinkedList<int[]>();
        for (int[] p : people) list.add(p[1], p);
        return list.toArray(people);
    }
}
