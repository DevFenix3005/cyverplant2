package com.cyver.plant.producer.service;

import com.cyver.plant.commons.exceptions.NodeResponseFailedException;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.databaseh2.tables.dtos.ConfigurationOfNode;

public interface NodeCommunicationService {

    NodeMeasurementResponse getMeasurement(ConfigurationOfNode configurationOfNode) throws NodeResponseFailedException;

    boolean isNodeAlive(ConfigurationOfNode configurationOfNode) throws NodeResponseFailedException;

}
