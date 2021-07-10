package DSA;



public class BitMasking_3 {
    public static void main(String[] args) {


//Find the ith position of bit
        // Set ith bit viceversa (o to 1 or 1 to 0)
        int a = 22;
        int i = 0;
//        int c = 1 << i;

//
//        if ((a & c) != 0) {
//            System.out.println("1");
//
//            c = ~c;
//            a = a& c;
//            System.out.println("a's value is : "+a);
//
//        } else {
//            System.out.println("0");
//            a = a | c;
//            System.out.println("b's value is : "+a);
//        }

int b = 27;

 a = a ^ b;
 int d = 1 << 0;
 int count=0;

 while( a != 0){
     if((a&d) != 0){
         count++;
     }
     a = a >> 1;
 }
        System.out.println(count);

    }
}
