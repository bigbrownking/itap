package com.example.new_project_challenge_15.configuration;

import com.example.new_project_challenge_15.modelsSer.UserSer;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.new_project_challenge_15.repositorySer",
        entityManagerFactoryRef = "SerEntityManagerFactory",
        transactionManagerRef = "SerTransactionManager")
public class SerDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.ser")
    public DataSourceProperties SerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ser.configuration")
    public DataSource SerDataSource() {
        return SerDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "SerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean SerEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(SerDataSource())
                .packages(UserSer.class)
                .build();
    }

    @Bean(name = "SerTransactionManager")
    public PlatformTransactionManager SerTransactionManager(
            @Qualifier("SerEntityManagerFactory") LocalContainerEntityManagerFactoryBean SerEntityManagerFactory) {
        return new JpaTransactionManager(SerEntityManagerFactory.getObject());
    }

}
