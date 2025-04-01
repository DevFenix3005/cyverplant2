package com.cyver.plant.producer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cyver.plant.database.domain.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.database.domain.tables.repositories.ConfigurationOfNodeRepository;
import com.cyver.plant.producer.service.NodeConfigurationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor_ = { @Autowired })
public class NodeConfigurationServiceImpl implements NodeConfigurationService {

    private final ConfigurationOfNodeRepository nodeRepository;

    @Override
    public List<ConfigurationOfNode> getAllNodes() {
        return nodeRepository.findAll();
    }

    @Override
    public ConfigurationOfNode createNode(final ConfigurationOfNode node) {
        ConfigurationOfNode newConfiguration = new ConfigurationOfNode(node);
        nodeRepository.insert(newConfiguration);
        return newConfiguration;
    }

    @Override
    public void deleteNode(final ConfigurationOfNode node) {
        nodeRepository.delete(node);
    }
}
