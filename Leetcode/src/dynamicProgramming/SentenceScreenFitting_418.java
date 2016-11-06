package dynamicProgramming;

/**
 * <p>
 * SentenceScreenFitting_418
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ31ÈÕ
 */
public class SentenceScreenFitting_418 {
    // we can use an array to maintain how many spaces needed to fix words from 0 to i
    // word1 word2 word3   f(2)
    // word1               f(0)
    //       word2 word3   f(2) - f(0) - 1
    // let f(-1) = -1
    // word1 word2 word3   f(2) - f(-1) - 1
    // word1               f(0) - f(-1) - 1
    //       word2 word3   f(2) - f(0) - 1
    // f(i, j) = f(j) - f(i - 1) - 1
    // TLE O(n(logm + m / l))
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        if (n == 0) return 0;
        int[] spaces = new int[n];
        spaces[0] = sentence[0].length();
        for (int i = 1; i < spaces.length; i++){
            spaces[i] = spaces[i - 1] + 1 + sentence[i].length();
        }
        int t = 0;
        int row = 1;
        int left = cols;// spaces left in current row
        int i = 0; // current word index from 0 .. n - 1
        while (row <= rows){
            int low = i == 0 ? -1 : spaces[i - 1];
            int l = i;
            int r = n - 1;
            // do a binary search
            while(l <= r){
                int mid = (l + r) / 2;
                if (spaces[mid] - low - 1 == left) {
                    r = mid;
                    break;
                }
                else if (spaces[mid] - low - 1 > left) r = mid - 1;
                else l = mid + 1;
            }
            i = (r + 1) % n;
            if (r == n - 1) {
                t++; // finish one round
                left -= (spaces[r] - low - 1) + 1;
            }
            else {
                // cannot fix any more word
                row++;
                left = cols;
            }   
        }
        return t;
    }
    // if we ignore the limitation of each row, we put all words in this "standardized" way:
    // word1 word2 word3 word1 word2 word3 ... 
    // number of whole sentences = size(m * n) / l(length of standardized sentence) 
    // now consider the row limitation, each row we may waste some spaces
    // number of whole sentences = (size(m * n) - waste) / l(length of standardized sentence) 
    // we only need to calculate the bias(waste) to standardized word sequence
    // if the length of sentence is l, for each row, only count % l need to be considered. count / l will be entire sentences(no waste).
    // to check which index is the last index can be used in current row, we don't need to do a binary search, we can maintain an array of length = length of all words, each length will have a corresponding attribute
    // for count % l part, we only need to know how many spaces have to skip(wasted)
    // and we decrement waste spaces from the count
    // then we start counting in a new row
    // abcde dfeffff ffsa |abcde dfeffff ffsa |...
    // ^current position
    //  -----                  waste 0
    //  ------                 waste 1
    //  -----------            waste 6
    //  ------------           waste -1(save one space)
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int l = s.length();
        int[] waste = new int[l];
        waste[l - 1] = 0;
        for (int i = 0; i < l - 1; i++){
            if (s.charAt(i + 1) == ' ') waste[i] = -1;
            else {
                int pre = i == 0 ? 0 : waste[i - 1]; 
                waste[i] = pre + 1;
            }
        }
        int count = 0;
        int p = -1; // last position
        for (int i = 1; i <= rows; i++){
            count += cols; // have a new row, move forward cols positions
            p = (p + cols) % l; // current last position
            count -= waste[p]; // decrement waste spaces
            p -= waste[p]; // go backward last position to the closest previous space
        }
        return count / l;
    }
    
    public static void main(String[] args){
        String[] sentence = {"a","bcd","e"};
        int rows = 3;
        int cols = 6;
        System.out.println(new SentenceScreenFitting_418().wordsTyping(sentence, rows, cols));
    }
    
}
