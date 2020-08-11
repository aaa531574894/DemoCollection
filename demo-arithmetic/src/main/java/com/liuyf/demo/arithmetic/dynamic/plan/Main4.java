package com.liuyf.demo.arithmetic.dynamic.plan;

/**
 * 计算最少出列多少位同学，使得剩下的同学排成合唱队形
 *
 * 说明：
 *
 * N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。
 * 合唱队形是指这样的一种队形：设K位同学从左到右依次编号为1，2…，K，他们的身高分别为T1，T2，…，TK，   则他们的身高满足存在i（1<=i<=K）使得T1<T2<......<Ti-1<Ti>Ti+1>......>TK。
 *
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 * <p>
 * <p>
 * Created by liuyf on 2020/8/12.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main4 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while ((str = br.readLine()) != null) {
            int total = Integer.parseInt(str);
            int[] data = new int[total];
            int[] reverseData = new int[total];
            // 处理身高数据
            str = br.readLine();
            String[] arr = str.split(" ");

            for (int i = 0; i < total; ++i) {
                data[i] = Integer.parseInt(arr[i]);
            }
            System.out.println(newSolution(data));

        }

    }


    public static int newSolution(int[] studentTalls){
        int[] maxDpInc = new int[studentTalls.length];
        int[] maxDpDec = new int[studentTalls.length];


        //求最长递增子序列
        for (int index = 0; index < studentTalls.length; index++) {
            int tempMaxLength =1;
            for (int pre = index - 1; ; pre--) {
                if(pre<0){
                    break;
                }else {
                    if(studentTalls[pre]<studentTalls[index]){
                        tempMaxLength = Math.max(tempMaxLength, maxDpInc[pre] + 1);
                    }else if( studentTalls[pre] == studentTalls[index]){
                        tempMaxLength = Math.max(tempMaxLength, maxDpInc[pre] );
                    }
                }
            }
            maxDpInc[index] = tempMaxLength;
        }
        //求最长递减子序列
        for (int index = studentTalls.length - 1; index >= 0; index--) {
            int tempMaxLength =1;
            for (int next = index + 1; ; next++) {
                if (next > studentTalls.length - 1) {
                    break;
                }else {
                    if (studentTalls[index] == studentTalls[next]  ) {
                        tempMaxLength = Math.max(tempMaxLength, maxDpDec[next]);
                    }else if( studentTalls[index] > studentTalls[next] ){
                        tempMaxLength = Math.max(tempMaxLength, maxDpDec[next]+1);
                    }
                }
            }
            maxDpDec[index] = tempMaxLength;
        }

        int min  = Integer.MAX_VALUE;
        for (int i = 0; i < maxDpDec.length; i++) {

            int tempMin = (i + 1) - maxDpInc[i] + (studentTalls.length - 1 - i + 1 - maxDpDec[i]);
            if (tempMin < min) {
                min = tempMin;
            }
        }


        return min;




    }

}
