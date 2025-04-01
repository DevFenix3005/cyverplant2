package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.io.Serializable;

public record NodeConfiguration(Long id, String name, String type, String url) implements Serializable {
    @Serial private static final long serialVersionUID = 8702253754397848112L;
}
