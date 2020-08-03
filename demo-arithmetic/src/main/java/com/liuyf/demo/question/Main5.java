package com.liuyf.demo.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 输入一个整形数组（可能有正数和负数），求数组中连续子数组（最少有一个元素）的最大和。要求时间复杂度为O(n)。
 * <p>
 * 第一行为 元素个数
 * 输入
 * 8
 * 1
 * -2
 * 3
 * 10
 * -4
 * 7
 * 2
 * -5
 * 输出
 * 18
 * <p>
 * <p>
 * Created by liuyf on 2020/8/2.
 */

public class Main5 {


    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String lineNumber = bufferedReader.readLine();

        int[] arrays = new int[Integer.parseInt(lineNumber)];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = Integer.parseInt(bufferedReader.readLine());
        }

        //动态规划！  利用一个数组存储可以复用的结果的思想
        int[][] resultSet = new int[arrays.length][arrays.length]; //储存各子连续数组的和
        int maxMum = Integer.MIN_VALUE;
        for (int subArrayLength = 1; subArrayLength <= arrays.length; subArrayLength++) {
            for (int j = 0; j < arrays.length; j++) {
                int end = j + subArrayLength - 1;
                if (end > arrays.length - 1) break;
                if (end - 1 < 0)
                    resultSet[j][end] = arrays[end];
                else
                    resultSet[j][end] = resultSet[j][end - 1] + arrays[end];
                if (resultSet[j][end] > maxMum) maxMum = resultSet[j][end];
            }
        }


        // 遍历  时间复杂度为O(n*n) 效率太低
        /*for (int i = 1; i < arrays.length; i++) { //i 为子数组长度

            for (int j = 0; j < arrays.length; j++) {
                if (j+i-1 >= arrays.length) break;
                int[] tempArray = new int[i];
                System.arraycopy(arrays, j, tempArray, 0, i);
                int tempResult = getAmout(tempArray);
                if(tempResult>maxMum) maxMum = tempResult;
            }
        }*/


        System.out.print(maxMum);

    }

}
