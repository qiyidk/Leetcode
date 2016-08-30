package math;

import java.util.Arrays;

/**
 * <p>
 * SortTransformedArray_360
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ18ÈÕ
 */
public class SortTransformedArray_360 {
    // f(x) = a(x + b/2a)^2 + (4ac- b^2) / 4a
    // if a != 0
    // a > 0 x > -b/2a increasing function
    // a > 0 x < -b/2a decreasing function
    // a < 0 x > -b/2a decreasing function
    // a < 0 x > -b/2a increasing function
    // if a = 0 f(x) = bx + c
    // b > 0 increasing function
    // b < 0 decreasing function
    // b = 0 constant value
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (a == 0){
            if (b == 0) {
                for (int i = 0; i < nums.length; i++) nums[i] = c;
                return nums;
            }
            if (b > 0) {
                for (int i = 0; i < nums.length; i++) nums[i] = function(nums[i], a, b, c);
                return nums;
            }
            else{
                int[] aux = new int[nums.length];
                for (int i = 0; i < nums.length; i++) aux[nums.length - 1 - i] = function(nums[i], a, b, c);
                return aux;
            }
        }
        else{
            double m = 1.0 * -b / (2 * a);
            int p = 0;
            int[] aux = new int[nums.length];
            for(; p < nums.length; p++) if (nums[p] >= m) break;
            if (p != 0 && m - nums[p - 1] < nums[p] - m) p = p - 1;
            int l = p - 1;
            int r = p + 1;
            if (a > 0){
                int i = 0;
                aux[i++] = function(nums[p], a, b, c);
                while(i <= nums.length - 1){
                    if (l < 0 || (r < nums.length && (m - nums[l] > nums[r] - m))){
                        //choose r
                        aux[i++] = function(nums[r++], a, b, c);
                    }
                    else aux[i++] = function(nums[l--], a, b, c);
                }
                
            }
            else{
                int i = nums.length - 1;
                aux[i--] = function(nums[p], a, b, c);
                while(i >= 0){
                    if (l < 0 || (r < nums.length && (m - nums[l] > nums[r] - m))){
                        //choose r
                        aux[i--] = function(nums[r++], a, b, c);
                    }
                    else aux[i--] = function(nums[l--], a, b, c);
                }
            }
            return aux;
        }
    }
    private int function(int x, int a, int b, int c){
        return a * x * x + b * x + c;
    }
    public static void main(String[] args){
        int[] nums = {-4,-2,2,4};
        nums = new SortTransformedArray_360().sortTransformedArray(nums, -1, 3, 5);
        for(int num : nums) System.out.println(num);
        System.out.println(Arrays.toString(nums));
    }
}
