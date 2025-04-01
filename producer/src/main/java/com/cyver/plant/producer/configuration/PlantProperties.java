package com.cyver.plant.producer.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "plant")
public class PlantProperties {

    private String user;

    private String topic;

    private String interval;

    private String statusInterval;

    private Hub hub;

    @Data
    public static final class Hub {
        private Location location;

        private String ipifyUrl;
    }

    @Data
    public static final class Location {
        private String cityName;

        private Double latitude;

        private Double longitude;
    }

}
