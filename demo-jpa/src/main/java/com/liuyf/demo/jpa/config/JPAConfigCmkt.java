package com.liuyf.demo.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactoryCmkt",
        transactionManagerRef = "TransactionManagerCmkt",
        basePackages = {"com.liuyf.demo.jpa.dao.cmkt", "com.liuyf.demo.jpa.entity.cmkt"}
)
public class JPAConfigCmkt {

    public final static String[] cmkt_packages = {"com.liuyf.demo.jpa.dao.cmkt", "com.liuyf.demo.jpa.entity.cmkt"};


    @Bean("EntityManagerFactoryCmkt")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("cmkt") DataSource dataSource) {
        HibernateJpaVendorAdapter              jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factoryBean      = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(cmkt_packages);
        factoryBean.setPersistenceUnitName("cmkt");
        return factoryBean;
    }


    @Bean("TransactionManagerCmkt")
    public PlatformTransactionManager transactionManager(
            @Qualifier("EntityManagerFactoryCmkt") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }


}
