package com.cyver.plant.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.cyver.plant.consumer", "com.cyver.plant.utilities" })
@EntityScan(basePackages = "com.cyver.plant.commons.entities")
@EnableJpaRepositories(basePackages = "com.cyver.plant.database")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConsumerApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

}
