package com.cyver.plant.producer.service;

import com.cyver.plant.commons.node.NodeMeasurement;
import com.cyver.plant.producer.configuration.PlantProperties;

public interface NodeCommunicationService {

    NodeMeasurement getMeasurement(PlantProperties.NodeConfiguration nodeConfiguration);

}
