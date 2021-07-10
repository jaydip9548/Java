package DES;

import java.lang.reflect.Array;
import java.util.*;

public class KeyGenerator {
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

     String key;

    public KeyGenerator(String key){
        this.key = key;
    }

    private static List<String> gFunction(List<String> lastCol) {
//        Rot Word Operation

        String temp = lastCol.get(0);
        for (int i = 1; i < lastCol.size(); i++) {
            lastCol.set((i - 1), lastCol.get(i));
        }
        lastCol.set(3, temp);

//        SubBytes Operation
        for (int i = 0; i < 4; i++) {
            String a = lastCol.get(i);
            int r = 0;
            int c = 0;
            if (a.length() == 2) {
                r = Integer.parseInt(String.valueOf(a.charAt(0)), 16);
                c = Integer.parseInt(String.valueOf(a.charAt(1)), 16);
            } else {
                r = Integer.parseInt("0", 16);
                c = Integer.parseInt(String.valueOf(a.charAt(0)), 16);
            }

            lastCol.set(i, Sbox[r][c].toUpperCase());
        }

        return lastCol;
    }


    public Map<Integer,List<String>> mainFunction(){

        String[] keyHex = new String[16];

//Generate The Hex of Key and stored in keyHex
        for (int i = 0; i < key.length(); i++) {
            keyHex[i] = Integer.toHexString((int) key.charAt(i)).toUpperCase();
        }

//        System.out.println(" Key Hex Value : " + Arrays.toString(keyHex));

//        @KeyState
//        Add to Metrix
//        [
//        54,53,50,31
//        45 43 49 32
//        41 4F 41 33
//        4D 52 4E 34
//        ]
        int pos = -1;
        String[][] keyMetrix = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pos++;
                keyMetrix[j][i] = keyHex[pos];
            }
        }

        List<String> lastCol = new ArrayList<>();
        String[][] keys = new String[4][4];
        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        HashMap<Integer, List<String>> map = new HashMap<>();
        int count = 0;

        map.put(count, Arrays.asList(keyHex));
        count++;

        for (int i = 0; i < 10; i++) {


            for (int j = 0; j < 4; j++) {

                lastCol.add(keyMetrix[j][3]);
            }
            lastCol = gFunction(lastCol);

            keys = calculateKey(lastCol, i, keyMetrix);

            for (int p = 0; p < 4; p++) {
                for (int q = 0; q < 4; q++) {
                    keyMetrix[p][q] = keys[p][q];
                }
            }
            ArrayList<String> copy = new ArrayList<>();
            for (int p = 0; p < 4; p++) {
                for (int q = 0; q < 4; q++) {
                    copy.add(keys[q][p]);
                }
            }
            map.put(count, copy);
            count++;


            lastCol.clear();
        }

//        for(Map.Entry<Integer,List<String>> e : map.entrySet()){
//            System.out.println(e.getKey()+" "+e.getValue()+" ");
//
//
//        }
        return map;
    }
    private static String[][] calculateKey(List<String> lastCol, int pos, String[][] keyMetrix) {
        int temp = 0;
        String ans[][] = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    temp = Integer.parseInt(keyMetrix[j][i], 16) ^ Integer.parseInt(lastCol.get(j), 16);
                    temp = temp ^ Integer.parseInt(RCON[j][pos], 16);
                    ans[j][i] = Integer.toHexString(temp).toUpperCase();
                } else {
                    temp = Integer.parseInt(keyMetrix[j][i], 16) ^
                            Integer.parseInt(ans[j][i - 1], 16);
                    ans[j][i] = Integer.toHexString(temp).toUpperCase();
                }
            }
        }
//        System.out.println("Keys " + (pos + 1) + "  " + Arrays.deepToString(ans));
        return ans;

    }
}
