package com.zcj.demo.core.shiro;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;


/**
 * @Auther: 10062376
 * @Date: 2018/12/19 09:31
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="shiroEntityManagerFactory",
        transactionManagerRef="shiroTransactionManager",
        basePackages= { "com.zcj.demo.dao" })
public class ShiroDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("shiroDataSource")
    private DataSource shiroDataSource;

    @Bean(name = "shiroEntityManager")
    public EntityManager shiroEntityManager(EntityManagerFactoryBuilder builder) {
        return shiroEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "shiroEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shiroEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(shiroDataSource)
                .properties(getVendorProperties(shiroDataSource))
                .packages("com.xxx.shiro.entity")
                .persistenceUnit("shiroPersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties((HibernateSettings) dataSource);
    }

    @Bean(name = "shiroTransactionManager")
    PlatformTransactionManager shiroTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(shiroEntityManagerFactory(builder).getObject());
    }
    /**
     * shiro数据源
     * @return
     */
    @Bean(name = "shiroDataSource")
    @Qualifier("shiroDataSource")
    @ConfigurationProperties(prefix="spring.datasource.shiro")
    public DataSource shiroDataSource() {
        return DataSourceBuilder.create().build();
    }
}

