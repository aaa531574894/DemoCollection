package com.liuyf.demo.mybatis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/8/8.
 */

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {


    @ConfigurationProperties(prefix = "spring.primary-datasource")
    @Bean("primaryDatasource")
    @Primary
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }







    @Primary
    @Bean("primaryTranscationManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDatasource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }
    /*

    @ConfigurationProperties(prefix = "spring.secondary-datasource")
    @Bean("secondaryDatasource")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean("secondaryTranscationManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryDatasource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }
*/


}
