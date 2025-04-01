package com.cyver.plant.producer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyver.plant.database.domain.tables.dtos.ConfigurationOfNode;

@Service
public interface NodeConfigurationService {

    List<ConfigurationOfNode> getAllNodes();

    ConfigurationOfNode createNode(ConfigurationOfNode node);

    void deleteNode(ConfigurationOfNode node);

}
