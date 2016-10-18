package matrix;

import java.util.List;

/**
 * <p>
 * ValidWordSquare_422
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ18ÈÕ
 */
public class ValidWordSquare_422 {
    public boolean validWordSquare(List<String> words) {
        for (int i = 0; i < words.size(); i++){
            String word = words.get(i); 
            for (int j = 0; j < word.length(); j++){
                if (words.size() < j + 1 || words.get(j).length() < i + 1 || word.charAt(j) != words.get(j).charAt(i)) return false;
            }
        }
        return true;
    }
}
