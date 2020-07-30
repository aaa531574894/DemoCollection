package com.liuyf.demo.rabbitmq.annotationdrive.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.liuyf.demo.rabbitmq.config.RabbitConfig.HELLO_WORLD_DIRECTIVE_QUEUE;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/29.
 */

@Component
public class DirectMode {

    @Component
    @Slf4j
    public static class Provider {
        @Autowired
        @Qualifier(HELLO_WORLD_DIRECTIVE_QUEUE)
        private AmqpTemplate amqpTemplate;


        public void send(String msg) {
            Message message = MessageBuilder.withBody(msg.getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();
            amqpTemplate.send(message);

            log.info(this + "已发送:" + msg);
        }
    }

    @Component
    @Slf4j
    public static class Consumer {
        @Autowired
        @Qualifier(HELLO_WORLD_DIRECTIVE_QUEUE)
        private AmqpTemplate amqpTemplate;

        @RabbitListener(queuesToDeclare = @Queue(HELLO_WORLD_DIRECTIVE_QUEUE))
        public void receive(String in) {
            log.info("接收:" + in);
        }
    }
}
