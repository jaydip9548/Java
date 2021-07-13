package DES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MixCol {
//  static  String[][] data = {
//            {"BA", "84", "E8", "1B"},
//            {"75", "A4", "8D", "40"},
//            {"F4", "8D", "06", "7D"},
//            {"7A", "32", "0E", "5D"}
//    };


//    public static void main(String[] args) {
//        Arrays.deepToString(InvMixColumns(data));
//    }
    public  String[][] InvMixColumns(String[][] baseHexBlock)
    {
        String[][] newHexBlock = new String[4][4];
        int[][] InverseRijndaelGaloisField = {
                {14, 11, 13, 9},
                {9, 14, 11, 13},
                {13, 9, 14, 11},
                {11, 13, 9, 14}
        };

        for (int i = 0; i < newHexBlock.length; i++)
        {
            for (int j = 0; j < newHexBlock[0].length; j++)
            {
                newHexBlock[i][j] = InvMixColumnMultiplication(baseHexBlock ,InverseRijndaelGaloisField , i , j);
            }
        }

        return newHexBlock;
    }
    public static long byTwoMultiply(long num)
    {
        if (num < 128)
            return 2*num;
        else
        {
            long res = (2 * num) ^ 27;
            String resString = Long.toHexString(res).toUpperCase();
            while(resString.length() > 2)
            {
                StringBuilder hexSb = new StringBuilder(resString);
                hexSb.deleteCharAt(0);
                resString = hexSb.toString();
            }
            return Long.parseLong(resString , 16);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String InvMixColumnMultiplication(String[][] hexBlock , int[][] InverseRijndaelBlock , int row , int col)
    {
        String hexResult = "";
        long[] values = new long[4];

        for (int i = 0; i < values.length; i++)
        {
            switch (InverseRijndaelBlock[row][i])
            {
                case 9:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 11:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 13:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 14:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    break;
            }
        }
        hexResult = Long.toHexString(values[0] ^ values[1] ^ values[2] ^ values[3]).toUpperCase();

        while(hexResult.length() > 2)
        {
            StringBuilder hexSb = new StringBuilder(hexResult);
            hexSb.deleteCharAt(0);
            hexResult = hexSb.toString();
        }

        if(hexResult.length() == 1)
        {
            hexResult =  "0" + hexResult;
        }

        return hexResult;
    }
    //------------------------------------------------------------------------------------------------------------------

}
