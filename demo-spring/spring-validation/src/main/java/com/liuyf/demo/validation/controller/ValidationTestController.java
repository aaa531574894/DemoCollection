package com.liuyf.demo.validation.controller;

import com.liuyf.demo.validation.bean.Student;
import com.liuyf.demo.validation.service.ValidationTestService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/4.
 */

@RestController
//@Validated
public class ValidationTestController {


    @Autowired
    private ValidationTestService validationTestService;

    @PostMapping("/test1")
    public String testControllerLayerValidation(@RequestParam @Length(min = 10) String userId, @RequestBody
    @Validated
            Student body) {
        return "success";
    }



    /*@RequestMapping("/test2")
    public String testServiceLayerValidation(){
        validationTestService
        return "success";
    }*/


}
