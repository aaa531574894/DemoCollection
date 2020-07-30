package com.liuyf.demo.rabbitmq.programlly.headers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */


@Component
public class HeaderMode {

    @Component
    public static class Provider {
        @Autowired
        @Qualifier("HEADER")
        private AmqpTemplate amqpTemplate;

        public void send(int id, String name) {
            Message message = MessageBuilder.withBody(("测试消息" + id + "---" + name).getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .setHeader("id", id)
                                                                                   .setHeader("name", name)
                                                                                   .build())
                                            .build();

            amqpTemplate.send(message);
        }


    }


    @Component
    @Slf4j
    public static class Consumer1 implements MessageListener {

        @Override
        public void onMessage(Message message) {
            log.info("consumer1 收到消息:" + new String(message.getBody()));

        }
    }


    @Component
    @Slf4j
    public static class Consumer2 implements MessageListener {


        @Override
        public void onMessage(Message message) {
            log.info("consumer2 收到消息:" + new String(message.getBody()));

        }
    }


}
