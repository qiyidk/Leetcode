package list;

import java.util.List;

import baseDataStructure.NestedInteger;

/**
 * <p>
 * NestedListWeightSumII_364
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ23ÈÕ
 */
public class NestedListWeightSumII_364 {
    // for each element, its result equals to (offset + depth) * v = offset * v + depth * v
    // base = v1 + v2 + ...
    // sum = offset * v1 + offset * v2...
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res[] = new int[3];// 0: sum, 1: base, 2 depth
        res[2] = 1;
        depthSumInverse(nestedList, 0, res);
        return res[0] + res[1] * res[2];
    }
    private void depthSumInverse(List<NestedInteger> nestedList, int offset, int[] res){
        for (NestedInteger n : nestedList){
            if (n.isInteger()){
                int i = n.getInteger();
                res[0] += offset * i;
                res[1] += i;
            }
            else {
                List<NestedInteger> list = n.getList();
                depthSumInverse(list, offset - 1, res);
            }
        }
        res[2] = Math.max(res[2], 1 - offset);
    }
}
