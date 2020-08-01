package com.liuyf.demo.jps;

import com.liuyf.demo.jpa.SpringbootApplication;
import com.liuyf.demo.jpa.dao.primary.ProductRepo;
import com.liuyf.demo.jpa.entity.primary.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@SpringBootTest(classes = SpringbootApplication.class)
public class JpaTest {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryTemplate;


    //测试jdbcTemplate用法
    @Test
    public void Test(){
        primaryTemplate.update("insert into TEST_ORDER (order_id,order_name,handler,create_time) " +
                                        "values(?,?,?,sysdate)", 1, "测试订单", "feifei");
    }

    @Test
    public void TestJpaCreateTable(){
        //do nothing
    }


    @Autowired
    private ProductRepo productRepo;
    @Test
    public void TestJpaInsert(){
        Product product = new Product();
        product.setProductId(124L);
        product.setProductName("测试商品1");
        productRepo.save(product);

    }
}
