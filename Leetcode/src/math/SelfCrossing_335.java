package math;

/**
 * <p>
 * SelfCrossing_335
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ30ÈÕ
 */
public class SelfCrossing_335 {
    // more concise one: first spiral outward(if any), 
    // then check the second move of the inward spiral(it may cause crossing), then spiral inward
    public boolean isSelfCrossing(int[] x) {
        if (x.length < 4) return false;
        int p = 2;
        while (p < x.length && x[p] > x[p - 2]) p++;
        // check p + 1
        // --------|
        // |-<---  |
        //    |----|
        //
        // ----------|
        // |         |
        // |--<--  --|
        //
        // ---->---|
        //    |----|
        if (p + 1 >= x.length) return false;
        if (
            x[p + 1] >= x[(p + 1) - 2] // go beyond the outer boundary
            ||
            (p >= 3 && x[p + 1] + x[(p + 1) - 4] >= x[(p + 1) - 2]  // if exist inner boundary, check it, too. 
            &&
            x[p] + (p >= 4 ? x[p - 4] : 0) >= x[p - 2])
            )
            return true; 
        p = p + 2;
        while (p < x.length && x[p] < x[p - 2]) p++;
        return p != x.length;
    }
    // it has two ways of moving
    // 1: each time moves to a bigger cycle
    // 2: each time moves to a smaller cycle
    // if it starts moving in the second way, it cannot go back to the first way
    // in the first way, there's no way to cross
    // in the second way, we only need to care about the closest boundary, which is generated in the last cycle
    public boolean isSelfCrossing2(int[] x) {
        if (x.length < 4) return false;
        int mode = 1; 
        int up = x[0]; 
        int left = -x[1];
        int right = left + x[3];
        int down = up - x[2];
        if (x[2] <= x[0]) mode = 2;
        else if (x[3] <= x[1]) {
            if (4 < x.length){
                if (x[4] + x[1] >= x[3] && x[3] >= x[1]) return true;
            }
        }
        if (x[3] >= x[1] && x[0] >= x[2]) return true;
        for (int i = 4; i < x.length; i++){
            switch(i % 4){
                case 0 : if (mode == 2 && down + x[i] >= up) return true; up = down + x[i]; break;
                case 1 : if (mode == 2 && right - x[i] <= left) return true; left = right - x[i]; break;
                case 2 : if (mode == 2 && up - x[i] <= down) return true; down = up - x[i]; break;
                case 3 : if (mode == 2 && left + x[i] >= right) return true; right = left + x[i]; break;
            }
            if (mode != 2 && x[i] <= x[i - 2]) {
                mode = 2;
                if (i + 1 < x.length){
                    if (x[i + 1] + x[i + 1 - 4] >= x[i + 1 - 2] && x[i] + x[i - 4] >= x[i - 2]) return true;
                }
            }
        }
        return false;
    }
}
