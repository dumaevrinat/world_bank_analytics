package com.firstline.worldbankanalytics.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class JDBCTemplateConfig {
    private final Environment environment;

    @Autowired
    public JDBCTemplateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "MySQLDataSource")
    public DataSource dataSourceMySQL() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("mysql.driver-class-name")));
        dataSource.setUrl(environment.getProperty("mysql.url"));
        dataSource.setUsername(environment.getProperty("mysql.username"));
        dataSource.setPassword(environment.getProperty("mysql.password"));

        return dataSource;
    }

    @Bean(name = "MySQLJdbcTemplate")
    public JdbcTemplate mySqlJdbcTemplate(@Qualifier("MySQLDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
