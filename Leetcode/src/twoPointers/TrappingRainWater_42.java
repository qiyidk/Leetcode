package twoPointers;

/**
 * <p>
 * TrappingRainWater_42
 * </p>
 *
 * @author qiyi
 * @version 2016��10��13��
 */
public class TrappingRainWater_42 {
    public int trap(int[] height) {
        // ������һ�ֽⷨ��˼·�����ǿ�����β���бȽϣ������ܿ����ҵ�һ����С�ģ�����õ�һ���ϴ����Ϊ��һ�ֽⷨ�е�������ֵֹ������������Сֵ֮���һ����ֵֹ֮ǰ����������һ���Ǹ��ϴ�ֵҲ���ܲ��ǣ��������Ļ��۶����Խ�Сֵ��Ϊ�߶�
        int n = height.length;
        if (n <= 2) return 0;
        int i = 0;
        int j = n - 1;
        int water = 0;
        int startH = 0; 
        while(i < j){
            if (height[i] <= height[j]) {
                if (startH > height[i]) water = water + startH - height[i];
                else startH = height[i];
                i++;
            }
            else{
                if (startH > height[j]) water = water + startH - height[j];
                else startH = height[j];
                j--;
            }
        }
        return water;
    }
    public int trap2(int[] height) {
        // �������ҵ���һ����start��Ľ�ֹ��������������Ҳ�����start������ҵ�start֮��ڶ���Ľ�ֹ�����������ɴ˿ɼ�������Ҫ��ǰֵ֮��ֵ�е����ֵ����˿���ʹ��maxStack
        int n = height.length;
        if (n <= 2) return 0;
        int water = 0;
        // calculate max stack
        int[] stack = new int[n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--){
            max = Math.max(height[i], max);
            stack[i] = max;
        }
        int start = 0;
        while(start < n - 2){
            int end = start + 1;
            if (stack[end] >= height[start]){
                // end up with the first edge that higher than or equal to start
                int h = height[start];
                while (height[end] < h){
                    water = water + h - height[end];
                    end++;
                }
            }
            else{
                // end up with stack[end]
                int h = stack[end];
                while (height[end] != h) {
                    water = water + h - height[end];
                    end++;
                }
            }
            start = end;
        }
        return water;
    }
}
