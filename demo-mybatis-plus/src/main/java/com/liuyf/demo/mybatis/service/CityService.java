package com.liuyf.demo.mybatis.service;

import com.liuyf.demo.mybatis.PO.City;
import com.liuyf.demo.mybatis.mapper.primary.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/9.
 */

@Service
public class CityService {


    @Autowired
    private CityMapper cityMapper;



    @Transactional()
    public void insertCity(City city){
        cityMapper.insert(city);
        int c = 1 / 0;
    }



}
