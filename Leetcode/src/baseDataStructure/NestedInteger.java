package baseDataStructure;

import java.util.List;

/**
 * <p>
 * NestedInteger
 * </p>
 *
 * @author qiyi
 * @version 2016��4��5��
 */
public class NestedInteger {
    public boolean isInteger;
    public Integer i;
    public List<NestedInteger> list;
    
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
}
