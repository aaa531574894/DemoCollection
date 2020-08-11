package com.liuyf.demo.arithmetic.dynamic.plan;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 题目描述
 * <p>
 * 小易经常沉迷于网络游戏.有一次,他在玩一个打怪升级的游戏,他的角色的初始能力值为 a.
 * 在接下来的一段时间内,他将会依次遇见n个怪物,每个怪物的防御力为b1,b2,b3...bn.
 * 如果遇到的怪物防御力bi小于等于小易的当前能力值c,那么他就能轻松打败怪物,
 * 并 且使得自己的能力值增加bi;如果bi大于c,那他也能打败怪物,但他的能力值只能增加bi 与c的最大公约数.
 * 那么问题来了,在一系列的锻炼后,小易的最终能力值为多少?
 * <p>
 * 输入描述:
 * 对于每组数据,第一行是两个整数n(1≤n<100000)表示怪物的数量和a表示小易的初始能力值.
 * 第二行n个整数,b1,b2...bn(1≤bi≤n)表示每个怪物的防御力
 * <p>
 * 输出描述:
 * 对于每组数据,输出一行.每行仅包含一个整数,表示小易的最终能力值
 * <p>
 * 示例1
 * 输入
 * 复制
 * <p>
 * <p>
 * 3 50
 * 50 105 200
 * 5 20
 * 30 20 15 40 100
 * <p>
 * 输出
 * 110<br/>205<br/>
 */

public class Main3 {

    public static void main(String[] args) throws Exception {


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String         inputLine;
        while ((inputLine = bf.readLine()) != null && inputLine.length() > 0) {
            String[] inputNumbers = inputLine.split(" ");

            int quality   = Integer.parseInt(inputNumbers[0]);
            int zhandouli = Integer.parseInt(inputNumbers[1]);


            inputLine = bf.readLine();
            String[] dataInString = inputLine.split(" ");
            int[]    data         = new int[dataInString.length];

            for (int i = 0; i < quality; i++) {
                data[i] = Integer.parseInt(dataInString[i]);
            }

            for (int index = 0; index < data.length; index++) {
                if (data[index] <= zhandouli) {
                    zhandouli += data[index];
                } else {
                    zhandouli += getGongyuShu(zhandouli, data[index]);
                }
            }


            System.out.println(zhandouli);

        }


    }


    public static int getGongyuShu(int a, int b) {

        for (int i = a; i > 1; i--) {

            if (a % i == 0 && b % i == 0) {
                return i;
            }

        }

        return 1;
    }
}
