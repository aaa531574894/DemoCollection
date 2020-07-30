package com.liuyf.demo.rabbitmq.annotationdrive.workqueue;

import com.liuyf.demo.rabbitmq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/29.
 */

@Component
public class WorkQueueMode {


    @Component
    @Slf4j
    public static class Provider {
        @Autowired
        @Qualifier(RabbitConfig.WORK_QUEUE_MODE)
        private AmqpTemplate amqpTemplate;

        public void send(String msg) {
            Message message = MessageBuilder.withBody(msg.getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();
            amqpTemplate.send(message);
            //log.info(this + "已发送:" + msg);
        }


    }


    @Component
    @Slf4j
    public static class Consumer {
        private final AtomicInteger atomicInteger1 = new AtomicInteger(0);


        //这个方法执行的慢, 所以会被分配少一点的任务; 原生client 消费端是通过autoAck控制的,spring里还不知道
        @RabbitListener(queuesToDeclare = @Queue(RabbitConfig.WORK_QUEUE_MODE))
        public void rec1(String string) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {

            }
            log.info("方法1 接收到 " + string + "  计数---" + atomicInteger1.incrementAndGet());
        }

    }


    @Component
    @Slf4j
    public static class Consumer2 {
        private final AtomicInteger atomicInteger2 = new AtomicInteger(0);


        @RabbitListener(queuesToDeclare = @Queue(RabbitConfig.WORK_QUEUE_MODE))
        public void rec2(String string) {
            log.info("方法2 接收到 " + string + "  计数---" + atomicInteger2.incrementAndGet());
        }
    }


}
