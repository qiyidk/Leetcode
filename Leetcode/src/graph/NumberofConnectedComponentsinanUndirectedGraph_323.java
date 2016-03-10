package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * NumberofConnectedComponentsinanUndirectedGraph_323
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class NumberofConnectedComponentsinanUndirectedGraph_323 {
    //saved
    public int countComponents(int n, int[][] edges) {
        // use union-find
        int count = n;
        int[] root = new int[n];
        //int[] height = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;
        for (int[] e : edges){
            int rootV = find(e[0], root);
            int rootW = find(e[1], root);
            if (rootV != rootW){
                // union
                root[rootV] = rootW;
                /**
                if (height[rootV] >= height[rootW]){
                    // rootV as new root
                    root[rootW] = rootV;
                    height[rootW]++;
                }
                else{
                    root[rootV] = rootW;
                    height[rootV]++;
                }
                **/
                count--;
            }
        }
        return count;
    }
    private int find(int v, int[] root){
        while (root[v] != v) {
            root[v] = root[root[v]]; // path compression
            v= root[v];
        }
        return v;
    }
    public int countComponents2(int n, int[][] edges) {
        int count = 0;
        boolean[] visited = new boolean[n];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])(new ArrayList[n]);
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < edges.length; i++){
            int v = edges[i][0];
            int w = edges[i][1];
            adj[v].add(w);
            adj[w].add(v);
        }
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                count++;
                dfs(i, adj, visited);
            }
        }
        return count;
    }
    private void dfs(int s, List<Integer>[] adj, boolean[] visited){
        if (!visited[s]){
            visited[s] = true;
            for (int w : adj[s]) if (!visited[w]) dfs(w, adj, visited);
        }
    }
    @SuppressWarnings("unused")
    private void bfs(int s, List<Integer>[] adj, boolean[] visited){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(!q.isEmpty()){
            int v = q.remove();
            visited[v] = true;
            for (int w: adj[v]){
                if (!visited[w]) q.add(w);
            }
        }
    }
}
