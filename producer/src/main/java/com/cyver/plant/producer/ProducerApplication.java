package com.cyver.plant.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cyver.plant.producer.configuration.PlantProperties;

@SpringBootApplication(scanBasePackages = { "com.cyver.plant.producer", "com.cyver.plant.utilities", "com.cyver.plant.databaseh2" })
@EnableScheduling
@EnableConfigurationProperties(PlantProperties.class)
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
