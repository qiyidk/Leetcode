package stack;

/**
 * <p>
 * LongestAbsoluteFilePath_388
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ30ÈÕ
 */
public class LongestAbsoluteFilePath_388 {
    // for a sub-directory, we store the length of each intermediate level in this sub-directory
    // to reduce calculation, we can just store the sum of length of previous intermediate level for level i
    public int lengthLongestPath(String input) {
        int[] len = new int[input.length() + 1];// start at level 1
        char[] c = input.toCharArray();
        int max = 0;
        int p = 0;
        while(p < c.length){
            // determine level by checking how many "\t" a sub-directory contained as a prefix
            int curLevel = 1;
            while(p + 1 < c.length && c[p] == '\\' && c[p + 1] == 't') {//notice escape character
                p += 2;
                curLevel++;
            }
            // determine the end of a sub-directory/file by checking "\n" or encounter the end of the input string
            int start = p;
            boolean isFile = false;
            while(p < c.length){
                if (c[p] == '.') isFile = true;
                if (p + 1 < c.length && c[p] =='\\' && c[p + 1] == 'n') break;//notice escape character
                p++;
            }
            len[curLevel] = len[curLevel - 1] + p - start + (isFile ? 0 : 1); 
            if (isFile && len[curLevel] > max) max = len[curLevel];
            p += 2;
        }
        return max;
    }
    public static void main(String[] args){
        new LongestAbsoluteFilePath_388().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }
}
