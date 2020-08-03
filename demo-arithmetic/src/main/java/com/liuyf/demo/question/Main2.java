package com.liuyf.demo.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/2.
 */

public class Main2 {


    public static void main(String args[]) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lineNumber = Integer.parseInt(br.readLine().trim());
        String[] otherInputLines = new String[lineNumber];
        for (int i = 0; i < lineNumber; i++) {
            otherInputLines[i] = br.readLine();
        }

        for (String toHandle : otherInputLines) {
            char[] rtn = trim(toHandle.toCharArray());
            System.out.println(new String(rtn));
        }


    }
    // Helllo 去掉 lll 第三个
    // Woooow 去掉第AABBCC中的第四个

    private static char[] trim(char[] fromChar) {
        char[] rtn = fromChar;
        for (int i = 2; i < fromChar.length; i++) {
            if (((fromChar[i - 2] == fromChar[i - 1]) && (fromChar[i - 1] == fromChar[i]))
                    || (i > 2 && (fromChar[i - 3] == fromChar[i - 2]) && (fromChar[i - 1] == fromChar[i]))) {
                char[] tempChar = new char[fromChar.length - 1];
                System.arraycopy(fromChar, i+1, fromChar, i , fromChar.length -1 -i );
                System.arraycopy(fromChar, 0, tempChar, 0, fromChar.length-1 );
                rtn = trim(tempChar);
                break;

            }
        }
        return rtn;
    }


}
