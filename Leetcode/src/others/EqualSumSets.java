package others;

/**
 * <p>
 * EqualSumSets
 * </p>
 *
 * @author qiyi
 * @version 2016年5月10日
 */

/*
 * 对于从一到N的连续整数集合，能划分成两个子集合，且保证每个集合的数字和是相等的. 求可划分的个数

定义V = 所有元素之和 k = v / 2
if v is odd, return 0
定义V[i] 为截止到当前轮次，累计和为i的组合数
V[0] = 1；//对于任意V[i]，总有{i}这个组合
for i = 1 to n //当前轮次允许使用1...i进行组合
    for j = k to i
        v[j] = v[j] + v[j - i];//v[j] 增加了使用i的情况，当v[j]使用i时，会额外增加仅使用1...i - 1时v[j - i]的值。
                               //这个设定保证了集合选取时不会有重复的元素
return v[k] / 2; //每个组合被计算了两次
 */
public class EqualSumSets {
    
    public int solution1(int n){
        if (n <= 2) return 0;
        int s = n * (n + 1) / 2;
        if (s % 2 == 1) return 0;
        int k = s / 2;
        int[] v = new int[k + 1];
        v[0] = 1;
        for (int i = 1; i <= n; i++){
            for (int j = k; j >= i; j--){
                v[j] += v[j - i];
            }
        }
        return v[k] / 2;
    }
    public int solution2(int n){
        if (n <= 2) return 0;
        int s = n * (n + 1) / 2;
        if (s % 2 == 1) return 0;
        int k = s / 2;
        return backtrack(1, k, n) / 2;
    }
    private int backtrack(int p, int remaining, int n){
        if (p > n) return 0;
        if (p == remaining) return 1;
        else if (p > remaining) return 0;
        return backtrack(p + 1, remaining, n) + backtrack(p + 1, remaining - p, n);
    }
    public static void main(String[] args){
        int n = 7;
        System.out.println(new EqualSumSets().solution1(n));
        System.out.println(new EqualSumSets().solution2(n));
        n = 20;
        System.out.println(new EqualSumSets().solution1(n));
        System.out.println(new EqualSumSets().solution2(n));
    }
}
