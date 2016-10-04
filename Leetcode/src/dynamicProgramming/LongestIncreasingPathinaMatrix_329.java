package dynamicProgramming;

/**
 * <p>
 * LongestIncreasingPathinaMatrix_329
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ9ÈÕ
 */
public class LongestIncreasingPathinaMatrix_329 {
    // for p1 point[x,y], if it has a longestIncreasingPath lip, 
    // its adjacent point p2 < p1, p2 won't overlap with lip, since all points in lip > p1 > p2
    // therefore if p has 4 neighbors, lip[p] = max(lip of each neighbor that > p) + 1
    // if a lip can go from p1 to p2(p1 < p2), it cannot go back(p2 > p1), therefore we don't need a visited array
    public static final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] lip = new int[m][n]; // lip that starts with i,j
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (lip[i][j] == 0) getLip(i, j, m, n, matrix, lip);
                if (lip[i][j] > max) max = lip[i][j];
            }
        }
        return max;
    }
    private int getLip(int i, int j, int m, int n, int[][] matrix, int[][] lip){
        if (lip[i][j] != 0) return lip[i][j];
        int max = 0;
        for (int[] dir : dirs){
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[i][j] < matrix[x][y]) 
                max = Math.max(max, getLip(x, y, m, n, matrix, lip));
        }
        lip[i][j] = max + 1;
        return lip[i][j];
    }
}
