package com.cyver.plant.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;
import com.cyver.plant.consumer.service.EnvMeasurementAvroHandlerConsumerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnvironmentalMeasurementListener {

    private final EnvMeasurementAvroHandlerConsumerService envMeasurementAvroHandlerConsumerService;

    public EnvironmentalMeasurementListener(final EnvMeasurementAvroHandlerConsumerService envMeasurementAvroHandlerConsumerService) {
        this.envMeasurementAvroHandlerConsumerService = envMeasurementAvroHandlerConsumerService;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(@Payload final EnvironmentalMeasurementAvro value,
            @Header(KafkaHeaders.RECEIVED_KEY) final String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic) {
        envMeasurementAvroHandlerConsumerService.handler(value, key, topic);
    }

}
