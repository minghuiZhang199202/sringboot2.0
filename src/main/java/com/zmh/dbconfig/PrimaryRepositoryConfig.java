package com.zmh.dbconfig;



import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.boot.autoconfigure.AutoConfigureAfter;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
        import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
        import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Import;
        import org.springframework.context.annotation.Primary;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.transaction.PlatformTransactionManager;

        import javax.persistence.EntityManager;
        import javax.sql.DataSource;
        import java.util.Map;

/**
 * <p>@author minghui_zhang </p>
 * <p>description   </p>
 * <p>date  created in  22:54 / 2017/12/23</p>
 * <p>modified by   </p>
 */

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.girl.persistence",
                })//设置dao（repo）所在位置
@Import({PrimaryDataSourceConfig.class})
@AutoConfigureAfter({PrimaryDataSourceConfig.class})
public class PrimaryRepositoryConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "entityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .packages("com.oqiji.ud.bean", "com.oqiji.jcommon.domain") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManager")
    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}


