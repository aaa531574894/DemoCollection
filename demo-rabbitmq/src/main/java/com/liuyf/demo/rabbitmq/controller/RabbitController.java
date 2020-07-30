package com.liuyf.demo.rabbitmq.controller;

import com.liuyf.demo.rabbitmq.annotationdrive.direct.DirectMode;
import com.liuyf.demo.rabbitmq.annotationdrive.fanout.FanoutMode;
import com.liuyf.demo.rabbitmq.annotationdrive.routing.RoutingMode;
import com.liuyf.demo.rabbitmq.annotationdrive.topic.TopicMode;
import com.liuyf.demo.rabbitmq.annotationdrive.workqueue.WorkQueueMode;
import com.liuyf.demo.rabbitmq.bean.Order;
import com.liuyf.demo.rabbitmq.programlly.workQueue.WorkqueueMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@RestController
@EnableScheduling
public class RabbitController {


    // direct 模式
    @Autowired
    DirectMode.Provider directProvider;

    //@Scheduled(fixedRate = 500)
    public void log() {
        directProvider.send("你好: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }


    //workqueue模式
    @Autowired
    private WorkQueueMode.Provider queueMode;

    int i = 0;
    //@Scheduled(fixedRate = 300)
    public void log_2() {
        if (i < 30) {
            queueMode.send(" 消息---- " + i++);
        }

    }


    //fanout 模式, 广播模式
    @Autowired
    private FanoutMode.Provider provider;

    //@Scheduled(fixedRate = 300)
    public void log_3() {
        provider.send("你好: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }


    //fanout 模式
    @Autowired
    private RoutingMode.Provider routingModeProvider;

    //@Scheduled(fixedRate = 300)
    public void log_4() {
        routingModeProvider.send("这是一条日志: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }


    //topic mode
    AtomicInteger atomicInteger = new AtomicInteger(0);
    @Autowired
    private TopicMode.Provider tpProvider;

    //@Scheduled(fixedRate = 300)
    public void log_5() {
        if (atomicInteger.incrementAndGet() % 2 != 0) {
            tpProvider.publishOrder("发布订单消息");

        } else tpProvider.publishProduct("发布商品消息");
    }


    int j = 0;
    //测试workqueue模式下   对象流的消息  与  container模式
    @Autowired
    WorkqueueMode.Provider SDFSDF;
    //@Scheduled(fixedRate = 200)
    public void log_6() throws Exception {
        if (j++ < 30) {
            Order order = new Order();
            order.setOrderId(Integer.parseInt("000000" + j));
            order.setCustomerName("客户" + j);
            SDFSDF.send(order);
        }


    }
}
