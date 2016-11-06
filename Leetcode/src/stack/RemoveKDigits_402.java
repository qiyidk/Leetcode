package stack;

/**
 * <p>
 * RemoveKDigits_402
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ1ÈÕ
 */
public class RemoveKDigits_402 {
    // if we pick elements from left to right, we need always choose the smallest valid element, so that we can get a smaller number
    // we need make sure the remaining numbers have enough digit to pick
    // if we have picked t nums, we need to pick num.length() - k - t nums, the biggest index of element that we can pick must satisfy: num.length() - index >= num.length() - k - t => index <= k + t
    // if there're many ties in the valid range, we pick the smallest index, so that we can have more choice to pick smaller number
    // one thing left is to find a way to pick the smallest index in a certain range efficiently
    // it can be done in O(n ^ 2) time, traverse from each start point and do a loop and store corresponding value
    // we choose to not store all possible combination, just traverse and find min value each time.(not so good, so we can try to find a better solution)
    // in total it will take k + nk time
    
    public String removeKdigits2(String num, int k) {
        char[] res = new char[num.length() - k];
        char[] s = num.toCharArray();
        int p = 0; // index for s
        int r = 0; // index for result
        for (int pick = 0; pick < num.length() - k; pick++){
            int min = 10;
            for (int i = p; i <= k + pick; i++){
                if (s[i] - '0' < min) {
                    min = s[i] - '0';
                    p = i + 1; // next position can be picked
                }
            }
            if (r != 0 || min != 0) res[r++] = (char)(min + '0');
        }
        if (r == 0) return "0";
        else return new String(res, 0, r);
    }
    
    // consider if we only need remove one digit, we should pick the first element that is greater than next element, if no element is greater than next element, remove the last one
    // 12335421   remove 5
    // 1233421
    // all the character before 5 are all in non-descending order, we only need to check stack top and the characters after it
    // all characters can at most push into stack and pop out of stack once, it will take O(n) time
    public String removeKdigits(String num, int k) {
        char[] stack = new char[num.length()];
        int p = -1; // stack top
        for (int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while(k > 0 && p != -1 && stack[p] > c){
                // remove stack[p]
                p--;
                k--;
            }
            stack[++p] = c;
        }
        // if all elements left are in non-descending order, remove element from end to front
        p -= k;
        if (p == -1) return "0";
        int start = 0;
        while(start <= p && stack[start] == '0') start++;
        if (start > p) return "0";
        return new String(stack, start, p - start + 1);
    }
}
