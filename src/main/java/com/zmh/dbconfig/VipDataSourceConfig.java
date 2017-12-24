package com.zmh.dbconfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:53 / 2017/12/23</p>
 * <p>modified by   </p>
 */
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class VipDataSourceConfig {

    @Bean(name = "dataSourceSec")
    @ConfigurationProperties(prefix="spring.datasource.secondary.hikari")
    public DataSource secondaryDataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader()).type(HikariDataSource.class)
                .driverClassName(properties.determineDriverClassName())
                .url(properties.determineUrl()).username(properties.determineUsername()).password(properties.determinePassword()).build();
    }


    @Bean(name = "jdbcTemplateSec")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("dataSourceSec") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
