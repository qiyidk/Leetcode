package string;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * TextJustification_68
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ4ÈÕ
 */
public class TextJustification_68 {
    // 1. add word to a new line, put this word in a cache.
    // 2. read another word, test whether current line can accommodate this word
    // 3. yes, put this word in a cache, we can only store the index range for current line(do a full justification) to save time and space. corner case: if only have one word in this line, do a left justification
    // 4. no, finalize current line and do step one
    // 5. if current line is the last line, do a left justification.
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int start = 0; //start pointer for each line
        int left = maxWidth; // the number of positions left for current line
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            if (word.length() > left){
                int num = i - start; // num of words in current line
                StringBuilder line = new StringBuilder();
                if (num == 1){
                    // corner case, don't have padding space bewteen words
                    line.append(words[start]);
                    for (int j = line.length(); j < maxWidth; j++) line.append(" ");
                }
                else{
                    int spaces = num + left; // spaces need to be padded
                    int s = spaces / (num - 1); // space need to be padded for each word
                    int extra = spaces % (num - 1); // extra spaces for the left words
                    StringBuilder padding = new StringBuilder();
                    while(s-- > 0) padding.append(" ");
                    for (int j = start; j < i - 1; j++){
                        line.append(words[j]).append(padding);
                        if (extra-- > 0) line.append(" ");
                    }
                    line.append(words[i - 1]);
                }
                res.add(line.toString());
                start = i;
                left = maxWidth;
            }
            // insert this word
            left = left - word.length() - 1; // at least one space followed
        }
        // deal with the last line
        StringBuilder line = new StringBuilder();
        line.append(words[start]);
        for (int j = start + 1; j < words.length; j++) line.append(" ").append(words[j]); // append " " then append word instead of word + " " to avoid the situation that the words just fill up the maxWidth, in which case, we don't need a padding space after the last word
        for (int j = line.length(); j < maxWidth; j++) line.append(" ");
        res.add(line.toString());
        return res;
    }
}
