package com.cyver.plant.producer.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "plant")
public class PlantProperties {

    private String user;

    private String topic;

    private List<NodeConfiguration> nodes;

    @Data
    public static class NodeConfiguration {
        private String name;

        private String type;

        private String url;

        private float minSoilMoisture;

    }

}
