package com.liuyf.demo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Bootstrap.class, args);

    }
}
