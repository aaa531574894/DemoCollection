package com.liuyf.demo.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

@Configuration
public class MyBatisConfig {




    @Configuration
    @MapperScan(basePackages = "com.liuyf.demo.mybatis.mapper.primary", sqlSessionFactoryRef  = "primarySqlSessionFactory")
    public  static class PrimaryDataSource{

        @Bean(name = "primarySqlSessionFactory")
        @Primary
        public SqlSessionFactory testSqlSessionFactory(@Qualifier("primaryDatasource") DataSource dataSource) throws Exception {

            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setCacheEnabled(false);
            configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);


            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setConfiguration(configuration);
            bean.setDataSource(dataSource);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/primary/*.xml"));
            return bean.getObject();
        }
    }

/*


    @Configuration
    @MapperScan(basePackages = "com.liuyf.demo.mybatis.mapper.secondary", sqlSessionFactoryRef  = "secondarySqlSessionFactory")
    public  static class SecondaryDataSource{

        @Bean(name = "secondarySqlSessionFactory")
        public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondaryDatasource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/secondary/*.xml"));
            return bean.getObject();
        }
    }
*/



}
