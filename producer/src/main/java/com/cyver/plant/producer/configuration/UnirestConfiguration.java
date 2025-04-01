package com.cyver.plant.producer.configuration;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestInstance;
import kong.unirest.jackson.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class UnirestConfiguration {

    @Bean
    public JacksonObjectMapper unirestJacksonObjectMapper(final ObjectMapper objectMapper) {
        return new JacksonObjectMapper(objectMapper);
    }

    @Bean
    public UnirestInstance unirest(final JacksonObjectMapper unirestJacksonObjectMapper) {
        final UnirestInstance unirest = Unirest.primaryInstance();
        unirest.config().setObjectMapper(unirestJacksonObjectMapper);
        unirest.config().connectTimeout(5000);
        unirest.config().instrumentWith(requestSummary -> {
            final long startNanos = System.nanoTime();
            return (responseSummary, exception) -> log.info("path: {} status: {} time: {}",
                    requestSummary.getRawPath(),
                    Objects.isNull(responseSummary) ? "N/A" : responseSummary.getStatus(),
                    System.nanoTime() - startNanos);
        });
        return unirest;
    }

}
