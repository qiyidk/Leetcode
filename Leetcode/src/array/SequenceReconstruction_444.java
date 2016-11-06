package array;

import java.util.HashMap;

/**
 * <p>
 * SequenceReconstruction_444
 * </p>
 *
 * @author qiyi
 * @version 2016Äê11ÔÂ4ÈÕ
 */
public class SequenceReconstruction_444 {
    // if seqs can reconstruct less than or more than one shortest common supersequence, must return false
    // if unique, we just need check whether it is equal to the org
    // therefore the question is actually check whether seqs can form unique shortest common supersequence
    // but it will be hard and inefficient if we try to generate all possible supersequences
    // if org is the unique shortest common supersequence
    // it will like this:
    // org:     1234567891011
    // seqs:1   12 456
    //      2   12345 
    //      3         7 9
    //      4          8 1011
    // 1. all seqs are subsequence of org   => supersequence
    // 2. all numbers in org will appear in some sequences of seqs => shortest supersequence
    // 3. any consecutive number pairs in org must appear in one or some sequences of seqs, therefore the order of adjacent number of org can be fixed, see example above, 6,7 pair are not fixed => unique supersequence
    // note that no duplicate in org; otherwise, a pair 5,6 can be appeared in org twice, if the seq has 5,6, we also don't know which 5,6 is matched.
    // if 3 satisfied, 2 must be satisfied
    // corner case: org only have one number, 2 must be checked independently
    // to deal with this, we add a dummy head
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        boolean[] cover = new boolean[org.length]; // whether (i, i+1) of org has been covered, from -1 to n - 1
        HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>();
        for (int i = 0; i < org.length; i++) indexes.put(org[i], i); 
        for (int[] seq: seqs){
            int last = -1;
            for (int n : seq){
                Integer index = indexes.get(n);
                if (index == null || index <= last) return false;
                if (index == last + 1) cover[last + 1] = true; // note that there's one offset for cover
                last = index;
            }
        }
        for (int i = 0; i < org.length; i++) if (!cover[i]) return false;
        return true;
    }
}
