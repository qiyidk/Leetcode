package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * EvaluateDivision_399
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ12ÈÕ
 */
public class EvaluateDivision_399 {
    // 1. if the queries have some words that are unknown, we should return -1.0
    // 2. for a/b, we can know the answer by either we have the exact equations or we have a/x, x/t, ... k/b
    // or we can get b/a by the same process
    // it just like a digraph:
    // if we have a/b, b/c, it will like this, and v(a,c) = v(a,b) * v(b,c) v(c,a) = v(c,b) * v(b,a)
    // a -- b -- c
    // if we have more elements, it may like this:
    // a -- b -- c -- d
    //      |
    //      e    f -- g -- h
    // so each query is a bfs/dfs in this digraph
    @SuppressWarnings("unchecked")
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Integer> variables = new HashMap<String, Integer>(); // variable name, vertex
        List<Edge>[] adj = null;
        int index = 0;
        // initiate
        for (int i = 0; i < equations.length; i++){
            Integer v = variables.get(equations[i][0]);
            if (v == null) {
                v = index++;
                variables.put(equations[i][0], v);
            }
            Integer w = variables.get(equations[i][1]);
            if (w == null) {
                w = index++;
                variables.put(equations[i][1], w);
            }
        }
        adj = new List[variables.size()];
        for (int i = 0; i < variables.size(); i++) adj[i] = new ArrayList<Edge>();
        for (int i = 0; i < equations.length; i++){
            Integer v = variables.get(equations[i][0]);
            Integer w = variables.get(equations[i][1]);
            if (v == w) continue; // one vertex cycle
            adj[v].add(new Edge(v, w, values[i]));
            adj[w].add(new Edge(w, v, 1.0 / values[i]));
        }
        //check
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++){
            Integer v = variables.get(queries[i][0]);
            if (v == null) {
                res[i] = -1.0;
                continue;
            }
            Integer w = variables.get(queries[i][1]);
            if (w == null) {
                res[i] = -1.0;
                continue;
            }
            //res[i] = bfs(v, w, adj);
            res[i] = dfs(v, w, 1.0, adj, new boolean[adj.length]);
        }
        return res;
    }
    private class Edge{
        @SuppressWarnings("unused")
        private int v;
        private int w;
        private double ratio;
        public Edge(int v, int w, double ratio){
            this.v = v;
            this.w = w;
            this.ratio = ratio;
        }
    }
    private double dfs(int v, int target, double value, List<Edge>[] adj, boolean[] visited){
        if (v == target) return value;
        for (Edge e : adj[v]){
            int w = e.w;
            if (!visited[w]){
                visited[w] = true;
                double val = dfs(w, target, value * e.ratio, adj, visited);
                if (val != -1.0) return val;
            }
        }
        return -1.0;
    }
        
    @SuppressWarnings("unused")
    private double bfs(int v, int w, List<Edge>[] adj){
        if (v == w) return 1.0;
        boolean[] visited = new boolean[adj.length];
        Queue<Integer> vertexes = new LinkedList<Integer>();
        Queue<Double> values = new LinkedList<Double>();
        vertexes.add(v);
        values.add(1.0);
        visited[v] = true;
        while(!vertexes.isEmpty()){
            int vertex = vertexes.remove();
            double value = values.remove();
            for (Edge e : adj[vertex]){
                int ww = e.w;
                if (ww == w) return value * e.ratio;
                if (!visited[ww]){
                    vertexes.add(e.w);
                    values.add(value * e.ratio);
                    visited[ww] = true;
                }
            }
        }
        return -1.0;
    }
}
