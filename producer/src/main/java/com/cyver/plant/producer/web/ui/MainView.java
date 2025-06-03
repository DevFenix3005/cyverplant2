package com.cyver.plant.producer.web.ui;

import java.io.Serial;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cyver.plant.database.producer.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.dto.ConfigurationOfNodesWrapper;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.lumo.Lumo;

import jakarta.annotation.security.PermitAll;

@Route
@UIScope
@Component
@PermitAll
public class MainView extends AppLayout implements BeforeEnterObserver {

    @Serial
    private static final long serialVersionUID = 7258882824487037065L;

    private final transient ConfigurationOfNodeService configurationOfNodeService;

    private final transient ConfigurationNodeEditor configurationNodeEditor;

    private final transient EventBus eventBus;

    private Grid<ConfigurationOfNode> grid;

    private TextField filter;

    private Button addNewBtn;

    private Button toggleButton;

    public MainView(final ConfigurationOfNodeService configurationOfNodeService, final ConfigurationNodeEditor configurationNodeEditor,
            final EventBus eventBus) {
        this.configurationOfNodeService = configurationOfNodeService;
        this.configurationNodeEditor = configurationNodeEditor;
        this.eventBus = eventBus;
    }

    @Override
    public void beforeEnter(final BeforeEnterEvent event) {
        createView();
        createEvents();
        // Initialize listing
        listCustomers(null);
        eventBus.register(this);
        UI.getCurrent().setPollInterval(1000);
    }

    private void createView() {

        grid = new Grid<>(ConfigurationOfNode.class);
        grid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("nodeId", "nodeName", "nodeType", "nodeUrl");
        grid.addColumn(new ComponentRenderer<>(node -> {
                    final boolean ableOrDisable = Objects.requireNonNullElse(node.getNodeAble(), false);
                    return ableOrDisable ? VaadinIcon.CHECK.create() : VaadinIcon.CLOSE.create();
                }))
                .setHeader("Status del nodo")
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.getColumnByKey("nodeId").setWidth("100px").setFlexGrow(0);

        filter = new TextField();
        filter.setPlaceholder("Filtrar por nombre");

        addNewBtn = new Button("Agregar Nodo", VaadinIcon.PLUS.create());
        addNewBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        toggleButton = new Button("Light", VaadinIcon.SUN_O.create());
        toggleButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        // build layout
        final HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, toggleButton);

        Image logo = new Image("themes/plant-producer-monitor/images/logo.png", "Cyberplanta logo");
        logo.setHeight("75px");

        HorizontalLayout header = new HorizontalLayout(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassName("py-0");
        header.addClassName("px-m");

        addToNavbar(header);
        setContent(new VerticalLayout(actions, grid, configurationNodeEditor));
    }

    private void createEvents() {
        // Hook logic to components
        // Replaces listing with filtered content when the user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.setValueChangeTimeout(800);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> configurationNodeEditor.editCustomer(e.getValue()));

        // Instantiate and edit new Node the new button is clicked
        addNewBtn.addClickListener(e -> configurationNodeEditor.editCustomer(new ConfigurationOfNode(null, "", "", "", false)));
        toggleButton.addClickListener(this::toggleTheme);

        // Listen to changes made by the editor, refresh data from the backend
        configurationNodeEditor.setChangeHandler(() -> {
            configurationNodeEditor.setVisible(false);
            listCustomers(filter.getValue());
        });
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

    void toggleTheme(ClickEvent<Button> click) {
        ThemeList themeList = UI.getCurrent().getElement().getThemeList();
        if (themeList.contains(Lumo.DARK)) {
            click.getSource().setText("Light");
            click.getSource().setIcon(VaadinIcon.SUN_O.create());
            themeList.remove(Lumo.DARK);
        } else {
            click.getSource().setText("Dark");
            click.getSource().setIcon(VaadinIcon.MOON_O.create());
            themeList.add(Lumo.DARK);
        }
    }

}
