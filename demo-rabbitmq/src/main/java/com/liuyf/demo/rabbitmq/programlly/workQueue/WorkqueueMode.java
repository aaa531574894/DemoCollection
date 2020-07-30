package com.liuyf.demo.rabbitmq.programlly.workQueue;

import com.liuyf.demo.rabbitmq.bean.Order;
import com.liuyf.demo.rabbitmq.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Component
public class WorkqueueMode {


    @Component
    public static class Provider{
        @Autowired
        @Qualifier(RabbitConfig.work_queue_plush)
        private AmqpTemplate amqpTemplate;

        public void send(Order order) throws Exception {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(order);
            Message message = MessageBuilder.withBody(byteArrayOutputStream.toByteArray())
                                            .andProperties(MessagePropertiesBuilder.newInstance()
                                                                                   .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                                                                                   .build())
                                            .build();

            amqpTemplate.send(message);

        }

    }



    @Component
    @Slf4j
    public static class MsgListener implements ChannelAwareMessageListener {
        @Override
        public void onMessage(Message message, Channel channel) throws IOException {
            Order order = null;
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBody());
                ObjectInputStream    objectInputStream    = new ObjectInputStream(byteArrayInputStream);
                order = (Order) objectInputStream.readObject();
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            assert order != null;
            log.info("监听器1 接受到订单:" + order.getOrderId() + "---" + order.getCustomerName());
            try {
                Thread.sleep(5000L);
                log.info("休眠5s,处理订单!");
            } catch (InterruptedException e) {

            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }

    }


    @Component
    @Slf4j
    public static class MsgListener2 implements ChannelAwareMessageListener {
        @Override
        public void onMessage(Message message, Channel channel) throws IOException {
            Order order = null;
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBody());
                ObjectInputStream    objectInputStream    = new ObjectInputStream(byteArrayInputStream);
                order = (Order) objectInputStream.readObject();
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            assert order != null;
            log.info("监听器2 接受到订单:" + order.getOrderId() + "---" + order.getCustomerName());
            try {
                Thread.sleep(5000L);
                log.info("休眠5s,处理订单!");
            } catch (InterruptedException e) {

            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        }


        @Override
        public void containerAckMode(AcknowledgeMode mode) {


        }
    }


}
