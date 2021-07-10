package DES;

import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
//        long p = 1999;
//        int q = 1997;

        long p = 53;
        int q = 59;
        long n = p * q;

        System.out.println(n);
        long phi = (p-1)*(q-1);

        // Selection of Encryption e
        long e = 2;
        while(gcd(phi,e) != 1 && e < phi){
            e++;
        }
        System.out.println("Encryption Key : "+e);
        // Get the decryption key

        int k = 2; // A constant value
        long d = (1 + (k*phi))/e;
        System.out.println("Decryption key : "+d);
//
//        //65535

//
//        long e = 1501;
//        long d = 26569;

//
      long a = (long) Math.pow(100,e);

      long b = (long) (a % n);

        System.out.println(b);
BigInteger b1 = new BigInteger(String.valueOf(b));
BigInteger b2 = b1.pow((int) d);
BigInteger b3 = b2.mod(BigInteger.valueOf(n));
        System.out.println(b3);




    }

    private static long gcd(long phi, long e) {
        if(e == 0){
            return phi;
        }else{
            return gcd(e,phi%e);
        }
    }
}
