package com.cyver.plant.producer.service.impl;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.cyver.plant.commons.node.NodeMeasurement;
import com.cyver.plant.producer.configuration.PlantProperties;
import com.cyver.plant.producer.service.NodeCommunicationService;

@Service
public class NodeCommunicationServiceImpl implements NodeCommunicationService {

    private final RestTemplate restTemplate;

    public NodeCommunicationServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NodeMeasurement getMeasurement(PlantProperties.NodeConfiguration nodeConfiguration) {
        URI uri = UriComponentsBuilder.fromUriString(nodeConfiguration.getUrl())
                .path("/measurement")
                .build()
                .toUri();

        ResponseEntity<NodeMeasurement> response = restTemplate.getForEntity(uri, NodeMeasurement.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error while getting measurement from node: " + nodeConfiguration.getName());
        }
    }
}
