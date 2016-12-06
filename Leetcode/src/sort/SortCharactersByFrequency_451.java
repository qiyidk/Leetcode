package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * SortCharactersByFrequency_451
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ9ÈÕ
 */
public class SortCharactersByFrequency_451 {
    private class Node implements Comparable<Node>{
        private char c;
        private int count;
        private Node(char c, int count){
            this.c = c;
            this.count = count;
        }
        public int compareTo(Node that){
            return that.count - this.count; //reverse order
        }
    }
    public String frequencySort(String s) {
        //HashMap<Character, Node> count = new HashMap<Character, Node>();
        Node[] count = new Node[128];
        for (char c = 0; c < 128; c++) count[c] = new Node(c, 0);
        for (char c : s.toCharArray()){
            /*
            Node n = count.get(c);
            if (n == null) count.put(c, new Node(c, 1));
            else n.count++;*/
            count[c].count++;
        }
        //Collection<Node> v = count.values(); do not support Collection surprisingly
        /*
        Node[] values = new Node[count.values().size()];
        int p = 0;
        for (Node n : count.values()) values[p++] = n;
        */
        Arrays.sort(count);
        char[] res = new char[s.length()];
        int p = 0;
        for (Node n : count){
            for (int i = 0; i < n.count; i++) res[p++] = n.c;
        }
        return new String(res);
    }
    
    // we can use bucket sort and don't need extra object
    public String frequencySort2(String s){
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int max = 0;
        for (char c : s.toCharArray()){
            int n = count.get(c) == null? 0 : count.get(c);
            count.put(c, n + 1);
            if (n + 1 > max) max = n + 1;
        }
        @SuppressWarnings("unchecked")
        List<Character>[] bucket = new ArrayList[max + 1];
        for (HashMap.Entry<Character, Integer> e : count.entrySet()){
            int n = e.getValue();
            if (bucket[n] == null) bucket[n] = new ArrayList<Character>();
            bucket[n].add(e.getKey());
        }
        char[] res = new char[s.length()];
        int p = 0;
        for (int i = max; i >= 1; i--){
            if (bucket[i] == null) continue;
            for (char c : bucket[i]){
                for (int j = 0; j < i; j++) res[p++] = c;
            }
        }
        return new String(res);
    }
}
