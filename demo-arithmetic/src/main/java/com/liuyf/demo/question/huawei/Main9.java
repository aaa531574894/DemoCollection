package com.liuyf.demo.question.huawei;

import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微商模式比较典型，下级每赚100元就要上交15元，给出每个级别的收入，求出金字塔尖上的人收入
 * <p>
 * 比如
 * <p>
 * （代理商代号）  （上级代理商代号）  （代理商转的钱）
 * <p>
 * 1                  0                    223
 * <p>
 * 2                  0                    323
 * <p>
 * 3                  2                    1203
 * <p>
 * 输出是0（金字塔顶代理商）105 （最终的钱数）
 * <p>
 * <p>
 * <p>
 * Created by liuyf on 2020/8/2.
 */

public class Main9 {


    public static void main(String[] args) throws Exception {

        int[][] inputData = new int[][]{{
                1, 0, 223
        }, {
                2, 0, 323
        }, {
                3, 2, 1203
        }
        };
        HashMap<Integer, Node> allBrokers = new HashMap<>();
        for (int[] thisBroker : inputData) {
            Node broker = new Node(thisBroker[0], thisBroker[1], thisBroker[2]);
            allBrokers.put(broker.me, broker);
        }

        //build tree
        Node top = new Node(0, null, 0);
        allBrokers.forEach( (k,v)->{


        });


    }

   /* static void buildChildNode(Node parent, Map<Integer, Node> allNodes) {
        allNodes.forEach( (k,v) ->{
            allNodes.computeIfAbsent()
        });

    }*/



    @Data
    static class Node {
        Integer me;
        Integer p;
        Node parent;
        Integer money;

        public Node(Integer a, Integer b, Integer c) {
            this.me = a;
            this.p = b;
            this.money = c;
        }

    }
}
