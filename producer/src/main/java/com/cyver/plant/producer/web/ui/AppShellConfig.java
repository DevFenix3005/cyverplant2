package com.cyver.plant.producer.web.ui;

import java.io.Serial;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;

@Push
@PageTitle("Plant Producer Monitor")
@PWA(name = "Plant Producer Monitor", shortName = "plant-producer-monitor", offlineResources = {"images/logo.png"})
public class AppShellConfig implements AppShellConfigurator {
    @Serial
    private static final long serialVersionUID = -8593694798915043585L;

}
