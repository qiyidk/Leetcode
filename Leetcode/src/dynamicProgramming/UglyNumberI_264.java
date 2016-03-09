package dynamicProgramming;

/**
 * <p>
 * UglyNumberI_264
 * </p>
 *
 * @author qiyi
 * @version 2016Äê3ÔÂ8ÈÕ
 */
public class UglyNumberI_264 {
    // It is my second time to do this problem(saved)
    // If we traverse all the numbers, at least we will need nlogn time
    // Also, Since lots of numbers are not ugly numbers, we cannot traverse numbers and count the number of ugly numbers, it will waste a lot of time
    // new ugly number can only be generated from old one.
    // what we need is to keep track of the most recently old ones we just used, and try to find a new one from them.
    // the new one may come from three ways: old one1 * 2 or old one2 * 3, or old one3 * 5, and then we choose the smallest one.
    // nnnnn (old one3) nnn (old one 2) nnnn (old one 1) nnn, what we need is to track the three intermediate value, when using one of them, update it to the next available number
    // Since we need to know what it is the next available number, we have to keep track of all the ugly numbers we find so far.
    public int nthUglyNumber(int n) {
        if (n <= 5) return n;
        int[] ugly = new int[n];
        ugly[0] = 1;
        int two = 0; //index of the next element that is multiplied by 2
        int three = 0; //index of the next element that is multiplied by 3
        int five = 0; //index of the next element that is multiplied by 5
        int index = 1;
        while (index < n){
            int min = Math.min(Math.min(ugly[two] * 2, ugly[three] * 3), ugly[five] * 5);
            if (ugly[two] * 2 == min) two++;
            if (ugly[three] * 3 == min) three++;
            if (ugly[five] * 5 == min) five++;
            ugly[index++] = min;
        }
        return ugly[n - 1];
    }
}
