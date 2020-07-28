package com.liuyf.demo.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */
@Slf4j
@Component
public class MsgConsumer {


    @RabbitListener(queues = "testQueue")
    public void receive(String content){
        log.info("收到消息<" + content + ">");
    }






}
