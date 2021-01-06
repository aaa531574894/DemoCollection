package com.yifei.demo.controller;

import com.yifei.demo.entity.Image;
import com.yifei.demo.service.CompressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */


@RestController
public class CompressionController {

    private CompressServiceImpl service;

    @GetMapping("/compress")
    public String begin() {

        service.compressIfNecessary();
        return "done";
    }


    @GetMapping("/test")
    public String test(){
        Image testImage = new Image();
        testImage.setUrl("https://wework-chinaos-dev.oss-cn-shanghai.aliyuncs.com/bis-img-migration/08223b18-5782-11e8-b0f3-1202be33576a.jpg");
        service.doCompress(testImage);
        return "1";
    }

    @Autowired
    public void setService(CompressServiceImpl service) {
        this.service = service;
    }


    public static void main(String[] args) {

    }
}
