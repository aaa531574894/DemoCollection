package com.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuyf.demo.mybatis.MybatisPlusApplication;
import com.liuyf.demo.mybatis.PO.City;
import com.liuyf.demo.mybatis.mapper.primary.CityMapper;
import com.liuyf.demo.mybatis.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/9.
 */

@SpringBootTest(classes = MybatisPlusApplication.class)
@RunWith(SpringRunner.class)
public class MybatisTest {


    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityService cityService;

    @Test
    public void testInsertWithAutoGenerateKeys(){
        City city = new City();
        city.setName("上海");
        city.setPopulation(21000000);
        city.setCountryCode("CN");
        city.setDistrict("CHINA");
        cityMapper.insert(city);
    }

    @Test
    //测试使用 mybatis page helper 实现分页查询
    public void testQueryByPage(){
        PageHelper.startPage(10, 10);
        List<City> result = cityMapper.queryCityByPage();
        PageInfo<City> ss = new PageInfo<>(result);

    }

    @Test
    //测试spring的事务管理器
    public void testTransactional(){
        City city = new City();
        city.setName("上海");
        city.setPopulation(21000000);
        city.setCountryCode("CN");
        city.setDistrict("CHINA");
        cityService.insertCity(city);

    }

}
