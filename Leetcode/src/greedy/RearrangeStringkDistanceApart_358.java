package greedy;

import java.util.PriorityQueue;

/**
 * <p>
 * RearrangeStringkDistanceApart_358
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ19ÈÕ
 */
public class RearrangeStringkDistanceApart_358 {

    // let the number of unique characters is m; if m < k, there's no way to rearrange
    // Therefore when we try to fill the new array, we should try to use the character that has most frenquency so far(so that we can have more unique characters left)
    // each time, we need to find the valid character (at least k distance apart) that has the most characters left
    // to get the most frequency character, we can use a priority queue.
    // to get current valid character, in each round(at most have k characters), after picking a character, we remove that character from pq(it is invalid now), and after each round, we record last k characters. When we start next round, we put them into pq one by one(after picking one character, a new character that has picked during the last k pickings was valid again).
    private class Node implements Comparable<Node>{
        private int count;
        private char c;
        public int compareTo(Node n){
            return n.count - this.count;// note that the pq is min heap.
        }
        public Node(int count, char c){
            this.count = count;
            this.c = c;
        }
    }
    public String rearrangeString(String str, int k) {
        if (k == 0) return str;// notice corner case k == 0
        char[] c = str.toCharArray();
        char[] res = new char[c.length];
        int[] count = new int[26];
        for (char ch : c) count[ch - 'a']++;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (int i = 0; i < 26; i++){
            if (count[i] != 0){
                Node node = new Node(count[i], (char)(i + 'a'));
                pq.add(node);
            }
        }
        int p = 0;
        char[] kSet = new char[k];
        for(int i = 0; i < k && p < res.length; i++,p++){
            if (pq.size() == 0) return "";// cannot arrange a new character
            Node node = pq.remove();
            kSet[i] = node.c;
            count[node.c - 'a']--;
            res[p] = node.c;
        }
        while(p < res.length){
            for(int i = 0; i < k && p < res.length; i++,p++){
                if (count[kSet[i] - 'a'] != 0){
                    Node n = new Node(count[kSet[i] - 'a'], kSet[i]);
                    pq.add(n); // add a new valid node
                }
                if (pq.size() == 0) return "";// cannot arrange a new character
                Node node = pq.remove();
                kSet[i] = node.c;
                count[node.c - 'a']--;
                res[p] = node.c;
            }
        }
        return new String(res);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(new RearrangeStringkDistanceApart_358().rearrangeString("abb", 2));
    }

}
