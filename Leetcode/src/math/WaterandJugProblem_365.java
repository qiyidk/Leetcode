package math;

import java.util.HashSet;

/**
 * <p>
 * WaterandJugProblem_365
 * </p>
 *
 * @author qiyi
 * @version 2016年6月24日
 */
public class WaterandJugProblem_365 {
    // if a = b have a
    // if a > b get a' = a - b, if a' > b get a' - b..
    // until 
    // if a'' = b stop
    // if a'' < b get a - (b - a'') = a - b + a'...until a''' has already existed or a''' = b
    // e.g. 7 5
    // a = 7 b = 5 
    // c = 7 c = 2 c = 4 c = 6 c = 1 c = 3 c = 5
    // 题目做了些许调整，本解法暂时不做同步修改
    public boolean canMeasureWater2(int x, int y, int z) {
        if (z == 0) return true;
        if (x == y) return x == z;
        int a = Math.max(x, y);
        int b = Math.min(x, y);
        if (z > a) return false;
        if (z == a || z == b) return true;
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(b);
        int c = a;
        while (true){
            if (c > b) c = c - b;
            else if (c < b) c = a - b + c;  
            if (c == z) return true;
            if (set.contains(c)) return false;
            set.add(c);
        }
    }
    // one more efficient way is getting rid of hashset. See proof below 
    // let k = greatest common divisor, a = mk, b = nk
    // a' = (m - n)k 
    // if a' < b a'' = (m - n)k + (m - n)k
    // if a' > b a'' = (m - n)k - nk
    // suppose condition 1 run n - 1 times, condition2 run x times
    // the final result will be (m - n)nk - xnk = (m - n - x)nk must be a divisor of b, which means we definitely can get b
    // if there's a loop that does not contain b, we cannot get b, contradiction.
    // if there's a loop that contains b, we must reach b before the second time that we reach the enter point of that loop, therefore it has already terminated, we won't reach the enter point.
    // therefore we can use b as the only terminate condition.
    // 题目做了些许调整，本解法暂时不做同步修改
    public boolean canMeasureWater3(int x, int y, int z) {
        if (z == 0) return true;
        if (x == y) return x == z;
        int a = Math.max(x, y);
        int b = Math.min(x, y);
        if (z > a) return false;
        if (z == a || z == b) return true;
        int c = a;
        while (true){
            if (c > b) c = c - b;
            else if (c < b) c = a - b + c;  
            if (c == z) return true;
            if (c == b) return false;
        }
    }
    // actually we can think the final result = xa - yb (or yb - xa). we can fill x times a, whenever a is full, pour it into b. Whenever b is full, dump it. Therefore the result must be t * gcd(a, b) (t = xc - yd, c,d are coprime)
    // we need to prove that cx - dy can get all integers, (c,d are coprime)
    // if cx - dy can get 1, it can get every integer
    // think about how we get the gcd of c,d, result r starts with the biggest number of c and d, if r is bigger, r = r minus smaller one (can be c or d). On the next step, if r is bigger, r = r minus smaller one again, if r is smaller, r = the bigger one minus r. r can always be represented by c and d. finally r will be equal to gcd of c,d, which is 1 since c and d are coprime. Since r can always be represented by cx - dy => 1 can be represented by cx - dy => every integer can be represented by cx - dy
    // therefore if the val is a mutiple of gcd(a, b), it can be measuerd; otherwise, it cannot.
    // 辗转相除法可以求gcd，为什么？ 设最后终止于k * gcd,可以得知上一个状态是2k * gcd,k * gcd->3k * gcd,k * gcd->。。。可知原始的两个数一定是k * gcd的倍数，因为除gcd以外，两个数不应该有其他公约数，所以k = 1.
    // 题目做了些许调整，本解法暂时不做同步修改
    public boolean canMeasureWater4(int x, int y, int z) {
        if (z > x && z > y) return false;
        if (z == 0) return true;
        if (Math.min(x, y) == 0) return Math.max(x, y) == z;
        return z % gcd1(x, y) == 0;
    }
    private int gcd1(int x, int y){// cannot be used when x or y == 0
        if (x == y) return x;
        else {
            if (x > y) return gcd1(x - y, y);
            else return gcd1(y - x, x);
        }
    }
    
    // one even better solution for gcd
    public boolean canMeasureWater5(int x, int y, int z) {
        if (z > x + y) return false;
        if (x== 0 && y == 0) return z == 0; // deal with gcd = 0
        int gcd = gcd2(x, y);
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        if (z < min) return z % gcd == 0;
        else if (z < max)  return z % gcd == 0 || (z - min) % gcd == 0;
        else return (z - max) % gcd == 0 || (z - min) % gcd == 0;
    }
    private int gcd2(int x, int y){// can be used when x or y == 0
        if (y == 0) return x;
        else {
            return gcd(y, x % y);// if y is bigger, return gcd(y, x). always put bigger one in the front and if x is bigger, it will save one comparison. One more important thing is if x is much bigger than y, x % y will save lots of rounds to calculate x - y.
        }
    }
    
    // since if z is a multiple of gcd, z - x or z - y must be a multiple of gcd(if z - x > 0 , z - y > 0)
    // therefore as long as z < x + y and z % gcd == 0, it can be guaranteed z can be measured.
    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) return false;
        if (x== 0 && y == 0) return z == 0; // deal with gcd = 0
        return z % gcd(x, y) == 0;
    }
    private int gcd(int x, int y){// can be used when x or y == 0
        if (y == 0) return x;
        else {
            return gcd(y, x % y);
        }
    }
}
