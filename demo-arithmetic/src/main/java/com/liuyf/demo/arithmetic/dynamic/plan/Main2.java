package com.liuyf.demo.arithmetic.dynamic.plan;

/**
 * 题目描述
 * 一条长l的笔直的街道上有n个路灯，若这条街的起点为0，终点为l，第i个路灯坐标为ai ，<br/>
 * 每盏灯可以覆盖到的最远距离为d，为了照明需求，所有灯的灯光必须覆盖整条街，<br/>
 * 但是为了省电，要使这个d最小，请找到这个最小的d。
 * <br/>
 * 输入描述:
 * 每组数据第一行两个整数n和l（n大于0小于等于1000，l小于等于1000000000大于0）。第二行有n个整数(均大于等于0小于等于l)，为每盏灯的坐标，多个路灯可以在同一点。<br/>
 * 输出描述:
 * 输出答案，保留两位小数。
 * 示例1
 * 输入<br/>
 * <p>
 * 7 15<br />15 5 3 7 9 14 0
 * 输出<br/>
 * <p>
 * 2.50
 * <p>
 * <br/>
 * <p>
 * <p>
 * Created by liuyf on 2020/8/11.
 */


import java.io.*;
import java.util.*;


public class Main2 {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;

        while ((inputLine = bf.readLine()) != null && inputLine.length() > 0) {
            String[] info       = inputLine.split(" ");
            int      number     = Integer.parseInt(info[0]);
            int      roadLength = Integer.parseInt(info[1]);

            String[] inputNumbers = bf.readLine().split(" ");

            int[] fromArray = new int[number + 2];

            for (int i = 0; i < inputNumbers.length; i++) {
                fromArray[i] = Integer.parseInt(inputNumbers[i]);
            }
            fromArray[fromArray.length - 1] = 0;
            fromArray[fromArray.length - 2] = roadLength;


            //排序
            Arrays.sort(fromArray);
            //冒泡一次找到最大值

            int maxInterval = Integer.MIN_VALUE;

            for (int index = 1, next = index + 1; next < fromArray.length - 1; index++, next++) {
                maxInterval = Math.max(maxInterval, fromArray[next] - fromArray[index]);
            }
            int   headMaxInterval = 0, tailMaxInterval = 0;
            headMaxInterval = fromArray[1] - fromArray[0];
            tailMaxInterval = fromArray[fromArray.length - 1] - fromArray[fromArray.length - 2];

            maxInterval = Math.max(2 * Math.max(tailMaxInterval, headMaxInterval), maxInterval);


            System.out.println((float) maxInterval / 2 + "0");


        }


    }


}
