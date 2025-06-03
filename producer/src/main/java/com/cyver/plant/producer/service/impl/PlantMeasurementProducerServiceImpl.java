package com.cyver.plant.producer.service.impl;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.PlantMeasurementProducerService;
import com.cyver.plant.utilities.map.MapUtilComponent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class PlantMeasurementProducerServiceImpl implements PlantMeasurementProducerService {

    private final KafkaTemplate<String, EnvironmentalMeasurementAvro> kafkaTemplate;

    private final MapUtilComponent mapUtilComponent;

    private final PlantProperties plantProperties;

    @Override
    public void sendMessage(final NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName, String plantType) {

        final PlantProperties.Location location = plantProperties.getHub().getLocation();
        double latitude = Objects.requireNonNullElse(location.getLatitude(), 0.0);
        double longitude = Objects.requireNonNullElse(location.getLongitude(), 0.0);

        final String topic = plantProperties.getTopic();
        final String key = UUID.randomUUID().toString();
        final EnvironmentalMeasurementAvro environmentalMeasurementAvro =
                mapUtilComponent.toAvroMessage(nodeMeasurementResponse, ownerId, plantName, plantType, longitude,
                        latitude);

        CompletableFuture<SendResult<String, EnvironmentalMeasurementAvro>> future =
                kafkaTemplate.send(topic, key, environmentalMeasurementAvro);
        future.whenComplete((stringStringSendResult, throwable) -> {
            if (throwable == null) {
                log.info("Produced event to topic {}: key = {} value = {}", topic, key, environmentalMeasurementAvro);
            } else {
                log.error("Error sending message: {}", String.valueOf(throwable));
            }
        });
    }
}
