package com.liuyf.demo;

import com.liuyf.demo.rabbitmq.Bootstrap;
import com.liuyf.demo.rabbitmq.mode.direct.DirectMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/29.
 */

@SpringBootTest(classes = Bootstrap.class)
public class RabbitTest {
    @Autowired
    private DirectMode.Provider provider;


    @Test
    public void test() {
        provider.send("nihao");

    }
}
