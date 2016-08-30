package binarySearch;

/**
 * <p>
 * SearchinRotatedSortedArray_33
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ12ÈÕ
 */
public class SearchinRotatedSortedArray_33 {
    // 0  1  2  4  5  6  7
    // 4  5  6  7  0  1  2
    // |--    --|
    // length of k + 1
    // the array was move forward by k + 1 steps
    // we can first find the right boundary k(7), which is the biggest element. 
    // if current >= num[0], belong to left part, check whether current is k, if not, go right
    // if current < num[0], belong to right part, check whether current - 1 is k, if not, go left
    // notice current element cannot be eliminated
    // the sorted array have indice from k + 1 to k. 
    // then we can map the original array to a sorted array
    // for index i'(index in sorted array)
    // i = (i' + k + 1) % n

    public int search2(int[] nums, int target) {
        // find the right boundary of sorted array
        int n = nums.length;
        if (n == 0) return -1;
        int l = 0;
        int r = n - 1;
        int k = 0;
        while (l <= r){
            
            //if (nums[k] >= nums[0]) l = k; 
            //else r = k; it will stuck when only have 2 elements left
            
            /*
            k = (l + r) / 2;
            if (nums[k] >= nums[0]){
                if (k == n - 1 || nums[k] > nums[k + 1]) break;
                else l = k + 1;
            }
            else{
                if (nums[k] < nums[k - 1]) {
                    k = k - 1;
                    break;
                }
                else r = k - 1;
            }
            */
            // a better way
            k = (l + r + 1) / 2;
            if (nums[k] > nums[l]) l = k;
            else r = k - 1;     
        }
        // search the element
        l = 0;
        r = n - 1;
        while (l <= r){
            int m = (l + r) / 2;
            int index = (m + k + 1) % n;
            if (nums[index] == target) return index;
            else if (nums[index] > target) r = m - 1;
            else l = m + 1;
        }
        return -1;
    }
    // one while loop
    // first check which part the m is belong to and then check target
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r){
            int m = (l + r) / 2;
            if (nums[m] == target) return m;
            if (nums[m] < nums[l]){
                // m belong to the second part
                // l r m r
                if (target >= nums[l] || target < nums[m]) r = m - 1;
                else l = m + 1;
            }
            else{
                // m belong to the first part
                // l m l r
                // note that the second condition cannot use target <= nums[r], the second part may not exist
                if (target > nums[m] || target < nums[l]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }
}
