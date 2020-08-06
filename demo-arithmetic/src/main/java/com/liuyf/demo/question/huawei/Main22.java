package com.liuyf.demo.question.huawei;

/**
 * String加密
 *
 * 给你一串未加密的字符串str，通过对字符串的每一个字母进行改变来实现加密，加密方式是在每一个字母str[i]偏移特定数组元素a[i]的量，
 * 数组a前三位已经赋值：a[0]=1,a[1]=2,a[2]=4。当i>=3时，数组元素a[i]=a[i-1]+a[i-2]+a[i-3]，
 *
 * 例如：原文 abcde 加密后 bdgkr，其中偏移量分别是1,2,4,7,13。
 *
 * 输入描述:
 *
 * 第一行为一个整数n（1<=n<=1000），表示有n组测试数据，每组数据包含一行，原文str（只含有小写字母，0<长度<=50）。
 *
 *
 *
 * 输出描述:
 *
 * 每组测试数据输出一行，表示字符串的密文
 *
 * 示例1
 *
 * 输入
 *
 * 1
 *
 * xy
 *
 * 输出
 *
 * ya
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main22 {


    public static void main(String[] args) {

        char[] chars = "xy".toCharArray();
        int[] offsets = new int[chars.length];


        for (int index = 0; index < chars.length; index++) {
            if (index==0){
                offsets[index] =1 ;
            }else if(index ==1){
                offsets[index] =2 ;
            }else if(index == 2){
                offsets[index] =4 ;
            }
            if(index>=3) {
                offsets[index] = offsets[index - 1] + offsets[index - 2] + offsets[index - 3];
            }

            chars[index] = (char) (((chars[index] + offsets[index]) % 'z'));
            if(chars[index] < 'a'){
                chars[index] +=  ('a'-1);
            }
        }

        System.out.println(new String(chars));


    }

}
