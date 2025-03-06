package com.cyver.plant.producer.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlantMeasurementProducerServiceImpl implements PlantMeasurementProducerService {

    private final KafkaTemplate<String, EnvironmentalMeasurementAvro> kafkaTemplate;

    private final EnvironmentalMeasurementAvroMapper environmentalMeasurementAvroMapper;

    private final PlantProperties plantProperties;

    private final DatabaseReader maxmindDatabase;

    @Autowired
    public PlantMeasurementProducerServiceImpl(
            final KafkaTemplate<String, EnvironmentalMeasurementAvro> kafkaTemplate,
            final EnvironmentalMeasurementAvroMapper environmentalMeasurementAvroMapper,
            final PlantProperties plantProperties, final DatabaseReader maxmindDatabase) {
        this.kafkaTemplate = kafkaTemplate;
        this.environmentalMeasurementAvroMapper = environmentalMeasurementAvroMapper;
        this.plantProperties = plantProperties;
        this.maxmindDatabase = maxmindDatabase;
    }

    @Override
    public void sendMessage(final NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName, String plantType) {

        float latitude = 0.0f;
        float longitude = 0.0f;

        try {
            InetAddress inetAddress = InetAddress.getByName("187.190.28.208");
            CityResponse response = maxmindDatabase.city(inetAddress);
            Location location = response.getLocation();
            latitude = location.getLatitude().floatValue();
            longitude = location.getLongitude().floatValue();
        } catch (IOException | GeoIp2Exception e) {
            log.error("Error getting location:", e);
        }

        final String topic = plantProperties.getTopic();
        final String key = UUID.randomUUID().toString();
        final EnvironmentalMeasurementAvro environmentalMeasurementAvro =
                environmentalMeasurementAvroMapper.toAvroMessage(nodeMeasurementResponse, ownerId, plantName, plantType, longitude,
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
