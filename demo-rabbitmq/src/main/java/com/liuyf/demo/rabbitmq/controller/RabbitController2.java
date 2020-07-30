package com.liuyf.demo.rabbitmq.controller;

import com.liuyf.demo.rabbitmq.programlly.headers.HeaderMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller1 太多了  再新建一个
 */
@RestController
public class RabbitController2 {

    int i1 = 0 ;

    @Autowired
    private HeaderMode.Provider provider1;
    @Scheduled(fixedRate = 300)
    public void log() {
        if (i1++ < 50 && i1 % 2 == 0) {

            provider1.send(100, "liuyf");
        }else {
            provider1.send(200, "chengrui");

        }
    }


}
