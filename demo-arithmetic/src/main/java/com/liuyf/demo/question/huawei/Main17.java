package com.liuyf.demo.question.huawei;

import sun.security.x509.AVA;

import java.util.LinkedList;

/**
 * 连续数列
 * <p>
 * 已知连续正整数数列{K}=K1,K2,K3...Ki的各个数相加之和为S，i=N (0<S<100000, 0<N<100000), 求此数列K。
 * <p>
 * <p>
 * <p>
 * 输入描述:
 * <p>
 * 输入包含两个参数，1）连续正整数数列和S，2）数列里数的个数N。
 * <p>
 * 输出描述:
 * <p>
 * 如果有解输出数列K，如果无解输出-1
 * <p>
 * 示例1
 * <p>
 * 输入
 * <p>
 * 525 6
 * <p>
 * 输出
 * <p>
 * 85 86 87 88 89 90
 */
public class Main17 {

    public static void main(String[] args) {

        int aimedSum = 165, number = 3;

        int avgNum = aimedSum / number;

        int sumExcludeBaseNumber = 0;
        for (int i = 0; i < number; i++) {
            sumExcludeBaseNumber += i;
        }

        if ((aimedSum - sumExcludeBaseNumber) % number == 0) {   //如果刚好可以模尽  说明是对的
            System.out.println((aimedSum - sumExcludeBaseNumber) / number);

        } else {
            System.out.println("-1");
        }


    }
}
