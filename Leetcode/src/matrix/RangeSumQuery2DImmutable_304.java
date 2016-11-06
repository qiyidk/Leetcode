package matrix;

/**
 * <p>
 * RangeSumQuery2DImmutable_304
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ29ÈÕ
 */
public class RangeSumQuery2DImmutable_304 {
    // O(mn)space O(m)time, store sum of each row from column 0 to column j
    // O(mn)space O(1)time
    // bbbbcccaaaaaaa
    // bbbbcccaaaaaaa
    // ddddeeeaaaaaaa
    // ddddeeeaaaaaaa
    // aaaaaaaaaaaaaa
    // aaaaaaaaaaaaaa
    // compute sum of e
    // we define sum[i][j] = sum of square from 0 to i,j
    // sum e = sum all - sum b - sum c - sum d = sum all - sum b - (sum b,c - sum b) - (sum b,d - sum b)
    //       = sum all - sum b, c - sum b, d + sum b
    // sum e = sum[row2][col2] + sum[row1 - 1][col1 - 1]
    //       - sum[row1 - 1][col2] - sum[row2][col1 - 1]
    private int[][] sum;
    public RangeSumQuery2DImmutable_304(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        sum = new int[m][n];
        sum[0][0] = matrix[0][0];
        for (int j = 1; j < n; j++){
            sum[0][j] = sum[0][j - 1] + matrix[0][j];
        }
        for (int i = 1 ; i < m; i++){
            int s = 0; // sum of elements in current row we've seen so far
            for (int j = 0; j < n; j++){
                s += matrix[i][j];
                sum[i][j] = s + sum[i - 1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum == null) return 0;
        int sumB = row1 == 0 || col1 == 0 ? 0 : sum[row1 - 1][col1 - 1];
        int sumBC = row1 == 0 ? 0 : sum[row1 - 1][col2];
        int sumBD = col1 == 0 ? 0 : sum[row2][col1 - 1];
        return sum[row2][col2] + sumB - sumBC - sumBD;
    }
}
