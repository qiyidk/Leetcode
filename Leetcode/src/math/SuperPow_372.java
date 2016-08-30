package math;

/**
 * <p>
 * SuperPow_372
 * </p>
 *
 * @author qiyi
 * @version 2016Äê8ÔÂ15ÈÕ
 */
public class SuperPow_372 {
    //without using theorem, divide b(123) into a^(100 + 20 + 3) = a^(1 * 100 + 2 * 10 + 3 * 1) 
    //= (a^100)^1 * (a^10)^2 * (a^1)^3
    public int superPow(int a, int[] b) {
        a = a % 1337;
        int res = 1;
        for (int i = b.length - 1; i >= 0; i--){
            res = res * pow(a, b[i]) % 1337;
            a = pow(a, 10);
        }
        return res;
    }
    private int pow(int a, int r){
        int res = 1;
        while(r != 0){
            if (r % 2 == 1) res = res * a % 1337;
            a = a * a % 1337;
            r = r / 2;
        }
        return res;
    }
    // Fermat's little theorem 
    // if p is prime, a^p mod p = a mod p, (a^p - a) mod p = 0, a^(p - 1) = (k / a)p + 1
    // if a and p are coprime, k must be divisible by a, k / a is an integer
    // a^(p - 1) mod p = 1
    
    // a^x = (n1*p + t1); a^y = (n2*p + t2) ; a^(x + y) = (n1*p + t1) * (n2*p + t2) = t1*t2 + n3*p
    // a^(k(p - 1)) = a^(p - 1) * a^(p - 1) *... = 1^k mod p = 1
    // consider a^b, if b = k(p - 1) + t, a^b = a^(k(p - 1)) * a^t mod p = a^t mod p
    // a^b mod p = a^(b mod (p - 1)) mod p
    
    // see Chinese Remainder theorem 
    // x % 7 = u and x % 191 = w 
    // x mod 7 * 191 = ut1m1 + wt2m2 m1 = 7 * 191 / 7 = 191; m2 = 7 * 191 / 191 = 7
    // t1 * m1 mod 7 = 1, t1 * m1 = 764; t2 * m2 mod 191 = 1; t2 * m2 = 574
    // x mod 7 * 191 = 764u + 574w
    
    // a = 1337k + t; a^b % 1337 = (1337k + t) * (1337k + t) * (1337k + t)... % 1337 = t^b % 1337
    
    public int superPow2(int a, int[] b) {
       a = a % 1337;
       return (764 * superPow(a, b, 7) + 574 * superPow(a, b, 191)) % 1337;
    }
    private int superPow(int a, int[] b, int p) {
        if (a % p == 0) return 0;// a and p are not coprime
        int r = 0;
        for (int digit : b){
            r = (r * 10 + digit) % (p - 1); // convert a^b % p to a^(b mod (p - 1)) mod p
        }
        int res = 1;
        while(r != 0){
            if (r % 2 == 1) res = res * a % p;
            a = a * a % p;
            r = r / 2;
        }
        return res;
    }
    
    /*
    Euler's theorem: 
    if a and p are coprime. a^b mod p = a ^ (b mod phi) mod p, where p ^ phi mod p = 1
    if a and p are not coprime. a^b mod p = a ^ (b mod phi + phi) mod p
    if factor p is prime, phi(p) = p - 1; otherwise, p = p1 * p2, phi(p) = phi(p1) * phi(p2)
    p^(ph1 * ph2) mod p = (p1p2^ph1)^ph2 mod p = (p1^ph1 * p2^ph1)^ph2 mod p 
    = (p1^ph1)^ph2 * (p2^ph1)^ph2 mod p = 1^ph2 * 1^ph1 mod p = 1
    */
    public int superPow3(int a, int[] b) {
        a = a % 1337;
        int phi = (191 - 1) * (7 - 1);
        int r = 0;
        for (int digit : b){
            r = (r * 10 + digit) % phi; // convert a^b % p to a^(b mod phi) mod p
        }
        if (a % 191 == 0 || a % 7 == 0) r += phi;// a and p are not coprime
        int res = 1;
        while(r != 0){
            if (r % 2 == 1) res = res * a % 1337;
            a = a * a % 1337;
            r = r / 2;
        }
        return res;
    }
}
