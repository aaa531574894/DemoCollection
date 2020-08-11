package com.liuyf.demo.question.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

public class Main11 {

    // 1 2 5 7 9 1 2 2
    // 4 1 9 14 1  pop结果
    // 1 14 9 1 4
    public static void main(String[] args) throws IOException {

        BufferedReader bf           = new BufferedReader(new InputStreamReader(System.in));
        String         inputLine    = bf.readLine();
        String[]       dataInString = inputLine.split(" ");
        int[]          fromArray    = new int[dataInString.length];
        for (int i = 0; i < fromArray.length; i++) {
            fromArray[i] = Integer.parseInt(dataInString[i]);
        }
        int finalLength = 0;

        //动态规划
        int[][] internalResult = new int[fromArray.length][fromArray.length];

        for (int currentIndex = 0, pre = currentIndex - 1; currentIndex < fromArray.length; currentIndex++, pre = currentIndex - 1) {
            if (pre < 0) {  //第一次入栈
                fromArray[currentIndex] = fromArray[currentIndex];
                finalLength = 1;
                updateTempResult(fromArray, internalResult, currentIndex);
            } else {

                boolean changed  =false;
                for (int cursor = pre; cursor >= 0; cursor--) {
                    if (internalResult[cursor][pre] == fromArray[currentIndex]) {
                        fromArray[cursor] = 2* fromArray[currentIndex]; //更新元素
                        updateTempResult(fromArray, internalResult, currentIndex);
                        finalLength = cursor+1;
                        changed = true;
                        break;
                    }
                }
                if (!changed){
                    fromArray[finalLength] = fromArray[currentIndex];
                    updateTempResult(fromArray, internalResult, currentIndex);
                    finalLength++;
                }

            }


        }

        //for (int i = finalLength - 1; i >= 0; i--) {
        for (int i =0 ; i < finalLength; i++) {
            System.out.print(fromArray[i]+" ");
        }




    }

    private static void updateTempResult(int[] fromArray, int[][] tempResult, int index){
        tempResult[index][index] = fromArray[index];
        for (int pre = index - 1; pre >= 0; pre--) {
            tempResult[pre][index] = tempResult[pre][index-1] + (fromArray[index]);
        }

    }













}
