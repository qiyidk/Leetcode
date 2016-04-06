package design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import baseDataStructure.NestedInteger;

/**
 * <p>
 * FlattenNestedListIterator_341
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ5ÈÕ
 */

public class FlattenNestedListIterator_341 implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> stack;
    private Iterator<NestedInteger> cur;
    private Integer nextValue;
    public FlattenNestedListIterator_341(List<NestedInteger> nestedList) {
        stack = new Stack<Iterator<NestedInteger>>();
        cur = nestedList.iterator();
        setNextValue();
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        int n = nextValue;
        setNextValue();
        return n;
    }

    @Override
    public boolean hasNext() {
        return nextValue != null;
    }
    
    private void setNextValue(){
        while(true){
            while(!cur.hasNext() && !stack.isEmpty()){
                cur = stack.pop();
            }
            if (!cur.hasNext()) {//reach the end
                nextValue = null;
                return;
            }
            NestedInteger next = cur.next();
            //try to find a single integer
            while (!next.isInteger()){
                List<NestedInteger> newList = next.getList();
                if (newList.isEmpty()) {
                    //ignore this list
                    if (cur.hasNext()) next = cur.next();
                    else break;// cur doesn't have next, move to next cur
                }
                else{
                    stack.push(cur);
                    cur = newList.iterator();
                    next = cur.next();    
                }
            }
            if (next.isInteger()) {
                nextValue = next.getInteger(); 
                return;
            }
        }
    }
    
    public static void main(String[] args){
        List<Integer> l = new ArrayList<Integer>();
        FlattenNestedListIterator_341 i = new FlattenNestedListIterator_341(null);
        while (i.hasNext()) l.add(i.next());
        for(int j : l) System.out.println(j);
    }
}