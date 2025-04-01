package com.cyver.plant.producer.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    public SpringLiquibase liquibase(final DataSource dataSource) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog/main.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}
