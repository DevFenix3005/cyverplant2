package com.cyver.plant.producer.task;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyver.plant.commons.exceptions.NodeResponseFailedException;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.NodeCommunicationService;
import com.cyver.plant.producer.service.PlantMeasurementProducerService;

@Component
public class SendMeasurementToKafkaTask {

    private static final Logger log = LoggerFactory.getLogger(SendMeasurementToKafkaTask.class);

    private final PlantProperties plantProperties;

    private final PlantMeasurementProducerService plantMeasurementProducerService;

    private final NodeCommunicationService nodeCommunicationService;

    @Autowired
    public SendMeasurementToKafkaTask(final PlantProperties plantProperties,
            final PlantMeasurementProducerService plantMeasurementProducerService,
            final NodeCommunicationService nodeCommunicationService) {
        this.plantProperties = plantProperties;
        this.plantMeasurementProducerService = plantMeasurementProducerService;
        this.nodeCommunicationService = nodeCommunicationService;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRateString="${plant.interval}")
    public void sendEnvironmentalMeasurementToTopic() {
        log.info("Sending plant measurement");
        for (final PlantProperties.NodeConfiguration node : plantProperties.getNodes()) {
            log.info("Sending plant measurement for node: {}", node);
            try {
                plantMeasurementProducerService.sendMessage(nodeCommunicationService.getMeasurement(node), plantProperties.getUser(),
                        node.getName(), node.getType());
            } catch (NodeResponseFailedException nodeResponseFailedException) {
                log.error("Failed to get measurement from node: {}", node);
                log.error("Trace", nodeResponseFailedException);
            }
        }
    }

}
