package com.cyver.plant.commons.node;

import java.io.Serial;
import java.io.Serializable;

import com.cyver.plant.commons.shared.TemperatureUnit;

import lombok.Data;

@Data
public class NodeMeasurementRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4726369092916016280L;

    private final TemperatureUnit unit;

}
