package com.liuyf.demo.question.huawei;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 组最大数字
 * 给出几组字符串的数字，需要获得组成的最大数字
 *
 * 比如输入123，546，8，32，输出854632123
 * Created by liuyf on 2020/8/3.
 */

public class Main10 {


    public static void main(String[] args) throws  Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        String[] numbers = input.split(",");


        TreeSet<String> treeSet = new TreeSet<String> ( (a, b)->{
            if(a.equals(b)) return 0;
            char[] aChars = a.toCharArray();
            char[] bChars = b.toCharArray();

            for (int i = 0; i < aChars.length && i < bChars.length; i++) {
                if(aChars[i]>bChars[i]) return -1;
                if(aChars[i]<bChars[i]) return 1;
            }
            return 0;
        });
        for (String str : numbers) {
            treeSet.add(str);
        }
        StringBuilder result = new StringBuilder();
        treeSet.forEach( item->{
            result.append(item);
        });
        System.out.println(result.toString());


    }
}
