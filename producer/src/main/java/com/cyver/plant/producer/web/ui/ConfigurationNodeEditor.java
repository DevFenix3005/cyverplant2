package com.cyver.plant.producer.web.ui;

import java.io.Serial;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.cyver.plant.databaseh2.tables.dtos.ConfigurationOfNode;
import com.cyver.plant.producer.service.ConfigurationOfNodeService;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import lombok.Setter;

@SpringComponent
@UIScope
public class ConfigurationNodeEditor extends VerticalLayout implements KeyNotifier {

    @Serial
    private static final long serialVersionUID = -6120068602711180202L;

    private final transient ConfigurationOfNodeService configurationOfNodeService;

    /**
     * The currently edited customer
     */
    private ConfigurationOfNode configuration;

    /* Fields to edit properties in Customer entity */
    final TextField nodeType = new TextField("Type");

    final TextField nodeUrl = new TextField("URL");

    /* Action buttons */
    final Button save = new Button("Guardar", VaadinIcon.CHECK.create());

    final Button cancel = new Button("Cancelar");

    final Button delete = new Button("Borrar", VaadinIcon.TRASH.create());

    final HorizontalLayout actions = new HorizontalLayout(save, delete, cancel);

    final Binder<ConfigurationOfNode> binder = new Binder<>(ConfigurationOfNode.class);

    @Setter
    private transient ChangeHandler changeHandler;

    @Autowired
    public ConfigurationNodeEditor(final ConfigurationOfNodeService configurationOfNodeService) {
        this.configurationOfNodeService = configurationOfNodeService;

        add(nodeType, nodeUrl, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        addKeyPressListener(Key.ENTER, e -> saveNode());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> saveNode());
        delete.addClickListener(e -> deleteNode());
        cancel.addClickListener(e -> editCustomer(configuration));
        setVisible(false);
    }

    void deleteNode() {
        configurationOfNodeService.deleteNode(configuration);
        changeHandler.onChange();
    }

    void saveNode() {
        if (Objects.nonNull(configuration.getNodeId())) {
            configurationOfNodeService.updateNode(configuration);
        } else {
            configurationOfNodeService.createNode(configuration);
        }
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCustomer(final ConfigurationOfNode c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getNodeId() != null;
        if (persisted) {
            // Find fresh entity for editing
            // In a more complex app, you might want to load
            // the entity/DTO with lazy loaded relations for editing
            configuration = configurationOfNodeService.fetchById(c.getNodeId());
        } else {
            configuration = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(configuration);

        setVisible(true);

        // Focus first name initially
        nodeType.focus();
    }

}
