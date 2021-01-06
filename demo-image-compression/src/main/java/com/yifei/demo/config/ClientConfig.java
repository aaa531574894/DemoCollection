package com.yifei.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */

@Configuration
public class ClientConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
