package com.zmh.dbconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:51 / 2017/12/23</p>
 * <p>modified by   </p>
 */
@Configuration
public class PrimaryDataSourceConfig extends DataSourceAutoConfiguration {
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader()).type(HikariDataSource.class)
                .driverClassName(properties.determineDriverClassName())
                .url(properties.determineUrl()).username(properties.determineUsername()).password(properties.determinePassword()).build();
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}

