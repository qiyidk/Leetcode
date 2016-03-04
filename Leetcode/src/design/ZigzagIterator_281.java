package design;

import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Integer> v = new ArrayList<Integer>();
    private Iterator<Integer> i;
    public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        Iterator<Integer> i1 = v1.iterator();
        Iterator<Integer> i2 = v2.iterator();
        while (i1.hasNext() || i2.hasNext()){
            if (i1.hasNext()) v.add(i1.next());
            if (i2.hasNext()) v.add(i2.next());
        }
        i = v.iterator();
    }
    public int next() {
        return i.next();
    }
    public boolean hasNext() {
        return i.hasNext();
    }
}
