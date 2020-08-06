package com.liuyf.demo.question.huawei;

import java.util.BitSet;

/**
 * 阿里五福
 *
 * 集五福作为近年来大家喜闻乐见迎新春活动，集合爱国福、富强福、和谐福、友善福、敬业福即可分享超大红包
 *
 * 以0和1组成的长度为5的字符串代表每个人所得到的福卡，每一位代表一种福卡，1表示已经获得该福卡，单类型福卡不超过1张，随机抽取一个不超过10人团队，求该团队最多可以集齐多少套五
 *
 * 输入描述:
 *
 * 输入类似11010,00110，由0、1组成的长度为5的字符串，代表指定团队中每个人福卡获得情况
 *
 * 注意1：1人也可以是一个团队
 *
 * 注意2：多人之间的福卡以英文逗号隔开
 *
 * 输出描述:
 *
 * 输出该团队能凑齐多少套五福
 *
 * 示例1
 *
 * 输入
 *
 * 11001,11100
 *
 * 输出
 *
 * 0
 *
 * 说明
 *
 * 第4个福卡无人获得，无法凑齐五福
 *
 * <p>
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main24 {


    public static void main(String[] args) {
        BitSet card1 = new BitSet(5), card2 = new BitSet(5), card3 = new BitSet(5), card4 = new BitSet(5), card5 = new BitSet(5);

        int[][] userCards = {
                {1,0,0,1,1},
                {0,1,1,1,0},
                {0,0,0,0,1},
                {1,0,0,0,0}
        };


        for (int row = 0; row < userCards.length; row++) {
            if (userCards[row][0] > 0) {
                card1.set(row);
            }
            if (userCards[row][1] > 0) {
                card2.set(row);
            }
            if (userCards[row][2] > 0) {
                card3.set(row);
            }
            if (userCards[row][3] > 0) {
                card4.set(row);
            }
            if (userCards[row][4] > 0) {
                card5.set(row);
            }
        }
        int minimum = userCards.length;

        if (card1.cardinality() < minimum) {
            minimum = card1.cardinality();
        }
        if (card2.cardinality() < minimum) {
            minimum = card2.cardinality();
        }
        if (card3.cardinality() < minimum) {
            minimum = card3.cardinality();
        }
        if (card4.cardinality() < minimum) {
            minimum = card4.cardinality();
        }
        if (card5.cardinality() < minimum) {
            minimum = card5.cardinality();
        }
        System.out.println("共集齐了"+minimum+"套");


    }
}
