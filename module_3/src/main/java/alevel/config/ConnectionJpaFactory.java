package alevel.config;

import alevel.util.ConnectionJpaProp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@AllArgsConstructor
public class ConnectionJpaFactory {

    private ConnectionJpaProp connectionJpaProp;


    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("alevel.model.impl");
        sessionFactoryBean.setHibernateProperties(propertiesHibernate());
        try{
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }


    private Properties propertiesHibernate() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", connectionJpaProp.getHbm2ddl());
        properties.setProperty("hibernate.dialect", connectionJpaProp.getDialect());
        properties.setProperty("hibernate.jdbc.max_size", connectionJpaProp.getMaxSize());
        properties.setProperty("hibernate.jdbc.min_size", connectionJpaProp.getMinSize());
        properties.setProperty("hibernate.jdbc.batch_size", connectionJpaProp.getBatchSize());
        properties.setProperty("hibernate.jdbc.fetch_size", connectionJpaProp.getFetchSize());

        return properties;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName(connectionJpaProp.getNameDriverClass());
        driverManager.setUsername(connectionJpaProp.getUsername());
        driverManager.setPassword(connectionJpaProp.getPassword());
        driverManager.setUrl(connectionJpaProp.getUrl());
        return driverManager;
    }
}
