package com.cyver.plant.producer.service;

import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.producer.configuration.PlantProperties;

public interface NodeCommunicationService {

    NodeMeasurementResponse getMeasurement(PlantProperties.NodeConfiguration nodeConfiguration);

}
