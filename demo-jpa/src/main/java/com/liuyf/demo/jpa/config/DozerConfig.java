package com.liuyf.demo.jpa.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */

@Configuration
public class DozerConfig {


    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        return new DozerBeanMapper();

    }



}
