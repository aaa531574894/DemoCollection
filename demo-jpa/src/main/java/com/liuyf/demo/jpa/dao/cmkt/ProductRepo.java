package com.liuyf.demo.jpa.dao.cmkt;

import com.liuyf.demo.jpa.entity.cmkt.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/31.
 */

public interface ProductRepo extends JpaRepository<Product,Long> {
}
