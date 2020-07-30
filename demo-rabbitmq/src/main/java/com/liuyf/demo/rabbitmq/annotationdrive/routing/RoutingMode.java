package com.liuyf.demo.rabbitmq.annotationdrive.routing;

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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * routing模式,一个生产者 ,一个消费者,生产者模拟生产 err info 两个级别的日志.
 * 两个消费者, 分别接受 err info 级别的日志
 *
 * <p>
 * <p>
 * Created by liuyf on 2020/7/29.
 */

@Component
public class RoutingMode {


    @Component
    @Slf4j
    public static class Provider {

        @Autowired
        @Qualifier(RabbitConfig.ROUTING_MODE)
        private       AmqpTemplate  amqpTemplate;
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        public void send(String msg) {
            if (atomicInteger.incrementAndGet() % 2 != 1) {
                msg = "info 级别" + msg;
                Message message = MessageBuilder.withBody(msg.getBytes())
                                                .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                       .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                       .build())
                                                .build();
                amqpTemplate.send("info", message);

            } else {
                msg = "err 级别" + msg;
                Message message = MessageBuilder.withBody(msg.getBytes())
                                                .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                       .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                       .build())
                                                .build();
                amqpTemplate.send("err", message);
            }

        }


    }


    @Component
    @Slf4j
    public static class Consumer {
        @RabbitListener(
                bindings = @QueueBinding(             //绑定交换机与队列
                        exchange = @Exchange(name = RabbitConfig.ROUTING_MODE, type = ExchangeTypes.DIRECT),  //指明交换机
                        value = @Queue,                                     // 声明临时队列
                        key = "err")                                        // 监听哪个routingkey
        )
        public void err(String msg) {
            log.error("监听err级日志: " + msg);

        }

        @RabbitListener(
                bindings = @QueueBinding(
                        exchange = @Exchange(name = RabbitConfig.ROUTING_MODE, type = ExchangeTypes.DIRECT),
                        value = @Queue,
                        key = "info")
        )
        public void info(String msg) {
            log.info("监听info级日志: " + msg);
        }


    }


}
