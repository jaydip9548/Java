import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P1 {
    public static void main(String[] args) {
     String s = findMod(470);
        System.out.println(s);
    }
    private static String findMod(int temp) {
        String a1 = Integer.toBinaryString(temp);
        String b1 = "100011011";
        String mode = "";

        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < a1.length(); i++) {
            a.add(Integer.parseInt(String.valueOf(a1.charAt(i))));
        }

        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < b1.length(); i++) {
            b.add(Integer.parseInt(String.valueOf(b1.charAt(i))));
        }
        while (b.size() <= a.size()) {
            if (a.get(0) == 1) {
                a.remove(0);
            }

                for (int j = 0; j < b.size() - 1; j++) {
                    a.set(j, (a.get(j) ^ b.get(j + 1)));
                }



        }
        for (Integer i : a) {
            mode += String.valueOf(i);
        }
        return mode;

    }

}
