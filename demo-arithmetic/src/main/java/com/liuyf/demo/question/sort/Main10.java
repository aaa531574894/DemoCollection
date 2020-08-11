package com.liuyf.demo.question.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

public class Main10 {


    // 7 5 9 4 2 6 8 3 5 4 3 9
    // length 12
    //
    public static void main(String[] args)  throws Exception{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input ;
        while ((input = bf.readLine()) != null && input.length() > 0) {

            String[] inputNumbers = input.split(" ");
            int[] inputData = new int[inputNumbers.length];
            for (int i = 0; i < inputData.length; i++) {
                inputData[i] = Integer.parseInt(inputNumbers[i]);
            }

            final int length = inputData.length;
            final int targetIndex = length - 1;
            final int maxStepSize = (length / 2) - 1;

            int minmumStepCount = Integer.MAX_VALUE;
            boolean reached = false; //是否到达过终点


            for (int stepSize = 1; stepSize < maxStepSize; stepSize++) {
                int tempStepCount=1;

                for (int index = stepSize; index < length; index += inputData[index]) {
                    if (index == targetIndex) {
                        reached         = true;
                        minmumStepCount = Math.min(minmumStepCount, tempStepCount);
                        break;
                    }
                    tempStepCount++;
                }
            }

            if(reached){
                System.out.println(minmumStepCount);
            }else {
                System.out.println("-1");
            }





        }

    }


}
