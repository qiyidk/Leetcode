package graph;

import java.util.PriorityQueue;

/**
 * <p>
 * TrappingRainWaterII_407
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ13ÈÕ
 */
public class TrappingRainWaterII_407 {
    // we can do a bfs/dfs(try to avoid using dfs in a graph, it will cause stack overflow easily), find each puddle and calculate the area of each puddle
    // for each puddle, its boundaries must have a closed loop. Each element inside the puddle has a lower height than the minimum height of boundaries. The outside adjacent elements of boundaries have equal or greater height than the minimum height of boundaries.
    // since a convex node can either go left or right(up or down), it may cross different puddles.
    // it will be very hard to find a specific puddle during each traversal.

    // actually we don't need to care about the exact range of each puddle. just like what water trap 1 did.
    // each cell can either be boundary or inner cell that traps the water
    // let's start with the the borders of the heightMap
    // all the borders of the heightMap must be boundaries, since they cannot trap water
    // since all the borders of the heightMap consist a closed loop, it can be a puddle.
    // we denote the minimum height of the borders as H.
    // all the water inside the heightMap can be trapped by boundaries has a height at least H
    // of course, it can be higher than H if have inner puddle which minimum boundaries has a height higher than H
    // if we choose the lowest boundary and moving to each direction. 
    // if current cell is equal to or higher than H, it must be a boundary, it cannot trap water, since the water can flow through H.
    // if current cell is lower than H, it can always be trapped, maybe by intermediate boundary, or at least by the minimum borders of the heightMap

    // first case: H can be discarded, since we can use the new boundary which may trap more water
    // in this case, we need to find the lowest boundary without H(usa a heap and remove H)
    // bbbbbbbHbbb
    // b      b  b
    // bbbbbbbbbbb
    // second case: we can use "a" to represent H £¨pass the same boundary£©in order to follow the same rule as the first case. if a cell has a height higher than H, it may pass though a then pass through H.
    // bbbbbbbHbbb
    // b      a  b
    // bbbbbbbbbbb

    // since after replace H with its neighbors(no matter H or new boundary), the boundaries are still a closed loop, all the following operations can follow the same rule as before.
     

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m < 3) return 0;
        int n = heightMap[0].length;
        if (n < 3) return 0;
        boolean[][] visited = new boolean[m][n];
        int water = 0;
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
        for (int i = 0; i < m; i++){
            if (i == 0 || i == m - 1){
                for (int j = 0; j < n; j++) {
                    visited[i][j] = true;
                    pq.add(new Cell(i, j, heightMap[i][j]));
                }
            }
            else {
                visited[i][0] = true;
                visited[i][n - 1] = true;
                pq.add(new Cell(i, 0, heightMap[i][0]));
                pq.add(new Cell(i, n - 1, heightMap[i][n - 1]));
            }
        }
        int[][] dirs = {{-1, 0},{1, 0},{0, 1},{0, -1}};
        while(!pq.isEmpty()){
            Cell cell = pq.remove();
            for (int[] dir : dirs){
                int x = dir[0] + cell.x;
                int y = dir[1] + cell.y;
                if (x > 0 && x < m - 1 && y > 0 && y < n - 1 && !visited[x][y]){
                    visited[x][y] = true;
                    if (heightMap[x][y] < cell.h){
                        //trap water
                        water += cell.h - heightMap[x][y];
                        pq.add(new Cell(x, y, cell.h));
                    }
                    else pq.add(new Cell(x, y, heightMap[x][y]));// new boundary
                }
            }
        }
        return water;
    }
    private class Cell implements Comparable<Cell>{
        private int x;
        private int y;
        private int h;
        public Cell(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
        public int compareTo(Cell that){
            return this.h - that.h; 
        }
    }
}
