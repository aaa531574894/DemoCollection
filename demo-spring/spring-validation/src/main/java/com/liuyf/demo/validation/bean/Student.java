package com.liuyf.demo.validation.bean;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/4.
 */


@Data
public class Student {

    @Valid
    @NotEmpty(message = "用户名不可为空")
    String name;
    @Min(value = 18, message = "年龄必须大于18")
    int age;


    @NotNull
    @Valid
    Father father;
}
