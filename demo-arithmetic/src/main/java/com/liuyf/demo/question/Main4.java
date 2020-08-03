package com.liuyf.demo.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
 import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 通过键盘输入一串小写字母(a~z)组成的字符串。
 * 请编写一个字符串归一化程序，统计字符串中相同字符出现的次数，并按字典序输出字符及其出现次数。
 * 例如字符串"babcc"归一化后为"a1b2c2"
 *
 *
 * 输入： dabcab
 * 输出： a2b2c1d1
 * <p>
 * <p>
 * Created by liuyf on 2020/8/2.
 */

public class Main4 {

    public static void main(String[] args)  throws Exception{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        SortedMap<Character, Integer> counter = new TreeMap<>((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            }else if (o1 == o2) {
                return 0;
            }
            return -1;
        });
        for (char c : input.toCharArray()) {

            counter.putIfAbsent( c , 0);
            counter.computeIfPresent( c , (k,v)->{
                v = v + 1;
                return v;
            });
        }

        StringBuilder sb = new StringBuilder();
        counter.forEach( (k,v)->{
            sb.append(k).append(v);
        });
        System.out.println(sb.toString());


    }

}
