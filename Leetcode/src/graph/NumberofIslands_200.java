package graph;

/**
 * <p>
 * NumberofIslands_200
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class NumberofIslands_200 {
    // saved
    // It is my second time to do this problem
    // this problem actually is asking us to find connected components in the graph
    // if we can modify the original grid, we can use '2' to represent visited status, otherwise we need to use a visited array
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j){
        grid[i][j] = '2';
        if (i != 0 && grid[i - 1][j] == '1') dfs(grid, i - 1, j);
        if (i != grid.length - 1 && grid[i + 1][j] == '1') dfs(grid, i + 1, j);
        if (j != 0 && grid[i][j - 1] == '1') dfs(grid, i, j - 1);
        if (j != grid[0].length - 1 && grid[i][j + 1] == '1') dfs(grid, i, j + 1);
    }
}
