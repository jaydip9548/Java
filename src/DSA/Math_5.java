package DSA;


public class Math_5 {
    public static void main(String[] args) {
        //Find The Trailing Zeroes

//        int n = 11232;
//        int res=0;
//        for(int i=5; i<n; i*= 5){
//            res +=n/i;
//        }
//        System.out.println("trailing Zeroes : "+res);


//        Find The number is Palindrome is not
        int n1 = 122;
        int i=0;
        int ans=0;
        int n = n1;
        while(n != 0){
            int a1 = n % 10;
            ans += a1*Math.pow(10,i);
            n /= 10;
            i++;
        }
        if(ans== n1){
            System.out.println("Plindrome");
        }else{
            System.out.println("Not Palindroe");
        }


    }
}
