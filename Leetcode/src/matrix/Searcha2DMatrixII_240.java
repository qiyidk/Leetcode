package matrix;

public class Searcha2DMatrixII_240 {
    // It is my second time to do this problem(saved)
    // we can start traversal from the left bottom corner, for each comparison, we can discard either one row or one colomn
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        int r = m - 1;
        int c = 0;
        while(r >= 0 && c <= n - 1){
            if (matrix[r][c] == target) return true;
            else if (matrix[r][c] < target) c++;
            else r--;
        }
        return false;
    }
}
