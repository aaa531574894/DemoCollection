package com.liuyf.demo.rabbitmq.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Data
public class Order implements Serializable {

    int    orderId;
    String customerName;


}
