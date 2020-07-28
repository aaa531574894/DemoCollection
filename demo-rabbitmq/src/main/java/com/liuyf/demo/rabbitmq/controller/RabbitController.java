package com.liuyf.demo.rabbitmq.controller;

import com.liuyf.demo.rabbitmq.provider.MsgProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@RestController
public class RabbitController {


    @Autowired
    private MsgProvider msgProvider;

    @PostMapping("/msg")
    public String sendMsg(@RequestParam String content){
        msgProvider.send(content);


        return "success";
    }




}
