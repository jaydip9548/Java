package DES;

import java.util.*;


public class Decryption {
  static   List<String> encrypt = Arrays.asList("29", "C3", "50", "5F", "57", "14", "20", "F6", "40", "22", "99", "B3", "1A", "02", "D7", "3A");
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

    static String[][] RCON = {{"01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"},
            {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
            {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
            {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},

    };

    static Map<Integer, List<String>> keys = new HashMap<>();

    public static void main(String[] args) {
        addKeys();

//        Add RoundKeys : encrypt ^ map.get(10)
        RoundKeyOperation(encrypt,keys.get(10));



    }

    private static void RoundKeyOperation(List<String> encrypt, List<String> strings) {
        System.out.println("Encrypt : "+encrypt);
        System.out.println(strings);

        List<String> repeatedafR = new ArrayList<>();
   for(int p=0; p<encrypt.size();p++){

       int result = Integer.parseInt(encrypt.get(p), 16) ^ Integer.parseInt(strings.get(p), 16);
       String s = Integer.toHexString(result).toUpperCase();
       if (s.length() < 2) {
           s = "0" + s;
       }
       repeatedafR.add(s);

   }
        System.out.println(repeatedafR);
    }

    private static void addKeys() {
        keys.put(0, Arrays.asList("54", "68", "61", "74", "73", "20", "6D", "79", "20", "4B", "75", "6E", "67", "20", "46", "75"));
        keys.put(1, Arrays.asList("E2", "32", "FC", "F1", "91", "12", "91", "88", "B1", "59", "E4", "E6", "D6", "79", "A2", "93"));
        keys.put(2, Arrays.asList("56", "8", "20", "7", "C7", "1A", "B1", "8F", "76", "43", "55", "69", "A0", "3A", "F7", "FA"));
        keys.put(3, Arrays.asList("D2", "60", "D", "E7", "15", "7A", "BC", "68", "63", "39", "E9", "1", "C3", "3", "1E", "FB"));
        keys.put(4, Arrays.asList("A1", "12", "2", " C9", "B4", "68", "BE", "A1", "D7", "51", "57", "A0", "14", "52", "49", "5B"));
        keys.put(6, Arrays.asList("BD", "3D", "C2", "87", "B8", "7C", "47", "15", "6A", "6C", "95", "27", "AC", "2E", "E", "4E"));
        keys.put(7, Arrays.asList("CC", "96", "ED", "16", "74", "EA", "AA", "3", "1E", "86", "3F", "24", "B2", "A8", "31", "6A"));
        keys.put(8, Arrays.asList("8E", "51", "EF", "21", "FA", "BB", "45", "22", "E4", "3D", "7A", "6", "56", "95", "4B", "6C"));
        keys.put(5, Arrays.asList("B1", "29", "3B", "33", "5", "41", "85", "92", "D2", "10", "D2", "32", "C6", "42", "9B", "69"));
        keys.put(9, Arrays.asList("BF", "E2", " BF", "90", "45", "59", "FA", "B2", "A1", "64", "80", "B4", "F7", "F1", "CB", "D8"));
        keys.put(10, Arrays.asList("28", "FD", "DE", "F8", "6D", "A4", "24", "4A", "CC", "C0", "A4", "FE", "3B", "31", "6F", "26"));
    }
}
