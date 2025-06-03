package com.cyver.plant.producer.task;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.dto.ConfigurationOfNodesWrapper;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;
import com.cyver.plant.producer.service.NodeCommunicationService;
import com.google.common.eventbus.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateNodeStatusTask {

    private final NodeCommunicationService nodeCommunicationService;
    private final ConfigurationOfNodeService configurationOfNodeService;
    private final EventBus eventBus;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRateString = "${plant.status-interval}", initialDelay = -1)
    public void updateNodeStatus() {
        final List<ConfigurationOfNode> configurations = configurationOfNodeService.getAllNodes().stream().map(configurationOfNode -> {
            final boolean isNodeAlive = nodeCommunicationService.isNodeAlive(configurationOfNode);
            configurationOfNode.setNodeAble(isNodeAlive);
            return configurationOfNodeService.updateNode(configurationOfNode);
        }).toList();
        eventBus.post(new ConfigurationOfNodesWrapper(configurations));
    }

}
