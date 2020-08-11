package com.liuyf.demo.arithmetic.dynamic.plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 题目源:
 * https://www.nowcoder.com/ta/exam-all
 * <p>
 * 题目编号: WY1
 * <p>
 *
 * <B>奖学金</B>
 * 题目描述:
 * 小v今年有n门课，每门都有考试，为了拿到奖学金，小v必须让自己的平均成绩至少为avg。
 * 每门课由平时成绩和考试成绩组成，满分为r。现在他知道每门课的平时成绩为ai ,
 * 若想让这门课的考试成绩多拿一分的话，小v要花bi 的时间复习，不复习的话当然就是0分。
 * 同时我们显然可以发现复习得再多也不会拿到超过满分的分数。为了拿到奖学金，小v至少要花多少时间复习。
 * <p>
 * <p>
 * 输入描述:
 * 第一行三个整数n,r,avg(n大于等于1小于等于1e5，r大于等于1小于等于1e9,avg大于等于1小于等于1e6)，接下来n行，每行两个整数ai和bi，均小于等于1e6大于等于1
 * <p>
 * 输出描述:
 * 一行输出答案。
 * <p>
 * 示例1
 * 输入<br/>
 * 5 10 9<br />0 5<br />9 1<br />8 1<br />0 1<br />9 100<br />
 * <p>
 * 输出
 * 43
 *
 * <p>
 * <p>
 * Created by liuyf on 2020/8/11.
 */

public class Main {


    // 用treeSet有顺序的特性,先将耗时低的课程给修满
    public static void main(String[] args) throws Exception {
        BufferedReader bf            = new BufferedReader(new InputStreamReader(System.in));
        int            maximumScore  = 10; //满分
        int            aimedAvgScore = 9;  //目标平均分
        int            courseNum     = 5;  //课程数
        String         inputLine;
        String[]       inputNumbers;

        inputLine    = bf.readLine();
        inputNumbers = inputLine.split(" ");

        courseNum     = Integer.parseInt(inputNumbers[0]);
        maximumScore  = Integer.parseInt(inputNumbers[1]);
        aimedAvgScore = Integer.parseInt(inputNumbers[2]);

        TreeSet<CourseScoreInfo> treeSet = new TreeSet<>((a, b) -> {
            if (a.getTimeCostPerScore() > b.getTimeCostPerScore()) {
                return 1;
            } else {
                return -1;
            }

        });

        int currentTotalScore = 0; //当前总分数
        for (int i = 0; i < courseNum; i++) {
            inputLine    = bf.readLine();
            inputNumbers = inputLine.split(" ");
            int score    = Integer.parseInt(inputNumbers[0]);
            int timeCost = Integer.parseInt(inputNumbers[1]);
            treeSet.add(new CourseScoreInfo(i, score, timeCost));
            currentTotalScore += score;
        }


        int scoreRemain   = aimedAvgScore * courseNum - currentTotalScore; //需提升分数
        int totalTimeCost = 0;

        for (Iterator<CourseScoreInfo> it = treeSet.iterator(); scoreRemain > 0 && it.hasNext(); ) {
            CourseScoreInfo courseInfo = it.next();
            if (courseInfo.getScore() >= maximumScore) continue;

            int sc = Math.min((maximumScore - courseInfo.getScore()), scoreRemain);
            totalTimeCost += sc * courseInfo.getTimeCostPerScore();

            scoreRemain -= sc;
        }

        System.out.println(totalTimeCost);

    }


    public static class CourseScoreInfo {
        private int courseNum;
        private int score;            //当前分数
        private int timeCostPerScore; //每分提升耗时


        public CourseScoreInfo(int courseNum, int score, int timeCostPerScore) {
            this.courseNum        = courseNum;
            this.score            = score;
            this.timeCostPerScore = timeCostPerScore;
        }

        public int getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(int courseNum) {
            this.courseNum = courseNum;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getTimeCostPerScore() {
            return timeCostPerScore;
        }

        public void setTimeCostPerScore(int timeCostPerScore) {
            this.timeCostPerScore = timeCostPerScore;
        }
    }
}
