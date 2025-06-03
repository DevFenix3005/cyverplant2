package com.cyver.plant.producer.web.ui;

import java.io.Serial;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@Theme(value = "plant-producer-monitor")
@Push
@PageTitle("Plant Producer Monitor")
@PWA(name = "Plant Producer Monitor", shortName = "plant-producer-monitor", manifestPath = "site.webmanifest")
public class AppShellConfig implements AppShellConfigurator {

    @Serial
    private static final long serialVersionUID = -8593694798915043585L;

    @Override
    public void configurePage(final AppShellSettings settings) {
        AppShellConfigurator.super.configurePage(settings);
        settings.addFavIcon(Inline.Position.PREPEND, "icon", "themes/plant-producer-monitor/images/logo.png", "1024x1024");
        settings.addFavIcon(Inline.Position.PREPEND, "icon", "themes/plant-producer-monitor/images/favicon.ico", "48x48");
    }
}
