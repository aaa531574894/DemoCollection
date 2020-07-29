package com.liuyf.demo.rabbitmq.mode.fanout;

import com.liuyf.demo.rabbitmq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/29.
 */

@Component
public class FanoutMode {

    @Component
    @Slf4j
    public static class Provider {

        @Autowired
        @Qualifier(RabbitConfig.FAN_OUT_MODE)
        private AmqpTemplate amqpTemplate;

        public void send(String msg) {
            Message message = MessageBuilder.withBody(msg.getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();
            amqpTemplate.send(message);
            log.info("已发送:" + msg);
        }

    }




    @Component
    @Slf4j        // 消费者1
    public static class Comsumer1 {

        @RabbitListener(
                bindings = @QueueBinding(    //绑定队列
                        value = @Queue,          //创建临时队列
                        exchange = @Exchange(       //指定交换机,与交换模式
                                value = RabbitConfig.FAN_OUT_MODE,
                                type = "fanout")))
        public void recv(String str) {
            log.info("cosumer 1 ----" + str);
        }


    }


    @Component
    @Slf4j            //消费者2
    public static class Comsumer2 {

        @RabbitListener(
                bindings = @QueueBinding(
                        value = @Queue,
                        exchange = @Exchange(
                                value = RabbitConfig.FAN_OUT_MODE,
                                type = "fanout")))
        public void recv(String str) {

            log.info("cosumer 2 ----" + str);

        }
    }


}
