package com.cyver.plant.producer.dto;

import java.util.List;

import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;

import lombok.Getter;

@Getter
public class ConfigurationOfNodesWrapper {

    private final List<ConfigurationOfNode> configurationOfNodes;

    public ConfigurationOfNodesWrapper(final List<ConfigurationOfNode> configurationOfNodes) {
        this.configurationOfNodes = configurationOfNodes;
    }

}
