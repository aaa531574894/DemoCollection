package com.liuyf.demo.redis.dao;

import com.liuyf.demo.redis.po.Book;
import com.liuyf.demo.redis.po.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */

@Repository
@Slf4j
public class StudentStore {

    private Map<String, Student> mockDB = new ConcurrentHashMap<>();



    @Cacheable(value = "student" ,key = "#name" ,sync = true)
    public Student query(String name) {
        log.info("查询数据库..." + name);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mockDB.get(name);
    }

    @CachePut(value = "student" ,key = "#student.name")
    public Student insert(Student student) {
        log.info("新增:" + student.getName());
        mockDB.put(student.getName(), student);
        return student;
    }

    @CacheEvict(value = "student" ,key = "#name")
    public void delete(String name) {
        log.info("删除:" + name);
        mockDB.remove(name);
    }

    @CacheEvict(value = "student" ,key = "#student.bookName")
    public void update(Student student) {
        log.info("更新:" + student.getName());
        mockDB.put(student.getName(), student);
    }

}
