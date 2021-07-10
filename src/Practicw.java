import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Practicw {


    public static void main(String[] args) throws Exception {
//        n =      3992003
//        Encryption Key : 1501
//        Decryption key : 26569
        String text1 = "Today i discuss in meeting !";
        byte[] myBytes = text1.getBytes("UTF-8");

        text1 = DatatypeConverter.printHexBinary(myBytes);
        if (text1.length() % 2 != 0) {
            while (text1.length() % 2 != 0) {
                text1 = "0" + text1;
            }
        }
//        Get Encrypted Number
        ArrayList<String> EncrytedNum = Encryption(text1);
//        System.out.println("Encryptino");
//        for(String i : EncrytedNum){
//
//            System.out.println(i);
//        }

        ArrayList<String> DecryptNum = Decryption(EncrytedNum);
//        System.out.println("Decryption : ");
//        for(String i : DecryptNum){
//            System.out.println(i);
//        }
        text1 = "";
        for(String i : DecryptNum){
            text1 += Integer.toHexString(Integer.parseInt(i));
        }
        System.out.println(text1);
        myBytes = DatatypeConverter.parseHexBinary(text1);
        System.out.println(new String(myBytes,"UTF-8"));
    }

    private static ArrayList<String> Decryption(ArrayList<String> encrytedNum) {
        BigInteger b5,b6,b3,b7;
        b3 = new BigInteger("3127");
        ArrayList<String> decrypt = new ArrayList<>();
        for(int i=0; i<encrytedNum.size(); i++){
            b5 = new BigInteger(encrytedNum.get(i));
            b6 = b5.pow(2011);
            b7 = b6.mod(b3);
            decrypt.add(b7.toString());
        }
        return decrypt;
    }
//3127
//    Encryption Key : 3
//    Decryption key : 2011
    private static ArrayList<String> Encryption(String text1) {
        ArrayList<String> EncrytedNum = new ArrayList<>();
        String text = "";

        System.out.println("Hex Text  " + text1);
        BigInteger b1;
        BigInteger b2, b3, b4 , b5,b6;
        for (int i = 0; i < text1.length(); i += 2) {

            text = text1.substring(i, i + 2);
            long plaintext = Long.parseLong(text, 16);
//            System.out.println("Plain text "+plaintext);
            ;
            b1 = new BigInteger(String.valueOf(plaintext));
//
            b2 = b1.pow(3);
            b3 = new BigInteger("3127");
            b4 = b2.mod(b3);
            System.out.print(" Encrypted Text : "+b4);

            EncrytedNum.add(b4.toString());
        }
        return EncrytedNum;
    }
}
