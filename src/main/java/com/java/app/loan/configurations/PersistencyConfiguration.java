package com.java.app.loan.configurations;

import com.java.app.loan.domains.user.repositories.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.Executors;

@Configuration
@EnableJpaRepositories(basePackages = "com.java.app.loan.domains")
public class PersistencyConfiguration {

    @Value("${spring.datasource.maximum-pool-size:100}")
    private int connectionPoolSize;

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) {
        HikariConfig datasourceConfig = new HikariConfig();
        datasourceConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.className"));
        datasourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        datasourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        datasourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));
        datasourceConfig.setPoolName(env.getRequiredProperty("spring.datasource.poolName"));

        return new HikariDataSource(datasourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.java.app.loan.entities");

        Properties jpaProperties = new Properties();
        jpaProperties.put("spring.jpa.properties.hibernate.dialect", env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("spring.jpa.hibernate.ddl-auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("spring.jpa.properties.hibernate.show_sql", env.getRequiredProperty("spring.jpa.properties.hibernate.show_sql"));
        jpaProperties.put("spring.jpa.properties.hibernate.format_sql", env.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));
        jpaProperties.put("spring.jpa.generate-ddl", env.getRequiredProperty("spring.jpa.generate-ddl"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
