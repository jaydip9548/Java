package DES;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Rounds {
    static String[][] Sbox = {
            {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
            {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
            {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
            {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
            {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
            {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };


    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = "Thats my Kung Fu";
        String plainText = "Two One Nine Two";

        KeyGenerator k = new KeyGenerator(key);
        Map<Integer, List<String>> keys = k.mainFunction();

//        for(int i=0; i<11;i++){
//            String s = "";
//            for(int j=0; j<keys.get(i).size();j++){
//                s += " \" " + keys.get(i).get(j) + "\" ,";
//            }
//            System.out.println(i+" "+s);
//
//        }

//        for (Map.Entry<Integer, List<String>> e : keys.entrySet()) {
//            System.out.println(e.getKey() + " " + e.getValue() + " ");
//        }

        byte[] myBytes = plainText.getBytes("UTF-8");

        plainText = DatatypeConverter.printHexBinary(myBytes);

        System.out.println(plainText + "  " + plainText.length());

        doEncryption(plainText, keys);

//        Send It to Client Side




    }

    private static void doEncryption(String plainText, Map<Integer, List<String>> keys) {

//  Add  Roundkey
        List<String> ptR = new ArrayList<>();
        for (int i = 0; i < plainText.length() - 1; i += 2) {
            ptR.add(plainText.substring(i, i + 2));
        }

//        System.out.println(pt);

        List<String> keyR = keys.get(0);

        List<String> afR = new ArrayList<>();

        System.out.println("Key : " + keyR);
        System.out.println("Text : " + plainText);

        for (int i = 0; i < keyR.size(); i++) {
            int result = Integer.parseInt(ptR.get(i), 16) ^ Integer.parseInt(keyR.get(i), 16);
            String s = Integer.toHexString(result).toUpperCase();
            if (s.length() < 2) {
                s = "0" + s;
            }
            afR.add(i, s);
        }
//        System.out.println("After R : "+afR);

//        Round Operations
        System.out.println(keys.size());
        for (int i = 1; i < keys.size(); i++) {
            if(i==10){
                afR = subBytes(afR);
                String nowMix[][] = shiftRows(afR);
                afR.clear();

                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        afR.add(nowMix[k][j]);
                    }
                }


            }else{
                afR = subBytes(afR);
                String nowMix[][] = shiftRows(afR);
                nowMix = mixColumn(nowMix);
                afR.clear();
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        afR.add(nowMix[k][j]);
                    }
                }
            }

            keyR = keys.get(i);

           List<String> repeatedafR = new ArrayList<>();

            for (int p = 0; p < afR.size(); p++) {
                int result = Integer.parseInt(keyR.get(p), 16) ^ Integer.parseInt(afR.get(p), 16);
                String s = Integer.toHexString(result).toUpperCase();
                if (s.length() < 2) {
                    s = "0" + s;
                }
                repeatedafR.add(p, s);
            }
            afR = repeatedafR;

            System.out.println("Round "+i+" "+repeatedafR);

        }


    }

    private static String[][] mixColumn(String[][] nowMix) {
        int fix[][] = {
                {2, 3, 1, 1},
                {1, 2, 3, 1},
                {1, 1, 2, 3},
                {3, 1, 1, 2},
        };
        String[][] ans = new String[4][4];
        int temp = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ArrayList<Integer> val = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
//                    System.out.print(fix[i][k] + " " + nowMix[k][j]);
                    if (fix[i][k] == 3) {
                        temp = mixvalueforTHree(fix[i][k], Integer.parseInt(nowMix[k][j], 16));

                    } else {
                        temp = findmixValue((fix[i][k] * Integer.parseInt(nowMix[k][j], 16)));
                    }
                    val.add(temp);
                }

//                /XOR Operation
                temp = val.get(0) ^ val.get(1) ^ val.get(2) ^ val.get(3);

                ans[i][j] = Integer.toHexString(temp).toUpperCase();
            }

        }

        return ans;
    }

    private static int findmixValue(int val) {
        int mix = val;
        String modi = findMod(val);
        return Integer.parseInt(modi, 2);
    }

    private static int mixvalueforTHree(int fix, int value) {
        int temp = 2 * value;
        String mod = findMod(temp);
        int con = Integer.parseInt(mod, 2);
        con = con ^ value;

//        System.out.println("con : "+Integer.toHexString(con));


        return con;

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


    private static String[][] shiftRows(List<String> afR) {
        String[][] ans = new String[4][4];
        int count = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                count++;
                ans[j][i] = afR.get(count);
            }
        }
//     Shift 1
        int shift = 0;
        String[] key = new String[4];

        for (int i = 1; i < 4; i++) {
            shift++;
            for (int j = 0; j < 4; j++) {
                int a = j - shift;
                if (a < 0) {
                    a += 4;
                }
                key[a] = ans[i][j];
            }
            for (int j = 0; j < 4; j++) {
                ans[i][j] = key[j];
            }
        }

//        afR.clear();
//        for(int i=0; i<4;i++){
//            for(int j=0; j<4;j++){
//                afR.add(ans[j][i]);
//            }
//        }

        return ans;
    }

    private static List<String> subBytes(List<String> afR) {
        for (int i = 0; i < afR.size(); i++) {
            String a = afR.get(i);
            int r = 0;
            int c = 0;
            r = Integer.parseInt(String.valueOf(a.charAt(0)), 16);
            c = Integer.parseInt(String.valueOf(a.charAt(1)), 16);

            afR.set(i, Sbox[r][c].toUpperCase());

        }
        return afR;
    }
}
