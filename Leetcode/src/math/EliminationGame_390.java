package math;

/**
 * <p>
 * EliminationGame_390
 * </p>
 *
 * @author qiyi
 * @version 2016Äê10ÔÂ7ÈÕ
 */
public class EliminationGame_390 {
    // after doing one round elimination, if we look at the remaining element reversly, it is an array with smaller size and reverse indices, and use the same rule to eliminate elements with the original one.
    // therefore we can reduce the origianl problem to a sub-problem that has a smaller size.
    // let f(n) denotes the index of survivor(starts with 1) when we has a total number of n
    // if n is odd(n = 2m + 1), the kth number in round2 is actually the 2(m - k + 1)th number in round1
    // f(2m + 1) = 2(m + 1 - f(n))
    // if n is even(n = 2m), the kth number in round2 is also the 2(m - k + 1)th number in round1
    // f(2m) = 2(m + 1 - f(n))
    // therefore f(n) = 2(floor(n / 2) + 1 - f(floor(n/2)))
    // this can be simply computed in O(logn) time
    // then we try to derive a general term formula rather than a recursive formula
    // denote x / y = floor(x / y) for convenience, note that n / 2 * 2 != n
    // f(1) = 1, let n = 2^m + l, (0 <= l < 2^m)
    // f(n / 2) = f(2 ^ (m - 1) + l / 2)
    // f(n) = 2 (n / 2 + 1 - 2f(2 ^ (m - 1) + l / 2))
    // =2 * n / 2 + 2 - 4(n / 2 / 2 + 1 - f(2 ^ (m - 2) + l / 2 / 2)
    // =2 * n / 2 - 4 n / 2 / 2 + 2 - 4 + 4f(2 ^ (m - 2) + l / 2 / 2)
    // if m is odd f(n) = 2 * n / 2 - 4 * n / 2 / 2 +... + 2^m * n/2/2../2(m 2s) + (2 - 4 + 8-...+2^m) - 2^m(f(1))
    // if m is even f(n) = 2 * n / 2 - 4 * n / 2 / 2 +... - 2^m * n/2/2../2(m 2s) + (2 - 4 + 8-...-2^m) + 2^m(f(1))
    
    // for m is even, denote g(j) = 2^j * n / 2 /... 2(j 2s) - 2^(j + 1) * n / 2 / 2 / ... 2(j + 1 2s)(j = 1,3,5,...,m - 1) 
    // let n / 2 / 2.. /2, (j 2s) = t
    // g(j) = 2 ^ j * t - 2 ^ j * 2 * (t / 2) = 2 ^ j (t - (t / 2) * 2)
    // if t % 2 == 0 the diff = 0; otherwise diff = 2 ^ j * 1 = 2 ^ j
    // we denote the least significant bit as the first bit, it can be seen that
    // g(j) = 0             if j + 1 bit is 0 
    //      = 2 ^ j         if j + 1 bit is 1
    // f(n) = sigma(g(j)) + (2 - 4) + (8 - 16) +... + (2^m-1 - 2^m) + 2^m
    // = sigma(g(j)) - 2^1 - 2^3 -... - 2^(m - 1) + (1 + ... + 2^m-1) + 1
    // = sigma(g(j)) + 2^0 + 2^2 + 2^4 +... + 2^(m - 2) + 1
    // note that 2^j corresponds to the j + 1 bit of n
    // it can be seen that, for odd bits except(m + 1) of f(n)(1,...,m - 1), they are all 1s. 
    // for even bits of f(n), it depends on the whether the j + 1's bit of n is 1,(j + 1 = 2, 4, ..., m)
    // if the (j + 1) bit of n is 1, an "1" in the j + 1 bit of f(n) will be remained, this is to say, the (j + 1)bit of n is equal to the (j + 1) bit of f(n)
    
    // if m is odd, f(n) = 2 * n / 2 - 4 * n / 2 / 2 +... + 2^m * n/2/2../2(m 2s) + (2 - 4 + 8-...+2^m) - 2^m(f(1)) 
    // = 2 * n / 2 - 4 * n / 2 / 2 +... - 2^(m-1) * n/2/2../2(m-1 2s) + 2^m * n/2/2../2(m 2s) + (2 - 4 + 8-...-2^(m-1)) + 2^m - 2^m(f(1))
    // = f(t) + 2^m * n/2/2../2(m 2s) (t = 2 ^ (m - 1) + l)
    // for all m - 2 bits(corresponding to 2^0...2^m-1), it is just the same with t(which has a m' is even)
    // for the new bit m - 1(corresponding to 2^m), it depends on 2^m * n/2/2../2(m 2s)
    // n/2/2../2(m 2s) = 1 when the m-1 th bit = 1, therefore the m-1 bit of f(n) is the same with the m-1 bit of n. note that m - 1 is even, and we treat even bit of f(n) as the same with that of n when m is even.
    // therefore, f(n) follows the same rule with the situation that m is even.
    
    // denote mask = ...10101 = 0x55555555, we can set all odd bits to 1 by n | mask and all even bits remain the same with n(note that it will bring more "1" bits than what we need)
    // use & with 00011...11 (m - 1 1s)to eliminate all the bits that are greater than the (m - 1)th bit
    
    
    // this problem is similar to Josephus problem when k = 2, but the derivation seems much difficult. If someone has a better derivation, please let me know.
    // https://en.wikipedia.org/wiki/Josephus_problem
    public int lastRemaining(int n) {
        int mask = 0x55555555;
        return ((n | mask) & (Integer.highestOneBit(n) - 1)) + 1; //highestOneBit will return 10..0(m - 1 0s) by constant time.(the implementation of this api is also quite interesting)
    }
    public int lastRemaining2(int n) {
         if (n == 1) return 1;
         else return 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}

