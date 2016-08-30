package dynamicProgramming;

/**
 * <p>
 * DungeonGame_174
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ16ÈÕ
 */
public class DungeonGame_174 {
    public int calculateMinimumHP2(int[][] dungeon) {
        int m = dungeon.length;
        if (m == 0) return 1;
        int n = dungeon[0].length;
        if (n == 0) return 1;
        // if we define minHp as at least minHp to reach block i,j
        // we cannot deduct minHp[i][j] from minHp[i - 1][j], minHp[i][j - 1], when geting minHp[i - 1][j], the hp left may not be sufficient to pass block i,j 
        int[][] minHp = new int[m][n]; // for block i,j, at least minHp to reach bottom-right corner 
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--){
                int extraHp = 0; // extraHp to pass the block after block i,j
                if (i != m - 1 && j != n - 1) extraHp = Math.min(minHp[i + 1][j], minHp[i][j + 1]);
                else{
                    if (i != m - 1) extraHp = minHp[i + 1][j];
                    else if (j != n - 1) extraHp = minHp[i][j + 1];
                    else extraHp = 1;
                }
                minHp[i][j] = extraHp <= 0 ? 1 - dungeon[i][j] : extraHp - dungeon[i][j];
            }
        return minHp[0][0] <= 0 ? 1 : minHp[0][0];
    }
    // reduce 2-dimension dp to 1-dimension
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if (m == 0) return 1;
        int n = dungeon[0].length;
        if (n == 0) return 1;
        int[] minHp_cur = new int[n]; // minHp for current row
        int[] minHp_next = new int[n]; // minHp for next row
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                int extraHp = 0; // extraHp to pass the block after block i,j
                if (i != m - 1 && j != n - 1) extraHp = Math.min(minHp_cur[j + 1], minHp_next[j]);
                else{
                    if (i != m - 1) extraHp = minHp_next[j];
                    else if (j != n - 1) extraHp = minHp_cur[j + 1];
                    else extraHp = 1;
                }
                minHp_cur[j] = extraHp <= 0 ? 1 - dungeon[i][j] : extraHp - dungeon[i][j];
            }
            minHp_next = minHp_cur;
            minHp_cur = new int[n];
        }
        return minHp_next[0] <= 0 ? 1 : minHp_next[0];
    }
}
