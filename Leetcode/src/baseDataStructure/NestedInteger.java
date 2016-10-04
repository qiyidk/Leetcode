package baseDataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * NestedInteger
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ5ÈÕ
 */
public class NestedInteger {
    public boolean isInteger;
    public Integer i;
    public List<NestedInteger> list;
    
    public NestedInteger(){
        isInteger = false;
        list = new ArrayList<NestedInteger>();
    }
    public NestedInteger(int value){
        i = value;
    }
    public Integer getInteger(){
        if (isInteger) return i;
        else return null;
    }
    public boolean isInteger(){
        return isInteger;
    }
    public List<NestedInteger> getList(){
        if (!isInteger) return list;
        else return null;
    }
    public void setInteger(int value){
        isInteger = true;
        i = value;
        list = null;
    }
    public void add(NestedInteger ni){
        isInteger = false;
        if (list == null) list = new ArrayList<NestedInteger>();
        list.add(ni);
    }
}
