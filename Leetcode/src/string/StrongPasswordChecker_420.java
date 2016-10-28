package string;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <p>
 * StrongPasswordChecker_420
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ28ÈÕ
 */
public class StrongPasswordChecker_420 {
    // in order to make less operation, we try to fix more violation at one time
    // we can deal with duplicate by adding new characters, or removing or modifying characters from the middle of consecutive characters
    // note that each insert can eliminate two duplicates; modification can eliminate 3 duplicates; remove only eliminate one character. it can been seen that modification will be more efficient
    // note that if we have many separate consecutive characters, they must be handled separately
    // corner case: "aabaabaabaabaabaabaaa" "aabaabaabaabaabaabaaaa" "aabaabaabaabaabaabaaaaa"
    // if length > 20 or < 6, we must at least remove 20 - l / insert 6 - l, and parts of duplicate can be fixed without extra operations, in which case, modification will not be the best solution
    // if > 20, remove will be better, but we should also take fully advantage of removing(the same with insertion)
    // if has aaaaaaaaa, aaaa, aaa, we can remove 1 characters, if length of consecutive characters = l; after removing t, it can save l / 3 - (l - 1) / 3 operaions. for each removing the best saving is save one operation. when l % 3 == 0, it can save 1 operation. you can use l % 3 = m and always use the group which has smallest m to remove 
    // if m1 = 6 m2 = 7, the first choice will be m1 = 6, then m1 = 5, m2 =7; then choose m2 = 7...
    //                       length             missingType         duplicate       
    // insert                ++                 --                  2
    // modification          0                  --                  3
    // remove                --                 0                   1
    
    public int strongPasswordChecker(String s) {
        int step = 0;
        boolean low = false;
        boolean upper = false;
        boolean digit = false;
        for (char c : s.toCharArray()){
            if (c >= '0' && c <= '9') digit = true;
            else if (c >= 'a' && c <= 'z') low = true;
            else if (c >= 'A' && c <= 'Z') upper = true;
        }
        int missingType = (low ? 0 : 1) + (upper ? 0 : 1) + (digit ? 0 : 1);
        int start = 0;
        int l = s.length();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
                public int compare(Integer i, Integer j){
                    return i % 3 - j % 3;
                }
            });
        
        for (int i = 1; i < s.length() + 1; i++){
            if (i != s.length() && s.charAt(i) == s.charAt(i - 1)) continue;
            else{
                int d = i - start;
                if (d >= 3) pq.add(d);
                start = i;
            }
        }
        if (l > 20){
            // remove
            int remove = l - 20;
            step += remove;
            while(!pq.isEmpty() && remove != 0){
                int d = pq.remove();
                d--;
                remove--;
                if (d >= 3) pq.add(d);
            }
        }
        else if (l < 6){
            // insert
            int insert = 6 - l;
            step += insert;
            missingType -= insert;
            while(!pq.isEmpty() && insert != 0){
                int d = pq.remove();
                d -= 2;
                insert--;
                if (d >= 3) pq.add(d);
            }
        }
        while(!pq.isEmpty()){
            int d = pq.remove();
            step += d / 3;
            missingType -= d / 3;
        } 
        if (missingType > 0) step += missingType;
        return step;
    }
}
