package com.liuyf.demo.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 读入一个字符串str，输出字符串str中的连续最长的数字串
 * 在一行内输出str中里连续最长的数字串。
 * 输入
 *  abcd12345ed125ss123456789
 * 输出
 *  123456789
 */

public class Main3 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        char[] chaArray = input.toCharArray();
        String result="",temp = "";
        for (char c : chaArray) {
            if (c >= '0'  && c <='9' ) {
                temp = temp + c;
            }else {
                temp = "";
            }
            if(temp.length() >result.length()) result = temp;
        }
        System.out.println(result);





    }
}
