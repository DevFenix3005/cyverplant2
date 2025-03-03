package com.cyver.plant.producer.service;

import com.cyver.plant.commons.node.NodeMeasurementResponse;

public interface PlantMeasurementProducerService {

    void sendMessage(NodeMeasurementResponse nodeMeasurementResponse, String ownerId, String plantName, String plantType);
}
