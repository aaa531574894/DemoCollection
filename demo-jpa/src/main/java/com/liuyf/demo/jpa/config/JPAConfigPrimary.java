package com.liuyf.demo.jpa.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.liuyf.demo.jpa.dao.primary", "com.liuyf.demo.jpa.entity.primary"},
        entityManagerFactoryRef = "primaryContainerEntiryManagerFactoryBean",
        transactionManagerRef = "primaryTranscationManager"
)
public class JPAConfigPrimary {

    public final static String[] primary_datasource_basePackages = {"com.liuyf.demo.jpa.dao.primary", "com.liuyf.demo.jpa.entity.primary"};


    @Bean("primaryContainerEntiryManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean productEntityManager(DataSource dataSource) {


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();


        LocalContainerEntityManagerFactoryBean em  = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(primary_datasource_basePackages);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());   //从spring 3.1 起，可以不用再通过 /META-INFO/perisients.xml 的方式配置 hibernate的配置了， 可以直接通过java编码来配置


        return em;
    }

    private Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        properties.setProperty("javax.persistence.schema-generation.create-source", "metadata");  //
        properties.setProperty("javax.persistence.schema-generation.scripts.action", "create");   //生成建表脚本
        properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "../create.sql");  //脚本导出位置

        return properties;
    }


}
