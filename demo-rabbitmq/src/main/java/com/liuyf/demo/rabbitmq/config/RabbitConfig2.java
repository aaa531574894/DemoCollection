package com.liuyf.demo.rabbitmq.config;

import com.liuyf.demo.rabbitmq.programlly.headers.HeaderMode;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * headers 模式的配置,上一个配置类太乱了 ,新建一个
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Configuration
public class RabbitConfig2 {


    @Bean(name = "HEADER")
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("HeadersExchange");
         return rabbitTemplate;
    }





    @Bean
    public SimpleMessageListenerContainer factoryCreatedContainerNoListener1(
            SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory,
            HeaderMode.Consumer1 listener) {
        SimpleMessageListenerContainer container = rabbitListenerContainerFactory.createListenerContainer();
        container.setMessageListener(listener);
        container.setQueueNames("headerQueue1");

        container.setPrefetchCount(1);
        return container;
    }


    @Bean
    public SimpleMessageListenerContainer factoryCreatedContainerNoListener2(
            SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory,
            HeaderMode.Consumer2 listener) {
        SimpleMessageListenerContainer container = rabbitListenerContainerFactory.createListenerContainer();
        container.setMessageListener(listener);
        container.setQueueNames("headerQueue2");
        container.setPrefetchCount(1);
        return container;
    }


    @Bean("headersExchange")
    public HeadersExchange headersExchange() {

        HeadersExchange headersExchange = new HeadersExchange("HeadersExchange", true, false);
        headersExchange.setShouldDeclare(true);
        return headersExchange;

    }

    @Bean(name = "headerQueue1")
    public Queue queue1() {
        return new Queue("headerQueue1");
    }

    @Bean(name = "headerQueue2")
    public Queue queue2() {
        return new Queue("headerQueue2");
    }

    @Bean
    public Binding headerBinding(@Qualifier("headerQueue1") Queue queue,
                                 @Qualifier("headersExchange") HeadersExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).whereAny(condition1).match();
    }


    public static final HashMap<String, Object> condition1 = new HashMap<String, Object>() {{
        put("id", 100);
        put("name", "liuyf");
    }};



    @Bean
    public Binding headerBinding2(@Qualifier("headerQueue2") Queue queue,
                                 @Qualifier("headersExchange") HeadersExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).whereAny(condition2).match();
    }


    public static final HashMap<String, Object> condition2 = new HashMap<String, Object>() {{
        put("id", 200);
        put("name", "chengrui");
    }};

}
