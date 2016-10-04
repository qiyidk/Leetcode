package string;

import baseDataStructure.NestedInteger;

/**
 * <p>
 * MiniParser_385
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ3ÈÕ
 */
public class MiniParser_385 {
    // a NestedInteger has an attribute to represent it holds a list or a number
    // if a NestedInteger hold a list, this list is a list<NestedInteger>, it cannot have integer value directly, if it want to have integer value, the value should be a NestedInteger holds one single integer
    // within one NestedInteger
    // each square bracket represents a start of a new NestedInteger(list)
    // each comma represents a start of new member in the same NestedInteger list
    // s = "[123,[456,[789]]]" NestedInteger holds a list(123,[456,[789]])
    // s = "123,[456,[789]],456" invalid
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s)); // only holds one single number
        else {// holds a list
            NestedInteger ni = new NestedInteger();
            deserialize(ni, s, 1);
            return ni;
        }
    }
    // construct NestedInteger that has a list in it
    // return next position to be visited
    private int deserialize(NestedInteger ni, String s, int p) {
        int v = 0;
        int start = p;
        int sign = 1;
        while(p < s.length()){
            switch(s.charAt(p)){
                case '[': NestedInteger n = new NestedInteger(); ni.add(n); p = deserialize(n, s, p + 1); break;
                // finalize the last v if have v, note that the list may like this : [[],[],[]]
                case ']': if (p != start && s.charAt(p - 1) != ']') ni.add(new NestedInteger(v * sign)); return p + 1; 
                case '-': sign = -1; p++; break;
                case ',': if (p != start && s.charAt(p - 1) != ']') ni.add(new NestedInteger(v * sign)); v = 0; sign = 1; p++; break;// finalize v and reset v
                default: v = v * 10 + (s.charAt(p) - '0'); p++;
            }
        }
        return p;
    }
}
