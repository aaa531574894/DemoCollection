package com.liuyf.demo.mybatis.mapper.primary;

import com.liuyf.demo.mybatis.PO.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/9.
 */

@Repository
public interface CityMapper {


    List<City> queryCityByPage();


    int insert(City city);




}
