package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * PacificAtlanticWaterFlow_417
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ28ÈÕ
 */
public class PacificAtlanticWaterFlow_417 {
    // we can use uf, just like percolation
    // actually, we don't have to use uf
    // we flow back from boundaries and find all the elements that can reach the boundaries
    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<int[]>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        boolean[][] canReachPacific = new boolean[m][n];
        boolean[][] canReachAtlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) if (!canReachPacific[i][0]) bfs(i, 0, canReachPacific, matrix);
        for (int j = 0; j < n; j++) if (!canReachPacific[0][j]) bfs(0, j, canReachPacific, matrix);
        for (int i = 0; i < m; i++) if (!canReachAtlantic[i][n - 1]) collect(i, n - 1, canReachPacific, canReachAtlantic, res, matrix);
        for (int j = 0; j < n; j++) if (!canReachAtlantic[m - 1][j]) collect(m - 1, j, canReachPacific, canReachAtlantic, res, matrix);
        return res;
    }
    private void bfs(int i, int j, boolean[][] canReachPacific, int[][] matrix){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(i);
        q.add(j);
        canReachPacific[i][j] = true;
        while(!q.isEmpty()){
            int x = q.remove();
            int y = q.remove();
            for (int[] dir : dirs){
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if (x1 < 0 || x1 > canReachPacific.length - 1 || y1 < 0 || y1 > canReachPacific[0].length - 1 
                    || canReachPacific[x1][y1] 
                    || matrix[x1][y1] < matrix[x][y])
                    continue;
                canReachPacific[x1][y1] = true;
                q.add(x1);
                q.add(y1);
            }
        }
    }
    private void collect(int i, int j, boolean[][] canReachPacific, boolean[][] canReachAtlantic, List<int[]> res, int[][] matrix){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(i);
        q.add(j);
        canReachAtlantic[i][j] = true;
        if (canReachPacific[i][j]) res.add(new int[]{i, j});
        while(!q.isEmpty()){
            int x = q.remove();
            int y = q.remove();
            for (int[] dir : dirs){
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if (x1 < 0 || x1 > canReachAtlantic.length - 1 || y1 < 0 || y1 > canReachAtlantic[0].length - 1 
                    || canReachAtlantic[x1][y1] 
                    || matrix[x1][y1] < matrix[x][y])
                    continue;
                canReachAtlantic[x1][y1] = true;
                if (canReachPacific[x1][y1]) res.add(new int[]{x1, y1});
                q.add(x1);
                q.add(y1);
            }
        }
    }
}
