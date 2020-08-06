package com.liuyf.demo.question.huawei;

/**
 * 找出出错的数字
 * 数字方阵就是N*N数组成的，其中有一个特殊的方阵，每行、每列、对角线的和都是一样，
 * 现在其中一个数字错误，导致的不在满足和相等，现在要找出这个位置。可以看到出错的位置在<3,2>，并且数字应该是9。
 * <p/>
 * 8 1 6
 * <p/>
 * 3 5 7
 * <p/>
 * 4 0 2
 * <p>
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main18 {

    public static void main(String[] args) {
        int[][] data = {
                {8, 1, 6},
                {3, 5, 7},
                {4, 0, 2}
        };

        boolean found    = false;
        int     wrongRow = 0, wrongColumn = 0;

        int row, column;
        int firstSum = 0, secondSum = 0, wrongSum = 0;

        for (row = 0; row < data.length && !found; row++) {
            int tempSum = 0;
            for (column = 0; column < data.length; column++) {
                tempSum += data[row][column];
            }
            if (row == 0) {
                firstSum = tempSum;
            } else if (row == 1) {
                secondSum = tempSum;
            } else {
                if (tempSum == firstSum && tempSum != secondSum) {
                    wrongSum = secondSum;
                    wrongRow = 2;
                    found    = true;
                } else if (tempSum == secondSum && tempSum != firstSum) {
                    wrongSum = firstSum;
                    wrongRow = 1;
                    found    = true;
                } else if (firstSum == secondSum && tempSum != secondSum) {
                    wrongSum = tempSum;
                    wrongRow = row;
                    found    = true;
                }
            }
        }


        for (column = 0; column < data.length; column++) {
            int tempSum = 0;
            for (row = 0; row < data.length; row++) {
                tempSum += data[row][column];
            }
            if (tempSum == wrongSum) {
                wrongColumn = column;
                break;
            }
        }
        System.out.println("错误的位置是:" + "row:" + wrongRow + " column:" + wrongColumn);


    }

}
