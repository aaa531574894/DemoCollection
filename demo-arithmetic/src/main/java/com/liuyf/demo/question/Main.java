package com.liuyf.demo.question;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("".split(",").length);

        BufferedReader bf            = new BufferedReader(new InputStreamReader(System.in));
        int            fetchSize     = Integer.parseInt(bf.readLine());
        int            arraySize     = Integer.parseInt(bf.readLine());
        int[][]        allData       = new int[arraySize][100];
        int[]          elementRemain = new int[arraySize];
        int            maxLength     = 0;

        int targetArrayLength = 0;
        for (int size = 0; size < arraySize; size++) {
            String   inputLine     = bf.readLine();
            if(inputLine.length()==0){
                elementRemain[size] = 0;
            }else {
                String[] originNumbers = inputLine.split(",");
                elementRemain[size] = originNumbers.length;
                                      maxLength = Math.max(maxLength, originNumbers.length);
                for (int i = 0; i < originNumbers.length; i++) {
                    allData[size][i] = Integer.parseInt(originNumbers[i]);
                    targetArrayLength++;
                }
            }

        }
        int[] targerArray = new int[targetArrayLength];

        int fetchTimes = (maxLength / fetchSize) + 1; //要拿多少趟
        int beginIndex = 0, endIndex = fetchSize - 1;
        int totalIndex = 0;
        for (int fetchTime = 1; fetchTime <= fetchTimes; fetchTime++, beginIndex += fetchSize, endIndex += fetchSize) {

            for (int rowNumber = 0; rowNumber < allData.length; rowNumber++) {
                if (elementRemain[rowNumber] <=0 ) continue; //取光了

                if (elementRemain[rowNumber] >0) { //剩余元素足够
                    int remain = elementRemain[rowNumber];
                    if(remain>fetchSize){
                        remain = fetchSize;
                    }
                    System.arraycopy(allData[rowNumber], beginIndex, targerArray, totalIndex, remain);
                    totalIndex += remain;
                    elementRemain[rowNumber] = elementRemain[rowNumber] - remain;
                }

            }


        }
        for (int i = 0; i < targetArrayLength; i++) {
            System.out.print(targerArray[i]);
            if(i!=targetArrayLength-1){
                System.out.print(",");
            }
        }


    }
}
