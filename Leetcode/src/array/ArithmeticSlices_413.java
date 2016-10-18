package array;

/**
 * <p>
 * ArithmeticSlices_413
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ12ÈÕ
 */
public class ArithmeticSlices_413 {
    // array may be consist of many separate area of arithmetic slices
    //      a1  a2  a3  a4  a5   a6  a7
    // diff:  a   a   b   b    b   c   c
    // an arithmetic slice cannot cross different areas
    // so the question converts into find each area that has the same difference, and then do a simple math calculation for each area
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int lastDiff = A[1] - A[0];
        int start = 0;
        int v = 0;
        for (int i = 2; i < A.length; i++){
            if (A[i] - A[i - 1] == lastDiff) continue;
            else{
                v += getNum(start, i - 1);
                start = i - 1;
                lastDiff = A[i] - A[i - 1];
            }
        }
        // deal with the last area
        v += getNum(start, A.length - 1);
        return v;
    }
    private int getNum(int start, int end){
        int n = end - start + 1;
        //if (n < 3) return 0; not necessary in this case
        //fix first 2 element, we have n - 2 choices, fix first 3 elements, we have n - 3 choices,...
        //num = (n - 2) + (n - 3) + ... + 1
        return (n - 1) * (n - 2) / 2;
    }
}
