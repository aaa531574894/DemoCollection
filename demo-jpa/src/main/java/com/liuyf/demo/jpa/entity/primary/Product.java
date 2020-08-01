package com.liuyf.demo.jpa.entity.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    long productId;
    @Column(name = "product_name")
    String productName;

}
