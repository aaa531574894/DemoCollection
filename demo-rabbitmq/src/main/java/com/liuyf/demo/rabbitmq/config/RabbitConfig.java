package com.liuyf.demo.rabbitmq.config;

import com.liuyf.demo.rabbitmq.programlly.workQueue.WorkqueueMode;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);

    }

    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("liuyf");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);
        connectionFactory.setHost("192.168.85.4");
        //connectionFactory.setUri("amqp://liuyf:123456@192.168.85.4/");
        connectionFactory.setVirtualHost("/TestVirtualHost");
        return connectionFactory;
    }


    // directive 模式
    public static final String HELLO_WORLD_DIRECTIVE_QUEUE = "hello.world.queue";

    @Bean(name = HELLO_WORLD_DIRECTIVE_QUEUE)
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("");
        rabbitTemplate.setRoutingKey(HELLO_WORLD_DIRECTIVE_QUEUE);
        rabbitTemplate.setDefaultReceiveQueue(HELLO_WORLD_DIRECTIVE_QUEUE);
        return rabbitTemplate;
    }


    // workQueue 模式
    public static final String WORK_QUEUE_MODE = "workqueue";

    @Bean(name = WORK_QUEUE_MODE)
    public AmqpTemplate template2(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("");
        rabbitTemplate.setRoutingKey(WORK_QUEUE_MODE);
        rabbitTemplate.setDefaultReceiveQueue(WORK_QUEUE_MODE);
        return rabbitTemplate;
    }


    //fanout 模式
    public static final String FAN_OUT_MODE = "fanOutMode";

    @Bean(name = FAN_OUT_MODE)
    public AmqpTemplate template3(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(FAN_OUT_MODE);
        return rabbitTemplate;
    }


    //routing mode
    public final static String ROUTING_MODE = "LoggerRouter";

    @Bean(name = ROUTING_MODE)
    public AmqpTemplate template4(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(ROUTING_MODE);
        return rabbitTemplate;
    }


    //topic mode
    @Bean("TOPICS")
    public AmqpTemplate template5(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("TOPICS");
        return rabbitTemplate;
    }


    @Bean(work_queue_plush)
    public AmqpTemplate template6(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(work_queue_plush);
        rabbitTemplate.setExchange("");
        return rabbitTemplate;
    }


    public final static String work_queue_plush = "work_queue_plush";

    @Bean(work_queue_plush)
    public SimpleMessageListenerContainer factoryCreatedContainerNoListener(
            SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory,
            @Autowired WorkqueueMode.MsgListener listener) {
        SimpleMessageListenerContainer container = rabbitListenerContainerFactory.createListenerContainer();
        container.setMessageListener(listener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames(work_queue_plush);
        container.setPrefetchCount(1);
        return container;
    }

    public final static String work_queue_plush_2 = "work_queue_plush_2";

    @Bean(work_queue_plush_2)
    public SimpleMessageListenerContainer factoryCreatedContainerNoListener2(
            SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory,
            @Autowired WorkqueueMode.MsgListener2 listener) {
        SimpleMessageListenerContainer container = rabbitListenerContainerFactory.createListenerContainer();
        container.setMessageListener(listener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames(work_queue_plush);
        container.setPrefetchCount(1);
        return container;
    }


    @Bean
    public Queue queue1() {
        return new Queue(work_queue_plush);
    }


    @Bean
    public SimpleMessageListenerContainer containerFactory(ConnectionFactory connectionFactory) {
        return new SimpleMessageListenerContainer(connectionFactory);
    }


}
