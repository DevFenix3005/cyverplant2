package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class PlantDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6237352833486438179L;

    private final String name;

    private final String type;

    private final double longitude;

    private final double latitude;

}
