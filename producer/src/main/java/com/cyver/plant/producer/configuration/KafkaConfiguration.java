package com.cyver.plant.producer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic cyverplantAvroTopic(final PlantProperties plantProperties) {
        return TopicBuilder.name(plantProperties.getTopic()).partitions(6).build();
    }

}
