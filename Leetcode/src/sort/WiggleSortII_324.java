package sort;

import java.util.Arrays;

/**
 * <p>
 * WiggleSortII_324
 * </p>
 *
 * @author qiyi
 * @version 2016年3月4日
 */
public class WiggleSortII_324 {
    public void wiggleSort(int[] nums) {
        //if all nums are distinct, we only need make comparsion and whenever find a violation, swap the two adjacent numbers. 
        //but here we cannot do that.
        //we need to find a median fisrt and use this median to partition the array, so that we can pick elements on each side of the median alternatively.
        //only one thing that may have problem is when we have duplicate medians. Can this method guarantee a valid result?
        //AAABBBCCC
        //    ^
        //let A = x B = y total = n if y > n / 2 + 1, there's no way to have a valid result at all
        //therefore, we need to find a way to pick up elements so that their index differece is greater than n / 2(which means this subarray contains more than n / 2 + 1 elements, and the fisrt element and last element cannot be the same)
        //when n is even
        //AAABBCCC
        // <-^   ^    
        // notice we must use this order, if use reverse order to traverse, the distance between the last element of first pair and the first element of the second pair will be less than n / 2
        //when n is odd
        //AAABBBCCC
        // <- ^   ^
        // note that the distance within one pair is equal to n / 2(not greater than), when n is odd, it may cause problem, since it may be like this:
        // AAABB or AABBB
        // for the first situation, since we won't use this pair AAABB, it will be ok
        //                                                       ^ ^
        // but for AABBB, it will cause a problem; however, the question require a[0] < a[1], therefore in this case, it won't have solution and we always don't need to worry about it.
        int n = nums.length;
        int[] aux = new int[n];
        //radixSort(nums, aux);
        //quickselect is also ok
        System.arraycopy(nums, 0, aux, 0, n);
        Arrays.sort(aux);
        //int l = n % 2 == 0 ? n / 2 - 1: n / 2;
        int l = (n - 1) / 2;
        int r = n - 1;
        int p = 0;
        for (; l >= 0; l--,r--){
            nums[p++] = aux[l];
            if (p < n) nums[p++] = aux[r];
        }
    }
        // if don't want to use extra space, we can use a permutation
        // m = (n - 1) / 2
        // for element i on the left side of m, it should go to (m - i) * 2 = 2m - 2i
        // for element i on the right side of m, it shoulg go to (n - 1 - i) * 2 + 1 = 2n - 2i - 1 = n - 1 - (2i -n)
        // 2i % n == 2i <= n ? 2i : 2i - n  
        // therefore we can use 2i % n instead of  2i / 2i - n
        // when n is odd, 2m = n - 1 f1 = n - 1 - 2i % n, f2 = n - 1 - 2i % n
        // when n is even, 2m = n - 2 f1 = n - 2 - 2i % n = n - 2 - 2i % (n + 1), f2 = n - 1 - 2i % n = n - 2 - 2i % (n + 1)
        // therefore f = n - 1 - 2i % (n + k) - k  when n is odd k = 0 else k = 1
        // f = n - 1 - (2i + k) % (n + k) = n - 1 - (2i|1) % (n|1) 
        // 这里用置换需要额外的一个数组或者用一个特殊的标志位放在每个数的最前面，都不是很好
        
        
        //i 偶数位放大于mid k 奇数位放小于mid 同时放置， j 扫描指针
        //奇数位从后往前放（相当于上面那套方法的从前往后放，notice partition的顺序不同），偶数位从前往后放，mid插空，如果mid相邻在一起则说明无解,已经尽最大可能分割mid了
        //#define A(i) nums[(1+2*(i)) % (n|1)]
        //int i = 0, j = 0, k = n - 1;
        //while (j <= k) {
        //    if (A(j) > mid)
        //        swap(A(i++), A(j++));
        //    else if (A(j) < mid)
        //        swap(A(j), A(k--));
        //    else
        //        j++;
        //}
        //}
        //
        //index:     0  1  2  3   4   5  6  7  8  9
        //number:   18 17 19 16  15  11 14 10 13 12
        //final:
        //index:     5  0  6  1  7  2  8  3  9  4
        //number:   11 17 14 16 10 19 13 17 12 15
        //Accessing A(0) actually accesses nums[1].
        //Accessing A(1) actually accesses nums[3].
        //Accessing A(2) actually accesses nums[5].
        //Accessing A(3) actually accesses nums[7].
        //Accessing A(4) actually accesses nums[9].
        //Accessing A(5) actually accesses nums[0].
        //Accessing A(6) actually accesses nums[2].
        //Accessing A(7) actually accesses nums[4].
        //Accessing A(8) actually accesses nums[6].
        //Accessing A(9) actually accesses nums[8].
        
    @SuppressWarnings("unused")
    private void radixSort(int[] nums, int[] aux){
        int p = nums.length - 1;//positive
        int n = 0;//negative
        for (int i = 0; i < nums.length; i++){
            if (nums[i] >= 0) aux[p--] = nums[i];
            else aux[n++] = nums[i];
        }
        for (int i = 4; i > 0; i--){
            //switch roles of aux and original array to avoid array copy
            if (i % 2 == 0){
                countSort(nums, aux, 0, n - 1, i);
                countSort(nums, aux, n, nums.length - 1, i);
            }
            else{
                countSort(aux, nums, 0, n - 1, i);
                countSort(aux, nums, n, nums.length - 1, i);
            }
        }
    }
    private void countSort(int[] nums, int[] aux, int start, int end, int bytes){
        int offset = (4 - bytes) * 8;
        int mask = 0xff << offset;
        int radix = 256;
        int[] count = new int[radix + 1];
        for (int i = start; i <= end; i++) count[((aux[i] & mask) >>> offset) + 1]++;
        for (int i = 1; i < radix; i++) count[i]+= count[i - 1];
        for (int i = start; i <= end; i++) nums[start + count[(aux[i] & mask) >>> offset]++] = aux[i];
    }
    
    // It is my second time to do this problem(saved)
    // 1 1 2 2 4 4 5 5
    // 1 1 2 2 2 4 4 5 5
    // if we sort the array, pick element from the first part and second part alternatively, the final result will conform the wiggled order
    // when picking element from each part, we should use reverse order, otherwise, it will cause problem when have lots of duplicate values
    public void wiggleSort2(int[] nums) {
        int[] aux = new int[nums.length];
        System.arraycopy(nums, 0 , aux, 0, nums.length);
        Arrays.sort(aux);
        int first = nums.length % 2 == 0 ? nums.length / 2 - 1: nums.length / 2;
        int second = nums.length - 1;
        for (int i = 0; i < nums.length; i++){
            if (i % 2 == 0){
                nums[i] = aux[first--];
            }
            else{
                nums[i] = aux[second--];
            }
        }
    }
}
