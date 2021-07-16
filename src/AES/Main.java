package AES;

import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        long start = System.currentTimeMillis();
        String key = "Thats my Kung Fu";
        String plainText = "Hello! Good Morning.";
        Encryption encryption = new Encryption(key, plainText);


        System.out.println(Encryption.final_encrypted);

        Decryption decryption = new Decryption(Encryption.final_encrypted,key);

        long end = System.currentTimeMillis();
        System.out.println((end-start) +" Ms");

    }
}
