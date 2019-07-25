package com.monese.test.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put("hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

        return hibernateProperties;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: application.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory() throws IOException {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.package.name"));

        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.afterPropertiesSet();

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws IOException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());

        return transactionManager;
    }

}