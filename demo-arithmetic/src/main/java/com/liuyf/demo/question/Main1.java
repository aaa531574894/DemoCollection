package com.liuyf.demo.question;

/**
 * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。现在小Y使用1024元的纸币购买了一件价值为N (0 < N \le 1024)N(0<N≤1024)的商品，请问最少他会收到多少硬币？
 * <p>
 * <p>
 * Created by liuyf on 2020/8/2.
 */
import java.util.Scanner;
import java.io.*;





public class Main1 {

    public static void main(String[] args) throws Exception{
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = bis.readLine();
        int input = Integer.parseInt(inputStr);
        input = 1024 - input ;
        int coinCount = 0;
        int remain = input ;
        while (remain >0){
            if ( remain / 64 >0){
                coinCount += (remain/64);
                remain = remain%64;
            } else if ( remain / 16 >0 ){
                coinCount += (remain/16);
                remain = remain%16;
            } else if ( remain / 4 >0 ){
                coinCount += (remain/4);
                remain = remain%4;
            } else if ( remain / 1 >0 ){
                coinCount += (remain/1);
                remain = remain%1;
            }
        }
        System.out.println(coinCount);


    }



}
