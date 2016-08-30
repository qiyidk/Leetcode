package divide_conquer;

/**
 * <p>
 * CountofRangeSum_327
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ6ÈÕ
 */
public class CountofRangeSum_327 {
    // divide-conquer & two pointers solution
    // define sum[i] = sum from 1 to i;
    // sum[i, j] = sum[j] - sum[i] + nums[i]
    // we can put sum[i], -sum[i] into two arrays and sorted, we just pick one element from each array to get a specific sum range
    // but if we sort the array, the relative position will be destroyed(if we pick i, we can not pick j(j < i) in the second array)
    // we use divide-conquer to do this process separately, each time, the first array only choose elements from the first half, the second array only choose elements from the second part, so that they won't overlap.
    // if we define sum[i] = sum from 1 to i - 1, sum[i, j] = sum[j] - sum[i]; sum from i to j - 1. we don't need the extra addition.
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0) return 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) sum[i + 1] = sum[i] + nums[i];
        return merge(sum, 0, nums.length, lower, upper);
    }
    private int merge(long[] sum, int start, int end, int lower, int upper){
        if (end - start + 1 <= 1) return 0;
        int count = 0;
        int mid = (start + end) / 2;
        count += merge(sum, start, mid, lower, upper);
        count += merge(sum, mid + 1, end, lower, upper);
        long[] aux = new long[end - start + 1];
        int j = mid + 1; // first element that has a sum greater than or equal to lower bound
        int k = mid + 1; // first element that has a sum greater than upper bound 
        int r = mid + 1; // pointer for merge sort
        int p = 0; // pointer for aux array
        for (int i = start; i <= mid; i++){
            while (j <= end && sum[j] - sum[i] < lower) j++;
            while (k <= end && sum[k] - sum[i] <= upper) k++;
            count += k - j;
            // merge sort
            while (r <= end && sum[r] < sum[i]) aux[p++] = sum[r++];
            aux[p++] = sum[i];
        }
        System.arraycopy(aux, 0, sum, start, p);// copy the elements whose positions have changed
        return count;
    }
    public static void main(String[] args){
        System.out.println(new CountofRangeSum_327().countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }
}
