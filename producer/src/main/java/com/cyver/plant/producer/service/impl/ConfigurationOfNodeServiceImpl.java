package com.cyver.plant.producer.service.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyver.plant.database.producer.Tables;
import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.database.producer.tables.repositories.ConfigurationOfNodeRepository;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ConfigurationOfNodeServiceImpl implements ConfigurationOfNodeService {

    private final ConfigurationOfNodeRepository nodeRepository;

    private final DSLContext dslContext;

    @Override
    public List<ConfigurationOfNode> getAllNodes() {
        return nodeRepository.findAll();
    }

    @Override
    public List<ConfigurationOfNode> getAllAblesNodes() {
        return nodeRepository.fetchByNodeAble(true);
    }

    @Override
    public ConfigurationOfNode createNode(final ConfigurationOfNode node) {
        final ConfigurationOfNode newConfiguration = new ConfigurationOfNode(node);
        nodeRepository.insert(newConfiguration);
        return newConfiguration;
    }

    @Override
    public ConfigurationOfNode updateNode(final ConfigurationOfNode node) {
        nodeRepository.merge(node);
        return node;
    }

    @Override
    public void deleteNode(final ConfigurationOfNode node) {
        nodeRepository.delete(node);
    }

    @Override
    public List<ConfigurationOfNode> fetchByName(final String filterText) {
        return dslContext.selectFrom(Tables.CONFIGURATION_OF_NODES)
                .where(
                        Tables.CONFIGURATION_OF_NODES.NODE_NAME.likeIgnoreCase("%" + filterText + "%")
                                .or(Tables.CONFIGURATION_OF_NODES.NODE_TYPE.likeIgnoreCase("%" + filterText + "%"))
                                .or(Tables.CONFIGURATION_OF_NODES.NODE_URL.likeIgnoreCase("%" + filterText + "%"))
                ).fetch(nodeRepository.mapper());
    }

    @Override
    public ConfigurationOfNode fetchById(final Long nodeId) {
        return nodeRepository.fetchOneByNodeId(nodeId);
    }
}
