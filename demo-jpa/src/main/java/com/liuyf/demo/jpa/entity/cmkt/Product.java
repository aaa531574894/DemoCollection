package com.liuyf.demo.jpa.entity.cmkt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */

@Entity
@Table(name ="TEST_PRODUCT" )
public class Product {

    @Id
    @GeneratedValue
    long productId;
    String productName;

}
