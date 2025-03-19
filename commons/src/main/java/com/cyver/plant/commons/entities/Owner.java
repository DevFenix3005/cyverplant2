package com.cyver.plant.commons.entities;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "owner", indexes = @Index(name = "owner_auth0_sid_idx", columnList = "auth0_sid", unique = true))
@AttributeOverride(name = "uuid", column = @Column(name = "owner_id"))
public class Owner extends Audit {

    @NonNull
    @Column(nullable = false, updatable = false)
    private String name;

    @NonNull
    @Column(nullable = false, updatable = false)
    private String email;

    @NonNull
    @Column(nullable = false, updatable = false)
    private String picture;

    @NonNull
    @Column(nullable = false, updatable = false)
    private String nickname;

    @Column(name = "auth0_sid", nullable = false, updatable = false)
    private String auth0Sid;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Plant> plants;

}
