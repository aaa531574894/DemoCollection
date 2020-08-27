package com.liuyf.demo.zuochengyun.linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/27.
 */

public class Q1 {

    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);

        final int     length  = 20;
        Node<Integer> current = head;
        for (int i = 2; i < length; i++) {
            current.setNext(new Node<>(i));
            current = current.getNext();
        }
        //reverse(head);
        //reversePart(head);
        System.out.println(isHuiWen());


    }


    /**
     * 翻转单链表
     * a -> b ->c -> d -> e
     */
    private static <E> void reverse(Node<E> head) {
        Node<E> pre, current, next;
        current = head;
        next    = current.getNext();
        current.setNext(null);
        while (next != null) {
            pre     = current;
            current = next;
            next    = current.getNext();
            current.setNext(pre);
        }
        print(current);

    }

    /**
     * 翻转部分单链表 如  a->b->c->d->e->f->g
     * 翻转 [3,5] ==> a->b->e->d->c->f->g
     */
    public static <E> void reversePart(Node<E> head) {
        final int startIndex = 4, endIndex = 7; // left include,right   include


        Node<E> ltrimNode   = null;
        Node<E> rtrimNode   = null;
        Node<E> currentNode = head;
        //找到翻转部分的前后节点
        for (int index = 1; currentNode != null; index++) {
            if (index == startIndex - 1) {
                ltrimNode = currentNode;
            } else if (index == endIndex + 1) {
                rtrimNode = currentNode;
                break;
            }
            currentNode = currentNode.getNext();
        }

        //翻转中间部分的节点
        Node<E> pre = rtrimNode;
        currentNode = ltrimNode.getNext();
        Node<E> next = currentNode.getNext();

        while (next != null && currentNode != rtrimNode) {
            currentNode.setNext(pre);
            pre         = currentNode;
            currentNode = next;
            next        = currentNode.getNext();
        }
        ltrimNode.setNext(pre);

        print(head);


    }


    /**
     * 判断一个链表是否为回文结构
     * 1 2 1 -> true
     * 1 2 2 1 ->true
     */
    private static boolean isHuiWen() {
        Node<String> head = new Node<>("a");
        head.setNext(new Node<>("b"));
        head.getNext().setNext(new Node<>("c"));
        head.getNext().getNext().setNext(new Node<>("b"));
        head.getNext().getNext().getNext().setNext(new Node<>("a"));
        //head.getNext().getNext().getNext().getNext().setNext(new Node<>("a"));

        LinkedList<Node<String>> stack = new LinkedList<>();
        Node<String>             next  = head;
        stack.push(next);
        while ((next = next.getNext()) != null) {
            stack.push(next);
        }
        Node<String> left = head, right = stack.pop();
        while (left != right && (left != null && right != null)) {
            if (!left.getElement().equals(right.getElement())) {
                return false;
            }
            left  = left.getNext();
            right = stack.poll();
        }
        return true;
    }


    /**
     * 给定一个无序单链表,与一个pivot
     * 将列表分成三部分, 左部分都比pivot小; 中间部分都等于pivot值; 右部分都比pivot大
     *
     * 思路： 将 新增三个子列表，6个变量分别存放head tail 指针。
     * 遍历一遍链表，然后放到对应的 新链表中
     * 最后组合三个链表
     */


    // 1 8 7  4  9 7 63 72 93  7  4 3     2     ------ 9
    private static <Integer> void splitListByPivot(Node<Integer> head, Integer pivot) {


    }

    /**
     * 将单链表的每K个节点进行逆序
     */
    private static <E> void reverseGroup(Node<E> head,int length){
        Node<E> next ;
        LinkedList<Node<E>> stack = new LinkedList<>();
        stack.push(head);


        Node<E> preGroupTail = null;   //前一组的队尾元素

        for (int stackSize = 1; (next = head.getNext()) != null; ) {

            if (stackSize == length) {  //凑够1组 开始逆序
                Node<E> topNode, preNode = preGroupTail;
                while ((topNode = stack.remove()) != null) {
                    preNode.setNext(topNode);
                    preNode = topNode;
                }




            }

            stack.push(next);
            stackSize++;



        }

    }



    private static <E> void print(Node<E> head) {
        Node<E> next = head;
        while (next != null) {
            System.out.print(next.getElement() + " ");
            next = next.getNext();
        }
    }


}
