package com.liuyf.demo.rabbitmq.config;

import com.liuyf.demo.rabbitmq.consumer.MsgConsumer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@Configuration
@EnableRabbit
public class RabbitConfig {


    @Bean
    public MessageListenerAdapter receiver1(MsgConsumer msgConsumer) {
        return new MessageListenerAdapter(msgConsumer, "receive");
    }



}
