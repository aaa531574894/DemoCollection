package com.liuyf.demo.question.huawei;

import java.util.BitSet;
import java.util.Random;

/**
 * 有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
 * <p>
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main23 {


    public static void main(String[] args) {

        Random random = new Random();
        BitSet bitSet = new BitSet(100000000);
        for (int i = 1; i <= 10000000; i++) {
            bitSet.set(random.nextInt(100000000));
        }

        System.out.println("共出现了" + bitSet.cardinality() + "个数组");






    }

}
