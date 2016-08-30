package topologicalSort;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * CourseSchedule_207
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ21ÈÕ
 */
public class CourseSchedule_207 {
    // the problem is to detect cycles in the graph   
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[numCourses];
        // don't forget initiation
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
        for (int[] pair : prerequisites) adj[pair[1]].add(pair[0]);
        int[] visited = new int[numCourses]; //0 : not visited 1: entered 2: completed
        return hasCycle(adj, visited);    
    }
    private boolean hasCycle(List<Integer>[] adj, int[] visited){
        for (int i = 0; i < adj.length; i++){            
            if (visited[i] == 0) if (hasCycle(i, adj, visited)) return false;
        }
        return true;
    }
    private boolean hasCycle(int cur, List<Integer>[] adj, int[] visited){
        visited[cur] = 1;
        for (int i : adj[cur]){
            if (visited[i] == 1) return true;
            if (visited[i] == 0) if (hasCycle(i, adj, visited)) return true;
        }
        visited[cur] = 2;
        return false;
    }
}
