package baseDataStructure;

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
