package com.cyver.plant.producer.service;

import com.cyver.plant.commons.node.NodeMeasurement;

public interface PlantMeasurementProducerService {

    void sendMessage( NodeMeasurement nodeMeasurement);
}
