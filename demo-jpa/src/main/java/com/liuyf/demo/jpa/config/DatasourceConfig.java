package com.liuyf.demo.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Configuration
@EnableTransactionManagement
public class DatasourceConfig {

    // 基础数据源   jdbcTemplate  transactionManager
    @ConfigurationProperties(prefix = "spring.primary-datasource")
    @Primary
    @Bean("primaryDatasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();

    }

    @Bean("primaryJdbcTemplate")
    @Primary
    public JdbcTemplate wgglJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean("primaryTranscationManager")
    public PlatformTransactionManager primaryTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());
        return transactionManager;
    }


    // 第二套数据源   jdbcTemplate  transactionManager


    @ConfigurationProperties(prefix = "spring.secondary-datasource")
    @Bean("secondaryDatasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean("secondaryJdbcTemplate")
    public JdbcTemplate cmktJdbcTemplate(@Qualifier("secondaryDatasource")
                                                 DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean("secondaryTranscationManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryContainerEntiryManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());
        return transactionManager;
    }


}
