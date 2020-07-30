package com.liuyf.demo.rabbitmq.annotationdrive.topic;


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
public class TopicMode {

    @Component
    public static class Provider {
        @Autowired
        @Qualifier("TOPICS")
        private AmqpTemplate amqpTemplate;


        public void publishOrder(String str) {


            Message message = MessageBuilder.withBody(str.getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();
            amqpTemplate.send("topic.order", message);


        }

        public void publishProduct(String str) {
            Message message = MessageBuilder.withBody(str.getBytes())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();
            amqpTemplate.send("topic.product", message);

        }

    }


    @Component
    @Slf4j
    public static class Consumer {


        @RabbitListener(bindings = {
                @QueueBinding(
                        value = @Queue,
                        exchange = @Exchange(value = "TOPICS", type = ExchangeTypes.TOPIC),
                        key = {"topic.order"}
                )

        })
        public void receiveOrder(String str) {
            log.info("订单接收器:" + str);
        }

        @RabbitListener(bindings = {
                @QueueBinding(
                        value = @Queue,
                        exchange = @Exchange(value = "TOPICS", type = ExchangeTypes.TOPIC),
                        key = {"topic.product"}
                )

        })
        public void receiveProduct(String str) {
            log.info("商品接收器:" + str);
        }

        @RabbitListener(bindings = {
                @QueueBinding(
                        value = @Queue,
                        exchange = @Exchange(value = "TOPICS", type = ExchangeTypes.TOPIC),
                        key = {"topic.*"}
                )

        })
        public void receiveAll(String str) {
            log.info("全部接收器:" + str);

        }


    }


}
