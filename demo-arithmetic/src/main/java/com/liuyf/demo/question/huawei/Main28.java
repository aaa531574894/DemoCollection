package com.liuyf.demo.question.huawei;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 给定n个字符串，请对n个字符串按照字典序排列。
 * 输入描述:
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 输出描述:
 * 数据输出n行，输出结果为按照字典序排列的字符串。
 *
 * 示例1
 *
 * 输入:
 * 9
 * cap
 * to
 * cat
 * card
 * two
 * too
 * up
 * boat
 * boot
 *
 * 输出:
 * boat
 * boot
 * cap
 * card
 * cat
 * to
 * too
 * two
 * up
 *
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

public class Main28 {


    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());

        String[] strArray = new String[size];

        for(int index =0;index<size;index++){
            String thisInput = bf.readLine();
            strArray[index] = thisInput;
            //插入排序
            for(int cur =index, pre = index-1; pre>=0 ; pre--,cur -- ){
                int result = compare( strArray[cur],strArray[pre]);
                if( result > 0 ) break;
                else {
                    swap(strArray,pre,cur);
                }
            }
        }

        for( int i=0;i<size;i++){
            System.out.println(strArray[i]);
        }
    }

    public static void swap(String[] array,int a,int b){
        String temp= array[a];
        array[a] =array[b];
        array[b] =temp;
    }

    // a > b 返回1  否则-1
    public static int compare(String a,String b){
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        for(int index=0; index<aChars.length && index < bChars.length ;index++){
            if( aChars[index] > bChars[index]){
                return 1;
            }else if ( aChars[index] < bChars[index]){
                return -1;
            }
        }

        return aChars.length>bChars.length ? 1 : -1;

    }
}
