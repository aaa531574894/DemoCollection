package com.liuyf.demo.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Configuration
public class DatasourceConfig {


    @ConfigurationProperties(prefix = "spring.datasource.wggl")
    @Bean("wggl")
     public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();

    }

    @ConfigurationProperties(prefix = "spring.datasource.cmkt")
    @Bean("cmkt")
    @Qualifier("cmkt")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }




    @Bean("wgglJdbcTemplate")
    public JdbcTemplate wgglJdbcTemplate(@Qualifier("wggl")
                                                 DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("cmktJdbcTemplate")
    public JdbcTemplate cmktJdbcTemplate(@Qualifier("cmkt")
                                                 DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
