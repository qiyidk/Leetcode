package topologicalSort;

/**
 * <p>
 * AlienDictionary_269
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ7ÈÕ
 */
public class AlienDictionary_269 {
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[26][26];
        boolean[] alphabeta = new boolean[26];
        if (words.length == 0) return "";
        char[] lastWord = words[0].toCharArray();
        for (int i = 0; i < lastWord.length; i++) alphabeta[lastWord[i] - 'a'] = true;
        for (int i = 1; i < words.length; i++){
            char[] c = words[i].toCharArray();
            int j = 0;
            while(j != lastWord.length){
                if (j == c.length) return ""; //illegal
                else if (c[j] == lastWord[j]) j++;
                else{
                    adj[lastWord[j] - 'a'][c[j] - 'a'] = true;
                    break;
                }
            }
            for (int k = 0; k < c.length; k++) alphabeta[c[k] - 'a'] = true;
            lastWord = c;
        }
        StringBuilder res = new StringBuilder();
        int[] visited = new int[26]; //0: not visited 1: entered into but not completed 2: completed
        for (int i = 0; i < 26; i++){
            if (alphabeta[i] && (visited[i] == 0)) {
                if (dfs(res, i, visited, adj)) return "";
            }
        }
        return res.reverse().toString();
    }
    // if detect a loop, return true
    private boolean dfs(StringBuilder res, int node, int[] visited, boolean[][] adj){
        visited[node] = 1;
        for (int i = 0; i < 26; i++){
            if (adj[node][i]){
                if (visited[i] == 0){
                    if (dfs(res, i, visited, adj)) return true;
                }
                else if (visited[i] == 1) return true; //has a loop
            }
        }
        visited[node] = 2;
        res.append((char)(node + 'a'));
        return false;
    }
    public static void main(String[] args){
        String[] words = new String[]{"wrt","wrf","er","ett","rftt"};
        System.out.println(new AlienDictionary_269().alienOrder(words));
    }
}
