package list;

import java.util.List;

import baseDataStructure.NestedInteger;

/**
 * <p>
 * NestedListWeightSum_339
 * </p>
 *
 * @author qiyi
 * @version 2016Äê4ÔÂ23ÈÕ
 */
public class NestedListWeightSum_339 {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    private int depthSum(List<NestedInteger> nestedList, int d){
        int sum = 0;
        for (NestedInteger e : nestedList){
            if (e.isInteger()) sum+= e.getInteger() * d;
            else sum+= depthSum(e.getList(), d + 1);     
        }
        return sum;
    }
}
