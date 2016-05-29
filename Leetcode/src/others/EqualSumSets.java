package others;

/**
 * <p>
 * EqualSumSets
 * </p>
 *
 * @author qiyi
 * @version 2016��5��10��
 */

/*
 * ���ڴ�һ��N�������������ϣ��ܻ��ֳ������Ӽ��ϣ��ұ�֤ÿ�����ϵ����ֺ�����ȵ�. ��ɻ��ֵĸ���

����V = ����Ԫ��֮�� k = v / 2
if v is odd, return 0
����V[i] Ϊ��ֹ����ǰ�ִΣ��ۼƺ�Ϊi�������
V[0] = 1��//��������V[i]������{i}������
for i = 1 to n //��ǰ�ִ�����ʹ��1...i�������
    for j = k to i
        v[j] = v[j] + v[j - i];//v[j] ������ʹ��i���������v[j]ʹ��iʱ����������ӽ�ʹ��1...i - 1ʱv[j - i]��ֵ��
                               //����趨��֤�˼���ѡȡʱ�������ظ���Ԫ��
return v[k] / 2; //ÿ����ϱ�����������
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
