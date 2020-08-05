package com.liuyf.demo.question.huawei;

/**
 * 整数分解之素数
 * <p>
 * 给定一个32位正整数，对其进行因数分解，分解成两个素数的乘积，如果不能分解输出-1，-1。
 * <p>
 * 输入
 * <p>
 * 15
 * <p>
 * 输出
 * <p>
 * 3，5
 * <p>
 * <p>
 * Created by liuyf on 2020/8/5.
 */

public class Main14 {

    public static void main(String[] args) {

        int a = 16;
        int[] result = new int[2];
        if (!split(result, a, 0)) {
            System.out.println("-1 -1");
        } else {
            for (int i : result) {
                System.out.println(i + " ");
            }
        }


    }

    public static boolean split(int[] fromArray, int fromNumber, int index) {
        if (index > 1) return false;  //分解次数超过2 说明与预期不符合

        int thisResult = -1;
        if ((thisResult = fenjie(fromNumber)) > -1 && thisResult != fromNumber) { //还能再分解
            fromArray[index] = thisResult;
            index++;
            return split(fromArray, fromNumber / thisResult, index);
        } else {         //不能分解了
            fromArray[index] = fromNumber;
            return index == 1;
        }
    }

    public static int fenjie(int a) {
        if (a < 2) {
            return -1;
        } else {
            for (int i = 2; i <= a; i++) {
                if (a % i == 0 ) {
                    return i;
                }
            }
        }
        return -1;
    }
}
