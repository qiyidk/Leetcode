package stack;

import java.util.Stack;

/**
 * <p>
 * LongestValidParentheses_32
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ22ÈÕ
 */
public class LongestValidParentheses_32 {
    // if and only if for any of prefixes of substring, the number of left parentheses is less than the number of right parentheses, the substring is invalid
    // every substring can be represented as i, j
    // we use j as the scan pointer, each time we find the longest valid substring that ends with j, we find the smallest i and get the maximum valid substring
    // all the parentheses pairs will be cancelled during traversal
    // consider different situations:
    // 1. (  ()()     )   all parentheses are cancelled, from left bound to j
    // 2. (  ()()(    )   
    //    (  ()(()    )
    // if not all parentheses are cancelled, the maximum valid substring starts from the last "(" that is not canceled + 1
    // 3. ()()     ) no way to find a valid substring, valid substring can only start after j
    // set left bound to j + 1.(We have to track the left bound; otherwise, we cannot deal with the situation 1)
    // proof for situation 3. if no left can be cancelled, for substring before j, right = left . if we have a prefix from t to j - 1 that satisfy left > right, so from left bound to t - 1 must be right > left, contradiction.
    // right parenthesis always cancels the closest left parenthesis, just like what stack do, therefore we can use a stack to store all the indices of "("s that are not cancelled  
    public int longestValidParentheses2(String s) {
        int max = 0;
        int n = s.length();
        Stack<Integer> left = new Stack<Integer>();
        int leftBound = 0;
        for (int j = 0; j < n; j++){
            if (s.charAt(j) == '(') left.add(j);
            else{
                if (left.isEmpty()) leftBound = j + 1;
                else{
                     int l = 0;
                     left.pop();
                     if (left.isEmpty()) l = j - leftBound + 1;
                     else l = j - (left.peek() + 1) + 1;
                     max = Math.max(max, l);
                }
            }
        }
        return max;
    }
    // dp solution
    // t
    //            i-1  i
    // (     )()  (    )  f(i) = f(i - 2) + 2
    // )     ()(  )    )  c(t) == '(' no valid substring
    // (     ()(  )    )  else f(i) = f(i - 1) + f(t - 1) + 2
    public int longestValidParentheses(String s) {
        int max = 0;
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n]; // length of the longest substring that ends with i
        for (int i = 1; i < n; i++){
            if (s.charAt(i) == ')'){
                if (s.charAt(i - 1) == '(') dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                else{
                    // (i - 1) - (t + 1) + 1 = dp[i - 1]
                    // t = i - 1 - dp[i - 1]
                    // if dp[i - 1] = 0, it should have no valid solution
                    // if dp[i - 1] = 0, t = i - 1, it must be ")" no valid result, it comforms the general form
                    // we only need to care about index < 0
                    // if no t, no valid solution
                    // if no t - 1, dp[t - 1] = 0
                    int t = i - 1 - dp[i - 1];
                    if (t >= 0 && s.charAt(t) == '('){
                        dp[i] = dp[i - 1] + 2 + (t >= 1 ? dp[t - 1] : 0);
                    }
                    else dp[i] = 0;
                }
            }
            else dp[i] = 0;
            max = Math.max(max, dp[i]); 
        }
        return max;
    }
    
    // each time, we choose a left bound, and move forward right bound to get the biggest right bound 
    // if and only if left >= right, it's possible to get a valid substring
    // 1. when we reach a point(r) that right > left, we find the biggest right bound = r - 1
    // at this situation, there's no way to find a valid left bound between the substring that can form a valid substring
    // proof: for each i between the substring, for (l, i), starts with l, therefore, for this substring, left >= right
    // for (i + 1, r - 1), since for (l, r), left = right; (i + 1,r - 1) = (l,r) - (l, i) left <= right. 
    // for (i + 1, r), left < right.
    // 2. if we cannot reach a point that right > left(left is always >= right)
    // we go backward, if and only if right >= left, the suffix substring can form a valid substring
    // then we do the same thing as condition 1. but this time, we will finally reach the end point, since left >= right, it finally will reach left = right, or left > right(no valid substring)
    public int longestValidParentheses3(String s) {
        int n = s.length();
        int l = 0;
        int r = 0;
        int diff = 0;
        int max = 0;
        while(l < n && r < n){
            if (s.charAt(l) == ')') l++;
            else{
                // find a valid left bound, start a new search
                r = l;
                while(r < n){
                    if (s.charAt(r) == '(') diff++;
                    else diff--;
                    if (diff >= 0) r++;
                    else break;
                }
                if (diff < 0){
                    // find a solution and start a new substring
                    max = Math.max(max, r - l);
                    l = r + 1;
                    diff = 0;
                }
            }
        }
        if (diff == 0) max = Math.max(max, r - l); // end with a valid substring
        else if (diff > 0){ // go backward, just the same process as forward
            r--;
            diff = 0;
            int l2 = r;
            while (r >= l && l2 >= l) {
                // find a valid right bound
                if (s.charAt(r) == '(') r--;
                else{
                    l2 = r;
                    while(l2 >= l){
                        if (s.charAt(l2) == '(') diff++;
                        else diff--;
                        if (diff <= 0) l2--;
                        else break;
                    }
                    if (diff > 0){
                        // find a solution and start a new substring
                        max = Math.max(max, r - l2);
                        r = l2 - 1;
                        diff = 0;
                    }
                }
            }
            if (diff == 0) max = Math.max(max, r - l2);// end with a valid substring
        }
        return max;
    }
}
