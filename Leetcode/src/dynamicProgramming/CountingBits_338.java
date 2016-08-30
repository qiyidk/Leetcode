package dynamicProgramming;


/**
 * <p>
 * CountingBits_338
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ27ÈÕ
 */
public class CountingBits_338 {
    // 00000 - 0ffff
    // 10000 - 1ffff for every number of last level, has one more "1"
    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        int offset = 1;
        for (int i = 1; i <= num; i++){
            if (i >= offset * 2) offset = offset * 2;
            res[i] = res[i - offset] + 1;
        }
        return res;
    }
    // one more concise solution: i & i - 1 means eliminate the first "1" bit from right to left
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++){
            res[i] = res[i & i - 1] + 1;
        }
        return res;
    }
    // sum of countbits
    public int countBits3(int num) {
        int[] countBits = new int[16]; // use 1 to 15
        int[] number = new int[16]; // number of elements in each level
        number[0] = 1; // for convenience
        for (int i = 1; i <= 15; i++){
            number[i] = number[i - 1] * 2;
        }
        int sum = 0;
        for (int i = 1; i < 16; i++){
            int maxNum = number[i] - 1; // the max num of current level
            if (num <= maxNum){
                int j = i;
                int c = 0;// extra '1's so far
                while (num != 0){
                    if (num >= number[j - 1]){
                        sum += countBits[j - 1] + c * number[j - 1];
                        c++;
                        num = num - number[j - 1];
                    }
                    j--;
                }
                sum += c; // plus the number of 1s of num itself
                break;
            }
            else{
                countBits[i] = countBits[i - 1] + (countBits[i - 1] + number[i - 1]);
            }
        }
        return sum;
    }
    public static void main(String[] args){
        CountingBits_338 c = new CountingBits_338();
        int num = 513;
        int[] s = c.countBits(num);
        int k = c.countBits3(num);
        int sum = 0;
        for (int n : s){
            sum += n;
        }
        System.out.println(sum + " " + k);
    }
}
