package hashTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

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
        HashMap<Character, Node> count = new HashMap<Character, Node>();
        
        for (char c : s.toCharArray()){
            Node n = count.get(c);
            if (n == null) count.put(c, new Node(c, 1));
            else n.count++;
        }
        Collection<Node> v = count.values();
        Node[] values = new Node[v.size()];
        int p = 0;
        for (Node n : v) values[p++] = n;
        Arrays.sort(values);
        char[] res = new char[s.length()];
        p = 0;
        for (Node n : values){
            for (int i = 0; i < n.count; i++) res[p++] = n.c;
        }
        return new String(res);
    }
    public static void main(String[] args){
        String s = "tree";
        System.out.println(new SortCharactersByFrequency_451().frequencySort(s));
    }
}
