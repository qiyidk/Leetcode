package stack;

/**
 * <p>
 * LargestRectangleinHistogram_84
 * </p>
 *
 * @author qiyi
 * @version 2016Äê9ÔÂ2ÈÕ
 */
public class LargestRectangleinHistogram_84 {
    // 1,2,3,4,5,6,4
    // if (n[i] > n[i - w]) the rectangle can also use n[i] as height, length of original valid height + 1
    // if (n[i] < n[i - w]) n[i - w] cannot be used as height any more
    // if equal, don't need to do anything
    // we need to maintain the height we can use and the start point for that height
    // when get a new number, if the number > current max valid height, we get rid of invalid height, and we also get the max area for that invalid height, we compare all these area with max
    // note that if we eliminate some invalid elements, the start point for the new adding element should be the start point of the last removing element

    public int largestRectangleArea(int[] heights) {
        int[] h = new int[heights.length];
        int[] start = new int[heights.length];
        int p = 0;
        int max = 0;
        for (int i = 0; i < heights.length; i++){
            if (p == 0 || heights[i] > h[p - 1]){
                //push
                h[p] = heights[i];
                start[p] = i;
                p++;
            }
            else if (heights[i] < h[p - 1]){
                int s = 0; // start of new adding element(note that it is not i)
                while(p != 0 && heights[i] < h[p - 1]){
                    //pop
                    s = start[p - 1];
                    int area = (i - start[p - 1]) * h[p - 1];
                    if (area > max) max = area;
                    p--;
                }
                if (p == 0 || heights[i] > h[p - 1]){
                    //push
                    h[p] = heights[i];
                    start[p] = s;
                    p++;
                }
            }
        }
        // deal with remaining elements
        for(int i = 0; i < p; i++){
            int area = (heights.length - start[i]) * h[i];
            if (area > max) max = area;
        }
        return max;
    }
}
