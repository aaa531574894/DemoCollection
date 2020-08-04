package com.liuyf.demo.validation.bean;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/4.
 */

@Data
public class Father {
    @NotEmpty
    String name ;
    @Min(value = 50)
    int age;
}
