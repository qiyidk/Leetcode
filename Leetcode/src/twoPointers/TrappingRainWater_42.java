package twoPointers;

/**
 * <p>
 * TrappingRainWater_42
 * </p>
 *
 * @author qiyi
 * @version 2016年10月13日
 */
public class TrappingRainWater_42 {
    public int trap(int[] height) {
        // 延续上一种解法的思路，我们可以首尾进行比较，这样总可以找到一个较小的，因而得到一个较大的作为上一种解法中的容器截止值，而在遇到较小值之后第一个截止值之前（可能是另一端那个较大值也可能不是），雨量的积累都会以较小值作为高度
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
        // 遍历，找到第一个比start大的截止生成容器，如果找不到比start大的则找到start之后第二大的截止生成容器，由此可见我们需要当前值之后值中的最大值，因此可以使用maxStack
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
