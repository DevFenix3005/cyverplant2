package com.cyver.plant.producer.service.impl;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.PlantMeasurementProducerService;
import com.cyver.plant.utilities.maps.EnvironmentalMeasurementAvroMapper;

@Service
public class PlantMeasurementProducerServiceImpl implements PlantMeasurementProducerService {

    private static final Logger logger = LoggerFactory.getLogger(PlantMeasurementProducerServiceImpl.class);

    private final KafkaTemplate<String, EnvironmentalMeasurementAvro> kafkaTemplate;

    private final EnvironmentalMeasurementAvroMapper environmentalMeasurementAvroMapper;

    private final PlantProperties plantProperties;

    @Autowired
    public PlantMeasurementProducerServiceImpl(
            final KafkaTemplate<String, EnvironmentalMeasurementAvro> kafkaTemplate,
            final EnvironmentalMeasurementAvroMapper environmentalMeasurementAvroMapper,
            final PlantProperties plantProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.environmentalMeasurementAvroMapper = environmentalMeasurementAvroMapper;
        this.plantProperties = plantProperties;
    }

    @Override
    public void sendMessage(final NodeMeasurementResponse nodeMeasurementResponse) {

        final String topic = plantProperties.getTopic();
        final String key = UUID.randomUUID().toString();
        final EnvironmentalMeasurementAvro environmentalMeasurementAvro =
                environmentalMeasurementAvroMapper.toAvroMessage(nodeMeasurementResponse);

        CompletableFuture<SendResult<String, EnvironmentalMeasurementAvro>> future =
                kafkaTemplate.send(topic, key, environmentalMeasurementAvro);
        future.whenComplete((stringStringSendResult, throwable) -> {
            if (throwable == null) {
                logger.info("Produced event to topic {}: key = {} value = {}", topic, key, environmentalMeasurementAvro);
            } else {
                logger.error("Error sending message: {}", String.valueOf(throwable));
            }
        });
    }
}
