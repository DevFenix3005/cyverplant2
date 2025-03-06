package com.cyver.plant.producer.service.impl;

import org.springframework.stereotype.Service;

import com.cyver.plant.commons.exceptions.NodeResponseFailedException;
import com.cyver.plant.commons.node.NodeMeasurementRequest;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.commons.shared.TemperatureUnit;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.NodeCommunicationService;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.UnirestInstance;

@Service
public class NodeCommunicationServiceImpl implements NodeCommunicationService {

    private final UnirestInstance unirest;

    public NodeCommunicationServiceImpl(final UnirestInstance unirest) {
        this.unirest = unirest;
    }

    @Override
    public NodeMeasurementResponse getMeasurement(PlantProperties.NodeConfiguration nodeConfiguration) {
        NodeMeasurementRequest request = new NodeMeasurementRequest(TemperatureUnit.CELSIUS);

        HttpResponse<NodeMeasurementResponse> response = unirest.post(nodeConfiguration.getUrl())
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .body(request)
                .asObject(NodeMeasurementResponse.class);
        if (response.isSuccess()) {
            return response.getBody();
        } else {
            throw new NodeResponseFailedException(String.format("Failed to get measurement from node: %s", nodeConfiguration));
        }

    }
}
