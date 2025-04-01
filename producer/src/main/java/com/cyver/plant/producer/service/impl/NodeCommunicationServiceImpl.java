package com.cyver.plant.producer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.commons.exceptions.NodeResponseFailedException;
import com.cyver.plant.commons.node.NodeMeasurementRequest;
import com.cyver.plant.commons.node.NodeMeasurementResponse;
import com.cyver.plant.commons.shared.TemperatureUnit;
import com.cyver.plant.databaseh2.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.service.NodeCommunicationService;

import elemental.json.Json;
import elemental.json.JsonObject;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.UnirestException;
import kong.unirest.core.UnirestInstance;
import kong.unirest.core.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NodeCommunicationServiceImpl implements NodeCommunicationService {

    private final UnirestInstance unirest;

    @Override
    public NodeMeasurementResponse getMeasurement(final ConfigurationOfNode configurationOfNode) throws NodeResponseFailedException {
        try {
            final NodeMeasurementRequest request = new NodeMeasurementRequest(TemperatureUnit.CELSIUS);
            final HttpResponse<NodeMeasurementResponse> response = unirest.post(String.format("%s/measurement", configurationOfNode.getNodeUrl()))
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .body(request)
                    .asObject(NodeMeasurementResponse.class);
            if (response.isSuccess()) {
                return response.getBody();
            } else {
                throw new UnirestException(String.format("Failed to get measurement from node: %s", configurationOfNode));
            }
        } catch (final UnirestException unirestException) {
            throw new NodeResponseFailedException(String.format("Failed to get measurement from node: %s", configurationOfNode));
        }

    }

    @Override
    public boolean isNodeAlive(final ConfigurationOfNode configurationOfNode) {
        try {
            final HttpResponse<JsonNode> response = unirest.get(configurationOfNode.getNodeUrl()).asJson();
            final boolean isAlive = response.isSuccess();
            if (isAlive) {
                final JSONObject jsonObject = response.getBody().getObject();
                final String message = jsonObject.getString("message");
                log.info("Node is alive: {}", message);
            }
            return isAlive;
        } catch (final UnirestException unirestException) {
            log.error("Failed to check if node is alive: {}", configurationOfNode);
            log.error("Error: ", unirestException);
            return false;
        }
    }


}
