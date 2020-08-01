package com.liuyf.demo.jpa.dao.secondary;

import com.liuyf.demo.jpa.entity.secondary.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */
public interface OrderRepo extends JpaRepository<BookOrder, Long> {

}
