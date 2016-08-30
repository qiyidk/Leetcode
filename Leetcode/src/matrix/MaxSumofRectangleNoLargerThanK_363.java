package matrix;

import java.util.TreeSet;

import baseDataStructure.MatrixGenerator;

/**
 * <p>
 * MaxSumofRectangleNoLargerThanK_363
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ22ÈÕ
 */
public class MaxSumofRectangleNoLargerThanK_363 {

    // more efficient way is to think this problem as a 4-sum problem(variables are x1, y1, x2, y2)
    // first we need pick 2 variables, and then manipulate the other two to form a sum that is the max number no larger than k
    // we can first pick a section of a row and manipulate the two column variables or pick the column variables and manipulate the other two
    // for picking the first two variables, it will take O(x^2),(x = m or n). For manipulate the other two varibales, it will take O(ylogy),(y = n or m), in total, it will take O(x^2 * ylogy) time
    // since the number of rows is much larger than the number of columns, we choose the two variables from columns
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int y1 = 0; y1 < n; y1++){
            int[] sum = new int[m]; // store the current sum of each row
            for (int y2 = y1; y2 < n; y2++){
                for (int r = 0; r < m; r++) sum[r] += matrix[r][y2]; //update sum
                // aaaa
                // bbbb
                // the sum of area bbbb = aaaa+bbbb - aaaa <= k => try to find a aaaa >= aaaa+bbbb - k. We need to find the minimum aaaa that satisfies the inequation.
                int v = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);// represent aaaa = 0
                for (int s : sum){
                    v += s;
                    int key = v - k;
                    Integer num = set.ceiling(key);//find aaaa
                    if (num != null && v - num > max) max = v - num; // note that num is aaaa, not bbbb!
                    set.add(v);// we cannot use aaaa+bbbb as aaaa in current round(area != 0)
                }
            }
        }
        return max;
    }
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        int[][] sum = new int[m][n];// sum of area that starts with 0,0
        for (int i = 0; i < m; i++){
            int s = 0;
            for (int j = 0; j < n; j++){
                s+= matrix[i][j];
                int t = i != 0 ? sum[i - 1][j] : 0;
                sum[i][j] = s + t;
            }
        }
        int max = Integer.MIN_VALUE;
        // this loop will take (1 + ... + m) * (1 + ... + n) time
        for (int x1 = 0; x1 < m; x1++){
            for (int y1 = 0; y1 < n; y1++){
                for (int x2 = x1; x2 < m; x2++){
                    for (int y2 = y1; y2 < n; y2++){
                        int s = getSum(x1, y1, x2, y2, sum);
                        if (s == k) return k;
                        else if (s < k && s > max) max = s;
                    }
                }
            }
        }
        return max;
    }
    private int getSum(int x1, int y1, int x2, int y2, int[][] sum){
        // a   b
        // c   s
        int s1 = x1 == 0 ? 0 : sum[x1 - 1][y2]; //a + b
        int s2 = y1 == 0 ? 0 : sum[x2][y1 - 1]; //a + c
        int s3 = x1 == 0 || y1 == 0 ? 0 : sum[x1 - 1][y1 - 1]; //a
        return sum[x2][y2] - s1 - s2 + s3;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "[[27,5,-20,-9,1,26,1,12,7,-4,8,7,-1,5,8],[16,28,8,3,16,28,-10,-7,-5,-13,7,9,20,-9,26],[24,-14,20,23,25,-16,-15,8,8,-6,-14,-6,12,-19,-13],[28,13,-17,20,-3,-18,12,5,1,25,25,-14,22,17,12],[7,29,-12,5,-5,26,-5,10,-5,24,-9,-19,20,0,18],[-7,-11,-8,12,19,18,-15,17,7,-1,-11,-10,-1,25,17],[-3,-20,-20,-7,14,-12,22,1,-9,11,14,-16,-5,-12,14],[-20,-4,-17,3,3,-18,22,-13,-1,16,-11,29,17,-2,22],[23,-15,24,26,28,-13,10,18,-6,29,27,-19,-19,-8,0],[5,9,23,11,-4,-20,18,29,-6,-4,-11,21,-6,24,12],[13,16,0,-20,22,21,26,-3,15,14,26,17,19,20,-5],[15,1,22,-6,1,-9,0,21,12,27,5,8,8,18,-1],[15,29,13,6,-11,7,-6,27,22,18,22,-3,-9,20,14],[26,-6,12,-10,0,26,10,1,11,-10,-16,-18,29,8,-8],[-19,14,15,18,-10,24,-9,-7,-19,-14,23,23,17,-5,6]]";
        int[][] res = MatrixGenerator.getMatrix(s);
        MatrixGenerator.print(res);
        int k = -100;
        System.out.println(new MaxSumofRectangleNoLargerThanK_363().maxSumSubmatrix(res, k));
    }

}
