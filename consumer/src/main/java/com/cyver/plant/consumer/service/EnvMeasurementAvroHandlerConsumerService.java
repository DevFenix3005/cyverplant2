package com.cyver.plant.consumer.service;

import com.cyver.plant.commons.avro.EnvironmentalMeasurementAvro;

public interface EnvMeasurementAvroHandlerConsumerService {

    void handler(final EnvironmentalMeasurementAvro value, final String key, final String topic);

}
