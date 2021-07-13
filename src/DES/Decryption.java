package DES;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class Decryption {
    static List<String> encrypt = Arrays.asList("29", "C3", "50", "5F", "57", "14", "20", "F6", "40", "22", "99", "B3", "1A", "02", "D7", "3A");
    static String[][] Sbox = {
            {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
            {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
            {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
            {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
            {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
            {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
            {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
            {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
            {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
            {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
            {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
            {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
            {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
            {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
            {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
            {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}
    };
    public final static int[] LogTable = {
            0, 0, 25, 1, 50, 2, 26, 198, 75, 199, 27, 104, 51, 238, 223, 3,
            100, 4, 224, 14, 52, 141, 129, 239, 76, 113, 8, 200, 248, 105, 28, 193,
            125, 194, 29, 181, 249, 185, 39, 106, 77, 228, 166, 114, 154, 201, 9, 120,
            101, 47, 138, 5, 33, 15, 225, 36, 18, 240, 130, 69, 53, 147, 218, 142,
            150, 143, 219, 189, 54, 208, 206, 148, 19, 92, 210, 241, 64, 70, 131, 56,
            102, 221, 253, 48, 191, 6, 139, 98, 179, 37, 226, 152, 34, 136, 145, 16,
            126, 110, 72, 195, 163, 182, 30, 66, 58, 107, 40, 84, 250, 133, 61, 186,
            43, 121, 10, 21, 155, 159, 94, 202, 78, 212, 172, 229, 243, 115, 167, 87,
            175, 88, 168, 80, 244, 234, 214, 116, 79, 174, 233, 213, 231, 230, 173, 232,
            44, 215, 117, 122, 235, 22, 11, 245, 89, 203, 95, 176, 156, 169, 81, 160,
            127, 12, 246, 111, 23, 196, 73, 236, 216, 67, 31, 45, 164, 118, 123, 183,
            204, 187, 62, 90, 251, 96, 177, 134, 59, 82, 161, 108, 170, 85, 41, 157,
            151, 178, 135, 144, 97, 190, 220, 252, 188, 149, 207, 205, 55, 63, 91, 209,
            83, 57, 132, 60, 65, 162, 109, 71, 20, 42, 158, 93, 86, 242, 211, 171,
            68, 17, 146, 217, 35, 32, 46, 137, 180, 124, 184, 38, 119, 153, 227, 165,
            103, 74, 237, 222, 197, 49, 254, 24, 13, 99, 140, 128, 192, 247, 112, 7
    };

    public final static int[] AlogTable = {
            1, 3, 5, 15, 17, 51, 85, 255, 26, 46, 114, 150, 161, 248, 19, 53,
            95, 225, 56, 72, 216, 115, 149, 164, 247, 2, 6, 10, 30, 34, 102, 170,
            229, 52, 92, 228, 55, 89, 235, 38, 106, 190, 217, 112, 144, 171, 230, 49,
            83, 245, 4, 12, 20, 60, 68, 204, 79, 209, 104, 184, 211, 110, 178, 205,
            76, 212, 103, 169, 224, 59, 77, 215, 98, 166, 241, 8, 24, 40, 120, 136,
            131, 158, 185, 208, 107, 189, 220, 127, 129, 152, 179, 206, 73, 219, 118, 154,
            181, 196, 87, 249, 16, 48, 80, 240, 11, 29, 39, 105, 187, 214, 97, 163,
            254, 25, 43, 125, 135, 146, 173, 236, 47, 113, 147, 174, 233, 32, 96, 160,
            251, 22, 58, 78, 210, 109, 183, 194, 93, 231, 50, 86, 250, 21, 63, 65,
            195, 94, 226, 61, 71, 201, 64, 192, 91, 237, 44, 116, 156, 191, 218, 117,
            159, 186, 213, 100, 172, 239, 42, 126, 130, 157, 188, 223, 122, 142, 137, 128,
            155, 182, 193, 88, 232, 35, 101, 175, 234, 37, 111, 177, 200, 67, 197, 84,
            252, 31, 33, 99, 165, 244, 7, 9, 27, 45, 119, 153, 176, 203, 70, 202,
            69, 207, 74, 222, 121, 139, 134, 145, 168, 227, 62, 66, 198, 81, 243, 14,
            18, 54, 90, 238, 41, 123, 141, 140, 143, 138, 133, 148, 167, 242, 13, 23,
            57, 75, 221, 124, 132, 151, 162, 253, 28, 36, 108, 180, 199, 82, 246, 1
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
        List<String> data = RoundKeyOperation(encrypt, keys.get(10));


        for (int i = 9; i >= 0; i--) {

            if (i == 0) {
                String[][] value = InverseshiftRows(data);
                data = InverseSbox(value);
                data = RoundKeyOperation(data, keys.get(i));


            } else {
                String[][] value = InverseshiftRows(data);
                data = InverseSbox(value);
                data = RoundKeyOperation(data, keys.get(i));
                System.out.println("Round " + i + " " + data);
                int count = -1;
                for (int p = 0; p < 4; p++) {
                    for (int j = 0; j < 4; j++) {
                        count++;
                        value[j][p] = data.get(count);
                    }
                }

                MixCol m1 = new MixCol();
                value = m1.InvMixColumns(value);
                data.clear();
                for (int p = 0; p < 4; p++) {
                    for (int j = 0; j < 4; j++) {
                        data.add(value[j][p]);
                    }
                }
            }


        }
        System.out.println("Round " + 0 + " " + data);
        String dec = "";
        for(int i=0; i<data.size();i++){
            dec += data.get(i);
        }
        byte[] b = DatatypeConverter.parseHexBinary(dec);

        try {
            System.out.println(new String(b, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private static List<String> InverseSbox(String[][] value) {
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean b = false;
                String val = value[j][i];
                for (int k = 0; k < Sbox.length; k++) {
                    for (int p = 0; p < Sbox[k].length; p++) {
                        if (value[j][i].equals(Sbox[k][p])) {
                            b = true;
                            data.add("" + Integer.toHexString(k).toUpperCase() + Integer.toHexString(p).toUpperCase());
                        }

                    }
                }
                if (!b) {
                    System.out.println("Data in SBOX is not Found" + val + " THis ");
                }
            }
        }

//        System.out.println("ANS : "+data);
        return data;
    }

    private static String[][] InverseshiftRows(List<String> afR) {
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
            for (int j = 3; j >= 0; j--) {
                int a = j + shift;
                a = a % 4;
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

    private static List<String> RoundKeyOperation(List<String> encrypt, List<String> strings) {

        List<String> repeatedafR = new ArrayList<>();
        String[][] data = new String[4][4];
        for (int p = 0; p < encrypt.size(); p++) {
            if (strings.get(p).length() == 3) {
                strings.set(p, strings.get(p).substring(1, 3));
            } else {
            }
            int result = Integer.parseInt(encrypt.get(p), 16) ^
                    Integer.parseInt(strings.get(p), 16);
            String s = Integer.toHexString(result).toUpperCase();
            if (s.length() < 2) {
                s = "0" + s;
            }
            repeatedafR.add(s);

        }

        return repeatedafR;
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
        keys.put(9, Arrays.asList("BF", "E2", "BF", "90", "45", "59", "FA", "B2", "A1", "64", "80", "B4", "F7", "F1", "CB", "D8"));
        keys.put(10, Arrays.asList("28", "FD", "DE", "F8", "6D", "A4", "24", "4A", "CC", "C0", "A4", "FE", "3B", "31", "6F", "26"));
    }
}
