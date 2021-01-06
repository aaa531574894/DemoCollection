package com.yifei.demo.dao;

import com.yifei.demo.config.TypeEnum;
import com.yifei.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByType(TypeEnum.Image type);
}
