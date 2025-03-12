package com.cyver.plant.commons.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -656833377309058179L;

    @NotNull
    private final String name;

    @NotNull
    private final String email;

    @NotNull
    private final String picture;

    @NotNull
    private final String nickname;
}
