package com.liuyf.demo.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;


/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2020/7/30.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactoryWggl",
        transactionManagerRef = "TransactionManagerWggl",
        basePackages = {"com.liuyf.demo.jpa.dao.wggl", "com.liuyf.demo.jpa.entity.wggl"}
        //,bootstrapMode = BootstrapMode.DEFERRED
)
public class JPAConfigWggl {

    public final static String[] wggl_packages = {"com.liuyf.demo.jpa.dao.wggl", "com.liuyf.demo.jpa.entity.wggl"};


    @Bean("EntityManagerFactoryWggl")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("wggl") DataSource dataSource) {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(wggl_packages);
        factoryBean.setPersistenceUnitName("wggl");
        return factoryBean;
    }


    @Bean("TransactionManagerWggl")
    public PlatformTransactionManager transactionManager(
            @Qualifier("EntityManagerFactoryWggl") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }


}
