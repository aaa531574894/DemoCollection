package com.liuyf.demo.jpa.dao.primary;

import com.liuyf.demo.jpa.entity.primary.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */
public interface ProductRepo extends JpaRepository<Product,Long> {
}
