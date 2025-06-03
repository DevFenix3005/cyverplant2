package com.cyver.plant.producer.service;

import java.util.List;

import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;

public interface ConfigurationOfNodeService {

    List<ConfigurationOfNode> getAllNodes();

    List<ConfigurationOfNode> getAllAblesNodes();

    ConfigurationOfNode createNode(ConfigurationOfNode node);

    ConfigurationOfNode updateNode(ConfigurationOfNode node);

    void deleteNode(ConfigurationOfNode node);

    List<ConfigurationOfNode> fetchByName(String filterText);

    ConfigurationOfNode fetchById(Long aLong);
}
