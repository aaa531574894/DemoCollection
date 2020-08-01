package com.liuyf.demo.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.liuyf.demo.jpa.dao.secondary", "com.liuyf.demo.jpa.entity.secondary"},
        entityManagerFactoryRef = "secondaryContainerEntiryManagerFactoryBean",
        transactionManagerRef = "secondaryTranscationManager"
)
public class JPAConfigSecondary {

    public final static String[] secondary_datasource_basePackages = {"com.liuyf.demo.jpa.dao.secondary", "com.liuyf.demo.jpa.entity.secondary"};


    @Bean("secondaryContainerEntiryManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean productEntityManager(@Qualifier("secondaryDatasource") DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(secondary_datasource_basePackages);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }


}
