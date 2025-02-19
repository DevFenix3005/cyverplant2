package com.cyver.plant.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;

@Service
public class EnvironmentalMeasurementAvroConsumerService {

    private final Logger logger = LoggerFactory.getLogger(EnvironmentalMeasurementAvroConsumerService.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload final EnvironmentalMeasurementAvro value,
            @Header(KafkaHeaders.RECEIVED_KEY) final String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic) {
        logger.info("Consumed event from topic {}: key = {} | value = {}", topic, key, value);
    }
}
