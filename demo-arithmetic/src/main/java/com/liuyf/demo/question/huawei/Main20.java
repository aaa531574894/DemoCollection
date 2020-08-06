package com.liuyf.demo.question.huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * 小兔子繁殖
 * <p>
 * 小兔子达到3个月时具备生育能力,可以每一个月都生一只小兔子;新出生的小兔子到达三个月时,也再次具备生育能力.
 * <p/>
 * 问: 现有1只3个月的小兔子,10个月时总共有多少只小兔子?
 * <p>
 * <p>
 * Created by liuyf on 2020/8/6.
 */

public class Main20 {

    public static void main(String[] args) {
        int month = 10;
        handleInListenerMode(month);
        handleWithIterator(month);


    }


    public static void handleWithIterator(int month) {


        int threeMonthsRabbitNumer = 1, twoMonthsRabbitNumber = 0, oneMonthRabbitNumber = 0, newBornRabbitNumber = 0;
        for (int m = 0; m < month; m++) {
            if (m == 0) {
                newBornRabbitNumber = threeMonthsRabbitNumer;
            } else if (m == 1) {
                oneMonthRabbitNumber = newBornRabbitNumber;
                newBornRabbitNumber  = threeMonthsRabbitNumer;
            } else {
                threeMonthsRabbitNumer += twoMonthsRabbitNumber;
                twoMonthsRabbitNumber = oneMonthRabbitNumber;
                oneMonthRabbitNumber  = newBornRabbitNumber;
                newBornRabbitNumber   = threeMonthsRabbitNumer;
            }
        }
        System.out.println(threeMonthsRabbitNumer + twoMonthsRabbitNumber + oneMonthRabbitNumber + newBornRabbitNumber);


    }


    public static void handleInListenerMode(int month) {
        Set<Rabbit>  child        = new HashSet<>();
        AgeListenner ageListenner = new AgeListenner(child);

        Set<Rabbit> allRabbit   = new HashSet<>();
        Rabbit      firstRabbit = new Rabbit();
        firstRabbit.age = 3;
        firstRabbit.addListener(ageListenner);
        allRabbit.add(firstRabbit);

        for (int i = 0; i < month; i++) {
            allRabbit.forEach(r -> {
                r.oneMonthPassed();
            });
            allRabbit.addAll(child);
            child.clear();
        }
        System.out.println(allRabbit.size());
    }


    public static class Rabbit {

        int          age = 0;
        AgeListenner ageListenner;

        public void oneMonthPassed() {
            age++;
            ageListenner.ageChanged(age);
        }

        public void addListener(AgeListenner listener) {
            this.ageListenner = listener;
        }

    }


    public static class AgeListenner {
        private Set<Rabbit> childCollection;

        public AgeListenner(Set<Rabbit> childCollection) {
            this.childCollection = childCollection;
        }

        public void ageChanged(int newAge) {
            if (newAge >= 3) {
                Rabbit child = new Rabbit();
                child.addListener(this);
                childCollection.add(child);
            }
        }


    }


}
