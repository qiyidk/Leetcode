package topologicalSort;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * CourseScheduleII_210
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ21ÈÕ
 */
public class CourseScheduleII_210 {
 // the problem is to do a topological sort and detect cycles
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
        for (int[] pair : prerequisites) adj[pair[1]].add(pair[0]);
        int[] visited = new int[numCourses]; //0 : not visited 1: entered 2: completed
        List<Integer> res = new ArrayList<Integer>();
        int[] r = new int[numCourses];
        if (hasCycle(adj, visited, res)) return new int[]{};
        else{
            for (int i : res) r[--numCourses] = i;
            return r;
        }   
    }
    private boolean hasCycle(List<Integer>[] adj, int[] visited, List<Integer> res){
        for (int i = 0; i < adj.length; i++){            
            if (visited[i] == 0) if (hasCycle(i, adj, visited, res)) return true;
        }
        return false;
    }
    private boolean hasCycle(int cur, List<Integer>[] adj, int[] visited, List<Integer> res){
        visited[cur] = 1;
        for (int i : adj[cur]){
            if (visited[i] == 1) return true;
            if (visited[i] == 0) if (hasCycle(i, adj, visited, res)) return true;
        }
        visited[cur] = 2;
        res.add(cur);
        return false;
    }
}
