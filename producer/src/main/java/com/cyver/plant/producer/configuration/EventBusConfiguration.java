package com.cyver.plant.producer.configuration;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfiguration {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

}
