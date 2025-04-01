package com.cyver.plant.producer.web.ui;

import java.io.Serial;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cyver.plant.databaseh2.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.dto.ConfigurationOfNodesWrapper;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import jakarta.annotation.security.PermitAll;

@Route
@UIScope
@Component
@PermitAll
public class MainView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 7258882824487037065L;

    private final transient ConfigurationOfNodeService configurationOfNodeService;

    private final Grid<ConfigurationOfNode> grid;

    private final TextField filter;

    public MainView(final ConfigurationOfNodeService configurationOfNodeService, final ConfigurationNodeEditor configurationNodeEditor,
                    final EventBus eventBus) {
        this.configurationOfNodeService = configurationOfNodeService;
        grid = new Grid<>(ConfigurationOfNode.class);
        filter = new TextField();
        final Button addNewBtn = new Button("Agregar Nodo", VaadinIcon.PLUS.create());

        // build layout
        final HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, configurationNodeEditor);

        grid.setHeight("300px");
        grid.setColumns("nodeId", "nodeName", "nodeType", "nodeUrl");
        grid.addColumn(new ComponentRenderer<>(node -> {
                    final boolean ableOrDisable = Objects.requireNonNullElse(node.getNodeAble(), false);
                    return ableOrDisable ? VaadinIcon.CHECK.create() : VaadinIcon.CLOSE.create();
                }))
                .setHeader("Status del nodo")
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.getColumnByKey("nodeId").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filtrar por nombre");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.setValueChangeTimeout(800);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> configurationNodeEditor.editCustomer(e.getValue()));

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> configurationNodeEditor.editCustomer(new ConfigurationOfNode(null, "", "", "", false)));

        // Listen changes made by the editor, refresh data from backend
        configurationNodeEditor.setChangeHandler(() -> {
            configurationNodeEditor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
        eventBus.register(this);
        UI.getCurrent().setPollInterval(1000);
    }

    @Subscribe
    public void manejarEvento(final ConfigurationOfNodesWrapper configurationOfNodesWrapper) {
        getUI()
                .filter(UI::isAttached)
                .ifPresent(ui -> ui.access(() -> {
                    grid.setItems(configurationOfNodesWrapper.getConfigurationOfNodes());
                    final Notification notification = Notification.show("Nodos actualizados!");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }));
    }

    void listCustomers(final String filterText) {
        if (StringUtils.hasText(filterText)) {
            grid.setItems(configurationOfNodeService.fetchByName(filterText));
        } else {
            grid.setItems(configurationOfNodeService.getAllNodes());
        }
    }
}
