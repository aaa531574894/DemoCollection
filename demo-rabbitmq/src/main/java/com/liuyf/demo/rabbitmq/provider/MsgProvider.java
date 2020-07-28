package com.liuyf.demo.rabbitmq.provider;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */


@Component
public class MsgProvider {


    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String str) {
        rabbitMessagingTemplate.send();

    }


}
