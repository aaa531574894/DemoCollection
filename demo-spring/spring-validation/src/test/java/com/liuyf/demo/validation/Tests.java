package com.liuyf.demo.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyf.demo.validation.bean.Father;
import com.liuyf.demo.validation.bean.Student;
import com.liuyf.demo.validation.service.ValidationTestService;
import javafx.scene.Parent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class Tests {

    @Autowired
    private ValidationTestService validationTestService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp() throws Exception {
        // 构造MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }


    //测试@NotEmpty注解是否可以用在service方法上
    @Test
    public void testServiceLayer1() {
        log.info("测试服务层");
        validationTestService.print("");


    }

    @Test
    public void testServiceLayer2() {
        log.info("测试服务层");
        Student student = new Student();
        student.setAge(19);
        //student.setName("liuyf");
        Father f = new Father();
        f.setName("sdf");
        student.setFather(f);
        validationTestService.acceptStudnet(student);

    }






    @Test
    public void testControllerLayer() throws Exception {
        log.info("测试服务层");
        Student student = new Student();
        student.setAge(19);
        student.setName("liuyf");
        Father f = new Father();
        f.setName("sdf");
        student.setFather(f);

        mockMvc.perform(MockMvcRequestBuilders.post("/test1").accept(MediaType.APPLICATION_JSON).param("userId", "liuyf").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(student)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
}
