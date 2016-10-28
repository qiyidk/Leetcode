package array;

/**
 * <p>
 * ThirdMaximumNumber_414
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ25ÈÕ
 */
public class ThirdMaximumNumber_414 {
    public int thirdMax2(int[] nums) {
        Integer first = null;
        Integer second = null;
        Integer third = null;
        for (int num : nums){
            if (first == null) first = num;
            else if (num > first){
                third = second;
                second = first;
                first = num;
            }
            else if (num == first) continue;
            else if (second == null) second = num;
            else if (num > second){
                third = second;
                second = num;
            }
            else if (num == second) continue;
            else if (third == null || num > third) third = num;
        }
        if (third == null) return first;
        else return third;
    }
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int num : nums){
            if (num == first || num == second) continue;
            if (num > first){
                third = second;
                second = first;
                first = num;
            }
            else if (num > second){
                third = second;
                second = num;
            }
            else if (num > third) third = num;
        }
        if (third != Long.MIN_VALUE) return (int)third;
        else return (int)first;
    }
}
