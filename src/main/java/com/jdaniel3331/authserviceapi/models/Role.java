package com.jdaniel3331.authserviceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "roles", schema = "auth")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false, precision = 1)
    private BigDecimal roleId;

    @Size(max = 32)
    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;


}