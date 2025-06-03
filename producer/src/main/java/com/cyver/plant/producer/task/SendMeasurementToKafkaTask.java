package com.cyver.plant.producer.task;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyver.plant.commons.exceptions.NodeResponseFailedException;
import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.NodeCommunicationService;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;
import com.cyver.plant.producer.service.PlantMeasurementProducerService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class SendMeasurementToKafkaTask {

    private static final Logger log = LoggerFactory.getLogger(SendMeasurementToKafkaTask.class);

    private final PlantProperties plantProperties;

    private final PlantMeasurementProducerService plantMeasurementProducerService;

    private final NodeCommunicationService nodeCommunicationService;

    private final ConfigurationOfNodeService configurationOfNodeService;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRateString = "${plant.interval}", initialDelay = 5)
    public void sendEnvironmentalMeasurementToTopic() {
        log.info("Sending plant measurement");
        configurationOfNodeService.getAllAblesNodes().forEach(node -> {
            log.info("Sending plant measurement for node: {}", node);
            try {
                plantMeasurementProducerService.sendMessage(nodeCommunicationService.getMeasurement(node), plantProperties.getUser(),
                        node.getNodeName(), node.getNodeType());
            } catch (final NodeResponseFailedException nodeResponseFailedException) {
                log.error("Failed to get measurement from node: {}", node);
                log.error("Trace", nodeResponseFailedException);
            }
        });

    }

}
