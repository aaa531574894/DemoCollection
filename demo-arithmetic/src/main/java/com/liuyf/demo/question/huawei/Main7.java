package com.liuyf.demo.question.huawei;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 数字从1开始，遇到数字7就会跳过，比如6后边直接是8，69后边直接是80，现在给你个数字，问是第几位？
 *
 * 比如输入8，输出7，就是第7个数。那89那？请你编程输出。
 *
 *
 */

public class Main7 {


    public static void main(String[] args) throws Exception {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(bufferedReader.readLine());
        int result = 0;
        for (int i = 1; i <= input; i++) {
            if( String.valueOf(i).contains("7")) continue;
            result++;
        }
        System.out.println(result);
    }
}
