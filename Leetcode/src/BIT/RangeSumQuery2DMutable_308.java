package BIT;

/**
 * <p>
 * RangeSumQuery2DMutable_308
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ6ÈÕ
 */
public class RangeSumQuery2DMutable_308 {
    // the straightforward way is to maintain m arrays for each row, each array maintains prefix sum for current row
    // http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
    private int[][] BIT;
    private int[][] matrix;
    public RangeSumQuery2DMutable_308(int[][] matrix) {
        if (matrix.length == 0) return;
        BIT = new int[matrix.length + 1][matrix[0].length + 1]; // 2d BIT, first build 1d BIT for each row, then build 2d BIT for each column based on 1d BIT(we don't need explicitly build 1d BIT)
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {//logmlogn time
        int delta = val - matrix[row][col];
        if (delta == 0) return;
        matrix[row][col] = val;
        for (int i = row + 1; i <= matrix.length; i += (i & -i)){
            for (int j = col + 1; j <= matrix[0].length; j += (j & -j)){
                BIT[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // define sum(row, col) = sum of elements from 0,0 to row, col
        // f(row1, col1, row2, col2) = sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1)
        return getSum(row2, col2) - getSum(row1 - 1, col2) - getSum(row2, col1 - 1) + getSum(row1 - 1, col1 - 1);
    }
    private int getSum(int row, int col){// O(logmlogn) time
        // sum of elements from 0,0 to row, col
        int s = 0;
        for (int i = row + 1; i > 0; i -= (i & -i)){
            for (int j = col + 1; j > 0; j -= (j & -j)){
                s += BIT[i][j];
            }
        }
        return s;
    }
    
    public static void main(String[] args){
        int[][] matrix = {{1,2,3,9},{4,5,6,11},{7,8,9,15}};
        new RangeSumQuery2DMutable_308(matrix);
    }
}
