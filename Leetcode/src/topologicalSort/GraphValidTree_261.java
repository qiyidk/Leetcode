package topologicalSort;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * GraphValidTree_261
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ29ÈÕ
 */
public class GraphValidTree_261 {
    // ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.¡±
    // first we need to check whether there's a cycle, second we need to check whether all nodes are connected
    public boolean validTree2(int n, int[][] edges) {
        if (n == 0) return true;
        if (edges.length != n - 1) return false;// if edge != vertex - 1. cannot form a tree
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] visited = new int[n]; // 0 : not visited 1: entered, but not completed 2 : completed
        if (hasCycle(0, -1, adj, visited)) return false;
        for (int v : visited) if (v == 0) return false;
        return true;
    }
    private boolean hasCycle(int vertex, int parent, List<Integer>[] adj, int[] visited){
        visited[vertex] = 1;
        for (int v : adj[vertex]){
            if (visited[v] == 1 && parent != v) return true;// a->b and b->a are not a cycle(for undirected graph)
            if (visited[v] == 0) {
                if (hasCycle(v, vertex, adj, visited)) return true;
            }
        }
        visited[vertex] = 2;
        return false;
    }
    // we can also use a uf method, if edge == vertex - 1 && we only have one root node(not a forest), we have a tree. (don't need to detect cycle)
    // actually, even if we don't use uf, we can also avoid detecting cycle by checking the number of edges
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return true;
        if (edges.length != n - 1) return false;// if edge != vertex - 1. cannot form a tree
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        dfs(0, adj, visited);
        for (boolean v : visited) if (!v) return false;
        return true;
    }
    private void dfs(int vertex, List<Integer>[] adj, boolean[] visited){
        visited[vertex] = true;
        for (int v : adj[vertex]){
            if (!visited[v]) dfs(v, adj, visited);
        }
    }
}
