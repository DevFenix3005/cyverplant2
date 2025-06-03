package com.cyver.plant.consumer.configuration;

import java.util.Set;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration extends ConnectionPoolFactory {

    private static final String INSTANCE_CONNECTION_NAME =
            System.getenv("INSTANCE_CONNECTION_NAME");

    private final Environment environment;

    public DataSourceConfiguration(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {

        Set<String> activeProfiles = Set.of(environment.getActiveProfiles());
        // The configuration object specifies behaviors for the connection pool.
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(environment.getProperty("spring.datasource.url", String.class));
        config.setUsername(environment.getProperty("spring.datasource.username", String.class)); // e.g. "root", _postgres"
        config.setPassword(environment.getProperty("spring.datasource.password", String.class)); // e.g. "my-password"
        config.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name", String.class));

        if (activeProfiles.contains("development")) {
            config.addDataSourceProperty("ssl", "true");
            config.addDataSourceProperty("sslmode", "verify-ca");
        }

        if (activeProfiles.contains("production") || activeProfiles.contains("cloud")) {

            // [START cloud_sql_postgres_servlet_connect_connector]
            // The following URL is equivalent to setting the config options below:
            // jdbc:postgresql:///<DB_NAME>?cloudSqlInstance=<INSTANCE_CONNECTION_NAME>&
            // socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=<DB_USER>&password=<DB_PASS>
            // See the link below for more info on building a JDBC URL for the Cloud SQL JDBC Socket Factory
            // https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory#creating-the-jdbc-url
            // Configure which instance and what database user to connect with.

            config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
            config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);

            // [END cloud_sql_postgres_servlet_connect_connector]

            // cloudSqlRefreshStrategy set to "lazy" is used to perform a
            // refresh when needed, rather than on a scheduled interval.
            // This is recommended for serverless environments to
            // avoid background refreshes from throttling CPU.
            config.addDataSourceProperty("cloudSqlRefreshStrategy", "lazy");
        }

        // ... Specify additional connection properties here.
        // [START_EXCLUDE]
        configureConnectionPool(config);
        // [END_EXCLUDE]

        // Initialize the connection pool using the configuration object.
        return new HikariDataSource(config);
    }

}
