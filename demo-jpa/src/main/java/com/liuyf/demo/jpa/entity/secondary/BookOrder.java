package com.liuyf.demo.jpa.entity.secondary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Entity
@Data
@Table( name = "BOOK_ORDER")
public class BookOrder {
    @Id
    //@GeneratedValue
    private long orderId;
    private String oderName;
    private String handler;
    private Date createTime;
}
