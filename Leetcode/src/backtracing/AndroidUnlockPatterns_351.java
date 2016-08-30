package backtracing;

/**
 * <p>
 * AndroidUnlockPatterns_351
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ24ÈÕ
 */
public class AndroidUnlockPatterns_351 {
    // a little not clear, 2 to 9 won't be blocked by 5 and 6 
    public int numberOfPatterns(int m, int n) {
        boolean[] visited = new boolean[10];//use 1 - 9
        visited[0] = true; // so that if block = 0, visit[block] = true
        int[][] block = new int[10][10];//use 1 - 9
        block[1][3] = block[3][1] = 2;
        block[1][7] = block[7][1] = 4;
        block[1][9] = block[9][1] = block[3][7] = block[7][3] = block[2][8] = block[8][2] = block[4][6] = block[6][4] = 5;
        block[7][9] = block[9][7] = 8;
        block[3][9] = block[9][3] = 6;
        int count = 0;
        count+= dfs(1, m, n, 1, visited, block) * 4;
        count+= dfs(4, m, n, 1, visited, block) * 4;
        count+= dfs(5, m, n, 1, visited, block);
        return count;
    }
    
    private int dfs(int p, int m, int n, int d, boolean[] visited, int[][] block){
        if (d == n) return 1;
        int count = 0;
        visited[p] = true;
        if (d >= m) count++;
        for (int i = 1; i < 10; i++){
            if (!visited[i] && visited[block[p][i]]) count+= dfs(i, m, n, d + 1, visited, block);
        }
        visited[p] = false;
        return count;
    }
}
