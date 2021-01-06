package com.yifei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */


@SpringBootApplication
@EnableAsync
public class ImageCompressionApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImageCompressionApplication.class, args);
    }

}
