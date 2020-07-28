package com.liuyf.demo.redis.controller;

import com.liuyf.demo.redis.dao.IBookStore;
import com.liuyf.demo.redis.dao.StudentStore;
import com.liuyf.demo.redis.po.Book;
import com.liuyf.demo.redis.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@RestController
@RequestMapping("/student")
public class StudentCacheTestController {

    @Autowired
    private StudentStore studentStore;

    @GetMapping
    public Student query(@RequestParam String name) {
        return studentStore.query(name);
    }

    @DeleteMapping
    public String delete(@RequestParam String name) {
        studentStore.delete(name);
        return "success";
    }

    @PutMapping
    public String update(@RequestBody Student student) {
        studentStore.update(student);
        return "success";
    }


    @PostMapping
    public String insert(@RequestBody Student student) {
        studentStore.insert(student);
        return "success";
    }


}
