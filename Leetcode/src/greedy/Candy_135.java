package greedy;

/**
 * <p>
 * Candy_135
 * </p>
 *
 * @author qiyi
 * @version 2016Äê7ÔÂ1ÈÕ
 */
public class Candy_135 {
    // ratings:
    //        r   r
    //  rr  rr rrr rrr  rr
    // r  rr          rr
    // peak : max(neighbors) + 1
    // bottom : should give 1 candy
    public int candy(int[] ratings) {
        int n = ratings.length;
        int sum = 0;
        int candy = 0; // candy to be given
        int start = 0; // the start point of each round 
        for (int i = 0; i < n; i++) {
            while(i + 1 < n && ratings[i + 1] < ratings[i]) i++;// find a bottom;
            int count = i - start; // the count of children excludes the peak point
            sum += (1 + count) * count / 2; // the sum of candies exclude the peak point
            if (candy < count + 1) sum = sum - candy + count + 1; // update the peak point if needed, note that the peak point has already been given candy in the last round
            candy = 1;
            while(i + 1 < n) {// deal with i + 1
                if (ratings[i + 1] > ratings[i]) candy++;
                else if (ratings[i + 1] == ratings[i]) candy = 1;
                else {
                    start = i; //starts with the peak point
                    break;
                }
                i++;
                sum += candy;
            }
        }
        return sum;
    }
}
