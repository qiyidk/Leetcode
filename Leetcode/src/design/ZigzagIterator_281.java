package design;

import java.util.List;

/**
 * <p>
 * ZigzagIterator_281
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ4ÈÕ
 */
public class ZigzagIterator_281 {
    //It is my second time to do this problem
    private int index = 0;
    private int listNumber = 0;
    private int maxL = 0; // max length of lists
    private Integer nextValue = null;
    private List<Integer>[] l;
    private int k; 
    @SuppressWarnings("unchecked")
    public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        this.k = 2;
        l = (List<Integer>[]) new List[k];
        l[0] = v1;
        l[1] = v2;
        // get max length
        for (int i = 0 ; i < k; i++){
            if (l[i].size() > maxL) maxL = l[i].size();
        }
        // set the first value
        setNext();
    }

    public int next() {
        if (!hasNext()) throw new RuntimeException("No such element");
        int value = nextValue;
        setNext();
        return value;
    }

    public boolean hasNext() {
        return nextValue != null;
    }
    
    private void setNext(){
        while(index < maxL){
            for (; listNumber < k; listNumber++){
                if (index < l[listNumber].size()){
                    nextValue = l[listNumber].get(index);
                    listNumber++;
                    return;
                }
            }
            //go to next index
            listNumber = 0;
            index++;
        }
        nextValue = null;
    }
}
