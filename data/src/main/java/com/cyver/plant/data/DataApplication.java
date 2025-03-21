/*
 * This source file was generated by the Gradle 'init' task
 */
package com.cyver.plant.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.cyver.plant.data", "com.cyver.plant.utilities" })
@EntityScan(basePackages = "com.cyver.plant.commons.entities")
@EnableJpaRepositories(basePackages = "com.cyver.plant.database")
public class DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

}
