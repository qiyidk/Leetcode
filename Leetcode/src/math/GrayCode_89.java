package math;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * GrayCode_89
 * </p>
 *
 * @author qiyi
 * @version 2016��11��11��
 */
public class GrayCode_89 {
    //gray code       binary
    //000             000
    //001             001
    //011             010
    //010             011
    //110             100
    //111             101
    //101             110
    //100             111
    //��������->�����루���룩��������λ�����ν�ÿһλ���ұ�һλ���XOR������Ϊ��Ӧ�������λ��ֵ�������һλ���䣻
    /*������-���������루���룩��unsigned int grayToBinary(unsigned int num)
    {
    unsigned int mask;
    for (mask = num >> 1; mask != 0; mask = mask >> 1)
    {
        num = num ^ mask;
    }
    return num;
    }*/
    // It is my second time to do this problem
    public List<Integer> grayCode(int n) {
        // to get a gray code, we need to traverse all the bits except the first bit, and let that bit is equal to the exclusive or of left bit and current bit. We don't need to calculate this one bit by one bit. We can just make a exclusive or of current number and the number that makes a one-bit right-shift of the original number.
        List<Integer> res = new ArrayList<Integer>();
        int max = (int)Math.pow(2, n) - 1;
        for (int i = 0; i <= max; i++){
            int num = i ^ (i >> 1);
            res.add(num);
        }
        return res;
    }
}
