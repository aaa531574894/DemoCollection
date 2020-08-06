package com.liuyf.demo.question.huawei;

/**
 * <b>特定和的子序列</b>
 * <br/>
 * 给出一个数组和一个固定SUM，求出连续元素和为sum。<br/>
 * 例如：[1,2,3,4,5,6,7,8]  sum = 10输出是1 2 3 4；sum= 15 输出是1 2 3 4 5；4 5 6；7 8。
 * <p>
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main19 {

    public static void main(String[] args) {

        int   aimedSum  = 15;
        int[] inputData = {1, 2, 3, 4, 5, 6, 7, 8};


        for (int subArrayLength = 2; subArrayLength < inputData.length; subArrayLength++) {

            int extraSum = 0;
            for (int i = 0; i < subArrayLength; i++) {
                extraSum += i;
            }
            int beginNum;
            if ((aimedSum - extraSum) >= inputData[0] && (aimedSum - extraSum) % subArrayLength == 0) {
                beginNum = (aimedSum - extraSum) / subArrayLength;
                for (int i = 0; i < subArrayLength; i++) {
                    System.out.print(beginNum + i);
                    System.out.print(" ");
                }
                System.out.println(" ");
            }
        }
    }
}
