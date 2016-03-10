package greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * RemoveDuplicateLetters_316
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ9ÈÕ
 */
public class RemoveDuplicateLetters_316 {
    // It is my second time to do this problem(saved)
    // If we always calculate and pick the best choice each time, we will have to traverse the string back and forth. So we don't need to get the best choice each time, we can just pick a relatively better choice and refine it during the traversal of the string. Since each element in the final result can only appear once, our refinement will happen in the 26 length stack, not the whole string.
    // we can maintain a stack to represent the elements we've already picked, if we have a better choice than picking the element at the stack top, we can discard the element at the stack top and keep checking whether the picking element is better than the current stack top until the element at the stack top is the best choice so far
    // Then we need to determine what is a better choice. If the current visited element is smaller than stack top and the stack top character still have inventory in the remaining string, then we find a better choice and we can discard the old one.
    // If one character is already in the stack, and we encounter the same character, picking it will not better than picking the first one, because if we pick the first one, we will have more choices to pick other elements
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26]; // the number of each character
        char[] stack = new char[26]; // result stack
        boolean[] inStack = new boolean[26]; //whether each character bas already been in the stack
        int top = -1; // stack top
        for (int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (inStack[c - 'a']) count[c - 'a']--;
            else{
                // check whether we can hava a better choice and discard elements that are not the best choices
                while(top != -1 && count[stack[top] - 'a'] > 0 && stack[top] > c){
                    // discard stack top
                    inStack[stack[top] - 'a'] = false;
                    top--;
                }
                // push current element in the stack
                inStack[c - 'a'] = true;
                count[c - 'a']--;
                top++;
                stack[top] = c;
            }
        }
        return new String(stack, 0, top + 1);
    }
    
    
    
    
    // acbabcde
    // each time we try to pick the smallest letter as long as we still have inventory of current letter after it
    // Since if we still have inventory for that letter, that letter won't be missed. And as long as we pick the smallest letter that we can pick, the final result will always the smallest. Since every time we get a smallest letter for a higher digit, it must be smaller than other choices no matter how we choose the other letters.
    // There is still one problem we need to deal with, what if we have many smallest letters, pick the first one will be always better, because we can have more choices for the remaining picking, which may lead to a better result. So we also need to keep track of the index of the min value.
    // We can also maintain a list to get the smallest character we can pick so far to make the process be able to terminate much earlier
    // We also need to calculate how many letters of each character we have, here we can just maintan the last index of each character
    // we also need to mark which character we've already picked, after each picking, we can set the last index to -1
    // The biggest problem for this algorithm is we have to retraverse many elements of the string if the min value we picked is far from  current visited element
    public String removeDuplicateLetters2(String s) {
        int[] lastIndex = new int[26];//last index of each character
        Arrays.fill(lastIndex, -1);
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++){
            lastIndex[c[i] - 'a'] = i;
        }
        Queue<Character> availableCharater = new LinkedList<Character>();// the characters that have not been picked
        for (int i = 0; i < 26; i++){
            if (lastIndex[i] != -1) availableCharater.add((char)(i + 'a'));// the availableCharater will maintain acsending order
        }
        int index = 0; // next index of array we can put value in
        char min = (char)('z' + 1); // minimum character we traversed so far
        int mIndex = -1; // index of minimum character so far
        for (int i = 0; i < c.length; i++){
            if (lastIndex[c[i] - 'a'] != -1){
                if (c[i] == availableCharater.peek()){
                    // best element we can pick
                    c[index++] = c[i];
                    availableCharater.remove(c[i]);
                    lastIndex[c[i] - 'a'] = -1;
                    min =  (char)('z' + 1);
                    if (availableCharater.size() == 0) break;
                } 
               else {
                    if (c[i] < min){
                        min = c[i];
                        mIndex = i;
                    }
                    if (lastIndex[c[i] - 'a'] == i){
                        // pick the min value so far
                        c[index++] = min;
                        availableCharater.remove(min);
                        lastIndex[min - 'a'] = -1;
                        min =  (char)('z' + 1);
                        i = mIndex;
                   }
               }
            }
        }
        return new String(c, 0, index);
    }
}
