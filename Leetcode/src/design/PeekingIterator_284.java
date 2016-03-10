package design;

import java.util.Iterator;

/**
 * <p>
 * PeekingIterator_284
 * </p>
 *
 * @author qiyi
 * @version 2016��3��10��
 */
// ������������null������һ���Ƿ�Ϊ�յ�booleanֵ����걸
public class PeekingIterator_284 implements Iterator<Integer>{
    private Integer peek;
    private Iterator<Integer> iterator;
    public PeekingIterator_284(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (this.iterator.hasNext()) peek = this.iterator.next();
        else peek = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer p = peek;
        if (this.iterator.hasNext()) peek = this.iterator.next();
        else peek = null;
        return p;
    }

    @Override
    public boolean hasNext() {
        return peek != null;
    }
}
