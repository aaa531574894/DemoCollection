package com.liuyf.demo.redis.controller;

import com.liuyf.demo.redis.dao.IBookStore;
import com.liuyf.demo.redis.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/28.
 */


@RestController
@RequestMapping("/book")
public class BookCacheTestController {

    @Autowired
    private IBookStore bookStore;

    @GetMapping
    public Book query(@RequestParam String bookName) {
        return bookStore.query(bookName);
    }

    @DeleteMapping
    public String delete(@RequestParam String bookName) {
        bookStore.delete(bookName);
        return "success";
    }

    @PutMapping
    public String update(@RequestBody Book book) {
        bookStore.update(book);
        return "success";
    }


    @PostMapping
    public String insert(@RequestBody Book book) {
        bookStore.insert(book);
        return "success";
    }


}
