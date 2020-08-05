package com.liuyf.demo.question.huawei;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 2、求子集的最大值：
 *
 * 给一个数组，找出相邻元素的和最大。
 *
 * 例如 例1、[1,-2,4,8,-1,3,-4]  -> 最大为14  因为相邻的4,8,-1,3之和
 *
 * <p>
 * <p>
 * Created by liuyf on 2020/8/5.
 */

public class Main16 {


    //动态规划
    public static void main(String[] args) throws Exception {

        //
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = bufferedReader.readLine();
        String[] inputNumbers = inputLine.split(",");
        int[] fromArray = new int[inputNumbers.length];

        for (int index = 0; index < fromArray.length; index++) {
            fromArray[index] = Integer.parseInt(inputNumbers[index]);
        }

        int[][] tempResult = new int[inputNumbers.length][inputNumbers.length];


        int maxmum = Integer.MIN_VALUE;
        for (int length = 0; length < fromArray.length; length++) {

            for (int j = 0; j + length < fromArray.length; j++) {
                int result;
                if(length>0){
                    result = tempResult[j][j + length - 1] + fromArray[j + length];
                }else {
                    result = fromArray[j];
                }
                tempResult[j][j + length] = result;
                if(result>maxmum) maxmum = result;
            }
        }
        System.out.println(maxmum);





    }
}
