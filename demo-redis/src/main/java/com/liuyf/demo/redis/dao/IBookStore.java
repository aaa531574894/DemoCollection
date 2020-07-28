package com.liuyf.demo.redis.dao;

import com.liuyf.demo.redis.po.Book;
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
public class IBookStore {

    private final Map<String, Book> mockDB = new ConcurrentHashMap<>();


    @Cacheable(value = "book" ,key = "#bookName" )
    public Book query(String bookName) {
        log.info("查询数据库..." + bookName);
        return mockDB.get(bookName);
    }

    @CachePut(value = "book" ,key = "#book.bookName")
    public Book insert(Book book) {
        log.info("新增:" + book.getBookName());
        mockDB.put(book.getBookName(), book);
        return book;
    }

    @CacheEvict(value = "book" ,key = "#bookName")
    public void delete(String bookName) {
        log.info("删除:" + bookName);
        mockDB.remove(bookName);
    }

    @CacheEvict(value = "book" ,key = "#book.bookName")
    public void update(Book book) {
        log.info("更新:" + book.getBookName());
        mockDB.put(book.getBookName(), book);
    }


}
