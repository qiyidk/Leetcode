package dynamicProgramming;

import java.util.Arrays;

/**
 * <p>
 * MaximalRectangle_85
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ2ÈÕ
 */
public class MaximalRectangle_85 {
    /*
    1 0 1 0 0 1
    0 0 0 1 0 0  
    0 0 1 1 1 0  
    0 1 1 1 1 1 
    */
    // you can see for row i, it just like a Histogram by using row i as horizontal axis
    // see 84. Largest Rectangle in Histogram
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int max = 0;
        int[] height = new int[n]; // each time we get height of cur row from height of last row
        int[] s = new int[n];
        int[] h = new int[n];
        int p = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
                if (p == 0 || height[j] > h[p - 1]){
                    h[p] = height[j];
                    s[p] = j;
                    p++;
                }
                else if (height[j] < h[p - 1]){
                    int start = 0; //start point for new adding element
                    while(p != 0 && height[j] < h[p - 1]){
                        start = s[p - 1];
                        int area = (j - start) * h[p - 1];
                        if (area > max) max = area;
                        p--;
                    }
                    if (p == 0 || height[j] > h[p - 1]){
                        h[p] = height[j];
                        s[p] = start;
                        p++;
                    }
                }
            }
            // deal with remaining elements
            while(p != 0){
                int area = (n - s[p - 1]) * h[p - 1];
                if (area > max) max = area;
                p--;
            }
        }
        return max;
    }
    // pure dp solution
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int[] left = new int[n]; // left boundary for height[i]
        int[] right = new int[n]; // left boundary for height[i]
        Arrays.fill(right, n); // no limitation for right boundary when scaning the first row
        int[] h = new int[n]; // height of column i when reaching current row
        int max = 0;
        for (int i = 0; i < m; i++){
            // update height
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1') h[j]++;
                else h[j] = 0;
            }
            // update left
            int l = 0; //left boundary of consecutive "1" of current row so far
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], l); // pick the tighter boundary
                else {
                    l = j + 1;
                    left[j] = -1; // no limitation for next row
                }
            }
            // update right
            int r = n - 1; //right boundary of consecutive "1" of current row so far
            for (int j = n - 1; j >= 0; j--){
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], r); // pick the tighter boundary
                else {
                    r = j - 1;
                    right[j] = n; // no limitation for next row
                }
            }
            // update max
            for (int j = 0; j < n; j++) max = Math.max(max, h[j] * (right[j] - left[j] + 1));
        }
        return max;
    }
}
