package com.liuyf.demo.question.huawei;

/**
 * 6、求子矩阵的最大和
 *
 * 这个题比较经典，给定一个矩阵，求出任意子矩阵的和，输出最大值，用到动态求解，网上都有实现的代码。主要是sum = p[j][m] - p[j][k-1] - p[i-1][m] + p[i-1][k-1]，首先初始化p[i][j] = p[i-1][j] + p[i][j-1] - p[i-1][j-1] + a[i][j]。
 *
 *
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main25 {
}
