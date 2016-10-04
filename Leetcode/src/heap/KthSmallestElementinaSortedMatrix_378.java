package heap;

import java.util.PriorityQueue;

/**
 * <p>
 * KthSmallestElementinaSortedMatrix_378
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ9ÈÕ
 */
public class KthSmallestElementinaSortedMatrix_378 {
    /*
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
    */
    // we can pick element one by one
    // at first we have all the number of first column, we pick 0,0
    // next potential element will be (0, 1) or the smallest one of the remaining number
    // we can maintain a heap to store the potential smallest one so far, each time get a smallest one and add a potential smallest number
    // the time complexity is klogn
    public int kthSmallest2(int[][] matrix, int k) {
        int[] column = new int[matrix.length];
        // pq maintains current valid row number that can be used
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
            (Integer row1, Integer row2) -> {return matrix[row1][column[row1]] - matrix[row2][column[row2]];}
            );
        for (int i = 0; i < matrix.length; i++) pq.add(i);
        int row = 0;
        while(k > 0){
            row = pq.remove();
            k--;
            column[row]++;
            if (column[row] < matrix.length) pq.add(row);
        }
        return matrix[row][column[row] - 1];
    }
    
    // we can use another way to deal with this problem
    // all elements must be between min(0,0) to max(n - 1, n - 1)
    // we can do a binary search, first get mid, and find the rank of mid, compare with k and decide which way to go
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0];
        int r = matrix[n - 1][n - 1];
        while(l < r){
            int mid = (l + r + 1) / 2;
            int count = 0;// number of elements that are less than mid
            int i = n - 1;
            int j = 0;
            while(i >= 0 && j < n){
                if (mid > matrix[i][j]){
                    count += i + 1;
                    j++;
                }
                else i--; // if less go up, if equal, still go up and do not count m[i][j]
            }
            // n(k - 1)  mid1  n(k)  mid2 n(k + 1)
            // position n(k) is the last position that has k - 1 elements less than n(k)
            // we need to make l and r go towards n(k)
            if (count >= k) r = mid - 1;
            else l = mid; // count <= k, even if count = k, mid my be the final result, may be not.
        }
        return r;
    }
}
