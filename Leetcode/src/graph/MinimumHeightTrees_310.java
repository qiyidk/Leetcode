package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * MinimumHeightTrees_310
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ10ÈÕ
 */
public class MinimumHeightTrees_310 {
    // For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.
    // Despite its triviality, let design a algorithm to find them.
    // Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is very easy to get the idea of two pointers. One from each end and move at the same speed. When they meet or they are one step away, (depends on the parity of n), we have the roots we want.
    // This gives us a lot of useful ideas to crack our real problem.
    // For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
    // It is easy to see that the last two pointers are from the two ends of the longest path in the graph.
    // The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!
    
    // the height of the tree is the distance from the farthest leaf to the root.
    // for a simple path, the best root is the middle one (or two, depends on whether the length is even or odd)
    // for a graph, the best root is the middle one (or two, depends on whether the length is even or odd), for the middle node, the distance from all the farthest leaves to the middle node will at most differ by 1, if the distance is the same, we have only one best root, otherwise we have 2 best root
    // collect all the leaves of degree 1, and go one step further(they have the same speed) and then we can get rid of those leaves, and find new leaves and keep going until we have only one or two leaves, which are the best root.
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<Integer>();
        if (n == 1) {
            res.add(n - 1);
            return res;
        }
        // maintain an adj list to calculate the degree of nodes
        List<Set<Integer>> adj = new ArrayList<Set<Integer>>(n);
        for (int i = 0; i < n; i++) adj.add(new HashSet<Integer>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // leaves of degree 1
        List<Integer> leaves = new ArrayList<Integer>();
        // find all the leaves
        for (int i = 0; i < n; i++) if (adj.get(i).size() == 1) leaves.add(i);
        while (n > 2) {
            // delete all leaves, go to a deeper level
            n = n - leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();
            // get new leaves
            for (int i : leaves) {
                for (int j : adj.get(i)){
                    // remove the connection between the old leaves and the nodes that the old leaves connected to 
                    adj.get(j).remove(i);
                    if (adj.get(j).size() == 1) newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
