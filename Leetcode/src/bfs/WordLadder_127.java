package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * <p>
 * WordLadder_127
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ17ÈÕ
 */
public class WordLadder_127 {
    // hit - > cig
    // if we only have cig cmt cmg, we keep the original letter i greedily, we cannot find the correct answer
    // if we think in reverse order
    // if we can get cig, we must have one of the words in the wordList(c_g, ci_, _ig)(one letter differ)
    // we can try every possible results of this pattern, if it equals to beginWord, we find the solution
    // if all the possible results fail, we try the pattern(c__, _i_, __g)(change one word, let the other two be the same), and do the same thing
    // if no word that conforms an intermediate pattern is in the wordList, there's no way to get a correct solution
    // if a word in current level has already been checked previously, there's no need to check it again
    // it is similar to a bfs
    public int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        if (!wordList.contains(endWord)) return 0;
        Queue<String> cur = new LinkedList<String>();
        Queue<String> next = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>(); // elements that have been visited in the dict, not queue
        int level = 1;
        cur.add(endWord);
        visited.add(endWord);
        wordList.add(beginWord); //beginWord can be regard as always being in the dict
        while (!cur.isEmpty()){ //cannot use null as check condition, it won't be null
            for(String word : cur){
                if (word.equals(beginWord)) return level;
                char[] str = word.toCharArray();
                for(int i = 0; i < word.length(); i++){
                    char tmp = str[i];
                    for (char j = 'a'; j <= 'z'; j++){
                        // try all possible words that differ with word by one letter
                        str[i] = j;
                        String word2 = new String(str); 
                        if (wordList.contains(word2) && !visited.contains(word2)){
                            next.add(word2);
                            visited.add(word2);// if we think it is visited when removing the word from the queue, we will make a mistake. all the words in current level should be put into the visited array before visiting next level, but when we visit the first element of next level, we only put it into the set, so that it will generate lots of duplicate words.
                            //  abcd -> bbcd, cbcd, dbcd, aacd, accd, adcd, 
                            //  remove bbcd. cbcd, dbcd are still not marked as visited
                            //  so bbcd can still generate cbcd, dbcd...
                            //  cbcd can still generate dbcd...
                        }
                    }
                    str[i] = tmp;
                }
            }
            level++;
            cur = next;
            next = new LinkedList<String>();
        }
        return 0;
    }
    
    // the time complexity is 26 * l(word) * O(n), n is the length of wordList(only the words in the dict will do a 26-loop, and each word at most do it once). 
    // The reason that this approach is faster because after each bfs, 
    // it always choose the set that has the smaller size, which means it always try to waste less computation to meet the goal
    // (the set that has the maximum size won't need to generate new words)
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        if (!wordList.contains(endWord)) return 0;
        HashSet<String> begin = new HashSet<String>();
        HashSet<String> end = new HashSet<String>();
        HashSet<String> tmp = new HashSet<String>();
        HashSet<String> visited = new HashSet<String>();
        int level = 1;
        begin.add(beginWord);
        visited.add(beginWord);
        end.add(endWord);
        visited.add(endWord);
        while(!begin.isEmpty() && !end.isEmpty()){
            if (find(wordList, begin, end, visited, tmp, level)) return level + 1;
            if (tmp.size() > end.size()){
                begin = end;
                end = tmp;
            }
            else begin = tmp;
            tmp = new HashSet<String>();
            level++;
        }
        return 0;
    }
    private boolean find(Set<String> wordList, Set<String> src, Set<String> target, Set<String> visited, Set<String> tmp, int level){
        for(String word : src){
            char[] str = word.toCharArray();
            for(int i = 0; i < word.length(); i++){
                char t = str[i];
                for (char j = 'a'; j <= 'z'; j++){
                    str[i] = j;
                    String word2 = new String(str);
                    if (target.contains(word2)) return true;
                    if (wordList.contains(word2) && !visited.contains(word2)){
                        tmp.add(word2);
                        visited.add(word2);
                    }
                }
                str[i] = t;
            }
        }
        return false;
    }
}
