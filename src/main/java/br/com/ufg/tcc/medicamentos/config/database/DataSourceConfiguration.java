package br.com.ufg.tcc.medicamentos.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig dataSourceConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(dataSourceConfig());
    }

    @Bean(name = "dataSource")
    @DependsOn(value = {"dataSource"})
    public DataSource dataSource(final DataSource dataSource) {
        return dataSource();
    }

    @Bean("namedJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dataSource") final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("transactionTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    TransactionTemplate transactionTemplate(
            @Qualifier("transactionManager") final PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
    
    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("wrappedTransactionManager") final PlatformTransactionManager wrapped) {
        return new CustomTransactionManager(
                wrapped);
    }

    @Bean(name = "wrappedTransactionManager")
    public PlatformTransactionManager wrappedTransactionManager(@Qualifier("dataSource") final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
