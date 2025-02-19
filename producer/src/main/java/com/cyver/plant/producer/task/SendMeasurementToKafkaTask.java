package com.cyver.plant.producer.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Scheduled(cron = "${plant.cron}")
    public void sendHobbitQuote() {
        log.info("Sending plant measurement");
        plantProperties.getNodes().stream()
                .map(nodeCommunicationService::getMeasurement)
                .forEach(plantMeasurementProducerService::sendMessage);
    }

}
