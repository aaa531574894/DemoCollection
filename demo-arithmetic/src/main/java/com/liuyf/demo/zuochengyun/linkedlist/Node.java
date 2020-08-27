package com.liuyf.demo.zuochengyun.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/27.
 */

@Data
public class Node<E> {
    private E       element;
    private Node<E> next;

    public Node(E e) {
        this.element = e;
    }

}
