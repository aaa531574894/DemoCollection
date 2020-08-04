package com.liuyf.demo.validation.service;

import com.liuyf.demo.validation.bean.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/4.
 */

@Service
//@Validated
public class ValidationTestService {


    @Validated
    public void print(@NotEmpty(message = "字符串不可为空") String str){
        //Assert.hasLength(str, "报错啦");
        System.out.println(str);
    }


    public void acceptStudnet(@Valid Student student) {

        System.out.println("接收学生成功");

    }









}
